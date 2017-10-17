package store;

import models.UserIdable;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;

import java.util.Optional;

public class UserStore {

    public Optional<User> getUserWithUserId(UserIdable userId) {
        DbUser dbUser = DbUserHelper.readDbUserById(userId.getUserId().getRawId());
        return Optional.ofNullable(new User(dbUser));
    }

}
