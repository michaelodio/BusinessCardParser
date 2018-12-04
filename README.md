 BusinessCardParser
 
 Requires Maven 3.5.4+, JDK 10.0.1+ , Windows 10
 
 This program offers a solution to a new smartphone app that enables users to snap a photo of a business card and have the information from the card automatically extracted and added to their contact list. We need you to write the component that parses the results of the optical character recognition (OCR) component in order to extract the name, phone number, and email address from the processed business card image.
 
 This program use Apache Commons Validator to validate any email address that may be found and uses openNLP library to parse
 and produce the most probable name on the business card.
 
 There are  other options to validating the phone number such as googles libphonenumber which can provide a robust form of validation on a possible phone number. But at the cost of using too much memory, this solution does not use it and instead uses regex.
 
 To run the program first download it from https://github.com/michaelodio/BusinessCardParser
 
 Once downloaded, use the command prompt and navigate to the project's directory
 
 example:
 
 C:users\mike\desktop\Business_Card_Parser>
 
 Use the following command to build the program mvn clean compile assembly:single
 
 example:
 
 C:\Users\Chel\Desktop\Business_Card_Parser>mvn clean compile assembly:single
 
 Once built navigate to the target directory of the project
 
 example:
 
 C:\Users\Chel\Desktop\Business_Card_Parser>cd target
 
 Inside the target directory type the following :
 
 java -jar BusinessCardParser-1.0-SNAPSHOT-jar-with-dependencies.jar
 
 This will execute the program
 
 example:
 
 C:\Users\Chel\Desktop\Business_Card_Parser\target>java -jar BusinessCardParser-1.0-SNAPSHOT-jar-with-dependencies.jar

Sample outcome:

Enter a file path for a txt file or exit to terminate:

C:\Users\Chel\Documents\example3.txt

Output

Name: Bob Smith

Phone Number: 17035551259

Email: bsmith@abctech.com


Enter a file path for a txt file or exit to terminate:
exit


