import java.util.Scanner;
import java.io.*; // idk if we're gonna need this or not, its imported just in case

public class Driver {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        // Placeholder function class that store all functions calculation
        Function f = new Function();
        
        int userFunctionSelection = 0;
        do{
            System.out.println("\nWelcome to Eternity, please select a function:\n" +
                "1- arccos(x)\n" + "2- ab^(x)\n" + "3- log_b (x)\n" +
                "4- gamma\n" + "5- MAD\n" + "6- std Deviation\n" + "7- sinh(x)\n" +
                "8- x^y\n" + "9- exit\n");

            userFunctionSelection = input.nextInt();

            switch (userFunctionSelection) {
                case 1:
                    break;
                case 2:
                	System.out.println("Please input value of a b x");
                	double a = input.nextDouble(), b = input.nextDouble(), x = input.nextDouble();
                	System.out.println(f.abx(a, b, x));
                    break;
                case 3:
                	System.out.println("Please input value of base b and value x");
                	double b_3 = input.nextDouble(), x_3 = input.nextDouble();
                	System.out.println(f.log(b_3, x_3));
                    break;
                case 4:
                    break;
                case 5:
                	System.out.println("Please input value of X myu N");
                	double X = input.nextDouble(), myu = input.nextDouble(), N = input.nextDouble();
                	System.out.println(f.MAD(X, myu, N));
                    break;
                case 6:
                	System.out.println("Please input data values with a space between each (e.g. 5 1 3)");
                    input.nextLine();
                    String dataString = input.nextLine();
                    String[] dataStrArray = dataString.split("\\s+");
                    double[] data = new double[dataStrArray.length];
                    for (int i = 0; i < data.length; i++) {
                        data[i] = Double.parseDouble(dataStrArray[i]);
                    }
                    System.out.println(f.stdDeviation(data));
                    break;
                case 7:
                    break;
                case 8:
                	System.out.println("Please input value of x y");
                	double x_9 = input.nextDouble(), y = input.nextDouble();
                	System.out.println(f.xy(x_9, y));
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
}
