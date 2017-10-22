package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbShiftQualification extends Model{
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int qualificationId;
    @Column(nullable = false)
    private int shiftNameId;

    public DbShiftQualification(int qualificationId, int shiftNameId) {
        this.qualificationId = qualificationId;
        this.shiftNameId = shiftNameId;
    }

    public int getId() {
        return id;
    }

    public int getQualificationId() {
        return qualificationId;
    }

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
