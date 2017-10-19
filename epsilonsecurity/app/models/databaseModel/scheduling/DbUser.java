package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbUser Table with DbUser id, user id, role ID, contact email, sfu email, phoneNumber, photoURL
 */

@Entity
public class DbUser extends Model {

    @Id
    private Integer id;

    @Column(columnDefinition = "integer default -1")
    public Integer roleId = -1;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false, unique = true)
    private String sfuEmail;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String photoURL;

    public DbUser() {
        // Required empty constructor for FormFactory
    }

    public DbUser(String contactEmail,
                  String sfuEmail,
                  String phoneNumber,
                  String photoURL) {
        this.contactEmail = contactEmail;
        this.sfuEmail = sfuEmail;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSfuEmail() {
        return sfuEmail;
    }

    public void setSfuEmail(String sfuEmail) {
        this.sfuEmail = sfuEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public static Finder<Integer, DbUser> find = new Finder<>(DbUser.class);
}
