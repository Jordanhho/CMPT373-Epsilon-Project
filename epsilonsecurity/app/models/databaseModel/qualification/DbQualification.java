package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbQualification extends Model {

    public static final String COLUMN_QUALIFICATION_NAME = "qualification_name";

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false, unique = true)
    private String qualificationName;

    public DbQualification(String qualificationName){
        this.qualificationName = qualificationName;
    }

    public int getId() {
        return id;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    @Override
    public String toString(){
        return "DbQualification{"
                + "id= " + id +
                ", qualificationName= " + qualificationName +
                "}";
    }

    public static Finder<Integer, DbQualification> find = new Finder<>(DbQualification.class);
}
