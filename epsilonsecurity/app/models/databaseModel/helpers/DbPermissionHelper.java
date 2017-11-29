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

    public static DbPermission readDbPermissionByName(String name) {
        return DbPermission.find
            .query()
            .where()
            .eq(DbPermission.COLUMN_NAME, name)
            .findOne();
    }

    /**
     * returns a list just all DbPermission
     * @return
     */
    public static List<DbPermission> readAllDbPermissions() {
        return DbPermission.find.all();
    }
}
