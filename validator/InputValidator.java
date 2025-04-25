package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating user inputs across the application.
 * This includes validation for NRIC format, date format/range, numeric input ranges,
 * availability constraints, and yes/no confirmation inputs.
 */
public class InputValidator {

    /**
     * Validates if the given NRIC string follows the standard Singapore NRIC format.
     * A valid NRIC starts with S, T, F, or G, followed by 7 digits, and ends with an uppercase letter.
     * @param nric The NRIC string to validate.
     * @return true if the NRIC is valid, false otherwise.
     */
    // Validate NRIC format
    public static boolean validateNric(String nric) {
        Pattern pattern = Pattern.compile("^[STFG]\\d{7}[A-Z]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nric);
        return matcher.find();
    }

    /**
     * Validates if a given date string matches a specified format.
     * @param dateStr The date string to validate.
     * @param format  The expected date format (e.g., "dd/mm/yyyy").
     * @return true if the date matches the format and is parsable, false otherwise.
     */
    // Validate date string against a specific format
    public static boolean validateDate(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates if an input string represents an integer within a given range.
     * @param input The string to check.
     * @param min   The minimum allowed value (inclusive), can be null to ignore.
     * @param max   The maximum allowed value (inclusive), can be null to ignore.
     * @return true if the input is a valid integer within range, false otherwise.
     */
    // Validate integer input within range
    public static boolean validateIntRange(String input, Integer min, Integer max) {
        try {
            Integer value = Integer.parseInt(input);
            if ((min != null && value < min) || (max != null && value > max)) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if a string represents a positive double value.
     * @param input The input string to validate.
     * @return true if the string is a positive double, false otherwise.
     */
    // Validate double input is positive
    public static boolean validatePositiveDouble(String input) {
        try {
            Double value = Double.parseDouble(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates that available units do not exceed the total units in a project.
     * @param available Number of available units.
     * @param total     Total number of units.
     * @return true if (available <= total), false otherwise.
     */
    // Validate available units not exceeding total units
    public static boolean validateAvailableUnits(int available, int total) {
        return available <= total;
    }

    /**
     * Validates if a user input is either 'Y' or 'N' (case insensitive).
     * @param input The user input string.
     * @return true if the input is "Y" or "N", false otherwise.
     */
    // Validate yes/no input
    public static boolean validateYesNo(String input) {
        return input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N");
    }

    /**
     * Validates that the end date is after the start date.
     * @param startDate The opening date.
     * @param endDate   The closing date.
     * @return true if endDate is after startDate, false otherwise.
     */
    // Validate if closing date is after opening date
    public static boolean validateDateRange(LocalDate startDate, LocalDate endDate) {
        return endDate.isAfter(startDate);
    }
}