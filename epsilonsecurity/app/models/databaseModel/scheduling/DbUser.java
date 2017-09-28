package models.databaseModel.scheduling;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbUser Table with DbUser id, user id, role ID, contact email, sfu email, phonenumber, photoURL
 */
@Entity
public class DbUser extends Model {

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer roleId;
    @Nonnull
    private String contactEmail;
    @Nonnull
    private String sfuEmail;
    @Nonnull
    private String phoneNumber;
    @Nonnull
    private String photoURL;



    /**
     * The Constructor for DbUser
     * <p>
     * id           the user Id
     * roleId       the role Id of the user
     *
     * @param contactEmail the contact Email of the User, Note: If the user selects their SFU email as their contact email, it should be the same value as sfu email
     * @param sfuEmail     the sfu Email of the user
     * @param phoneNumber  the phone number of the user
     * @param photoURL     the url to the photo of that user
     */

    public DbUser(@Nonnull String contactEmail, @Nonnull String sfuEmail,
                  @Nonnull String phoneNumber, @Nonnull String photoURL) {
        this.roleId = -1;
        this.contactEmail = contactEmail;
        this.sfuEmail = sfuEmail;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
    }

    @Nonnull
    public Integer getUserId() {
        return id;
    }

    @Nonnull
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(@Nonnull Integer roleId) {
        this.roleId = roleId;
    }

    @Nonnull
    public String getContactEmail() {
        return contactEmail;
    }

    @Nonnull
    public String getSfuEmail() {
        return sfuEmail;
    }

    @Nonnull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Nonnull
    public String getPhotoURL() {
        return photoURL;
    }

    @Override
    public String toString() {
        return "DbUser{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", contactEmail='" + contactEmail + '\'' +
                ", sfuEmail='" + sfuEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }

    public static Finder<Integer, DbUser> find = new Finder<>(DbUser.class);
}
