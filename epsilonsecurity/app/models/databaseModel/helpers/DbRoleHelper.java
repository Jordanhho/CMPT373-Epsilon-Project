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

    /**
     * creates a DbRole from name
     * @param name
     */
    public static void createDbRole(@Nonnull String name) {
        DbRole dbRole = new DbRole(name);
        dbRole.save();
    }

    /**
     * deletes a DbRole by DbRoleId
     * @param id
     */
    public static void deleteDbRoleById(@Nonnull Integer id) {
        DbRole dbRole = readDbRoleById(id);
        dbRole.delete();
    }

    /**
     * finds a DbRole by DbRoleId
     * @param id
     * @return
     */
    public static DbRole readDbRoleById(@Nonnull Integer id) {
        DbRole dbRole = DbRole.find.byId(id);
        return dbRole;
    }

    /**
     * returns a list of all DbRole
     * @return
     */
    public static List<DbRole> readAllDbRole() {
        List<DbRole> dbRole = DbRole.find.all();
        return dbRole;
    }
}
