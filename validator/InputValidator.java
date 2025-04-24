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
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    // Validate date string against a specific format
    public static boolean validateDate(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validate integer input range
    public static boolean validateIntRange(String input, Integer min, Integer max) {
        try {
            Integer value = Integer.parseInt(input);
            if ((min != null && value < min) || (max != null && value > max)) {
                return true;
            }
            else{
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate positive double
    public static boolean validatePositiveDouble(String input, Double min) {
        try {
            Double value = Double.parseDouble(input);

            if (min != null && value < min) {
                return true;
            }
            else {
                return false;
            }
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
