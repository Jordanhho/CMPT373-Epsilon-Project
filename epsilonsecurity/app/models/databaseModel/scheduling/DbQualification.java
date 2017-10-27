package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DbQualification extends Model {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    public DbQualification() {
        // Required empty constructor for FormFactory
    }

    public DbQualification(String name) {
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

    public static Finder<Integer, DbQualification> find = new Finder<>(DbQualification.class);
}
