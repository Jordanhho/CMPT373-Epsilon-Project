package models.databaseModel.helpers;

import models.databaseModel.roles.DbPermission;

import java.util.List;


/**
 * CRUD operations for DbPermission Class
 */
public final class DbPermissionHelper {

    private DbPermissionHelper() {

    }

    public static void createDbPermission(DbPermission dbPermission) {
        dbPermission.save();
    }

    public static void deleteDbPermission(DbPermission dbPermission) {
        dbPermission.delete();
    }

    public static DbPermission readDbPermissionById(Integer id) {
        return DbPermission.find.byId(id);
    }

    public static List<DbPermission> readAllDbPermissions() {
        return DbPermission.find.all();
    }
}
