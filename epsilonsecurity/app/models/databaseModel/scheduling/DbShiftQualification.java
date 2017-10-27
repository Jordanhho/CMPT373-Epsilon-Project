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
public class DbShiftQualification extends Model {

    @Id
    private Integer id;

    @Column(nullable = false)
    private Integer shiftTypeId;

    @Column(nullable = false)
    private Integer qualificationId;


    public DbShiftQualification(Integer shiftTypeId, Integer qualificationId) {
        this.shiftTypeId= shiftTypeId;
        this.qualificationId = qualificationId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShiftTypeId() {
        return shiftTypeId;
    }

    public void setShiftTypeId(Integer shiftTypeId) {
        this.shiftTypeId = shiftTypeId;
    }

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public static Finder<Integer, DbShiftQualification> find = new Finder<>(DbShiftQualification.class);
}