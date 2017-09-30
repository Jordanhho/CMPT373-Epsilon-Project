package models.databaseModel.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbRolePermission Table with DbRolePermission Id and name of position
 */
@Entity
public class DbRolePermission extends Model {

    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_PERMISSION_ID = "permission_id";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer roleId;

    @Nonnull
    private int permissionId;

    /**
     * The constructor of the RolePermission table
     * Id  the id of the RolePermission
     *
     * @param roleId       the role Id of the RolePermission
     * @param permissionId the permission Id of the RolePermission
     */
    public DbRolePermission(@Nonnull Integer roleId, @Nonnull Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_ROLE_ID)
    public Integer getRoleId() {
        return roleId;
    }

    @Nonnull
    @Column(name = COLUMN_PERMISSION_ID)
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
