package store;

import models.databaseModel.roles.AccessLevel;
import models.databaseModel.roles.DbPermission;

public class Permission {

    private PermissionId permissionId;
    private String name;
    private AccessLevel accessLevel;

    public Permission(DbPermission permission, AccessLevel level) {
        this.permissionId = new PermissionId(permission.getId());
        this.name = permission.getName();
        this.accessLevel = level;
    }

    public PermissionId getPermissionId() {
        return permissionId;
    }

    public String getName() {
        return name;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
}
