package controllers;

import models.DummyDatabase.DummyDataBase;
import models.databaseModel.helpers.DbTeamHelper;
import models.databaseModel.scheduling.DbTeam;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class TeamController extends Controller {

    private final FormFactory formFactory;

    @Inject
    TeamController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    private DbTeam getDbTeamFromForm() {

        // From the request, create a form that can handle a DbTeam object.
        Form<DbTeam> form = formFactory.form(DbTeam.class).bindFromRequest();

        // Create a DbTeam object from the form data.
        DbTeam dbTeam = form.get();

        return dbTeam;
    }

    public Result createTeam() {

        // Create a DbTeam object from the form data.
        DbTeam dbTeam = getDbTeamFromForm();

        // Enter the DbTeam into the database.
        DbTeamHelper.createDbTeam(dbTeam);

        return ok();
    }

    public Result retrieveTeam(String name) {
        DbTeam dbTeam = DbTeamHelper.readDbTeamByName(name);

        return ok(Json.toJson(dbTeam));
    }

    public Result updateTeam(String name) {
        return ok();
    }

    public Result deleteTeamByName(String name) {
        DbTeam dbTeamToDelete = DbTeamHelper.readDbTeamByName(name);
        DbTeamHelper.deleteDbTeamByName(dbTeamToDelete);
        return ok();
    }

    public Result listAllTeams() {
        //new DummyDataBase();
        List<DbTeam> dbTeamList = DbTeamHelper.readAllDbTeam();

        return ok(Json.toJson(dbTeamList));
    }
}
