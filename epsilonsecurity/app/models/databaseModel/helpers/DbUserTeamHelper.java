package models.databaseModel.helpers;

import io.ebean.Expr;
import io.ebean.Expression;
import models.databaseModel.scheduling.DbUserTeam;
import scala.reflect.api.Exprs;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbUserTeam class
 */
public final class DbUserTeamHelper {

    private DbUserTeamHelper() {

    }

    public static void createDbUserTeam(@Nonnull Integer teamId, Integer userId) {
        DbUserTeam dbUserTeam = new DbUserTeam(teamId, userId);
        dbUserTeam.save();
    }

    public static void deleteDbUserTeamByUserAndTeamId(@Nonnull Integer userId, Integer teamId) {
        DbUserTeam dbUserTeam = readDbTeamByUserAndTeamId(userId, teamId);
        dbUserTeam.delete();
    }

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

    public static List<DbUserTeam> readAllDbUserTeams() {
        List<DbUserTeam> dbUserTeamList = DbUserTeam.find.all();
        return dbUserTeamList;
    }

}
