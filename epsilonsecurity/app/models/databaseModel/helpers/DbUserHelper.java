package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;
import models.databaseModel.scheduling.query.QDbUser;

import java.util.List;

public final class DbUserHelper {

    private DbUserHelper() {

    }

    public static void createDbUser(DbUser dbUser) {
        dbUser.save();
    }

    public static void deleteDbUserBySfuEmail(DbUser dbUser) {
        dbUser.delete();
    }

    public static DbUser readDbUserById(Integer id) {
        DbUser dbUser = new QDbUser()
                .id
                .eq(id)
                .findUnique();

        return dbUser;
    }

    public static DbUser readDbUserBySfuEmail(String sfuEmail) {
        DbUser dbUser = new QDbUser()
                .sfuEmail
                .eq(sfuEmail)
                .findUnique();

        return dbUser;
    }

    public static DbUser readDbUserByContactEmail(String contactEmail) {
        DbUser dbUser = new QDbUser()
                .contactEmail
                .eq(contactEmail)
                .findUnique();

        return dbUser;
    }

    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUsers = new QDbUser()
                .findList();

        return dbUsers;
    }
}
