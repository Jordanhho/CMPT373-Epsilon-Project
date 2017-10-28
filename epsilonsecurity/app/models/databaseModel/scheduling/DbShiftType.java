package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DbShiftType extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String colorCode;

    public DbShiftType(String name, String colorCode) {
        this.name = name;
        this.colorCode = colorCode;
    }

    public DbShiftType(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColorCode(String colorCode){
        this.colorCode = colorCode;
    }

    public static Finder<Integer, DbShiftType> find = new Finder<>(DbShiftType.class);

    @Override
    public String toString() {
        return "DbShiftType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colorCode='" + colorCode + '\'' +
                '}';
    }
}
