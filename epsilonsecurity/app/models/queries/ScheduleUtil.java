package models.queries;


import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;
import models.databaseModel.helpers.DbOneTimeUnavailabilityHelper;
import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Schedule Util class for table queries
 */
public final class ScheduleUtil {

    //Search for available users based on teamId, start and end time of shift
    public static List<DbUser> queryUsersBasedOnAvailiability(Integer teamId, Integer timeStart, Integer timeEnd) {
        //find all users in right team/location
        List<DbUserTeam> userTeamListByLocation = DbUserTeam.find.query().where().eq(DbUserTeam.COLUMN_TEAM_ID, teamId).findList();
        //find all availability in right time range
        List<DbOneTimeAvailability> oneTimeAvailabilityList = DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> userTeamListByAvailability = new ArrayList<>();
        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            userTeamListByAvailability.add(DbUserTeam.find.byId(oneTimeAvailability.getUserTeamId()));
        }
        //return all unavailability in range
        List<DbOneTimeUnavailability> oneTimeUnavailabilityList = DbOneTimeUnavailabilityHelper.readDbOneTimeUnavailabilityByTimeRange(timeStart, timeEnd);

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
            userShiftList.add(DbUserShift.find.query().where().eq(DbUserShift.COLUMN_SHIFT_ID, shift.getId()).findOne());
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




