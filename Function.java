<<<<<<< Updated upstream:Function.java
=======
package Functions;

// java packages
import java.util.ArrayList;

>>>>>>> Stashed changes:Functions/Function.java
/**
 * The Function aspect of the Eternity calculator
 *
 * @author Cyrus Stonebanks
 * @author Tristan Szittner-Francis
 * @author Nick Taddio
 * @author Hy Khang Tran
 * @author Jeremy Tang
 * @author Minh Thien Tran
 * @author Minghe Sun
 */

public class Function {

	/**
<<<<<<< Updated upstream:Function.java
	 * These functions are for later, when we formalized the data structure of our program
	 * This Function class will become abstract
	 * Becoming a blue print for the derived transcendental function class
=======
	 * * takes care of input, and return the result from the input with the current function choice
	 * @param number input data list
	 * @return result
	 * @throws IllegalArgumentException
	 * @throws ArithmeticException
>>>>>>> Stashed changes:Functions/Function.java
	 */

	/**
	 * takes care of input
	 * @param in
	 * @throws IllegalArgumentException
	 */
	public void input(double[] in) throws IllegalArgumentException {
	}

	/**
	 * Performs the calculation
	 * @throws ArithmeticException
	 */
	public void calculate() throws ArithmeticException {
	}

	/**
	 *  toString print out calculation
	 */
	public String toString() {
		return null;
	}

	/**
	 * These are the functions for D2. Feel free to seperate them into their own class.
	 * If you do so, emember to derived from this Function class
	 */

	/**
	 * arccos(x) Using Newton's method to approximate arccosine
	 * @param x value within [-1,1]
	 * @return result
	 */
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

	/**
	 * ab^x The exponential function
	 * @param a constant value
	 * @param b base value
	 * @param x	exponent value
	 * @return result
	 */
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


    /**
     *  Method to calculate ln(x) using a Taylor series
     * @param x positive value
     * @return result
     */

	public double abx_2(double a, double b, double x) {
		double result = a * power(b, x);
		return result;
	}

	public static double power(double base, double exponent) {
		double powerResult = 1;
		if (exponent > 0 && (int) exponent == exponent) { // Check if the exponent is a positive whole number
			for (int i = 0; i < exponent; i++) {
				powerResult *= base;
			}
		} else if (exponent < 0 && (int) exponent == exponent) { // Check if the exponent is a negative whole number
			for (int i = 0; i > exponent; i--) {
				powerResult *= base;
			}
			powerResult = 1 / powerResult;
		} else if (exponent > 0 && (int) exponent != exponent) { // Check if the exponent is a positive fractional
																	// number
			powerResult = exp(exponent * calculateLn(base));

		} else if (exponent < 0 && (int) exponent != exponent) { // Check if the exponent is a negative fractional
																	// number
			powerResult = 1 / exp(-exponent * calculateLn(base));
		}
		return powerResult;
	}

	public static double exp(double x) { // exp(x)
		double result = 1.0; // first term
		double term = 1.0; // for each iteration, term = term * x / i
		int n = 40; // maximum number of iterations

		for (int i = 1; i <= n; i++) {
			term *= x / i;
			result += term;
		}

		return result;
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

	/**
	 * log_b(x)
	 * @param base , the base value
	 * @param x	, the number value
	 * @return result
	 */
	public double log(double base, double x)
	{
        double lnX = calculateLn(x);
        double lnBase = calculateLn(base);

        return lnX / lnBase;
	}



	private static double computeMean(double[] data) {
		double sum = 0;
		for (double num : data) {
			sum += num;
		}
		return sum / data.length;
	}
	// Function to compute the absolute value
	private static double absolute(double value) {
		return value < 0 ? -value : value;
	}


	/**
	 * Gamma function using Lanczos Approximation
	 * @param x input value, positive values only
	 * @return result
	 */
	public double gamma(double x)
	{

		if (x < 0.5){

			if (x == (int)x) {
				throw new IllegalArgumentException("Negative integers are undefined");	// All negative integers are undefined for gamma function
			}

			return Math.PI / (Math.sin(Math.PI * x) * gamma(1-x));						// Reflection formula for inputs under 0.5 (including negative values)
		}

		if (x == (int)x) {

			return factorial((int)x - 1);

		}

		else {																					// Lanczos Formula for larger inputs

			final double G = 7;					// constant used in Lanczos Formula

		 	final double[] GAMMACOEFFS = {		// coefficients dependent on G needed for approximating gamma function
				0.99999999999980993,
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
			double a = GAMMACOEFFS[0];						// accumulator variable
			double t = x + G + 0.5;									// variable to simplify Lanczos Formula manipulation

			for (int i = 1; i < GAMMACOEFFS.length; i++) {

				a += GAMMACOEFFS[i] / (x + i);

			}

			return Math.sqrt(2 * Math.PI) * xy(t, x + 0.5) * Math.exp(-t) * a;					// Lanczos Formula result

		}



	}

	/**
	 *  MAD (Mean Absolute Deviation)
	 *
	 * @return
	 * @throws ArithmeticException
	 */
	public double MAD(double data[]) throws ArithmeticException
	{
		if (data.length == 0) throw new ArithmeticException("Sample size is zero!");

		double mean = computeMean(data);
		double sumAbsoluteDeviations = 0;
		for (double num : data) {
			sumAbsoluteDeviations += absolute(num - mean);
		}

		return sumAbsoluteDeviations / data.length;
	}


	/**
	 *  Standard Deviation
	 * @param data input value
	 * @return result
	 */
	public double stdDeviation(double[] data)


	// (Standard Deviation)
  // public double stdDeviation()

	{
		int n = data.length;
		if(n == 0){
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
            varianceSum +=  xy((value - mean), 2);
        }
        double variance = varianceSum / n;

        // Calculate standard deviation
        return Math.sqrt(variance);
	}


	/**
	 * factorial function using recursion
	 * @param x input value
	 * @return result
	 */
	public long factorial(int x) {
		if (x == 0 || x == 1)					//returns 1 for end of recursion
			return 1;

		return x * factorial(x - 1);			//recursivly multiplies result by x - 1

	}

	/**
	 * sinh(x) Using the definition of hyperbolic sine: sinh(x) = (e^x - e^-x) / 2
	 * @param x input value
	 * @return result
	 */
	public double sinh(double x){
		double expX = Math.exp(x);
        double expNegX = Math.exp(-x);
        return (expX - expNegX) / 2.0;
	}

	/**
	 * x^y exponent function
	 * @param x base value
	 * @param y exponent value
	 * @return result
	 */
	double xy(double x, double y)
	{
		double exponent = (y < 0) ? -y : y;

		double result = 1;

		for (int i = 0; i < exponent; i++) {
			result *= x;
		}

		return (y < 0) ? 1/result : result;
	}

	/**
	 * modulus function
	 * @param a dividen value
	 * @param b divisor value
	 * @return result
	 */
	public static int modulo(int a, int b){
		return a % b;
	}
}
