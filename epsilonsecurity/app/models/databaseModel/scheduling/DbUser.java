package models.databaseModel.scheduling;

import javax.persistence.*;

import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbUser Table with DbUser id, user id, role ID, contact email, sfu email, phoneNumber, photoURL
 */

@Entity
public class DbUser extends Model {

    public static final String COLUMN_ROLE_ID = "role_id";
    public static final String COLUMN_CONTACT_EMAIL = "contact_email";
    public static final String COLUMN_SFU_EMAIL = "sfu_email";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PHOTO_URL = "photo_url";

    public static final String FORM_ROLE_ID = "roleId";
    public static final String FORM_CONTACT_EMAIL = "contactEmail";
    public static final String FORM_SFU_EMAIL = "sfuEmail";
    public static final String FORM_PHONE_NUMBER = "phoneNumber";
    public static final String FORM_PHOTO_URL = "photoURL";

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;
    @Nonnull
    private Integer roleId;

    //TODO ADD IN NAME (FIRST NAME, MIDDLE NAME, LAST NAME, NICKNAME, GLHF)
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
     * @param contactEmail the contact Email of the User, Note: If the user selects their SFU email as their
     *                     contact email, it should be the same value as sfu email
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
    @Column(name = COLUMN_ROLE_ID)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(@Nonnull Integer roleId) {
        this.roleId = roleId;
    }

    @Nonnull
    @Column(name = COLUMN_CONTACT_EMAIL, unique = true)
    public String getContactEmail() {
        return contactEmail;
    }

    @Nonnull
    @Column(name = COLUMN_SFU_EMAIL, unique = true)
    public String getSfuEmail() {
        return sfuEmail;
    }

    @Nonnull
    @Column(name = COLUMN_PHONE_NUMBER, unique = true)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Nonnull
    @Column(name = COLUMN_PHOTO_URL)
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
