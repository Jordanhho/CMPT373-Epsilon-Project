package models.databaseModel.helpers;

import models.databaseModel.roles.DbRolePermission;
import models.databaseModel.roles.AccessLevel;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbRolePermission Class
 */
public final class DbRolePermissionHelper {

    private DbRolePermissionHelper() {

    }

    /**
     * creates a DbRolePermission from teamId, roleId, permissionId, accessLevel
     * @param teamId
     * @param roleId
     * @param permissionId
     * @param accessLevel
     */
    public static void createDbRolePermission(@Nonnull Integer teamId,
                                              @Nonnull Integer roleId,
                                              @Nonnull Integer permissionId,
                                              @Nonnull AccessLevel accessLevel) {
        DbRolePermission dbRolePermission = new DbRolePermission(teamId, roleId, permissionId, accessLevel);
        dbRolePermission.save();
    }

    /**
     * deletes a DbRolePermission by DbRolePermissionId
     * @param id
     */
    public static void deleteDbRolePermissionById(@Nonnull Integer id) {
        DbRolePermission dbRolePermission = readDbRolePermissionById(id);
        dbRolePermission.delete();
    }

    /**
     * finds a DbRolePermission by DbRolePermissionId
     * @param id
     * @return
     */
    public static DbRolePermission readDbRolePermissionById(@Nonnull Integer id) {
        DbRolePermission dbRolePermission = DbRolePermission.find.byId(id);
        return dbRolePermission;
    }

    /**
     * returns a list of all DbRolePermission
     * @return
     */
    public static List<DbRolePermission> readAllDbRolePermission() {
        List<DbRolePermission> dbRolePermission = DbRolePermission.find.all();
        return dbRolePermission;
    }
}
