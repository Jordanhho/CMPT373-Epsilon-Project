package controllers;

import play.libs.Json;
import play.mvc.*;
import models.databaseModel.roles.DbRole;
import models.databaseModel.helpers.DbRoleHelper;

public class RoleController extends Controller {

//    TODO: Implement functionality just RoleController
    public Result listRoles() {
        return ok();
    }

    public Result createRole() {
        return ok();
    }

    public Result retrieveRole(Integer roleId) {
        DbRole dbRole = DbRoleHelper.readDbRoleById(roleId);
        return ok(Json.toJson(dbRole));
    }

    public Result deleteRole(Integer roleId) {
        return ok();
    }
}
