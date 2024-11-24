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
		
		new MyCalculator("Eternity");
		MyCalculator.isRunning = true;

		while(true){
			try{
				if(!MyCalculator.isRunning){
					new MyCalculator("Eternity");
				}
			}catch(Exception e){
				MyCalculator.isRunning = false;
			}
				
		}


		
	}

}
