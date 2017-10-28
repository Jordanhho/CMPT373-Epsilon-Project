package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DbTeam extends Model {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    public DbTeam() {
        // Required empty constructor for FormFactory
    }

    public DbTeam(String name) {
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

    public static Finder<Integer, DbTeam> find = new Finder<>(DbTeam.class);

    @Override
    public String toString() {
        return "DbTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
