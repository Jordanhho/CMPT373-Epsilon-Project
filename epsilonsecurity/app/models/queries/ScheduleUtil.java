package models.queries;


import models.databaseModel.helpers.DbOneTimeAvailabilityHelper;
import models.databaseModel.helpers.DbOneTimeUnavailabilityHelper;
import models.databaseModel.helpers.DbShiftHelper;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.*;
import models.schedule.Shift;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Schedule Util class for table queries
 */
public class ScheduleUtil {


    public static List<DbUser> queryUsersBasedOnAvailiability(Integer teamId, Integer timeStart, Integer timeEnd) {
        List<DbUserTeam> list1 = DbUserTeam.find.query().where().eq(DbUserTeam.COLUMN_TEAM_ID, teamId).findList();

        List<DbOneTimeAvailability> oneTimeAvailabilityList = DbOneTimeAvailabilityHelper.readDbOneTimeAvailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> list2 = new ArrayList<>();
        for(DbOneTimeAvailability oneTimeAvailability : oneTimeAvailabilityList){
            list2.add(DbUserTeam.find.byId(oneTimeAvailability.getUserTeamId()));
        }
        //return all unavailibilities in range
        List<DbOneTimeUnavailability> oneTimeUnavailabilityList = DbOneTimeUnavailabilityHelper.readDbOneTimeUnavailabilityByTimeRange(timeStart, timeEnd);

        List<DbUserTeam> list3 = new ArrayList<>();
        for(DbOneTimeUnavailability oneTimeUnavailability : oneTimeUnavailabilityList){
            list3.add(DbUserTeam.find.byId(oneTimeUnavailability.getUserTeamId()));
        }

        List<DbShift> shifts = DbShiftHelper.readDbShiftByTime(timeStart, timeEnd);
        List<DbUserShift> userShiftList = new ArrayList<>();
        for(DbShift shift : shifts){
            userShiftList.add(DbUserShift.find.query().where().eq(DbUserShift.COLUMN_SHIFT_ID, shift.getId()).findOne());
        }
        List<DbUserTeam> list4 = new ArrayList<>();
        for(DbUserShift userShift : userShiftList){
            list4.add(DbUserTeam.find.byId(userShift.getUserTeamId()));
        }

        //combine list1 & list2
        //then subtract list3 & list4

        System.out.println("list 1 size " + list1.size());
        System.out.println("list 2 size " + list2.size());
        System.out.println("list 3 size " + list3.size());
        System.out.println("list 4 size " + list4.size());

        List<DbUser> dbUserList = new ArrayList<>();

        Set<DbUserTeam> filteredList = new LinkedHashSet<>(list1);
        filteredList.addAll(list2);
        filteredList.removeAll(list3);
        filteredList.removeAll(list4);
        for (DbUserTeam userTeam : filteredList){
            dbUserList.add(DbUserHelper.readDbUserById(userTeam.getUserId()));
        }
        return dbUserList;
    }






}




