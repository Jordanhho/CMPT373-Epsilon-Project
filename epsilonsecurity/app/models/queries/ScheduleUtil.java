package models.queries;

import models.databaseModel.helpers.*;
import models.databaseModel.qualification.DbQualification;
import models.databaseModel.scheduling.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Schedule Util class for table queries
 */
public final class ScheduleUtil {

    /**
     * Search for available users based on teamId, start and end time of shift
     * function searches for users with the team in question
     * and any availability matching the time range
     * intersect them to find qualified users in the right team and are available at that time,
     * and remove any user assigned with a shift in that time range
    */

    public static List<DbUser> queryUsersBasedOnAvailability(Integer teamId, Integer shiftTypeId, Long timeStart, Long timeEnd) {

        List<DbUserTeam> userTeamListByLocation = DbUserTeamHelper.readDbUserTeamByTeamId(teamId);

        List<DbUserTeam> userTeamListByAvailability = getDbUserTeamsByAvailability(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByShift = getDbUserTeamsByShift(timeStart, timeEnd);

        List<DbUser> dbUserList = new ArrayList<>();

        Set<DbUserTeam> filteredList = new LinkedHashSet<>(userTeamListByAvailability);

        //find intersection of userTeam list by location and time range
        filteredList.retainAll(new LinkedHashSet<>(userTeamListByLocation));

        //remove elements with in time-range unavailability and shift time
        filteredList.removeAll(userTeamListByShift);

        for (DbUserTeam userTeam : filteredList){
            dbUserList.add(DbUserHelper.readDbUserById(userTeam.getUserId()));
        }

        //intersect with user with matching qualification
        dbUserList.retainAll(getUserByQualification(shiftTypeId));

        return dbUserList;
    }


    /**
     * find all availability in right time range and matching userTeam
     */
    private static List<DbUserTeam> getDbUserTeamsByAvailability(Long timeStart, Long timeEnd) {

        List<DbOneTimeAvailability> oneTimeAvailabilityList = DbOneTimeAvailabilityHelper
                .readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByAvailability = new ArrayList<>();

        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            userTeamListByAvailability.add(DbUserTeam.find.byId(oneTimeAvailability.getUserTeamId()));
        }
        return userTeamListByAvailability;
    }

    /**
     * find all shift in range and matching userTeam
     */
    private static List<DbUserTeam> getDbUserTeamsByShift(Long timeStart, Long timeEnd) {
        List<DbShift> shifts = DbShiftHelper.readDbShiftByTime(timeStart, timeEnd);

        List<DbUserShift> userShiftList = new ArrayList<>();

        for(DbShift shift : shifts){
            userShiftList.add(DbUserShift.find
                    .query()
                    .where()
                    .eq(DbUserShift.COLUMN_SHIFT_ID, shift.getId())
                    .findUnique());
        }

        List<DbUserTeam> userTeamListByShift = new ArrayList<>();

        for(DbUserShift userShift : userShiftList){
            userTeamListByShift.add(DbUserTeam.find.byId(userShift.getUserTeamId()));
        }
        return userTeamListByShift;
    }

    /**
     * get list of user qualified for the job
     */
    public static List<DbUser> getUserByQualification(Integer shiftTypeId) {
        List<DbQualification> qualificationList = DbShiftQualificationHelper.readDbQualificationByShiftTypeId(shiftTypeId);
        Set<DbUser> qualifiedUserList = new LinkedHashSet<>(new ArrayList<DbUser>());

        for (DbQualification qualification : qualificationList){
            qualifiedUserList.retainAll(DbUserQualificationHelper.readDbUserByQualificationId(qualification.getId()));
        }
        return new ArrayList<>(qualifiedUserList);
    }

    /**
     * find all unavailability in range and matching userTeam
     */
    private static List<DbUserTeam> getDbUserTeamsByUnavailability(Integer teamId, Long timeStart, Long timeEnd) {
        List<DbOneTimeUnavailability> oneTimeUnavailabilityList = DbOneTimeUnavailabilityHelper
                .readDbOneTimeUnavailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByUnavailability = new ArrayList<>();

        for(DbOneTimeUnavailability oneTimeUnavailability : oneTimeUnavailabilityList){
            if(oneTimeUnavailability.getUserTeamId().equals(teamId)){
                userTeamListByUnavailability.add(DbUserTeam.find.byId(oneTimeUnavailability.getUserTeamId()));
            }
        }
        return userTeamListByUnavailability;
    }


    /**
     * get all users from a team/Campus
     * @param teamId
     * @return
     */
    public static List<DbUser> getAllUsersFromCampusTeam(Integer teamId) {
        //find all users in team/campus
        List<DbUserTeam> userTeamListByLocation = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_TEAM_ID, teamId)
                .findList();

        List<DbUser> dbUserList = new ArrayList<>();

        for (DbUserTeam userTeam: userTeamListByLocation){
            dbUserList.add(DbUserHelper.readDbUserById(userTeam.getUserId()));
        }

        return dbUserList;
    }


    /**
     * Get a list of all shifts (including campus) assigned to a user
     * @param userId the database ID of the target user
     * @return A list containing all the shift data required for the frontend
     */
    public static List<ShiftWithCampus> getShiftsWithCampusByUserId(Integer userId) {
        DbUser targetUser = DbUserHelper.readDbUserById(userId);
        List<DbUserTeam> dbUserTeamList = DbUserTeamHelper.readAllDbUserTeamsByUserId(targetUser.getId());

        List<List<DbUserShift>> dbUserShiftList = new ArrayList<>();
        for (DbUserTeam dbUserTeam : dbUserTeamList) {
            dbUserShiftList.add(DbUserShiftHelper.readDbUserShiftByUserTeamId(dbUserTeam.getId()));
        }

        List<ShiftWithCampus> shiftsWithCampusList = new ArrayList<>();
        int i = 0;
        for (List<DbUserShift> targetUserShiftList : dbUserShiftList) {
            for (DbUserShift dbUserShift : targetUserShiftList) {
                DbShift dbShift = DbShiftHelper.readDbShiftById(dbUserShift.getShiftId());
                DbTeam dbTeam = DbTeamHelper.readDbTeamById(dbUserTeamList.get(i).getTeamId());
                DbShiftType dbShiftType = DbShiftTypeHelper.readDbShiftTypeById(dbShift.getShiftTypeId());
                ShiftWithCampus targetShift = new ShiftWithCampus(dbShift, dbTeam, dbShiftType);
                shiftsWithCampusList.add(targetShift);
            }
            i++;
        }

        return shiftsWithCampusList;
    }

    public static float getTotalHourWorkingByUserID(int userId){
        float hoursWorking = 0;
        List<DbShift> shiftList = DbShiftHelper.readDbShiftByUserId(userId);
        for(DbShift shift: shiftList){
            hoursWorking += TimeUtil.calculateHourBetweenEpochSecondInstants(shift.getTimeStart(), shift.getTimeEnd());
        }
        return hoursWorking;
    }


    public static List<HourByShiftType> getListOfHourWithShiftTypeByUserId(int userId){
        List<HourByShiftType> hourByShiftTypeList = new ArrayList<>();

        List<DbShift> shiftList = DbShiftHelper.readDbShiftByUserId(userId);
        //Use a hash map here?
        List<Integer> shiftTypeIdList = DbShiftHelper.readUniqueShiftTypeIdFromShiftList(shiftList);
        for(int shiftTypeId : shiftTypeIdList){
            hourByShiftTypeList.add(
                    new HourByShiftType(
                        shiftTypeId,
                        DbShiftTypeHelper.readDbShiftTypeById(shiftTypeId).getName(),
                        0
                    ));
        }
        for(DbShift shift : shiftList){
            float shiftHour = TimeUtil.calculateHourBetweenEpochSecondInstants(shift.getTimeStart(), shift.getTimeEnd());
            for(HourByShiftType hourByShiftType : hourByShiftTypeList){
                if(shift.getShiftTypeId().equals(hourByShiftType.getShiftTypeId())){
                    hourByShiftType.addHour(shiftHour);
                    break;
                }
            }
        }

        return hourByShiftTypeList;
    }

    /**
     * Get the status of a user's DbOneTimeAvailability within a time range
     * Note: Since availabilities are submitting weekly, all availabilities within a week will have
     *       the same status
     * @param userId The database ID of the target user
     * @param timeStart The starting time of the time range
     * @param timeEnd The ending time of the time range
     */

    public static Status getOneTimeAvailStatus(Integer userId, Integer teamId, Long timeStart, Long timeEnd) {
        List<DbOneTimeAvailability> dbOneTimeAvailByTimeRangeList = getAllOneTimeAvailByUserIdAndTimeRange(userId, timeStart, timeEnd);
        if(dbOneTimeAvailByTimeRangeList.size() > 0) {
            return dbOneTimeAvailByTimeRangeList.get(0).getStatus();
        }
        else {
            return Status.Open;
        }
    }

    public static List<DbOneTimeAvailability> getAllOneTimeAvailByUserIdAndTimeRange(Integer userId, Long timeStart,
                                                                                     Long timeEnd) {
        List<DbUserTeam> dbUserTeamList = DbUserTeamHelper.readAllDbUserTeamsByUserId(userId);

        List<DbOneTimeAvailability> dbOneTimeAvailByUser = new ArrayList<>();
        for (DbUserTeam dbUserTeam : dbUserTeamList) {
            List<DbOneTimeAvailability> targetOneTimeAvailList = DbOneTimeAvailabilityHelper
                    .readDbOneTimeAvailabilityByUserTeamId(dbUserTeam.getId());
            dbOneTimeAvailByUser.addAll(targetOneTimeAvailList);
        }

        List<DbOneTimeAvailability> dbOneTimeAvailByTimeRange = DbOneTimeAvailabilityHelper
                .readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        Set<DbOneTimeAvailability> filteredSet = new LinkedHashSet<>(dbOneTimeAvailByUser);
        filteredSet.retainAll(dbOneTimeAvailByTimeRange);

        List<DbOneTimeAvailability> dbOneTimeAvailabilityList = new ArrayList<>();
        dbOneTimeAvailabilityList.addAll(filteredSet);

        return dbOneTimeAvailabilityList;
    }
}




