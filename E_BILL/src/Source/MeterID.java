package Source;

import java.util.Scanner;


public class MeterID {

	
	public static String enterMeterId()
	{
		 System.out.println("Enter your targeted meterID");
		 Scanner sc=new Scanner (System.in);
	        String targetMeterId = sc.nextLine();
	        
	        while(true)
	        {
	        	boolean p=true;
	        	
	        	for(int i=0;i<targetMeterId.length();i++)
	        	{
	        		if(!(targetMeterId.charAt(i)>='0'&&targetMeterId.charAt(i)<='9'))
	        		{
	        		    p=false;
	        		    break;
	        		}
	        		
	        	}
	        	if(!p)
	        	{
	        		System.out.println("You Enter the wrong format of Meter id (You have to put in Integers only)");
	        		targetMeterId=sc.nextLine();
	        	}
	        	else
	        	{
	        		break;
	        	}
	        }
			return targetMeterId;
	}
}
