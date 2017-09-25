package models.scheduling;

import models.UserIdable;
import models.id.RoleId;
import models.id.UserId;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;


import javax.annotation.Nonnull;



@Entity
public class User extends Model implements UserIdable {
    @Id
    @Nonnull private UserId id;
    @Nonnull private RoleId roleId;
    @Nonnull private String contactEmail;
    @Nonnull private String sfuEmail;
    @Nonnull private String phoneNumer;
    @Nonnull private String photoURL;

    public User(@Nonnull UserId id, @Nonnull RoleId roleId, @Nonnull String contactEmail, @Nonnull String sfuEmail, @Nonnull String phoneNumer, @Nonnull String photoURL) {
        this.id = id;
        this.roleId = roleId;
        this.contactEmail = contactEmail;
        this.sfuEmail = sfuEmail;
        this.phoneNumer = phoneNumer;
        this.photoURL = photoURL;
    }

    @Nonnull
    @Override
    public UserId getUserId() { return id; }

    @Nonnull
    public RoleId getRoleId() {
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
    public String getPhoneNumer() {
        return phoneNumer;
    }

    @Nonnull
    public String getPhotoURL() {
        return photoURL;
    }

    public static Finder<Integer, User> find = new Finder<>(User.class);
}
