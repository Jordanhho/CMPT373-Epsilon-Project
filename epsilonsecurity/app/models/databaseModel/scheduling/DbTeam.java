package models.databaseModel.scheduling;

import io.ebean.Finder;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbTeam {

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
