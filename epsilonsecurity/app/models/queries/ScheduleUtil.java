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
}




