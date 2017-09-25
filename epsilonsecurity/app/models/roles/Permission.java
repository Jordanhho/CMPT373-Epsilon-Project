package models.roles;

import models.id.PermissionId;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;

@Entity
public class Permission extends Model {

    @Id
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

    public static Finder<Integer, Permission> find = new Finder<>(Permission.class);
}
