package models.databaseModel.roles;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Java Object for DbPermission Table with permission Id and name of position
 */
@Entity
public class DbPermission extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    /**
     * Constructor for Permission table
     * Id  the id of the permission
     *
     * @param name the name of this permission
     */
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
}
