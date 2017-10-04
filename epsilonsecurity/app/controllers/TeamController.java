package controllers;

import models.databaseModel.helpers.DbShiftNameHelper;
import models.databaseModel.helpers.DbTeamHelper;
import models.databaseModel.scheduling.DbShiftName;
import models.databaseModel.scheduling.DbTeam;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Map;

public class TeamController extends Controller {

    public Result createTeam() {
        final Map<String, String[]> values = request().body().asFormUrlEncoded();

        String name = values.get("name")[0];

        DbTeamHelper.createDbTeam(name);

        return ok();
    }

    public Result deleteTeamByName(String teamName) {
        DbTeam dbTeam = DbTeamHelper.readDbTeamByName(teamName);
        DbTeamHelper.deleteDbTeamByName(dbTeam.getName());

        return ok();
    }

}
