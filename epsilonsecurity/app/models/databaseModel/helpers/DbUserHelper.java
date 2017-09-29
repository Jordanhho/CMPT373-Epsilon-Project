package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * CRUD operations for DbUser class
 */
public final class DbUserHelper {

    private DbUserHelper() {

    }

    public static void createDbUser(@Nonnull String contactEmail,
                                    @Nonnull String sfuEmail,
                                    @Nonnull String phoneNumber,
                                    @Nonnull  String photoURL) {
        DbUser dbUser = new DbUser(
                contactEmail,
                sfuEmail,
                phoneNumber,
                photoURL);
        dbUser.save();
    }


    public static void deleteDbUserById(@Nonnull Integer id) {
        DbUser dbUser = readDbUserById(id);
        dbUser.delete();
    }

    public static DbUser readDbUserById(@Nonnull Integer id) {
        DbUser dbUser = DbUser.find.byId(id);
        return dbUser;
    }

    public static DbUser readDbUserBySfuEmail(@Nonnull String sfuEmail){
        DbUser dbUser = DbUser.find.query().where().eq("sfu_email",
                sfuEmail).findOne();
        return dbUser;
    }

    public static DbUser readDbUserByContactEmail(@Nonnull String contactEmail){
        DbUser dbUser = DbUser.find.query().where().eq("contact_email",
                contactEmail).findOne();
        return dbUser;
    }

    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUsers = DbUser.find.all();
        return dbUsers;
    }
}
