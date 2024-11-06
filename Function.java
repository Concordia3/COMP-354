
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
	
	// gamma
	public double gamma(double base, double x)
	{
		return 0;
	}
	
	// MAD (Mean Absolute Deviation)
	public double MAD(double X, double myu, double N) throws ArithmeticException
	{
		if (N == 0) throw new ArithmeticException("Sample size is zero!");
		
		double absDeviation = (X - myu < 0) ? myu - X : X - myu;
		
		return (absDeviation) / N;
	}
	
	// Standard Deviation
	public double stdDeviation(double[] data)
	{
		int n = data.length;
		if(n = 0){
			throw new IllegalArgumentException("No data was given!");
		}
        double sum = 0;
        
        // Calculate mean
        for (double value : data) {
            sum += value;
        }
        double mean = sum / n;

		// Calculate variance
        double varianceSum = 0;
        for (double value : data) {
            varianceSum += Math.pow(value - mean, 2);
        }
        double variance = varianceSum / n;
        
        // Calculate standard deviation
        return Math.sqrt(variance);
	}
	
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
