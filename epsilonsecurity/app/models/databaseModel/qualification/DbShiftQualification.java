package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbShiftQualification extends Model{

    public static final String COLUMN_QUALIFICATION_ID = "qualification_id";
    public static final String COLUMN_SHIFT_TYPE_ID = "shift_type_id";

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private int qualificationId;
    @Column(nullable = false)
    private int shiftTypeId;

    public DbShiftQualification(int qualificationId, int shiftTypeId) {
        this.qualificationId = qualificationId;
        this.shiftTypeId = shiftTypeId;
    }

    public int getId() {
        return id;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public int getShiftTypeId() {
        return shiftTypeId;
    }

    @Override
    public String toString(){
        return "DbShiftQualification{" +
                "id=" + id +
                ", shiftTypeId=" + shiftTypeId +
                ", qualificationId='" + qualificationId +
                '}';
    }

    public static Finder<Integer, DbShiftQualification> find = new Finder<>(DbShiftQualification.class);
}
