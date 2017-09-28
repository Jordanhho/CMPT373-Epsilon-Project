package models.databaseModel.roles;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbRolePermission Table with DbRolePermission Id and name of position
 */
@Entity
public class DbRolePermission extends Model {

    @Id
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer roleId;

    @Nonnull
    private int permissionId;

    /**
     * The constructor of the RolePermission table
     * @param id  the id of the RolePermission
     * @param roleId  the role Id of the RolePermission
     * @param permissionId  the permission Id of the RolePermission
     */
    public DbRolePermission(@Nonnull Integer id, @Nonnull Integer roleId, @Nonnull Integer permissionId) {
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

    @Override
    public String toString() {
        return "DbRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }

    public static Finder<Integer, DbRolePermission> find = new Finder<>(DbRolePermission.class);


}
