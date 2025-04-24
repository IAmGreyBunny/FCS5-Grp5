package validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    // Validate NRIC format
    public static boolean validateNric(String nric) {
        Pattern pattern = Pattern.compile("^[STFG]\\d{7}[A-Z]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nric);
        return matcher.find();
    }

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

    // Validate double input is positive
    public static boolean validatePositiveDouble(String input) {
        try {
            Double value = Double.parseDouble(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate available units not exceeding total units
    public static boolean validateAvailableUnits(int available, int total) {
        return available <= total;
    }

    // Validate yes/no input
    public static boolean validateYesNo(String input) {
        return input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N");
    }

    // Validate if closing date is after opening date
    public static boolean validateDateRange(LocalDate startDate, LocalDate endDate) {
        return endDate.isAfter(startDate);
    }
}