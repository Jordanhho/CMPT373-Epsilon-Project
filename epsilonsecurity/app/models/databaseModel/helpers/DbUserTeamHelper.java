package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.scheduling.query.QDbTeam;
import models.databaseModel.scheduling.query.QDbUser;
import models.databaseModel.scheduling.query.QDbUserTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for DbUserTeam class
 */
public final class DbUserTeamHelper {

    private DbUserTeamHelper() {

    }

    public static void createDbUserTeam(DbUserTeam dbUserTeam) {
        dbUserTeam.save();
    }


    public static void deleteDbUserTeam(DbUserTeam dbUserTeam) {
        dbUserTeam.delete();
    }

    /**
     * Searches for a dbUserTeam by dbUserTeam id
     *
     * @param userId
     * @param teamId
     * @return
     */
    public static DbUserTeam readDbTeamByUserAndTeamId(Integer userId, Integer teamId) {
        DbUserTeam dbUserTeam = new QDbUserTeam()
                .userId
                .eq(userId)
                .and()
                .teamId
                .eq(teamId)
                .findUnique();

        return dbUserTeam;
    }

    /**
     * obtains list of all DbUserTeam
     *
     * @return
     */
    public static List<DbUserTeam> readAllDbUserTeams() {
        List<DbUserTeam> dbUserTeamList = new QDbUserTeam()
                .findList();

        return dbUserTeamList;
    }

    /**
     * Read user team by user id
     *
     * @param userId
     * @return
     */
    public static List<DbTeam> readDbUserTeamByUserId(Integer userId) {
        List<DbUserTeam> dbUserTeamList = new QDbUserTeam()
                .id
                .eq(userId)
                .findList();

        List<DbTeam> teamList = new ArrayList<>();

        for (DbUserTeam userTeam : dbUserTeamList) {
            teamList.add(new QDbTeam()
                    .id
                    .eq(userTeam.getTeamId())
                    .findUnique());
        }

        return teamList;
    }

    /**
     * Finds all users on that campus
     *
     * @param teamId the campus location
     * @return a List of users from target campus
     */
    public static List<DbUser> findAllUsersByTeamId(Integer teamId) {
        List<DbUserTeam> dbUserTeamList = new QDbUserTeam()
                .teamId
                .eq(teamId)
                .findList();

        List<DbUser> userList = new ArrayList<>();

        for (DbUserTeam userTeam : dbUserTeamList) {
            userList.add(new QDbUser()
                    .id
                    .eq(userTeam.getUserId())
                    .findUnique());
        }

        return userList;
    }
}
