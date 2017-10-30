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

    public static void deleteDbUser(DbUser dbUser) {
        dbUser.delete();
    }

    public static DbUser readDbUserById(Integer id) {
        DbUser dbUser = new QDbUser()
                .id
                .eq(id)
                .findUnique();

        return dbUser;
    }

    public static DbUser readDbUserByRoleId(Integer roleId) {
        DbUser dbUser = new QDbUser()
                .roleId
                .eq(roleId)
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

    public static void updateUserEnable(Integer userId, Boolean enabled) {
        DbUser dbUser = readDbUserById(userId);
        dbUser.setEnabled(enabled);
        dbUser.update();
    }

    //TODO: Fix magic number
    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUserList = new QDbUser()
                .findList();

        dbUserList.removeIf(dbUser -> dbUser.getRoleId() == 1);

        return dbUserList;
    }

}
