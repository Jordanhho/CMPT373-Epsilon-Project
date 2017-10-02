package models.databaseModel.helpers;

import models.databaseModel.roles.DbRole;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbRole Class
 */
public final class DbRoleHelper {

    private DbRoleHelper() {

    }

    public static void createDbRole(@Nonnull String name) {
        DbRole dbRole = new DbRole(name);
        dbRole.save();
    }


    public static void deleteDbRoleById(@Nonnull Integer id) {
        DbRole dbRole = readDbRoleById(id);
        dbRole.delete();
    }

    public static DbRole readDbRoleById(@Nonnull Integer id) {
        DbRole dbRole = DbRole.find.byId(id);
        return dbRole;
    }

    public static List<DbRole> readAllDbRole() {
        List<DbRole> dbRole = DbRole.find.all();
        return dbRole;
    }
}
