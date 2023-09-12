package Source;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class InputData {
         private String aadhar;
        private int purpose;
        private String meterId;
        private String fullName;
        private String password;
        private String startDate;
        private String address;
        private String paymentStatus;
        private double totalBill;
        private int units;
        private int perUnitPrice;

    public String getAadhar() {
        return aadhar;
    }

    public int getPurpose() {
        return purpose;
    }


    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    // Generate a random 5-digit number for BILLID
    int min = 10000;
    int max = 99999;

    public InputData() {

        this.paymentStatus = "Done";
        this.totalBill = 0d;
        this.units = 0;
        this.perUnitPrice = 0;
    }

    public void takeInputFromUser()
    {
        System.out.println("---------Enter The Important Information for Registration---------");
         fullName=InputFullName.InputName();
         aadhar=InputAadhaar.inputAadhaar();
         startDate=InputDate.Date();       
        System.out.println("Enter your Full Address ");
        address = scanner.nextLine();
        System.out.println("your meterID will be generated soon");
        System.out.println("Enter your Use Purpose 1 for Commercial or 2 for Private");
        purpose =checkChoices.choice();
        while(purpose<1||purpose>2)
        {
        	System.out.println("Purpose value should be 1 & 2 So please enter them");
        	purpose=checkChoices.choice();
        }
        meterId = String.valueOf(random.nextInt((max-min) + 1)+min);
        System.out.println("----------------------------------------------");
//        System.out.println("Your meterID is--> "+ meterId);
        password = generatePassword();
//        System.out.println("Your Password is --> "+password);

        System.out.println("---------------------------------------------------");


    }

    public String generatePassword()
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Define the password length
        int passwordLength = 6;

        // Create a SecureRandom object for generating random numbers
        SecureRandom random = new SecureRandom();

        // Initialize a StringBuilder to build the password
        StringBuilder passwordBuilder = new StringBuilder();

        // Generate the random password
        for (int i = 0; i < passwordLength; i++) {
            // Get a random index within the character set
            int randomIndex = random.nextInt(characters.length());

            // Append the character at the random index to the password
            passwordBuilder.append(characters.charAt(randomIndex));
        }

        // Convert the StringBuilder to a String to get the final password
        String randomPassword = passwordBuilder.toString();

        return randomPassword;
    }







    public String getMeterId() {
        return meterId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public int getUnits() {
        return units;
    }

    public int getPerUnitPrice() {
        return perUnitPrice;
    }

    public void performRegisteredOperation() {
        System.out.println("we will perform this operation later");
    }
}
