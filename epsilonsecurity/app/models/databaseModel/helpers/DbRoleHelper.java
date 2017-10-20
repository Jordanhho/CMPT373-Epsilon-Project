package models.databaseModel.helpers;

import models.databaseModel.roles.DbRole;
import models.databaseModel.roles.query.QDbRole;

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

    /**
     * finds a DbRole by DbRoleId
     * @param id
     * @return
     */
    public static DbRole readDbRoleById(Integer id) {
        DbRole dbRole = new QDbRole()
                .id
                .eq(id)
                .findUnique();

        return dbRole;
    }

    /**
     * returns a list of all DbRole
     * @return
     */
    public static List<DbRole> readAllDbRole() {
        List<DbRole> dbRoleList = new QDbRole()
                .findList();

        return dbRoleList;
    }
}
