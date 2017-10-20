package controllers;

import play.mvc.*;
import models.databaseModel.roles.DbPermission;
import models.databaseModel.helpers.DbPermissionHelper;

public class PermissionController extends Controller {

//    TODO: Implement functionality of PermissionController
    public Result listPermissions() {
        return ok();
    }

    public Result createPermission() {
        return ok();
    }

    public Result retrievePermission(Integer permissionId) {
        return ok();
    }

    public Result deletePermission() {
        return ok();
    }
}
