package models.databaseModel.helpers;

import models.databaseModel.roles.DbRolePermission;
import models.databaseModel.roles.query.QDbRolePermission;

import java.util.List;

/**
 * CRUD operations for DbRolePermission Class
 */
public final class DbRolePermissionHelper {

    private DbRolePermissionHelper() {

    }

    public static void createDbRolePermission(DbRolePermission dbRolePermission) {
        dbRolePermission.save();
    }

    public static void deleteDbRolePermission(DbRolePermission dbRolePermission) {
        dbRolePermission.delete();
    }

    /**
     * finds a DbRolePermission by DbRolePermissionId
     * @param id
     * @return
     */
    public static DbRolePermission readDbRolePermissionById(Integer id) {
        DbRolePermission dbRolePermission = new QDbRolePermission()
                .id
                .eq(id)
                .findUnique();

        return dbRolePermission;
    }

    /**
     * returns a list of all DbRolePermission
     * @return
     */
    public static List<DbRolePermission> readAllDbRolePermission() {
        List<DbRolePermission> dbRolePermission = new QDbRolePermission()
                .findList();
        
        return dbRolePermission;
    }
}
