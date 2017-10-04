package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

import javax.annotation.Nonnull;

@Entity
public class DbTeam extends Model {

    public static final String COLUMN_NAME = "name";

    public static final String FORM_NAME = "name";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private String name;

    public DbTeam(@Nonnull String name) {
        this.name = name;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    @Column(name = COLUMN_NAME, unique = true)
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "DbTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Finder<Integer, DbTeam> find = new Finder<>(DbTeam.class);
}
