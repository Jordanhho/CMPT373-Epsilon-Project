package models.databaseModel.qualification;

import io.ebean.Finder;
import io.ebean.Model;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DbQualification extends Model {
    @Id
    @GeneratedValue
    private int id;
    @Nonnull
    private String qualificationName;

    public DbQualification(String qualificationName){
        this.qualificationName = qualificationName;
    }

    @Nonnull
    public int getId() {
        return id;
    }

    @Nonnull
    public String getQualificationName() {
        return qualificationName;
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
