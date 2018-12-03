package MichaelOdio;

import java.util.Scanner;

public class BusinessCardParser {

    private ParseName parseName;
    private ParsePhoneNumber parsePhoneNumber;
    private ParseEmail parseEmail;

    public BusinessCardParser() {
        parseEmail = new ParseEmail();
        parsePhoneNumber = new ParsePhoneNumber();
        parseName = new ParseName();
    }

    public ContactInfo getContactInfo(String document) {
        Scanner sc = new Scanner(document);
        String email = null;
        String phone = null;
        String name = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (email == null && parseEmail.validateEmail(line) != null) {
                email = parseEmail.validateEmail(line);
            } else if (phone == null && parsePhoneNumber.validatePhoneNumber(line) != null) {
                phone = parsePhoneNumber.validatePhoneNumber(line);
            } else {
                name = parseName.validateName(line);
            }
        }
        sc.close();
        ContactInfo ci = new ContactInfo(name, phone, email);
        return ci;
    }
}

