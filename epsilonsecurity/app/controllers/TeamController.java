package controllers;

import models.databaseModel.helpers.DbTeamHelper;
import models.databaseModel.scheduling.DbTeam;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

public class TeamController extends Controller {

    public Result listTeams() {
        return ok();
    }

    public Result createTeam() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String name = values.get(DbTeam.FORM_NAME)[0];

        DbTeamHelper.createDbTeam(name);

        return ok();
    }

    public Result retrieveTeamByName(String teamName) {
        return ok();
    }

    public Result deleteTeamByName() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String teamName = values.get(DbTeam.FORM_NAME)[0];

        DbTeam dbTeam = DbTeamHelper.readDbTeamByName(teamName);
        DbTeamHelper.deleteDbTeamByName(dbTeam.getName());

        return ok();
    }

}
