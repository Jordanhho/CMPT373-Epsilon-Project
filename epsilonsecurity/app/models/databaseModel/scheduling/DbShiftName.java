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

    public DbShiftName(String name) {
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

    public static void setFind(Finder<Integer, DbShiftName> find) {
        DbShiftName.find = find;
    }

    @Override
    public String toString() {
        return "DbShiftName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
