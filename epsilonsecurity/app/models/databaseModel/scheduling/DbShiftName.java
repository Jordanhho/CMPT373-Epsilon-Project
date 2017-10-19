package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
public class DbShiftName extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public DbShiftName(@Nonnull String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Finder<Integer, DbShiftName> find = new Finder<>(DbShiftName.class);
}
