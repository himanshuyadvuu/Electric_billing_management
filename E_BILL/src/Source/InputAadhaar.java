package Source;

import java.util.Scanner;

class InputAadhaar {

	public static String inputAadhaar()
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Aadhaar Number");

		String Aadhaar=sc.nextLine();
		
		while(true)
		{
		 boolean p=true;
		 
		 for(int i=0;i<Aadhaar.length();i++)
		 {
			 if(!Character.isDigit(Aadhaar.charAt(i)))
			 {
				 p=false;
				 break;
			 }
		 }
		 if(!p)
		 {
				System.out.println("The Aadhaar number is not valid");
				Aadhaar=sc.nextLine();
		 }
		 else if(Aadhaar.length()!=12)
		 {
			 System.out.println("The Aadhaar number is not valid");
				Aadhaar=sc.nextLine();
		 }
		 else
		 {
			// System.out.println("The Aadhaar is valid now");
			 break;
		 }
		}
		return Aadhaar;
	}

}
