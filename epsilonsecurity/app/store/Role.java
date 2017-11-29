package store;

import models.databaseModel.roles.DbPermission;
import models.databaseModel.roles.DbRole;
import models.databaseModel.roles.DbRolePermission;

import javax.annotation.Nonnull;
import java.util.List;

public class Role {
    @Nonnull
    private RoleId roleId;
    @Nonnull
    private String name;
    @Nonnull
    private List<Permission> permissions;

    @Nonnull
    public Role(DbRole role, List<DbPermission> permissions, List<DbRolePermission> rolePermissions) {
        this.roleId = new RoleId(role.getId());
        this.name = role.getName();
        this.permissions = Permissions.wrap(permissions, rolePermissions);
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public List<Permission> getPermissions() {
        return permissions;
    }
}
