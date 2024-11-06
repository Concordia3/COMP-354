
public class Function {

	/*
	 * These functions are for later, when we formalized the data structure of our
	 * program
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
	 * These are the functions for D2. Feel free to seperate them into their own
	 * class.
	 * If you do so, emember to derived from this Function class
	 */

	// arccos(x)
	public double arccos(double x) {
		return 0;
	}

	// ab^x
	public double abx(double a, double b, double x) {
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

	// exp(x)
	public static double exp(double x) {
		double result = 1.0; // first term
		double term = 1.0; // for each iteration, term = term * x / i
		int n = 40; // maximum number of iterations

		for (int i = 1; i <= n; i++) {
			term *= x / i;
			result += term;
		}

		return result;
	}

	// calculate base^exponent
	public static double power(double base, int exponent) {
		double result = 1.0;
		for (int i = 0; i < exponent; i++) {
			result *= base;
		}
		return result;
	}

	// abs(x)
	public static double absolute(double value) {
		return value < 0 ? -value : value;
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

	// log_b(x)
	public double log(double base, double x) {
		double lnX = calculateLn(x);
		double lnBase = calculateLn(base);
		return lnX / lnBase;
	}

	// gamma
	public double gamma(double base, double x) {
		return 0;
	}

	// MAD (Mean Absolute Deviation)
	public double MAD(double X, double myu, double N) throws ArithmeticException {
		if (N == 0)
			throw new ArithmeticException("Sample size is zero!");

		double absDeviation = (X - myu < 0) ? myu - X : X - myu;

		return (absDeviation) / N;
	}

	// (Standard Deviation)
	public double stdDeviation() {
		return 0;
	}

	// sinh(x)
	public double sinh(double x) {
		return 0;
	}

	// x^y
	double xy(double x, double y) {
		double exponent = (y < 0) ? -y : y;
		double result = 1;

		for (int i = 0; i < exponent; i++) {
			result *= x;
		}

		return (y < 0) ? 1 / result : result;
	}

}
