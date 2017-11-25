package controllers;

import models.databaseModel.helpers.DbTeamHelper;
import models.databaseModel.scheduling.DbTeam;
import models.databaseModel.scheduling.DbUser;
import models.queries.TeamIdArrayForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import models.databaseModel.scheduling.DbUserTeam;
import models.databaseModel.helpers.DbUserTeamHelper;

import javax.inject.Inject;
import java.util.ArrayList;
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

    private Integer[] getDbTeamArrayFromForm() {
        Form<TeamIdArrayForm> form = formFactory.form(TeamIdArrayForm.class).bindFromRequest();
        return form.get().getTeamIdList();
    }

    public Result listUserTeams() {
        return ok();
    }

    public Result createUserTeamByUserIdAndTeamIdArray(Integer userId) {
        List<DbUserTeam> dbUserTeamList = DbUserTeamHelper.readAllDbUserTeamsByUserId(userId);
        for (DbUserTeam dbUserTeam : dbUserTeamList) {
            DbUserTeamHelper.deleteDbUserTeam(dbUserTeam);
        }

        Integer[] teamIdArray = getDbTeamArrayFromForm();
        List<DbTeam> dbTeamList = new ArrayList<>();
        for (Integer teamId : teamIdArray) {
            dbTeamList.add(DbTeamHelper.readDbTeamById(teamId));
        }

        for (DbTeam dbTeam : dbTeamList) {
            DbUserTeam dbUserTeam = new DbUserTeam(dbTeam.getId(), userId);
            DbUserTeamHelper.createDbUserTeam(dbUserTeam);
        }

        return ok();
    }

    public Result createUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result retrieveUserTeam(Integer userId, Integer teamId) {
        return ok();
    }

    public Result readAllUsersByTeamId(Integer teamId) {
        List<DbUser> dbUserList = DbUserTeamHelper.readAllDbUsersByTeamId(teamId);

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
        List<DbTeam> dbTeamList = DbUserTeamHelper.readAllDbTeamsByUserId(userId);

        return ok(Json.toJson(dbTeamList));
    }
}
