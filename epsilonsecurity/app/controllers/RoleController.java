package controllers;

import play.libs.Json;
import play.mvc.*;
import models.databaseModel.roles.DbRole;
import models.databaseModel.helpers.DbRoleHelper;
import java.util.List;

public class RoleController extends Controller {

    public Result listRoles() {
      List<DbRole> roles = DbRoleHelper.readAllDbRole();

      return ok(Json.toJson(roles));
    }


//    TODO: Implement functionality of RoleController
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
