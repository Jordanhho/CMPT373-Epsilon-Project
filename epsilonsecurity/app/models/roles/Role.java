package models.roles;

import models.id.RoleId;

import javax.annotation.Nonnull;

public class Role {
    @Nonnull private RoleId id;
    @Nonnull private String name;

    public Role(@Nonnull RoleId id, @Nonnull String name) {
        this.id = id;
        this.name = name;
    }

    @Nonnull
    public RoleId getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }
}
