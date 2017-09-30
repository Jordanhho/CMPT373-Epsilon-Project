package models.databaseModel.scheduling;

import io.ebean.Finder;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbUserTeam {

    @Id
    @GeneratedValue
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer teamId;

    @Nonnull
    private Integer userId;

    public DbUserTeam(@Nonnull Integer teamId, Integer userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getTeamId() {
        return teamId;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DbUserShift{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", userId=" + userId +
                '}';
    }

    public static Finder<Integer, DbUserTeam> find = new Finder<>(DbUserTeam.class);
}
