package controllers;

import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.DbUser;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.helpers.DbUserTeamHelper;

import javax.inject.Inject;
import java.util.List;

public class UserTeamController extends Controller {

    private final FormFactory formFactory;

    @Inject
    UserTeamController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbUserTeam getDbUserTeamFromForm() {
        Form<DbUserTeam> form = formFactory.form(DbUserTeam.class).bindFromRequest();
        return form.get();
    }

    public Result listUserTeams() {
        return ok();
    }

    public Result createUserTeam(Integer userId) {
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

    public Result readDbUserTeamByUserId(Integer userId) {
        List<DbTeam> dbTeamList = DbUserTeamHelper.readDbUserTeamByUserId(userId);

        return ok(Json.toJson(dbTeamList));
    }
}
