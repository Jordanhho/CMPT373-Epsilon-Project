package models;
import models.UserId;

import javax.annotation.Nonnull;

public interface UserIdable {
    @Nonnull public UserId getUserId();
}
