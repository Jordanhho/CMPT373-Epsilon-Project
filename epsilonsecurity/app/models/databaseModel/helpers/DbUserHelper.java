package models.databaseModel.helpers;


import models.databaseModel.scheduling.DbUser;

import java.util.List;

public final class DbUserHelper {

    private DbUserHelper() {

    }

    /**
     * creates a DbUser from contactEmail, sfuEmail, phoneNumber, photoURL
     * @param contactEmail
     * @param sfuEmail
     * @param phoneNumber
     * @param photoURL
     */
    public static void createDbUser(String contactEmail, String sfuEmail,
                                    String phoneNumber, String photoURL) {
        DbUser dbUser = new DbUser(
                contactEmail,
                sfuEmail,
                phoneNumber,
                photoURL);
        dbUser.save();
    }

    /**
     * Deletes a DbUser by DbUserId
     * @param id
     */
    public static void deleteDbUserById(Integer id) {
        DbUser dbUser = readDbUserById(id);
        dbUser.delete();
    }

    /**
     * Finds a DbUser by  DbUserId
     * @param id
     * @return
     */
    public static DbUser readDbUserById(Integer id) {
        DbUser dbUser = DbUser.find.byId(id);
        return dbUser;
    }

    /**
     * Finds a DbUser by sfuEmail
     * @param sfuEmail
     * @return
     */
    public static DbUser readDbUserBySfuEmail(String sfuEmail) {
        DbUser dbUser = DbUser.find
                .query()
                .where()
                .eq(DbUser.COLUMN_SFU_EMAIL, sfuEmail)
                .findOne();

        return dbUser;
    }

    /**
     * Finds a DbUser by ContactEmail
     * @param contactEmail
     * @return
     */
    public static DbUser readDbUserByContactEmail(String contactEmail) {
        DbUser dbUser = DbUser.find
                .query()
                .where()
                .eq(DbUser.COLUMN_CONTACT_EMAIL, contactEmail)
                .findOne();

        return dbUser;
    }

    /**
     * Returns a list of all DbUser
     * @return
     */
    public static List<DbUser> readAllDbUsers() {
        List<DbUser> dbUsers = DbUser.find.all();
        return dbUsers;
    }


}
