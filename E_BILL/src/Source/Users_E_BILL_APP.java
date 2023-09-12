package Source;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Users_E_BILL_APP {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "SYSTEM";
        String password = "Himanshusingh@2001";

        Scanner scanner = new Scanner(System.in);
        int a;
        InputData inputData = new InputData();


        while (true) {
            System.out.println("Who are you ADMIN OR USER");
            System.out.println("Choose 1 for ADMIN and 2 for USER");
            System.out.println("press 3 for quit Operaion");
            a=checkChoices.choice();

            if (a == 2) {
                System.out.println("choose 1 for new user and 2 for already Registered");
                int b = scanner.nextInt();
                if (b == 1) {
                    inputData.takeInputFromUser();
                    System.out.println("--------------------------------------------------------------------");

                    String meterId = inputData.getMeterId();
                    String fullName = inputData.getFullName();
                    String startDate = inputData.getStartDate(); // Date in 'YYYY-MM-DD' format
                    String address = inputData.getAddress();
                    String paymentStatus = inputData.getPaymentStatus();
                    double totalBill = inputData.getTotalBill();
                    int units = inputData.getUnits();
                    double perUnitPrice = inputData.getPerUnitPrice();
                    String passwordValue = inputData.getPassword();
                    String aadharNo = inputData.getAadhar();
                    int purpose = inputData.getPurpose();
                    String usage = "";
                    if (purpose == 1) {
                        perUnitPrice = 20.0d;
                        usage = "Commercial";
                    } else {
                        perUnitPrice = 10.0d;
                        usage = "Private";
                    }

                    insertIntoOracleDB(meterId, fullName, startDate, address, paymentStatus, totalBill, units, perUnitPrice, passwordValue, jdbcUrl, username, password, aadharNo, usage);


                } else if (b == 2)
                    checkUserProfile(username, password);
                else {
                    System.out.println("Thankyou for using our service");
                    break;
                }

            } else if (a == 3) {
                break;
            } else {
                updateBillsByAdmin(username, password);
            }

        }

        // Define the data for the new record
    }

    public static void insertIntoOracleDB(String meterId, String fullName, String startDate, String address, String paymentStatus, Double totalBill, int units, Double perUnitPrice, String passwordValue, String jdbcUrl, String username, String password, String aadhar, String purpose) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Define the SQL insert statement
            String insertSQL = "INSERT INTO ElectricityBill (meterId, fullName, startDate, address, paymentStatus, totalBill, units, perUnitPrice, password, aadharcard, purpose) VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ? )";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

            // Set values for the placeholders
            preparedStatement.setString(1, meterId);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, startDate); // Date in 'YYYY-MM-DD' format
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, paymentStatus);
            preparedStatement.setDouble(6, totalBill);
            preparedStatement.setInt(7, units);
            preparedStatement.setDouble(8, perUnitPrice);
            preparedStatement.setString(9, passwordValue);
            preparedStatement.setString(10, aadhar);
            preparedStatement.setString(11, purpose);

            // Execute the SQL statement to insert the record
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Record inserted successfully.");
                System.out.println("**IMPORTANT** Collect your MeterID : " + meterId);
                System.out.println("Your password is: " + passwordValue);
            } else {
                System.out.println("Record insertion failed.");
            }


            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void checkUserProfile(String usernamedb, String pass) {
        Scanner scan = new Scanner(System.in);
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your Oracle database connection URL
        String username = usernamedb;
        String password = pass;
        // Replace with the specific meterId you're looking for
        String targetMeterId=MeterID.enterMeterId();
        System.out.println("Enter Your Password");
        String targetPassword = scan.nextLine(); // Replace with the specific password you're looking for
        String meterId = "";
        String fullName = "";
        String paymentStatus = "";
        double totalBill = 0d;
        double perUnitPrice = 0d;
        String purpose = "";
        int units = 0;
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Define the SQL query with placeholders for meterId and password
            String sqlQuery = "SELECT * FROM ElectricityBill WHERE meterId = ? AND password = ? ";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set parameter values for the placeholders
            preparedStatement.setString(1, targetMeterId);
            preparedStatement.setString(2, targetPassword);


            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from columns
                // Replace with the actual column name
                meterId = resultSet.getString("meterId");
                fullName = resultSet.getString("fullName");
                paymentStatus = resultSet.getString("paymentStatus");
                totalBill = resultSet.getDouble("totalBill");
                perUnitPrice = resultSet.getDouble("perUnitPrice");
                purpose = resultSet.getString("purpose");
                units = resultSet.getInt("units");


                // Print or process the retrieved data
                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");

                System.out.println("Meter ID: " + meterId);
                System.out.println("Full Name: " + fullName);
                System.out.println("PaymentStatus: " + paymentStatus);
                System.out.println("TotalBills :" + totalBill);
                System.out.println("per Unit Price :" + perUnitPrice);

                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");
                // Add more columns as needed

                // You can process and use the retrieved data here
            }

            if (totalBill > 0) {
                System.out.println("Your Bills are Due if you want to pay the Bill Press Y");
                char c = scan.next().charAt(0);
                System.out.println("Enter how much unit you want to pay");
                int updatedunit = checkChoices.choice();
                units -= updatedunit;
                if (purpose.charAt(0) == 'P')
                    totalBill -= (updatedunit * 10);
                else {
                    totalBill -= (updatedunit * 20);

                }


                String sqlQuery1 = "UPDATE ElectricityBill SET totalBill = ?, units = ? WHERE meterID = ?";

                // Create a PreparedStatement
                PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery1);

                // Set parameter values for the placeholders
                preparedStatement2.setDouble(1, totalBill);
                preparedStatement2.setInt(2, units);
                preparedStatement2.setString(3, targetMeterId);


                // Execute the query and retrieve the result set
                int rowsUpdated = preparedStatement2.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Paid updated successfully.");
                } else {
                    System.out.println("Paid update failed. MeterID not found.");
                }


            } else {
            	String sqlQuery1 = "UPDATE ElectricityBill SET paymentStatus = ? WHERE meterID = ?";

            	// Create a PreparedStatement
            	PreparedStatement preparedStatement2 = connection.prepareStatement(sqlQuery1);

            	// Set parameter values for the placeholders
            	preparedStatement2.setString(1, "Paid");
            	preparedStatement2.setString(2, targetMeterId);


            	// Execute the query and retrieve the result set
            	if(preparedStatement2.executeUpdate()>0)
            	System.out.println("your bills are paid ThankYou");
            	else {
            	    System.out.println("informatoin are not correcet");
            	}
            }


            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateBillsByAdmin(String user, String pass) {
        Scanner scan = new Scanner(System.in);
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your Oracle database connection URL
        String username = user;
        String password = pass;
        int count = 3;
        String targetMeterId = MeterID.enterMeterId(); // Replace with the specific meterId you're looking for

        // Replace with the specific password you're looking for

        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Double units = 0d;
            Double totalBills = 0d;
            String meterId = "";
            String fullName = "";
            String paymentStatus = "";
            double totalBill = 0d;
            double perUnitPrice = 0d;
            String purpose = "";

            // Define the SQL query with placeholders for meterId and password
            String sqlQuery = "SELECT * FROM ElectricityBill WHERE meterId = ? ";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            // Set parameter values for the placeholders
            preparedStatement.setString(1, targetMeterId);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from columns
                // Replace with the actual column name
                meterId = resultSet.getString("meterId");
                fullName = resultSet.getString("fullName");
                paymentStatus = resultSet.getString("paymentStatus");
                totalBill = resultSet.getDouble("totalBill");
                units = resultSet.getDouble("units");
                perUnitPrice = resultSet.getDouble("perUnitPrice");
                purpose = resultSet.getString("purpose");


                // Print or process the retrieved data
                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");

                System.out.println("Meter ID: " + meterId);
                System.out.println("Full Name: " + fullName);
                System.out.println("PaymentStatus: " + paymentStatus);
                System.out.println("TotalBills :" + totalBill);
                System.out.println("per Unit Price :" + perUnitPrice);
                System.out.println("Electricity Purpose : " + purpose);

                System.out.println("---------------------------------------------------------------------");
                System.out.println("---------------------------------------------------------------------");
                // Add more columns as needed

                // You can process and use the retrieved data here
            }

            System.out.println("Choose The Option for Operations");
            System.out.println("Choose 1 for Update the bills");
            System.out.println("Chooose 2 for name change");
            System.out.println("Choose 3 for Address Update");
            int choice = scan.nextInt();

            switch (choice) {
                case 1: {
                    String status = "";

                    System.out.println("How much units you want to increase");
                    units += checkChoices.choice();
                    totalBills += (units * perUnitPrice);
                    if (units > 0) {
                        status = "Due";
                    }

                    String sqlForUpdate = "UPDATE ElectricityBill SET totalbill = ?, units = ?,paymentstatus = ? WHERE meterID = ?";

                    // Create a PreparedStatement to execute the update
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sqlForUpdate);

                    // Set the new totalbill value and the meterID

                    preparedStatement1.setDouble(1, totalBills);
                    preparedStatement1.setDouble(2, units);
                    preparedStatement1.setString(3, status);
                    preparedStatement1.setString(4, targetMeterId);

                    // Execute the update statement
                    int rowsUpdated = preparedStatement1.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Totalbill updated successfully.");
                    } else {
                        System.out.println("Totalbill update failed. MeterID not found.");
                    }


                    break;
                }

                case 2: {
                    Scanner scanner = new Scanner(System.in);
                    String updatedName = "";
                    System.out.println("Enter the new Updated name");
                    updatedName = InputFullName.InputName();
                    String sqlForUpdate2 = "UPDATE ElectricityBill SET fullname = ? WHERE meterID = ?";

                    // Create a PreparedStatement to execute the update
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sqlForUpdate2);


                    preparedStatement2.setString(1, updatedName);
                    preparedStatement2.setString(2, targetMeterId);


                    // Execute the update statement
                    int rowsUpdated2 = preparedStatement2.executeUpdate();

                    if (rowsUpdated2 > 0) {
                        System.out.println("Name updated successfully.");
                    } else {
                        System.out.println("Name update failed. MeterID not found.");
                    }

                    break;

                }

                case 3: {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter the New Address");
                    String updatedAddress = scanner.nextLine();
                    String sqlForUpdate3 = "UPDATE ElectricityBill SET address = ? WHERE meterID = ?";

                    // Create a PreparedStatement to execute the update
                    PreparedStatement preparedStatement3 = connection.prepareStatement(sqlForUpdate3);

                    // Set the new totalbill value and the meterID

                    preparedStatement3.setString(1, updatedAddress);
                    preparedStatement3.setString(2, targetMeterId);


                    // Execute the update statement
                    int rowsUpdated = preparedStatement3.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Address updated successfully.");
                    } else {
                        System.out.println("Address update failed. MeterID not found.");
                    }

                    break;

                }

                default:
                    System.out.println("Invalid Choice");

            }


            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}








