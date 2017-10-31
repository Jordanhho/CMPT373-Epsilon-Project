package models.databaseModel.scheduling;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Java Object for DbShiftQualification Table,
 */
@Entity
public class DbUserQualification extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Integer qualificationId;


    public DbUserQualification(Integer userId, Integer qualificationId) {
        this.userId = userId;
        this.qualificationId = qualificationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public static Finder<Integer, DbUserQualification> find = new Finder<>(DbUserQualification.class);

    @Override
    public String toString() {
        return "DbUserQualification{" +
                "id=" + id +
                ", userId=" + userId +
                ", qualificationId=" + qualificationId +
                '}';
    }
}