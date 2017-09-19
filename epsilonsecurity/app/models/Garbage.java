package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import io.ebean.*;

@Entity
public class Garbage extends Model {

    @Id
    public Integer key;
    public Integer number;
    public String string;

    public static Finder<Integer, Garbage> find = new Finder<>(Garbage.class);
}