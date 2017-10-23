package database;

import models.databaseModel.helpers.DbUserHelper;
import models.databaseModel.scheduling.DbUser;
import org.junit.Assert;
import org.junit.Test;

public class DbUserHelperTest extends BaseDatabaseTest {

    private String contactEmail = "contact.email@sfu.ca";
    private String sfuEmail = "sfu.email@sfu.ca";
    private String phoneNumber = "60412234567";
    private String photoUrl = "http.cat/404";

    @Test
    public void createUserTest() {
        DbUserHelper.createDbUser(contactEmail, sfuEmail, phoneNumber, photoUrl);
        DbUser user = new DbUser(contactEmail, sfuEmail, phoneNumber, photoUrl);
        Assert.assertEquals(DbUserHelper.readAllDbUsers().size(), 1);
    }

}
