package store;

import models.TeamIdable;
import models.UserIdable;
import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import models.queries.ScheduleUtil;
import org.sqlite.core.DB;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static store.StoreException.Reason.ID_NOT_FOUND;
import static store.StoreException.Table.USER;

public class UserStore extends DataStore {

    private static UserStore instance = new UserStore();

    public static UserStore getInstance() {
        return instance;
    }

    public Future<User> getUserWithUserId(UserIdable userId) {
        return runAsync(() -> {
           DbUser dbUser = DbUserHelper.readDbUserById(userId.getUserId().getRawId());
            if (dbUser == null) {
                throw new StoreException(USER, ID_NOT_FOUND, null);
            }
            return new User(dbUser);
        });
    }

    public Future<List<User>> getUsersWithAvailabilityInTeam(TeamIdable teamId, Instant start, Instant end) {
        return runAsync(() -> {
            // TODO: update this once we convert to using Longs
            Integer startInt = Long.valueOf(start.getEpochSecond()).intValue();
            Integer endInt = Long.valueOf(end.getEpochSecond()).intValue();
            List<DbUser> users = ScheduleUtil.queryUsersBasedOnAvailiability(teamId.getTeamId().getRawId(),
                                                                            startInt,
                                                                            endInt);
            return users.stream()
                .map(User::new)
                .collect(Collectors.toList());

        });
    }



}
