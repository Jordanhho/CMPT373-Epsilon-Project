package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.DbUserTeam;
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
     * finds a DbUser by UserTeamId
     *
     * @param id
     * @return
     */
    public static DbUser readDbUserByUserTeamId(Integer id) {
        DbUserTeam dbUserTeam = new QDbUserTeam()
                .id
                .eq(id)
                .findUnique();

        DbUser dbUser = new QDbUser()
                .id
                .eq(dbUserTeam.getUserId())
                .findUnique();

        return dbUser;
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
