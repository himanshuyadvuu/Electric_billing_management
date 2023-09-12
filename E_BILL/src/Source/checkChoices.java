package Source;

import java.util.Scanner;

public class checkChoices {

	public static  int choice()
	{
		Scanner sc=new Scanner(System.in);
		
		int a;
		while(true)
		{
			try {
				a=sc.nextInt();
				break;
			}catch(Exception e)
			{
				System.out.println(e);
				sc.next();
				System.out.println("Wrong Format! Enter your value again");
			}
		}
		return a;
		
	}
}

