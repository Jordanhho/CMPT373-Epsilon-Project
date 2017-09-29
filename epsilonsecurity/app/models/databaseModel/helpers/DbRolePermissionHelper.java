package models.databaseModel.helpers;

import models.databaseModel.roles.DbRolePermission;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbRolePermission Class
 */
public final class DbRolePermissionHelper {

    private DbRolePermissionHelper() {

    }

    public static void createDbRolePermission(@Nonnull Integer roleId, @Nonnull Integer permissionId) {
        DbRolePermission dbRolePermission = new DbRolePermission(roleId, permissionId);
        dbRolePermission.save();
    }


    public static void deleteDbRolePermissionById(@Nonnull Integer id) {
        DbRolePermission dbRolePermission = readDbRolePermissionById(id);
        dbRolePermission.delete();
    }

    public static DbRolePermission readDbRolePermissionById(@Nonnull Integer id) {
        DbRolePermission dbRolePermission = DbRolePermission.find.byId(id);
        return dbRolePermission;
    }

    public static List<DbRolePermission> readAllDbRolePermission() {
        List<DbRolePermission> dbRolePermission = DbRolePermission.find.all();
        return dbRolePermission;
    }
}
