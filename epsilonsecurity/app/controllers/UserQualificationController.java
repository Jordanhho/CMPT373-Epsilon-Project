package controllers;

import models.databaseModel.helpers.DbUserQualificationHelper;
import models.databaseModel.qualification.DbQualification;
import models.databaseModel.qualification.DbUserQualification;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class UserQualificationController extends Controller {
    private FormFactory formFactory;

    @Inject
    public UserQualificationController(FormFactory formFactory){
        this.formFactory = formFactory;
    }

    private DbUserQualification getDbUserQualificationFromForm(){
        Form<DbUserQualification> form = formFactory.form(DbUserQualification.class).bindFromRequest();

        DbUserQualification dbUserQualification = form.get();

        return dbUserQualification;
    }

    public Result createUserQualification(){
        DbUserQualification dbUserQualification = getDbUserQualificationFromForm();
        DbUserQualificationHelper.createDbUserQualification(dbUserQualification);

        return ok();
    }

    public Result readQualificationByUserId(Integer userId){
        List<DbQualification> dbQualificationList = DbUserQualificationHelper.readAllDbQualificationsByUserId(userId);
        return ok(Json.toJson(dbQualificationList));
    }
}
