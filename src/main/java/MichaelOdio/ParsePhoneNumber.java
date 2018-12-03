package MichaelOdio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsePhoneNumber {

    private String digits = null;

    public String validatePhoneNumber(String line) {

        String phoneRegex = ".*?([0-9]+)[^0-9]*[^0-9]*([0-9]*)[^0-9]*([0-9]*)[^0-9]*([0-9]*).*?";
        Pattern pattern = Pattern.compile(phoneRegex);
        String digits = "";

        Matcher matcher = pattern.matcher(line);
        if (line.contains("f") || line.contains("F")) {
            return null;
        } else if (matcher.matches()) {
            //at least 1 group of 1 or more digits (but possibly up to 4 groups)
            if (matcher.group(1) != null) {
                digits += matcher.group(1);
            }
            if (matcher.group(2) != null) {
                digits += matcher.group(2);
            }
            if (matcher.group(3) != null) {
                digits += matcher.group(3);
            }
            if (matcher.group(4) != null) {
                digits += matcher.group(4);
            }

            int length = digits.length();
            if (length == 10 || length == 11) {
                return digits;
            } else {
                return null;
            }
        }
        else{
            return null;
        }


    }

}
