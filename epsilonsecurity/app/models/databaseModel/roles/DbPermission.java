package models.databaseModel.roles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;


/**
 * Java Object for DbPermission Table with permission Id and name of position
 */
@Entity
public class DbPermission extends Model {

    private static final String COLUMN_NAME = "name";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private String name;

    /**
     * Constructor for Permission table
     * Id  the id of the permission
     *
     * @param name the name of this permission
     */
    public DbPermission(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_NAME)
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DbPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Finder<Integer, DbPermission> find = new Finder<>(DbPermission.class);
}
