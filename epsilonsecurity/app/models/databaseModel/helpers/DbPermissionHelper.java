package models.databaseModel.helpers;

import models.databaseModel.roles.DbPermission;
import models.databaseModel.roles.query.QDbPermission;

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

    /**
     * finds a DbPermission from DbPermissionId
     * @param id
     * @return
     */
    public static DbPermission readDbPermissionById(Integer id) {
        DbPermission dbPermission = new QDbPermission()
                .id
                .eq(id)
                .findUnique();

        return dbPermission;
    }

    /**
     * returns a list just all DbPermission
     * @return
     */
    public static List<DbPermission> readAllDbPermissions() {
        List<DbPermission> dbPermissionList = new QDbPermission()
                .findList();

        return dbPermissionList;
    }
}
