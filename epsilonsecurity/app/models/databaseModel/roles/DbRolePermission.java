package models.databaseModel.roles;

import javax.persistence.*;

import io.ebean.*;
import models.databaseModel.scheduling.Level;

import javax.annotation.Nonnull;

/**
 * Java Object for DbRolePermission Table with DbRolePermission Id and name of position
 */
@Entity
public class DbRolePermission extends Model {

    private static final String COLUMN_TEAM_ID = "team_id";
    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_PERMISSION_ID = "permission_id";
    private static final String COLUMN_LEVEL = "level";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer teamId;

    @Nonnull
    private Integer roleId;

    @Nonnull
    private int permissionId;

    @Enumerated(EnumType.STRING)
    @Nonnull
    private Level level;

    /**
     * The constructor of the RolePermission table
     * Id  the id of the RolePermission
     *
     * @param teamId       the team id of the RolePermission
     * @param roleId       the role Id of the RolePermission
     * @param permissionId the permission Id of the RolePermission
     * @param level        the permission level of the RolePermission
     */
    public DbRolePermission(@Nonnull Integer teamId, @Nonnull Integer roleId, @Nonnull Integer permissionId,
                            @Nonnull Level level) {
        this.teamId = teamId;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.level = level;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_TEAM_ID)
    public Integer getTeamId() {
        return teamId;
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

    @Nonnull
    @Column(name = COLUMN_LEVEL)
    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "DbRolePermission{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", level=" + level +
                '}';
    }

    public static Finder<Integer, DbRolePermission> find = new Finder<>(DbRolePermission.class);

}
