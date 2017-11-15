package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbUserQualification extends Model{

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_QUALIFICATION_ID = "qualification_id";

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int userId;
    @Column(nullable = false)
    private int qualificationId;

    public DbUserQualification(int userId, int qualificationId) {
        this.userId = userId;
        this.qualificationId = qualificationId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    @Override
    public String toString(){
        return "DbUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", qualificationId='" + qualificationId +
                '}';
    }

    public static Finder<Integer, DbUserQualification> find = new Finder<>(DbUserQualification.class);
}
