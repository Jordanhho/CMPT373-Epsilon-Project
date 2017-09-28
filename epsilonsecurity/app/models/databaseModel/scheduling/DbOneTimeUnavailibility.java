package models.databaseModel.scheduling;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

import javax.annotation.Nonnull;

/**
 * Java Object for DbOneTimeUnavailibility Table with unavailbility event id, user id and time block -> start and finish time of event
 */
@Entity
public class DbOneTimeUnavailibility extends Model {

    @Id
    @Nonnull
    private Integer id;

    @Nonnull
    private Integer userId;

    @Nonnull
    private Integer timeStart;

    @Nonnull
    private Integer timeEnd;

    /**
     * The constructor for the OneTimeUnavailibility
     * @param id the id of this OneTimeUnavailibility
     * @param userId the user id of this OneTimeUnavailibility
     * @param timeStart the time start of this OneTimeUnavailibility
     * @param timeEnd the time end of this OneTimeUnavailibility
     */
    public DbOneTimeUnavailibility(@Nonnull Integer id, @Nonnull Integer userId, @Nonnull Integer timeStart, @Nonnull Integer timeEnd) {
        this.id = id;
        this.userId = userId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    @Nonnull
    public Integer getId() {
        return id;
    }

    @Nonnull
    public Integer getUserId() {
        return userId;
    }

    @Nonnull
    public Integer getTimeStart() {
        return timeStart;
    }

    @Nonnull
    public Integer getTimeEnd() {
        return timeEnd;
    }

    @Override
    public String toString() {
        return "DbOneTimeUnavailibility{" +
                "id=" + id +
                ", userId=" + userId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }

    public static Finder<Integer, DbOneTimeUnavailibility> find = new Finder<>(DbOneTimeUnavailibility.class);

}
