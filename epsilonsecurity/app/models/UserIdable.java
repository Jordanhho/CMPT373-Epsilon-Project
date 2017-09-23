package models;

import models.id.UserId;

import javax.annotation.Nonnull;

public interface UserIdable {
    @Nonnull public UserId getUserId();
}