
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
	// Using Newton's method to approximate arccosine
	public double arccos(double x){
		if (x < -1.0 || x > 1.0) {
            throw new IllegalArgumentException("Input value must be between -1 and 1");
        }
        double guess = Math.PI / 2; // Initial guess
        double epsilon = 1e-10; // Tolerance for convergence
        double diff;

        do { // Compute the current guess and update the difference
            double current = Math.cos(guess) - x;
            double derivative = -Math.sin(guess); // Derivative of cosine
            double newGuess = guess - (current / derivative);
            diff = Math.abs(newGuess - guess);
            guess = newGuess;
        }
        while (diff > epsilon); // Iterate until convergence

        return guess;
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

	//log_b(x)
	public double log(double base, double x)
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

	// �� (Standard Deviation)
	public double stdDeviation()
	{
		return 0;
	}

	// sinh(x)
	// Using the definition of hyperbolic sine: sinh(x) = (e^x - e^-x) / 2
	public double sinh(double x){
		double expX = Math.exp(x);
        double expNegX = Math.exp(-x);
        return (expX - expNegX) / 2.0;
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

	// gamma function
	public double gamma(double x){
		return 0;
	}

	public static int modulo(int a, int b){
		return a % b;
	}
}
