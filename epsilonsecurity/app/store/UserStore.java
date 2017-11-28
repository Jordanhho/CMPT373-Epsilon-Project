package store;

import models.databaseModel.helpers.DbUserShiftHelper;
import models.databaseModel.helpers.DbUserTeamHelper;
import models.databaseModel.scheduling.DbUserShift;
import store.TeamIdable;
import store.UserIdable;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import models.queries.ScheduleUtil;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static store.StoreException.Reason.EXISTS;
import static store.StoreException.Reason.ID_NOT_FOUND;
import static store.StoreException.Table.USER;

public class UserStore extends DataStore {

    private static UserStore instance = new UserStore();

    public static UserStore getInstance() {
        return instance;
    }

    public CompletableFuture<User> getUserWithUserId(UserIdable userId) {
        return runAsync(() -> {
            DbUser dbUser = DbUserHelper.readDbUserById(userId.getUserId().getRawId());
            if (dbUser == null) {
                throw new StoreException(USER, ID_NOT_FOUND, null);
            }
            return new User(dbUser);
        });
    }

    public CompletableFuture<User> getUserForShift(ShiftIdable shiftId) {
        return runAsync(() -> {
            User firstUser = DbUserShiftHelper.readDbUserShiftByShiftId(shiftId.getShiftId().rawId)
                .stream()
                .map(userShift -> DbUserTeamHelper.readDbUserTeamById(userShift.getUserTeamId()))
                .map(userTeam -> DbUserHelper.readDbUserById(userTeam.getUserId()))
                .map(User::new)
                .collect(Collectors.toList())
                .get(0);
            // TODO: check in on this; are we supposed to be getting multiple users in a single shift?
            return firstUser;
        });
    }

}
