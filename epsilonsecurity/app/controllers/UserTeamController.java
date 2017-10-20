package controllers;

import play.mvc.*;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.helpers.DbUserTeamHelper;

public class UserTeamController extends Controller {

//    TODO: Implement functionality of UserTeamController
    public Result listUserTeams() {
        return ok();
    }

    public Result createUserTeam() {
        return ok();
    }

    public Result retrieveUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result deleteUserTeam() {
        return ok();
    }
}
