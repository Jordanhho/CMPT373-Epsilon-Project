package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;

import java.util.List;

public final class DbUserHelper {

    private DbUserHelper() {

    }

    public static void createDbUser(String contactEmail, String sfuEmail,
                                    String phoneNumber, String photoURL) {
        DbUser dbUser = new DbUser(
                contactEmail,
                sfuEmail,
                phoneNumber,
                photoURL);
        dbUser.save();
    }

    public static void deleteDbUserById(Integer id) {
        DbUser dbUser = readDbUserById(id);
        dbUser.delete();
    }

    public static DbUser readDbUserById(Integer id) {
        DbUser dbUser = DbUser.find.byId(id);
        return dbUser;
    }

    public static DbUser readDbUserBySfuEmail(String sfuEmail){
        DbUser dbUser = DbUser.find.query().where().eq(DbUser.COLUMN_SFU_EMAIL,
                sfuEmail).findOne();
        return dbUser;
    }

    public static DbUser readDbUserByContactEmail(String contactEmail){
        DbUser dbUser = DbUser.find.query().where().eq(DbUser.COLUMN_CONTACT_EMAIL,
                contactEmail).findOne();
        return dbUser;
    }

    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUsers = DbUser.find.all();
        return dbUsers;
    }
}
