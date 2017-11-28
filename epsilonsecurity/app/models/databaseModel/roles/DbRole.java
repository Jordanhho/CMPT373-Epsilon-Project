package models.databaseModel.roles;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbRole table with DbRole id and name just role
 */
@Entity
public class DbRole extends Model {

    public static final String COLUMN_NAME = "name";

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The constructor for the role table
     * id  the id just the role
     *
     * @param name the name just the role
     */
    public DbRole(String name) {
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

    public static Finder<Integer, DbRole> find = new Finder<>(DbRole.class);

    @Override
    public String toString() {
        return "DbRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
