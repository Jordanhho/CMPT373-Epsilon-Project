package models.queries;

import models.databaseModel.scheduling.DbOneTimeAvailability;

/**
 * Java Object for generating a form filled with an array of DbOneTimeAvailabilities
 */
public class OneTimeAvailabilityArrayForm {
    private DbOneTimeAvailability[] dbOneTimeAvailabilities;

    public void setDbOneTimeAvailabilities(DbOneTimeAvailability[] dbOneTimeAvailabilities) {
        this.dbOneTimeAvailabilities = dbOneTimeAvailabilities;
    }

    public DbOneTimeAvailability[] getDbOneTimeAvailabilities() {
        return dbOneTimeAvailabilities;
    }
}
