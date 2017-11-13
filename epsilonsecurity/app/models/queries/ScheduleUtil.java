package models.queries;


import models.databaseModel.helpers.*;
import models.databaseModel.scheduling.*;
import models.databaseModel.scheduling.query.QDbUserShift;
import models.databaseModel.scheduling.query.QDbUserTeam;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Schedule Util class for table queries
 */
public final class ScheduleUtil {

    //Search for available users based on teamId, start and end time of shift
    public static List<DbUser> queryUsersBasedOnAvailability(Integer teamId, Long timeStart, Long timeEnd) {

        //find all users in right team/location
        List<DbUserTeam> userTeamListByLocation = new QDbUserTeam()
                .teamId
                .eq(teamId)
                .findList();

        //find all availability in right time range
        List<DbOneTimeAvailability> oneTimeAvailabilityList = DbOneTimeAvailabilityHelper
                .readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByAvailability = new ArrayList<>();

        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            userTeamListByAvailability.add(DbUserTeam.find.byId(oneTimeAvailability.getUserTeamId()));
        }

        //return all unavailability in range
        List<DbOneTimeUnavailability> oneTimeUnavailabilityList = DbOneTimeUnavailabilityHelper
                .readDbOneTimeUnavailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByUnavailability = new ArrayList<>();

        for(DbOneTimeUnavailability oneTimeUnavailability : oneTimeUnavailabilityList){
            if(oneTimeUnavailability.getUserTeamId().equals(teamId)){
                userTeamListByUnavailability.add(DbUserTeam.find.byId(oneTimeUnavailability.getUserTeamId()));
            }
        }

        //return all shifts in range
        List<DbShift> shifts = DbShiftHelper.readDbShiftByTime(timeStart, timeEnd);

        List<DbUserShift> userShiftList = new ArrayList<>();

        for(DbShift shift : shifts){
            userShiftList.add(new QDbUserShift().shiftId.eq(shift.getId()).findUnique());
        }

        List<DbUserTeam> userTeamListByShift = new ArrayList<>();

        for(DbUserShift userShift : userShiftList){
            userTeamListByShift.add(DbUserTeam.find.byId(userShift.getUserTeamId()));
        }

        //combine list1 & list2
        //then subtract list3 & list4
        List<DbUser> dbUserList = new ArrayList<>();

        Set<DbUserTeam> filteredList = new LinkedHashSet<>(userTeamListByAvailability);

        //find intersection of userTeam list by location and time range
        filteredList.retainAll(userTeamListByLocation);

        //remove elements with in time-range unavailability and shift time
        filteredList.removeAll(userTeamListByUnavailability);
        filteredList.removeAll(userTeamListByShift);

        for (DbUserTeam userTeam : filteredList){
            dbUserList.add(DbUserHelper.readDbUserById(userTeam.getUserId()));
        }

        return dbUserList;
    }


    /**
     * Get all shifts between two dates or time periods on same day
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public static List<DbShift> getAllShiftsBetweenTimePeriods(Long timeStart, Long timeEnd) {
        List<DbShift> dbShiftList = new ArrayList<>();
        dbShiftList = DbShiftHelper.readDbShiftByTime(timeStart, timeEnd);
        return dbShiftList;
    }

    /**
     * get all information about one specific user
     * @param userId
     * @return
     */
    public static DbUser getInfoOnUser(Integer userId) {
        DbUser dbUser = DbUserHelper.readDbUserById(userId);
        return dbUser;
    }

    /**
     * get all users from a team/Campus
     * @param teamId
     * @return
     */
    public static List<DbUser> getAllUsersFromCampusTeam(Integer teamId) {
        //find all users in team/campus
        List<DbUserTeam> userTeamListByLocation = new QDbUserTeam()
                .teamId
                .eq(teamId)
                .findList();

        List<DbUser> dbUserList = new ArrayList<>();

        for (DbUserTeam userTeam: userTeamListByLocation){
            dbUserList.add(DbUserHelper.readDbUserById(userTeam.getUserId()));
        }

        return dbUserList;
    }

    /**
     * get list of users available for a job also need to be refactored to take into user's qualification into account.
     * @param
     * @return
     */
    public static List<DbUser> getListOfUserForJob() {
        //TODO implement when database schema is updated
        return null;
    }

    /**
     * Get user's qualification for job(s)
     * @param
     * @return
     */
    public static void getUserQualificationForJob() {
        //TODO implement when database schema is updated
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
            // TODO: replace getUserId() with getId()
            dbUserShiftList.add(DbUserShiftHelper.readDbUserShiftByUserTeamId(dbUserTeam.getUserId()));
        }

        List<ShiftWithCampus> shiftsWithCampusList = new ArrayList<>();
        int i = 0;
        for (List<DbUserShift> targetUserShiftList : dbUserShiftList) {
            for (DbUserShift dbUserShift : targetUserShiftList) {
                DbShift dbShift = DbShiftHelper.readDbShiftByShiftId(dbUserShift.getShiftId());
                DbTeam dbTeam = DbTeamHelper.readDbTeamById(dbUserTeamList.get(i).getTeamId());
                DbShiftType dbShiftType = DbShiftTypeHelper.readDbShiftTypeById(dbShift.getShiftTypeId());
                ShiftWithCampus targetShift = new ShiftWithCampus(dbShift, dbTeam, dbShiftType);
                shiftsWithCampusList.add(targetShift);
            }
            i++;
        }

        return shiftsWithCampusList;
    }
}




