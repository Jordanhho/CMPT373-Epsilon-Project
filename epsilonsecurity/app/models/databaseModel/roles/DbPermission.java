package models.databaseModel.roles;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Java Object for DbPermission Table with permission Id and name just position
 */
@Entity
public class DbPermission extends Model {

    public static final String COLUMN_NAME = "name";

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

<<<<<<< HEAD
    /**
     * Constructor for Permission table
     * Id  the id just the permission
     *
     * @param name the name just this permission
     */
=======
>>>>>>> master
    public DbPermission(String name) {
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

    public static Finder<Integer, DbPermission> find = new Finder<>(DbPermission.class);

    @Override
    public String toString() {
        return "DbPermission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
