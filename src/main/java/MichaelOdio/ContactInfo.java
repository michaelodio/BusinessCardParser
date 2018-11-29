package MichaelOdio;

public class ContactInfo {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public ContactInfo(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String toString() {
        return "Name: " + name + "\n" + "Phone Number: " + phoneNumber + "\n" + "Email: " + emailAddress;

    }
}