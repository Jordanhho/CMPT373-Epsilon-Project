package store;

import models.UserIdable;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;

import java.util.concurrent.Future;

import static store.StoreException.Reason.ID_NOT_FOUND;
import static store.StoreException.Table.USER;

public class UserStore {

    public static Future<User> getUserWithUserId(UserIdable userId) {
        return StoreUtils.runAsync(() -> {
           DbUser dbUser = DbUserHelper.readDbUserById(userId.getUserId().getRawId());
            if (dbUser == null) {
                throw new StoreException(USER, ID_NOT_FOUND, null);
            }
            return new User(dbUser);
        });
    }



}
