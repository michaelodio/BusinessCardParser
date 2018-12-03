package MichaelOdio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseEmail {

    public String validateEmail(String line) {

        if (line.contains("@")) {
            String emailRegex = ".*?([^\\s]+@[^\\s]+).*?";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                return matcher.group(1);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }
}
