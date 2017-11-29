package store;

import auth.dsl.AuthDSL;
import models.databaseModel.roles.AccessLevel;
import models.databaseModel.roles.DbPermission;
import models.databaseModel.roles.DbRolePermission;

import java.util.*;
import java.util.stream.Collectors;

public class Permissions {

    private final static String encodingDelimiter = AuthDSL.IN;

    public static boolean userDoesHaveEncodedPermissions(PermissionedUser user, List<String> encodedPermissions) {
        List<Permission> userPermissionsList = user.getRole().getPermissions();
        Map<String, Permission> userPermissions = new HashMap<>(userPermissionsList.size());
        userPermissionsList .stream()
            .forEach(permission -> userPermissions.put(permission.getName(), permission));
        for (String encodedPermission : encodedPermissions) {
            Optional<String> encodedPermissionName = permissionNameForEncodedPermission(encodedPermission);
            Optional<AccessLevel> encodedPermissionAccessLevel = accessLevelForEncodedPermission(encodedPermission);
            // could not decode permission; play it safe
            if (encodedPermissionName.isPresent() == false) {
                return false;
            }
            // could not decode access level; play it safe
            if (encodedPermissionAccessLevel.isPresent() == false) {
                return false;
            }
            AccessLevel accessLevelToMeet = encodedPermissionAccessLevel.get();
            String permissionName = encodedPermissionName.get();
            if (userPermissions.containsKey(permissionName) == false) {
                return false;
            }
            Permission userPermission = userPermissions.get(permissionName);
            // non-null permission; check if access level matches or exceeds
            if (isAccessLevelMet(userPermission.getAccessLevel(), accessLevelToMeet)) {
                continue;
            } else {
                System.out.println("User does not have permission to "
                                           + accessLevelToMeet.toString()
                                           + " in "
                                           + userPermission.getName()
                                           + " because they can only " + userPermission.getAccessLevel());
                // a single mismatch is an early return
                return false;
            }
        }
        // if you made it this far, you're golden
        return true;
    }

    /**
     * convenience-initializer to create AuthenticatedAction permissions
     *
     * @param accessLevel must be met or exceeded
     * @param permission what the specific permission is
     *
     * @return String representation. Disregard this; it is an implementation
     * detail.
     */
    public strictfp static String must(AccessLevel accessLevel, String permission) {
        return accessLevel.toString() + encodingDelimiter + permission;
    }


    protected static List<Permission> wrap(List<DbPermission> dbPermissions, List<DbRolePermission> dbRolePermissions) {
        // We use a map so that ordering does not have to be guaranteed on input
        // this also provides the convenience that the output is ordered the same way
        // as the input
        Map<Integer, DbRolePermission> rolePermissionMap = new HashMap<>(dbRolePermissions.size());
        dbRolePermissions.forEach(rolePermission -> rolePermissionMap.put(rolePermission.getPermissionId(), rolePermission));
        List<Permission> permissions = dbPermissions.stream()
                .map(permission -> {
                    if (rolePermissionMap.containsKey(permission.getId()) == false) {
                        return new Permission(permission, AccessLevel.NONE);
                    }
                    AccessLevel accessLevel = rolePermissionMap.get(permission.getId()).getAccessLevel();
                    return new Permission(permission, accessLevel);
                })
                .collect(Collectors.toList());
        return permissions;
    }

    private static boolean isAccessLevelMet(AccessLevel candidate, AccessLevel toMatch) {
        boolean isAccessLevelMet = accessLevelValue(candidate) >= accessLevelValue(toMatch);
        return isAccessLevelMet;
    }

    private static Optional<AccessLevel> accessLevelForEncodedPermission(String encoded) {
        String[] splitEncoding = encoded.split(encodingDelimiter);
        // this is a pretty critical error though
        if (splitEncoding.length != 2) { return Optional.empty(); }
        AccessLevel accessLevel = AccessLevel.valueOf(splitEncoding[0]);
        return Optional.of(accessLevel);
    }

    private static Optional<String> permissionNameForEncodedPermission(String encoded) {
        List<String> splitEncoding = Arrays.asList(encoded.split(encodingDelimiter));
        // pretty bad if we don't have both
        if (splitEncoding.size() != 2) { return Optional.empty(); }
        return Optional.of(splitEncoding.get(1));
    }


    private static Integer accessLevelValue(AccessLevel accessLevel) {
        switch (accessLevel) {
            case NONE:
                return 0;
            case READ:
                return 1;
            case WRITE:
                return 2;
            case DELETE:
                return 3;
            default:
                // why doesn't Java check for exhaustiveness?
                // idiotic language
                // I'd rather throw a IncompatibleClassChangeError exception
                // than have to force the reader to see this
                return -1;
        }
    }

}
