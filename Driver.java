import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Main Driver for the Eternity Calculator
 *
 * @author Cyrus Stonebanks
 * @author Tristan Szittner-Francis
 * @author Nick Taddio
 * @author Hy Khang Tran
 * @author Jeremy Tang
 * @author Minh Thien Tran
 * @author Minghe Sun
 */

public class Driver {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Placeholder function class that store all functions calculation
        double a, b, x, y;
        String dataString;
        String[] dataStrArray;
        double[] data;
        Function f = new Function();

        int userFunctionSelection = 0;
        do{
            System.out.println("\nWelcome to Eternity, please select a function:\n" +
                "1- arccos(x)\n" + "2- ab^(x)\n" + "3- log_b (x)\n" +
                "4- gamma (x)\n" + "5- MAD\n" + "6- std Deviation\n" + "7- sinh(x)\n" +
                "8- x^y\n" + "9- exit");

            userFunctionSelection = input.nextInt();

            switch (userFunctionSelection) {
                case 1:
                    System.out.println("\nPlease enter a value for 'x'");
                    x = input.nextDouble();
                    if (x < -1 || x > 1){
                        System.out.println("The value enterred doesn't fit the range for this function.\n" +
                                            "The value should be between -1 and 1 (inclusively).");
                        break;
                    }
                    start_time();
                    System.out.println(f.arccos(x) + " rad");
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 2:
                	System.out.println("\nPlease input value of a");
                	a = input.nextDouble();
                    System.out.println("Please input value of b");
                    b = input.nextDouble();
                    System.out.println("Please input value of x");
                    x = input.nextDouble();

                    start_time();
                	System.out.println(f.abx(a, b, x));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 3:
                	System.out.println("Please input value of base b");
                	    b = input.nextDouble();
                    System.out.println("Please input the value for x");
                        x = input.nextDouble();
                    start_time();
                	System.out.println(f.log(b, x));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 4:
                    System.out.println("Please input a value");
                    double gam = input.nextDouble();
                    start_time();
                    System.out.println(f.gamma(gam));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 5:
                    System.out.println("Please input data values with a space between each (e.g. 5 1 3)");
                    input.nextLine();
                    dataString = input.nextLine();
                    dataStrArray = dataString.split("\\s+");
                    data = new double[dataStrArray.length];
                    for (int i = 0; i < data.length; i++) {
                        data[i] = Double.parseDouble(dataStrArray[i]);
                    }
                    start_time();
                    System.out.println(f.MAD(data));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 6:
                	System.out.println("Please input data values with a space between each (e.g. 5 1 3)");
                    input.nextLine();
                    dataString = input.nextLine();
                    dataStrArray = dataString.split("\\s+");
                    data = new double[dataStrArray.length];
                    for (int i = 0; i < data.length; i++) {
                        data[i] = Double.parseDouble(dataStrArray[i]);
                    }
                    start_time();
                    System.out.println(f.stdDeviation(data));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 7:
                    System.out.println("Please enter the value of 'x'");
                    x = input.nextDouble();
                    start_time();
                    System.out.println(f.sinh(x));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 8:
                	System.out.println("Please input value of x");
                	    x = input.nextDouble();
                    System.out.println("Please input value of y");
                        y = input.nextDouble();
                    start_time();
                	System.out.println(f.xy(x, y));
                    end_time();
                    write_Time(calculate_runtime(start_time(), end_time()));
                    break;

                case 9:
                	System.out.println("Thank you for using eternity");
                    break;
                default:
                    System.out.println("Please provide a valid input");
                    break;
            }
        }
        while (userFunctionSelection != 9);

        // END
        input.close();
    }

    // file writing
	private static void write_Time(double runtime){
		String desktopPath = System.getProperty("user.home") + "/Desktop/CALCULATOR/";
		String filePath = desktopPath + "/Calculator_Runtime.txt";

    	try {
			// Create path to check if directory tree exists
			Path path = Paths.get(filePath);
			File file = path.getParent().toFile();
			if (!file.exists()) file.mkdirs();
		} catch (Exception e) {
			System.err.println("Error creating dir: " + filePath);
			e.printStackTrace();
		}

		try (FileWriter writer = new FileWriter(filePath, true)) {
            // Write some content to the file
            writer.write("Function Runtime (nanoseconds): "  + runtime +  " ns\n" +
				"Function Runtime (milliseconds): " + runtime/1000000 + " ms\n\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
	}
	private static double calculate_runtime(double start, double end) {
		return end - start;
	}
	private static double start_time(){
		return System.nanoTime();
	}
	private static double end_time(){
		return System.nanoTime();
	}


}
