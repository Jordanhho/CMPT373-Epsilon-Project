package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbShiftQualification extends Model{
    @Id
    @GeneratedValue
    private int id;
    @Nonnull
    private int qualificationId;
    @Nonnull
    private int shiftNameId;

    public DbShiftQualification(int qualificationId, int shiftNameId) {
        this.qualificationId = qualificationId;
        this.shiftNameId = shiftNameId;
    }

    @Nonnull
    public int getId() {
        return id;
    }

    @Nonnull
    public int getQualificationId() {
        return qualificationId;
    }

    @Nonnull
    public int getShiftNameId() {
        return shiftNameId;
    }

    @Override
    public String toString(){
        return "DbShiftQualification{" +
                "id=" + id +
                ", shiftNameId=" + shiftNameId +
                ", qualificationId='" + qualificationId +
                '}';
    }

    public static Finder<Integer, DbShiftQualification> find = new Finder<>(DbShiftQualification.class);
}
