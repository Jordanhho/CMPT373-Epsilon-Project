package models.databaseModel.helpers;

import models.databaseModel.scheduling.DbUser;
import io.ebean.Expr;
import io.ebean.Expression;
import models.databaseModel.scheduling.DbUserTeam;
import scala.reflect.api.Exprs;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * CRUD operations for DbUserTeam class
 */
public final class DbUserTeamHelper {

    private DbUserTeamHelper() {

    }

    /**
     * Creates a dbUser from teamId and userId
     * @param teamId
     * @param userId
     */
    public static void createDbUserTeam(@Nonnull Integer teamId, Integer userId) {
        DbUserTeam dbUserTeam = new DbUserTeam(teamId, userId);
        dbUserTeam.save();
    }

    /**
     * Deletes a DbUserTeam by DbUserTeam id
     * @param userId
     * @param teamId
     */
    public static void deleteDbUserTeamByUserAndTeamId(@Nonnull Integer userId, Integer teamId) {
        DbUserTeam dbUserTeam = readDbTeamByUserAndTeamId(userId, teamId);
        dbUserTeam.delete();
    }

    /**
     * Searches for a dbUserTeam by dbUserTeam id
     * @param userId
     * @param teamId
     * @return
     */
    public static DbUserTeam readDbTeamByUserAndTeamId(@Nonnull Integer userId, @Nonnull Integer teamId) {
        DbUserTeam dbUserTeam = DbUserTeam.find
                .query()
                .where()
                .conjunction()
                .add(Expr.eq(DbUserTeam.COLUMN_TEAM_ID, teamId))
                .add(Expr.eq(DbUserTeam.COLUMN_USER_ID, userId))
                .findOne();

        return dbUserTeam;
    }

    /**
     * obtains list of all DbUserTeam
     * @return
     */
    public static List<DbUserTeam> readAllDbUserTeams() {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find.all();
        return dbUserTeamList;
    }

    /**
     * finds a DbUser by UserTeamId
     * @param id
     * @return
     */
    public static DbUser readDbUserByUserTeamId(@Nonnull Integer id){
        DbUserTeam dbUserTeam = DbUserTeam.find.query().where().eq("id", id).findOne();
        DbUser dbUser = DbUser.find.byId(dbUserTeam.getUserId());
        return dbUser;
    }

    /**
     * Finds all users on that campus
     * @param teamId the campus location
     * @return a List of users from target campus
     */
    public static List<DbUser> findUserByTeamId(@Nonnull Integer teamId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find.query().where().eq(DbUserTeam.COLUMN_TEAM_ID, teamId).findList();
        List<DbUser> userList = new ArrayList<>();

        for(DbUserTeam userTeam : dbUserTeamList){
            userList.add(DbUser.find.byId(userTeam.getUserId()));
        }

        return userList;
    }

}
