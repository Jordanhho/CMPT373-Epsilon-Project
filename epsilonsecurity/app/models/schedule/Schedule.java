package models.schedule;

import models.UserId;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import org.javatuples.KeyValue;

public class Schedule {

    private Map<TimeBlock, Set<UserId>> userTimeBlocks = new HashMap<>();

    public Schedule() {
    }

    public void createTimeBlock(TimeBlock timeBlock) {
        // TODO:
        // precondition: this time block does not yet exist (check)
        userTimeBlocks.put(timeBlock, new HashSet<>());
    }

    public void assignUserToTimeBlock(UserId user, TimeBlock timeBlock) {
        // TODO:
        // precondition: check that shift exists
        Set<UserId> users = getUsersForTimeBlock(timeBlock);
        users.add(user);
        putUsersForTimeBlock(users, timeBlock);
    }

    public void removeUserFromTimeBlock(UserId user, TimeBlock timeBlock) {
        Set<UserId> users = getUsersForTimeBlock(timeBlock);
        users.remove(user);
        putUsersForTimeBlock(users, timeBlock);
    }

    public Set<TimeBlock> getTimeBlocks() {
        return userTimeBlocks.keySet();
    }

    public boolean removeTimeBlock(TimeBlock timeBlock) {
        Object result = userTimeBlocks.remove(timeBlock);
        return result != null;
    }

    public Set<Shift> getShifts() {
        Set<Shift> shifts = userTimeBlocks.entrySet()
            .stream()
            .map( x -> KeyValue.with(x.getKey(), x.getValue()))
            .flatMap( keyValue -> {
                return keyValue.getValue()
                    .stream()
                    .map( userId -> KeyValue.with(keyValue.getKey(), userId) );
            })
            .map( timeBlockUserId -> {
                TimeBlock timeBlock = timeBlockUserId.getKey();
                UserId userId = timeBlockUserId.getValue();
                return new Shift(userId, timeBlock, Optional.empty());
            })
            .collect(Collectors.toSet());

        return shifts;
    }

    private Set<UserId> getUsersForTimeBlock(TimeBlock timeBlock) {
        return userTimeBlocks.get(timeBlock);
    }

    private void putUsersForTimeBlock(Set<UserId> users, TimeBlock timeBlock) {
        userTimeBlocks.put(timeBlock, users);
    }


}
