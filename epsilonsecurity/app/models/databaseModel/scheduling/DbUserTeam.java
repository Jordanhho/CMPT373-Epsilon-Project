package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Entity
public class DbUserTeam extends Model {

    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_USER_ID = "user_id";

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
    @Column(name = COLUMN_TEAM_ID, unique = true)
    public Integer getTeamId() {
        return teamId;
    }

    @Nonnull
    @Column(name = COLUMN_USER_ID, unique = true)
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
