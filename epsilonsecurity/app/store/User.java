package store;

import models.UserId;
import models.UserIdable;
import models.databaseModel.scheduling.DbUser;

import javax.annotation.Nonnull;

public class User implements UserIdable {
    private UserId userId;
    private String contactEmail;
    private String sfuEmail;
    private String phoneNumber;
    private String photoUrl;

    protected User(DbUser dbUser) {
        this.userId = new UserId(dbUser.getUserId());
        this.contactEmail = dbUser.getContactEmail();
        this.sfuEmail = dbUser.getSfuEmail();
        this.phoneNumber = dbUser.getPhoneNumber();
        this.photoUrl = dbUser.getPhotoURL();
    }

    @Nonnull
    @Override
    public UserId getUserId() {
        return userId;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getSfuEmail() {
        return sfuEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
