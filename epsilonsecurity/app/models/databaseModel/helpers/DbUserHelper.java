package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;
import org.junit.Assert;

import java.util.List;
import play.libs.Json;

public final class DbUserHelper {

    private DbUserHelper() {

    }

    public static void createDbUser(DbUser dbUser) {
        dbUser.save();
    }

    public static void deleteDbUser(DbUser dbUser) {
        dbUser.delete();
    }

    public static DbUser readDbUserById(Integer id) {
        return DbUser.find.byId(id);
    }

    public static List<DbUser> readAllDbUserByRoleId(Integer roleId) {
        List<DbUser> dbUserList = DbUser.find
                .query()
                .where()
                .eq(DbUser.COLUMN_ROLE_ID, roleId)
                .findList();

        return dbUserList;
    }

    public static DbUser readDbUserBySfuEmail(String sfuEmail) {
        DbUser dbUser = DbUser.find
                .query()
                .where()
                .eq(DbUser.COLUMN_SFU_EMAIL, sfuEmail)
                .findUnique();

        return dbUser;
    }

    public static DbUser readDbUserByContactEmail(String contactEmail) {
        DbUser dbUser = DbUser.find
                .query()
                .where()
                .eq(DbUser.COLUMN_CONTACT_EMAIL, contactEmail)
                .findUnique();

        return dbUser;
    }

    public static void updateUserEnable(Integer userId, Boolean enabled) {
        DbUser dbUser = readDbUserById(userId);
        dbUser.setEnabled(enabled);
        dbUser.update();
    }

    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUserList = DbUser.find.all();
        dbUserList.removeIf(dbUser -> dbUser.getRoleId() == 1);
        return dbUserList;
    }

    //cherry picked from @nick
    public static DbUser readDbUserByUsername(String username) {

        String sfuEmail = username + "@sfu.ca";
        System.out.println("dbhelper> will search for: " + sfuEmail);

        DbUser dbUser = readDbUserBySfuEmail(sfuEmail);
        System.out.println("dbhelper> found: " + Json.toJson(dbUser));


//        DbUser dummyUser = new DbUser(
//                "Uzziah",
//                "Eyee", "ueyee@sfu.ca",
//                "example@email.ca",
//                "(123)-654-7878",
//                "https://www.PhotoUrl.ca");

        return dbUser;
    }

}
