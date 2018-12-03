package MichaelOdio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private String mFile;

    public String getDocument(){
        return mFile;
    }

     private void readFile(String fileName) {
         StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();
            mFile = sb.toString();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String args[]) {
        Main m = new Main();


        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a file path: ");
        String file = reader.next();
        m.readFile(file);
        reader.close();

        BusinessCardParser businessCardParser = new BusinessCardParser();

        ContactInfo contactInfo = businessCardParser.getContactInfo(m.getDocument());
        System.out.println(contactInfo.toString());

    }
}
