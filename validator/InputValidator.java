package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    public static boolean validateNric(String nric)
    {
        Pattern pattern = Pattern.compile("^[STFG]\\d{7}[A-Z]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nric);
        if(!matcher.find())
        {
            return false;
        }
        return true;
    }
}
