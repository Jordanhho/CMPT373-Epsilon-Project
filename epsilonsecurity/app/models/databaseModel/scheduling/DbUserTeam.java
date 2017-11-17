package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DbUserTeam extends Model {

    public static final String COLUMN_TEAM_ID = "team_id";
    public static final String COLUMN_USER_ID = "user_id";

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer teamId;

    @Column(nullable = false)
    private Integer userId;

    public DbUserTeam(Integer teamId, Integer userId) {
        this.teamId = teamId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static Finder<Integer, DbUserTeam> find = new Finder<>(DbUserTeam.class);

    @Override
    public String toString() {
        return "DbUserTeam{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", userId=" + userId +
                '}';
    }
}
