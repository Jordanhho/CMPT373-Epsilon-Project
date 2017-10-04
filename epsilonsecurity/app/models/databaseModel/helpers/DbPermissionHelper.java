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

    /**
     * creates a DbPermission by name
     * @param name
     */
    public static void createDbPermission(@Nonnull String name) {
        DbPermission dbPermission = new DbPermission(name);
        dbPermission.save();
    }

    /**
     * deletes a DbPermission by DbPermissionId
     * @param id
     */
    public static void deleteDbPermissionById(@Nonnull Integer id) {
        DbPermission dbPermission = readDbPermissionById(id);
        dbPermission.delete();
    }

    /**
     * finds a DbPermission from DbPermissionId
     * @param id
     * @return
     */
    public static DbPermission readDbPermissionById(@Nonnull Integer id) {
        DbPermission dbPermission = DbPermission.find.byId(id);
        return dbPermission;
    }

    /**
     * returns a list of all DbPermission
     * @return
     */
    public static List<DbPermission> readAllDbPermission() {
        List<DbPermission> dbPermission = DbPermission.find.all();
        return dbPermission;
    }
}
