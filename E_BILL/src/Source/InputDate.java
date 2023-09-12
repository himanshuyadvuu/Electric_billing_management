package Source;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class InputDate {
   
	public static  String Date()
	{
		 Scanner sc = new Scanner(System.in);

	        System.out.println("Enter the date in the format YYYY/MM/DD");
	        String dateString = sc.nextLine();

	        while (!validateJavaDate(dateString)) {
	            System.out.println("Enter Date Again");
	            dateString = sc.nextLine();
	        }
	        return dateString;
	}
	public static boolean validateJavaDate(String strDate)
	{
	 if (strDate.trim().equals("")) {
         return false;
     }
     /* Date is not 'null' */
     else {
         SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy/MM/dd");
         sdfrmt.setLenient(false);
         try {
             Date javaDate = sdfrmt.parse(strDate);
             System.out.println(strDate + " is a valid date format");
         } 
         /* Date format is invalid */
         catch (ParseException e) {
             System.out.println(strDate + " is an Invalid Date format");
             return false;
         }
         /* Return true if date format is valid */
         return true;
     }
	 
	}
		
}
