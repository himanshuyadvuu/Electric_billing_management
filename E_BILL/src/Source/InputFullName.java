package Source;

import java.util.Scanner;

public class InputFullName {
	

	public static String InputName()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your Name");
		String fullName=sc.nextLine();
		
		while(true)
		{
			boolean p=true;
			int c=0;
			
			 
			 for(int i=0;i<fullName.length();i++)
			 {
				 boolean a=fullName.charAt(i)>='a'&&fullName.charAt(i)<='z';
				 boolean b=fullName.charAt(i)>='A'&&fullName.charAt(i)<='Z';
				 if(fullName.charAt(i)==' ')
					 c++;
				 
				 if(Character.isDigit(fullName.charAt(i)))
				 {
					 p=false;
					 break;
				 }
				 if(!(a||b||(fullName.charAt(i)==' '&&c==1)))
				 {
					 
					 p=false;
					 break;
				 }
			 }
			 if(!p)
			 {
					System.out.println("The Name is not valid");
					fullName=sc.nextLine();
			 }
			 else
			 {
				// System.out.println("The Aadhaar is valid now");
				 break;
			 }
		}
		return fullName;
	}

}
