package models.queries;

import java.time.*;

/**
 * Utility class for time conversion between database and backend
 */
public class TimeUtil {

    /**
     * Takes a user input from front end, and converts it to epochseconds Long type for database storage
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return
     */
    public static Long getEpochSecondsFromUserInput(int year, int month, int day, int hour, int minute) {
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        return instant.getEpochSecond();
    }


    /**
     * Takes a database's epoch seconds, and converts it to a LocalDateTime to be used by front end
     * @param epochSeconds
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromDataBase(Long epochSeconds) {
        Instant instant = Instant.ofEpochSecond(epochSeconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return localDateTime;
    }

    /**
     * Takes a database's epoch seconds, and converts it to a ISO 8601 UTC timestamp to be used by frontend
     * @param epochSeconds time representation from the database
     * @return String containing the newly formatted timestamp
     */
    public static String convertEpochSecondsToIsoUtcTime(Long epochSeconds) {
        LocalDateTime localDateTime = getLocalDateTimeFromDataBase(epochSeconds);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return zonedDateTime.toString();
    }
}