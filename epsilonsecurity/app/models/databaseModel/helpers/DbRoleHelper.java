package models.databaseModel.helpers;

import models.databaseModel.roles.DbRole;

import java.util.List;

/**
 * CRUD operations for DbRole Class
 */
public final class DbRoleHelper {

    private DbRoleHelper() {

    }

    public static void createDbRole(DbRole dbRole) {
        dbRole.save();
    }

    public static void deleteDbRoleById(DbRole dbRole) {
        dbRole.delete();
    }

    public static DbRole readDbRoleById(Integer id) {
        return DbRole.find.byId(id);
    }

    public static List<DbRole> readAllDbRole() {
        return DbRole.find.all();
    }
}
