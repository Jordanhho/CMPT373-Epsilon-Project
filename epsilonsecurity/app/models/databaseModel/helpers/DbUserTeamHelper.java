package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserTeam;

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

    public static DbUserTeam readDbTeamByUserAndTeamId(Integer userId, Integer teamId) {
        DbUserTeam dbUserTeam = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_USER_ID, userId)
                .and()
                .eq(DbUserTeam.COLUMN_TEAM_ID, teamId)
                .findOne();

        return dbUserTeam;
    }

    public static List<DbUserTeam> readAllDbUserTeams() {
        return DbUserTeam.find.all();
    }

    public static List<DbUserTeam> readAllDbUserTeamsByUserId(Integer userId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_USER_ID, userId)
                .findList();

        return dbUserTeamList;
    }

    public static List<DbUserTeam> readAllDbUserTeamsByTeamId(Integer teamId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_TEAM_ID, teamId)
                .findList();

        return dbUserTeamList;
    }

    public static List<DbTeam> readAllDbTeamsByUserId(Integer userId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_USER_ID, userId)
                .findList();

        List<DbTeam> teamList = new ArrayList<>();
        for (DbUserTeam userTeam : dbUserTeamList) {
            teamList.add(DbTeam.find
                    .byId(userTeam.getTeamId()));
        }

        return teamList;
    }

    public static List<DbUser> readAllDbUsersByTeamId(Integer teamId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find
                .query()
                .where()
                .eq(DbUserTeam.COLUMN_TEAM_ID, teamId)
                .findList();

        List<DbUser> userList = new ArrayList<>();

        for (DbUserTeam userTeam : dbUserTeamList) {
            userList.add(DbUser.find
                    .byId(userTeam.getTeamId()));
        }

        return userList;
    }
}
