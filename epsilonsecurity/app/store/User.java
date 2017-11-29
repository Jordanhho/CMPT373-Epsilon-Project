package store;

import models.databaseModel.scheduling.DbUser;

import javax.annotation.Nonnull;

public class User
    implements UserIdable, RoleIdable {

    private UserId userId;
    private String contactEmail;
    private String sfuEmail;
    private String phoneNumber;
    private String photoUrl;
    private RoleId roleId;

    protected User(DbUser dbUser) {
        this.userId = new UserId(dbUser.getId());
        this.contactEmail = dbUser.getContactEmail();
        this.sfuEmail = dbUser.getSfuEmail();
        this.phoneNumber = dbUser.getPhoneNumber();
        this.photoUrl = dbUser.getPhotoURL();
        this.roleId = new RoleId(dbUser.getRoleId());
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

    public RoleId getRoleId() {
        return roleId;
    }
}
