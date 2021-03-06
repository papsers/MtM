package mtm.db.Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Validator
{
	//This class contains some functions that we should use in order to know if the user is
	//introducing the type of value we are storing in the variable.
 
	static BufferedReader c = new BufferedReader(new InputStreamReader(System.in));
	
	// Writing Methods

	public static int writeNumber() 
	{
		//P Methods used to write an integer value without conditions (Just to be an Integer)
        boolean out = false;
        int answer = -1;
        String stringNumber = "";
        try
        {
        	 do 
        	 {
        		    
                 System.out.println("Introduce a number\n");
   
                     stringNumber = c.readLine();
                     if (valNumString(stringNumber)) 
                     {
                         answer = Integer.parseInt(stringNumber);
                         out = true;
                     }
                
        	 } while (!out);//Mientras que no me introduzca un numero, no le dejo salir
        }catch(IOException ex)
        {
        	 ex.printStackTrace();
        }

        return answer;
    }
	
	public static int writeNumber(int upperLim)
	{
		//P Method used to set one limit, the upper limit
		int numIntro = -1;
		boolean out = false;
		String stringNumber = "";
		try
		{
			while((numIntro > upperLim) || (numIntro < 1))
			{
				 do 
	        	 {
	                 System.out.println("Introduce a number\n");
	   
	                     stringNumber = c.readLine();
	                     if (valNumString(stringNumber)) 
	                     {
	                         numIntro = Integer.parseInt(stringNumber);
	                         out = true;
	                     }
	                
	        	 } while (!out);//Mientras que no me introduzca un numero, no le dejo salir
				
				if((numIntro > upperLim) || (numIntro < 0))//si hay 5 opciones no puedes poner 6
				{	
					System.out.printf("Out of established limits %d\n",upperLim);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error Introducing the values");
		}
		return numIntro;
	}
		
	public static int writeNumber(int lowerLim, int upperLim)
	{
		//P Method used to set one limit, the upper limit
				int numIntro = -1;
				boolean out = false;
				String stringNumber = "";
				try
				{
					while((numIntro > upperLim) || (numIntro < lowerLim))
					{
						 do 
			        	 {
			                 System.out.printf("Introduce a number between %d and %d \n",lowerLim,upperLim);
			   
			                     stringNumber = c.readLine();
			                     if (valNumString(stringNumber)) 
			                     {
			                         numIntro = Integer.parseInt(stringNumber);
			                         out = true;
			                     }
			                
			        	 } while (!out);//Mientras que no me introduzca un numero, no le dejo salir
						
						if((numIntro > upperLim) || (numIntro < 0))//si hay 5 opciones no puedes poner 6
						{	
							System.out.println("Out of established limits ["+upperLim+","+lowerLim+"]\n");
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("Error Introducing the values");
				}
				return numIntro;
	}

	public static String writeString()
	{
		String string="";
		System.out.println("Introduce a word/sentence\n");
		try
		{
			string = c.readLine();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return string;
	}
	
	// Validating Methods
	public static boolean valNumString(String val) 
	//Method used to validate if the value val is realy an integer or not
	{
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }
	
	public static boolean writeOption(String option)
	{
		boolean a = false;
		boolean change = false;
		while(!change)
		{
			if(option.compareTo("YES") == 0 || option.compareTo("yes") == 0)
			{
				a = true;
				change = true;
			}
			 
			if(option.compareTo("NO") == 0 || option.compareTo("no") == 0)		
			{

				a = false;
				change = true;
			}
			
			if(!change)
			{
				System.out.println("\n Introduce YES or NO\n");
				option = writeString();
			}
			
		}
		

		
		
		return a;
	}
	
	// More Functions

	public static String[] createDate()
		{
			String [] date = new String [3];
			
			System.out.println("\nDay:");
			int day1 = writeNumber(31);
			String day1S;
			if(day1 < 10)
			{
				day1S = "0"+day1+"";
			}else
			{
				day1S = ""+day1+"";
			}
			date[0]=day1S;
			System.out.println("\nMonth:");
			int month1 = writeNumber(12);
			String month1S;
			if(month1 < 10)
			{
				month1S = "0"+month1+"";
			}else
			{
				month1S = ""+month1+"";
			}
			date[1] = month1S;
			System.out.println("\nYear:");
			int year1 = writeNumber(1999,2017);
			String year1S = ""+year1+"";
			date[2] = year1S;
			return date;
		}
		
    public static void waitEnter()
    {	
        System.out.println("Press enter to continue: \n");
        try{
            String a = c.readLine();
            a = a+"a";
        }catch(IOException ex){
        	ex.printStackTrace();
        }
    }

}
