package models.databaseModel.roles;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbRole table with DbRole id and name of role
 */
@Entity
public class DbRole extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    /**
     * The constructor for the role table
     * id  the id of the role
     *
     * @param name the name of the role
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
}
