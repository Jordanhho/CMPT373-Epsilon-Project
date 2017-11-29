package models.databaseModel.helpers;

import models.databaseModel.roles.DbRolePermission;

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

    public static DbRolePermission readDbRolePermissionById(Integer id) {
        return DbRolePermission.find.byId(id);
    }

    public static List<DbRolePermission> readAllDbRolePermission() {
        return DbRolePermission.find.all();
    }

    public static List<DbRolePermission> readDbRolePermissionsByRoleId(Integer roleId) {
        return DbRolePermission.find
            .query()
            .where()
            .eq(DbRolePermission.COLUMN_ROLE_ID, roleId)
            .findList();
    }
}
