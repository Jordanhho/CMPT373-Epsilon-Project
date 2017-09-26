package models.scheduling;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.ebean.*;


import javax.annotation.Nonnull;


@Entity
public class User extends Model {

    @Id
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

    public User(@Nonnull Integer id, @Nonnull Integer roleId, @Nonnull String contactEmail, @Nonnull String sfuEmail,
                @Nonnull String phoneNumber, @Nonnull String photoURL) {
        this.id = id;
        this.roleId = roleId;
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

    public static Finder<Integer, User> find = new Finder<>(User.class);
}
