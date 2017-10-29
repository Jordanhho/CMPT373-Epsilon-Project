package database;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import org.junit.Assert;
import org.junit.Test;

public class DbUserHelperTest extends BaseDatabaseTest {

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String contactEmail = "contact.email@sfu.ca";
    private String sfuEmail = "sfu.email@sfu.ca";
    private String phoneNumber = "60412234567";
    private String photoUrl = "http.cat/404";

    @Test
    public void createUserTest() {
        DbUser user = new DbUser(firstName, lastName, sfuEmail, contactEmail, phoneNumber, photoUrl);
        DbUserHelper.createDbUser(user);
        Assert.assertEquals(DbUserHelper.readAllDbUsers().size(), 1);
    }

}
