package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
public class DbShiftName extends Model {

    public static final String COLUMN_NAME = "name";

    public static final String FORM_NAME = "name";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private String name;

    @Nonnull
    private String colorCode;

    public DbShiftName(@Nonnull String name, @Nonnull String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
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

    @Nonnull
    public String getColorCode() {
        return colorCode;
    }

    public static Finder<Integer, DbShiftName> find = new Finder<>(DbShiftName.class);

}
