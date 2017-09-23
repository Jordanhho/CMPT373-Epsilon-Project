package models.roles;

import models.id.PermissionId;
import models.id.RoleId;
import models.id.RolePermissionId;

import javax.annotation.Nonnull;

public class RolePermission {
    @Nonnull private RolePermissionId id;
    @Nonnull private RoleId roleId;
    @Nonnull private PermissionId permissionId;

    public RolePermission(@Nonnull RolePermissionId id, @Nonnull RoleId roleId, @Nonnull PermissionId permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @Nonnull
    public RolePermissionId getId() {
        return id;
    }

    @Nonnull
    public RoleId getRoleId() {
        return roleId;
    }

    @Nonnull
    public PermissionId getPermissionId() {
        return permissionId;
    }
}
