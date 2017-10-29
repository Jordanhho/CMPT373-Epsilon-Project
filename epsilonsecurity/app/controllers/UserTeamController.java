package controllers;

import models.databaseModel.scheduling.DbTeam;
import play.libs.Json;
import play.mvc.*;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.helpers.DbUserTeamHelper;

import java.util.List;

public class UserTeamController extends Controller {

//    TODO: Implement functionality of UserTeamController
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

    public Result deleteUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result readDbUserTeamByUserId(Integer userId) {
        List<DbTeam> dbTeamList = DbUserTeamHelper.readDbUserTeamByUserId(userId);

        return ok(Json.toJson(dbTeamList));
    }
}
