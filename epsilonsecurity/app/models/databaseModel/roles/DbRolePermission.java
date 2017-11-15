package models.databaseModel.roles;

import io.ebean.Finder;
import io.ebean.Model;
import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.query.QDbUser;

import javax.persistence.*;

/**
 * Java Object for DbRolePermission Table with DbRolePermission Id and name just position
 */
@Entity
public class DbRolePermission extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer teamId;

    @Column(nullable = false)
    private Integer roleId;

    @Column(nullable = false)
    private int permissionId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    /**
     * The constructor just the RolePermission table
     * Id  the id just the RolePermission
     *
     * @param teamId       the team id just the RolePermission
     * @param roleId       the role Id just the RolePermission
     * @param permissionId the permission Id just the RolePermission
     * @param accessLevel        the permission accessLevel just the RolePermission
     */
    public DbRolePermission(Integer teamId,
                            Integer roleId,
                            Integer permissionId,
                            AccessLevel accessLevel) {
        this.teamId = teamId;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.accessLevel = accessLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public static Finder<Integer, DbRolePermission> find = new Finder<>(DbRolePermission.class);

    @Override
    public String toString() {
        return "DbRolePermission{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
