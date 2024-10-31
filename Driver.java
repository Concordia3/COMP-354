import java.util.Scanner;
import java.io.*; // idk if we're gonna need this or not, its imported just in case

public class Driver {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int userFunctionSelection = 0;
        do{
            System.out.println("\nWelcome to Eternity, please select a function:\n" +
                "1- arccos(x)\n" + "2- ab^(x)\n" + "3- log_b (x)\n" +
                "4- Γ(x)\n" + "5- MAD\n" + "6- σ\n" + "7- sinh(x)\n" +
                "8- x^y\n" + "9- exit\n");

            userFunctionSelection = input.nextInt();

            switch (userFunctionSelection) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;


                default:
                    System.out.println("Please provide a vald input");
                    break;
            }
        }
        while (userFunctionSelection != 9);

        input.close();
    }
}
