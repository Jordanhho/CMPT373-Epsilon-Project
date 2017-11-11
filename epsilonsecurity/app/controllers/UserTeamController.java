package controllers;

import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.DbUser;
import play.libs.Json;
import play.mvc.*;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.helpers.DbUserTeamHelper;

import java.util.List;

public class UserTeamController extends Controller {

    public Result listUserTeams() {
        return ok();
    }

    public Result createUserTeam() {
        return ok();
    }

    public Result createUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result retrieveUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result readAllUsersByTeamId(Integer teamId) {
        List<DbUser> dbUserList = DbUserTeamHelper.findAllUsersByTeamId(teamId);

        return ok(Json.toJson(dbUserList));
    }

    public Result deleteUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result deleteUserFromAllTeams(Integer userId) {
        List<DbUserTeam> userTeamList = DbUserTeamHelper.readAllDbUserTeamsByUserId(userId);
        for (DbUserTeam userTeam : userTeamList) {
            DbUserTeamHelper.deleteDbUserTeam(userTeam);
        }

        return ok();
    }

    public Result readDbUserTeamByUserId(Integer userId) {
        List<DbTeam> dbTeamList = DbUserTeamHelper.readDbUserTeamByUserId(userId);

        return ok(Json.toJson(dbTeamList));
    }
}
