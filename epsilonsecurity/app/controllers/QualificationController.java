package controllers;

import models.databaseModel.qualification.DbQualification;
import models.databaseModel.helpers.DbQualificationHelper;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class QualificationController extends Controller {

    public Result listAllQualifications() {
        List<DbQualification> dbQualificationList = DbQualificationHelper.readAllDbQualification();
        return ok(Json.toJson(dbQualificationList));
    }
}
