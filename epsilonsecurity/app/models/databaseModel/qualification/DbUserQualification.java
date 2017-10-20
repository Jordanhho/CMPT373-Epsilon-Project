package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbUserQualification extends Model{
    @Id
    @GeneratedValue
    private int id;
    @Nonnull
    private int userId;
    @Nonnull
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
