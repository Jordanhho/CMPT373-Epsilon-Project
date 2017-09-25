package models.roles;

import models.id.PermissionId;
import models.id.RoleId;
import models.id.RolePermissionId;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;

@Entity
public class RolePermission extends Model {

    @Id
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

    public static Finder<Integer, RolePermission> find = new Finder<>(RolePermission.class);


}
