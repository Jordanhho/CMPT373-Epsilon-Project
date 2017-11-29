package store;

import javax.annotation.Nonnull;
import java.util.List;

public class PermissionedUser implements UserIdable {
    private User user;
    private Role role;

    public PermissionedUser(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Nonnull
    @Override
    public UserId getUserId() {
        return user.getUserId();
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}
