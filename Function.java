
public class Function {
	
	/*
	 * These functions are for later, when we formalized the data structure of our program
	 * This Function class will become abstract
	 * Becoming a blue print for the derived transcendental function class
	 */
	
	// take care of input
	public void input(double[] in) throws IllegalArgumentException {
	}
	
	// do the calculation
	public void calculate() throws ArithmeticException {
	}
	
	// toString print out calculation
	public String toString() {
		return null;
	}
	
	/*
	 * These are the functions for D2. Feel free to seperate them into their own class.
	 * If you do so, emember to derived from this Function class
	 */
	
	// arccos(x)
	public double arccos(double x)
	{
		return 0;
	}
	
	// ab^x
	public double abx(double a, double b, double x)
	{
		double bx = 1;
		double exponent = (x < 0) ? -x : x;
		
		for (int i = 0; i < exponent; i++) {
			bx *= b;
		}
		
		bx = (x < 0) ? 1/bx : bx;
		
		return a*bx;
	}
	
    // Method to calculate ln(x) using a Taylor series
    public static double calculateLn(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be positive.");
        }
        
        // Transform x to be close to 1 for faster convergence
        int k = 0;
        while (x > 2) {
            x /= 2;
            k++;
        }
        
        x = (x - 1) / (x + 1);
        double result = 0.0;
        double term = x;
        double xSquared = x * x;

        for (int i = 1; i <= 100; i += 2) { // Taylor series sum
            result += term / i;
            term *= xSquared;
        }

        result *= 2; // Multiply by 2 as per Taylor series formula
        result += k * 0.69314718056; // Adjust for factors of 2 (ln(2) ≈ 0.693147)

        return result;
    }
	
	//log_b(x)
	public double log(double base, double x)
	{
        double lnX = calculateLn(x);
        double lnBase = calculateLn(base);
        return lnX / lnBase;
	}
	


	// gamma function using Lanczos Approximation
	public double gamma(double x)
	{

		if (x <= 0){
			throw new IllegalArgumentException("Positive value was expected!");			// Error if input is a non-positive number
		}
			
		if (x < 0.5){
			return Math.PI / (Math.sin(Math.PI * x) * gamma(1-x));								// Reflection formula for inputs under 0.5 (small values)
		}
			
		else {																					// Lanczos Formula for larger inputs
			
			final double G = 7;					// constant used in Lanczos Formula

		 	final double[] GAMMACOEFFS = {		// coefficients dependent on G needed for approximating gamma function
				676.5203681218851,
				-1259.1392167224028,
				771.32342877765313,
				-176.61502916214059,
				12.507343278686905,
				-0.13857109526572012,
				9.9843695780195716e-6,
				1.5056327351493116e-7
		};

			x -= 1;									
			double a = 0.99999999999980993;							// accumulator variable 
			double t = x + G + 0.5;									// variable to simplify Lanczos Formula manipulation

			for (int i = 0; i < GAMMACOEFFS.length; i++) {

				a += GAMMACOEFFS[i] / (x + i + 1);

			}

			return Math.sqrt(2 * Math.PI) * xy(t, x + 0.5) * Math.exp(-t) * a;					// Lanczos Formula result

		}



	}

	// MAD (Mean Absolute Deviation)
	public double MAD(double X, double myu, double N) throws ArithmeticException
	{
		if (N == 0) throw new ArithmeticException("Sample size is zero!");
		
		double absDeviation = (X - myu < 0) ? myu - X : X - myu;
		
		return (absDeviation) / N;
	}
	
	// (Standard Deviation) 
	public double stdDeviation()
	{
		return 0;
	}
	
	// factorial function
	public long factorial(int x) {

		if (x == 0 || x == 1)					//returns 1 for end of recursion
			return 1;

		return x * factorial(x - 1);			//recursivly multiplies result by x - 1

	}

/* 	// sin(x) function
	public double sin(double x) {

		x = x % (2 * Math.PI);		

		double result = 0.0;

		for (int i = 0; i < 10; i++) {

			int exponent = 2 * i + 1;
			double term = xy(x, exponent) / factorial(exponent);
			
			if (i % 2 != 0)
				term = -term;

			result += term;
			
		}

		return result;

	}*/

	// sinh(x)
	public double sinh(double x)
	{
		return 0;
	}
	
	// x^y
	double xy(double x, double y)
	{
		double exponent = (y < 0) ? -y : y;
		double result = 1;
		
		for (int i = 0; i < exponent; i++) {
			result *= x;
		}
		
		return (y < 0) ? 1/result : result;
	}
	

}
