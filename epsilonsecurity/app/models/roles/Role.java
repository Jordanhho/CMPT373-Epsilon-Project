package models.roles;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

@Entity
public class Role extends Model {

    @Id
    @Nonnull
    private Integer id;
    @Nonnull
    private String name;

    public Role(@Nonnull Integer id, @Nonnull String name) {
        this.id = id;
        this.name = name;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public static Finder<Integer, Role> find = new Finder<>(Role.class);
}
