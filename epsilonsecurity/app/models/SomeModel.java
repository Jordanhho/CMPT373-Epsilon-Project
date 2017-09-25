package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;

@Entity
public class SomeModel extends Model {

    @Id
    public Integer id;
    public Integer roleId;
    public String contactEmail;

    public SomeModel(Integer id, Integer roleId, String contactEmail) {
        this.id = id;
        this.roleId = roleId;
        this.contactEmail = contactEmail;
    }

    public SomeModel() {
        this.id = 1;
        this.roleId = 2;
        this.contactEmail = "@sfu.ca";
    }

    public static Finder<Integer, SomeModel> find = new Finder<>(SomeModel.class);
}
