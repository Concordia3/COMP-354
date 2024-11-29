// User libraries
import Calculator.MyCalculator;

/**
*
* Main GUI Driver for the Eternity Calculator
*
* @author Cyrus Stonebanks
* @author Tristan Szittner-Francis
* @author Nick Taddio
* @author Hy Khang Tran
* @author Jeremy Tang
* @author Minh Thien Tran
* @author Minghe Sun
*/


public class GUIDriver {
	
	public static void main(String[] args) {
		
		MyCalculator.isRunning = false;

		while(true)
		{
			try
			{
				if(!MyCalculator.isRunning)
				{
					new MyCalculator("Eternity");
					MyCalculator.isRunning = true;
				}
			}
			catch(Exception e)
			{
				System.err.println("\nERROR! Restarting Calculator!\n");
				MyCalculator.isRunning = false;
			}
			
			
		}


		
	}

}
