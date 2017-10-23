package database;

import io.ebean.Ebean;
import io.ebean.Transaction;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import play.Application;
import play.test.Helpers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static org.junit.Assert.fail;

public class BaseDatabaseTest {

    public static String createDdl = "";
    public static String dropDdl = "";

    public static Application app;

    @BeforeClass
    public static void startApp() throws IOException {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);

        // Reading the evolution file
        String evolutionContent = FileUtils.readFileToString(
                app.getWrappedApplication().getFile("conf/evolutions/test/1.sql"));

        String[] splittedEvolutionContent = evolutionContent.split("# --- !Ups");
        String[] upsDowns = splittedEvolutionContent[1].split("# --- !Downs");

        createDdl = upsDowns[0];
        dropDdl = upsDowns[1];
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void wipeDatabase() {
        dropTables();
        upTables();
    }

    private void dropTables() {
        Transaction transaction = Ebean.beginTransaction();
        Connection connection = transaction.getConnection();
        Arrays.asList(dropDdl.split("\n"))
                .stream()
                .filter(statement -> statement.trim().isEmpty() == false)
                .forEach( sql -> {
            Statement statement = null;
            try {
                connection.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                fail(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        Ebean.commitTransaction();
        Ebean.endTransaction();
    }

    private void upTables() {
        Transaction transaction = Ebean.beginTransaction();
        Connection connection = transaction.getConnection();
        Arrays.asList(createDdl.split("\n\n"))
                .stream()
                .filter(statement -> statement.trim().isEmpty() == false)
                .map(statement -> statement.replace('\n', ' '))
                .forEach( sql -> {
                    try {
                        connection.createStatement().executeUpdate(sql);
                    } catch (SQLException e) {
                        fail(e.getLocalizedMessage());
                        e.printStackTrace();
                    }
                });
        Ebean.commitTransaction();
        Ebean.endTransaction();
    }

}
