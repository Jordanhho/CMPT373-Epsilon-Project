package models.roles;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

@Entity
public class RolePermission extends Model {

    @Id
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer roleId;
    @Nonnull
    private int permissionId;

    public RolePermission(@Nonnull Integer id, @Nonnull Integer roleId, @Nonnull Integer permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getRoleId() {
        return roleId;
    }

    @Nonnull
    public Integer getPermissionId() {
        return permissionId;
    }

    public static Finder<Integer, RolePermission> find = new Finder<>(RolePermission.class);


}
