package models.databaseModel.roles;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbRole table with DbRole id and name of role
 */
@Entity
public class DbRole extends Model {

    @Id
    @Nonnull
    private Integer id;

    @Nonnull
    private String name;

    /**
     * The constructor for the role table
     * @param id  the id of the role
     * @param name   the name of the role
     */
    public DbRole(@Nonnull Integer id, @Nonnull String name) {
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

    @Override
    public String toString() {
        return "DbRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Finder<Integer, DbRole> find = new Finder<>(DbRole.class);
}
