package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {DbShiftName.COLUMN_NAME}
        )
)

@Entity
public class DbShiftName extends Model {

    public static final String COLUMN_NAME = "name";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private String name;

    public DbShiftName(@Nonnull String name) {
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

    public static Finder<Integer, DbShiftName> find = new Finder<>(DbShiftName.class);

}
