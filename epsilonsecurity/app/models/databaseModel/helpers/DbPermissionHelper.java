package models.databaseModel.helpers;

import models.databaseModel.roles.DbPermission;

import javax.annotation.Nonnull;
import java.util.List;


/**
 * CRUD operations for DbPermission Class
 */
public final class DbPermissionHelper {

    private DbPermissionHelper() {

    }

    public static void createDbPermission(@Nonnull String name) {
        DbPermission dbPermission = new DbPermission(name);
        dbPermission.save();
    }


    public static void deleteDbPermissionById(@Nonnull Integer id) {
        DbPermission dbPermission = readDbPermissionById(id);
        dbPermission.delete();
    }

    public static DbPermission readDbPermissionById(@Nonnull Integer id) {
        DbPermission dbPermission = DbPermission.find.byId(id);
        return dbPermission;
    }

    public static List<DbPermission> readAllDbPermission() {
        List<DbPermission> dbPermission = DbPermission.find.all();
        return dbPermission;
    }
}
