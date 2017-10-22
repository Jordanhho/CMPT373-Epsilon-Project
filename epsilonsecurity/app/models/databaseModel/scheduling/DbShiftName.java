package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DbShiftName extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Nonnull
    private String colorCode;

    public DbShiftName(@Nonnull String name, @Nonnull String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
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

    @Nonnull
    public String getColorCode() {
        return colorCode;
    }

    public static Finder<Integer, DbShiftName> find = new Finder<>(DbShiftName.class);
    public void setName(String name) {
        this.name = name;
    }

    public static Finder<Integer, DbShiftName> find = new Finder<>(DbShiftName.class);
}
