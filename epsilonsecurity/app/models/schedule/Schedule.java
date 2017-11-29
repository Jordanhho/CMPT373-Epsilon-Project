package models.schedule;

import store.UserId;

import java.util.*;
import java.util.stream.Collectors;

import store.UserIdable;
import org.javatuples.KeyValue;

public class Schedule {

    private Map<TimeBlock, Set<UserIdable>> userTimeBlocks = new HashMap<>();

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
        Set<UserIdable> users = getUsersForTimeBlock(timeBlock);
        users.add(user);
        putUsersForTimeBlock(users, timeBlock);
    }

    public void removeUserFromTimeBlock(UserId user, TimeBlock timeBlock) {
        Set<UserIdable> users = getUsersForTimeBlock(timeBlock);
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
//        Set<Shift> shifts = userTimeBlocks.entrySet()
//            .stream()
//            .map( x -> KeyValue.with(x.getKey(), x.getValue()))
//            .flatMap( keyValue -> {
//                return keyValue.getValue()
//                    .stream()
//                    .map( userId -> KeyValue.with(keyValue.getKey(), userId) );
//            })
//            .map( timeBlockUserId -> {
//                TimeBlock timeBlock = timeBlockUserId.getKey();
//                UserIdable user = timeBlockUserId.getValue();
//                return new Shift()
//                return new Shift(user.getUserId(), timeBlock, Optional.empty());
//            })
//            .collect(Collectors.toSet());
//
//        return shifts;
        // TODO: bring me back
        return null;
    }

    private Set<UserIdable> getUsersForTimeBlock(TimeBlock timeBlock) {
        return userTimeBlocks.get(timeBlock);
    }

    private void putUsersForTimeBlock(Set<UserIdable> users, TimeBlock timeBlock) {
        userTimeBlocks.put(timeBlock, users);
    }


}
