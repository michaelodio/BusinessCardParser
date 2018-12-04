package MichaelOdio;


import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessCardParser {



    private Tokenizer tokenizer = null;
    private NameFinderME nameFinder = null;
    private double highestNameProb = 0.0;
    private String name = null;

    public BusinessCardParser() {
        try {
            tokenizer = new TokenizerME(new TokenizerModel(ClassLoader.getSystemResourceAsStream("en-token.bin")));
            nameFinder = new NameFinderME(new TokenNameFinderModel(ClassLoader.getSystemResourceAsStream("en-ner-person.bin")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactInfo getContactInfo(String document) {
        Scanner sc = new Scanner(document);
        String email = null;
        String phone = null;
        String name = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (email == null && validateEmail(line) != null) {
                email = validateEmail(line);
            } else if (phone == null && validatePhoneNumber(line) != null) {
                phone = validatePhoneNumber(line);
            } else {
                name = validateName(line);
            }
        }
        sc.close();
        ContactInfo ci = new ContactInfo(name, phone, email);
        return ci;
    }

    /**Checks current line and validates for email
    First simple check occurs then passes
     to regex to parse out Phone or tel then
     to apache commons validator for a robust
     validation
     **/
    private String validateEmail(String line) {
        if (line.contains("@")) {
            String emailRegex = ".*?([^\\s]+@[^\\s]+).*?";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                boolean isEmail = EmailValidator.getInstance().isValid(matcher.group(1));
                if(isEmail) {
                    return matcher.group(1);
                }else{
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

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
    public String validateName(String line) {

        String tokens[] = tokenizer.tokenize(line);
        Span nameSpans[] = nameFinder.find(tokens);
        if (nameSpans.length > 0) {
            double nameProb = nameFinder.probs(nameSpans)[0];
            if (nameProb > highestNameProb) {
                highestNameProb = nameProb;
                name = Span.spansToStrings(nameSpans, tokens)[0];
            }
        }
        return name;
    }


}

