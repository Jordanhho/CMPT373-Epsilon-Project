package models.roles;

import models.id.PermissionId;

import javax.annotation.Nonnull;

public class Permission {
    @Nonnull private PermissionId id;
    @Nonnull private String name;

    public Permission(@Nonnull PermissionId id, @Nonnull String name) {
        this.id = id;
        this.name = name;
    }

    @Nonnull
    public PermissionId getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }
}
