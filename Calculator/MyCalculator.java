package Calculator;

/*********************************************
SOURCE: https://www.javatpoint.com/calculator-in-java
**********************************************/
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Functions.Function;
import Utilities.Utilities;
/*********************************************/

public class MyCalculator extends JFrame {

    boolean setClear = true;
    double number[] = new double[10], memValue[] = new double[10];
    String op;
    
    static int inputNeeded = -1, functionChoice = -1;

    // Button text arrays
    String digitButtonText[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "_"};
    String operatorButtonText[] = {"/", "sqrt", "*", "%", "-", "1/X", "+", "="};
    String memoryButtonText[] = {"MC", "MR", "MS", "M+"};
    String specialButtonText[] = {"Backspc", "C", "CE"};
    String transcendButtonText[] = Utilities.Constant.transcendButtonText;
    
    // Button arrays
    MyDigitButton[] digitButton = new MyDigitButton[digitButtonText.length];
    MyOperatorButton[] operatorButton = new MyOperatorButton[operatorButtonText.length];
    MyMemoryButton[] memoryButton = new MyMemoryButton[memoryButtonText.length];
    MySpecialButton[] specialButton = new MySpecialButton[specialButtonText.length];
    MyTranscendButton[] transcendButton = new MyTranscendButton[transcendButtonText.length];

    // Labels for display and memory
    JLabel displayLabel = new JLabel("0", JLabel.RIGHT);
    JLabel instructLabel = new JLabel(":^)", JLabel.RIGHT);
    JLabel memLabel = new JLabel(" ", JLabel.RIGHT);

    // Constants for layout
    final int FRAME_WIDTH = Utilities.Constant.FRAME_WIDTH, FRAME_HEIGHT = Utilities.Constant.FRAME_HEIGHT;
    final int HEIGHT = Utilities.Constant.HEIGHT, WIDTH = Utilities.Constant.WIDTH;
    final int H_SPACE = Utilities.Constant.H_SPACE, V_SPACE = Utilities.Constant.V_SPACE;
    final int TOPX = Utilities.Constant.TOPX, TOPY = Utilities.Constant.TOPY;

    public MyCalculator(String frameText) {
        super(frameText);

        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        int tempX = TOPX, y = TOPY;

        // Display Label
        displayLabel.setBounds(tempX, y, 400, HEIGHT);
        displayLabel.setBackground(Color.BLUE);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setOpaque(true);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(displayLabel);
        
        // Instruction Label
        instructLabel.setBounds(tempX, FRAME_HEIGHT - y - HEIGHT/2, 400, HEIGHT/2);
        instructLabel.setBackground(Color.BLACK);
        instructLabel.setForeground(Color.WHITE);
        instructLabel.setOpaque(true);
        instructLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        add(instructLabel);

        // Memory Label
        memLabel.setBounds(TOPX, TOPY + HEIGHT + V_SPACE, WIDTH, HEIGHT);
        add(memLabel);

        // Memory Buttons
        tempX = TOPX;
        y = TOPY + 2 * (HEIGHT + V_SPACE);
        for (int i = 0; i < memoryButton.length; i++) {
            memoryButton[i] = new MyMemoryButton(tempX, y, WIDTH, HEIGHT, memoryButtonText[i], this);
            memoryButton[i].setForeground(Color.RED);
            y += HEIGHT + V_SPACE;
        }

        // Special Buttons
        tempX = TOPX + (WIDTH + H_SPACE);
        y = TOPY + HEIGHT + V_SPACE;
        for (int i = 0; i < specialButton.length; i++) {
            specialButton[i] = new MySpecialButton(tempX, y, WIDTH * 2, HEIGHT, specialButtonText[i], this);
            specialButton[i].setForeground(Color.RED);
            tempX += 2 * WIDTH + H_SPACE;
        }

        // Digit Buttons
        tempX = TOPX + WIDTH + H_SPACE;
        y = TOPY + 2 * (HEIGHT + V_SPACE);
        for (int i = 0; i < digitButton.length; i++) {
            digitButton[i] = new MyDigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
            digitButton[i].setForeground(Color.BLUE);
            tempX += WIDTH + H_SPACE;
            if ((i + 1) % 3 == 0) {
                tempX = TOPX + WIDTH + H_SPACE;
                y += HEIGHT + V_SPACE;
            }
        }

        // Operator Buttons
        int opsX = tempX + 3 * (WIDTH + H_SPACE);
        y = TOPY + 2 * (HEIGHT + V_SPACE);
        for (int i = 0; i < operatorButton.length; i++) {
            operatorButton[i] = new MyOperatorButton(opsX, y, WIDTH, HEIGHT, operatorButtonText[i], this);
            operatorButton[i].setForeground(Color.RED);
            opsX += WIDTH + H_SPACE;
            if ((i + 1) % 2 == 0) {
                opsX = tempX + 3 * (WIDTH + H_SPACE);
                y += HEIGHT + V_SPACE;
            }
        }
        
        // Transcend Buttons
        int transX = TOPX + WIDTH + H_SPACE;
        y += V_SPACE;
        for (int i = 0; i < operatorButton.length; i++) {
            transcendButton[i] = new MyTranscendButton(transX, y, WIDTH, HEIGHT, transcendButtonText[i], this);
            transcendButton[i].setForeground(Color.RED);
            transX += WIDTH + H_SPACE;
            if ((i + 1) % 5 == 0) {
            	transX = TOPX + WIDTH + H_SPACE;
                y += HEIGHT + V_SPACE;
            }
        }
        
        // Draw
        draw();

        // Show the window
        setVisible(true);
    }
    
	private static void draw() {
		// TODO Auto-generated method stub
		
	}

    public static String getFormattedText(double temp) {
        String resText = "" + temp;
        if (resText.lastIndexOf(".0") > 0)
            resText = resText.substring(0, resText.length() - 2);
        return resText;
    }
    
    public static int autosetFont(int width, int height, String cap) {
        // Set the font size for the text

		return 15;
	}

    public static void main(String[] args) {
        new MyCalculator("Eternity");
    }
}

////////////////////////////////////////////////

class MyDigitButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyDigitButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
        double font = MyCalculator.autosetFont(width, height, cap);
        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 18));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    static boolean isInString(String s, char ch) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) return true;
        }
        return false;
    }

    public void actionPerformed(ActionEvent ev) {
        String tempText = ((MyDigitButton) ev.getSource()).getText();

        if (tempText.equals(".")) {
            if (cl.setClear) {
                cl.displayLabel.setText("0.");
                cl.setClear = false;
            } else if (!isInString(cl.displayLabel.getText(), '.')) {
                cl.displayLabel.setText(cl.displayLabel.getText() + ".");
            }
            return;
        }

        int index;
        try {
            index = Integer.parseInt(tempText);
        } catch (NumberFormatException e) {
            return;
        }

        if (index == 0 && cl.displayLabel.getText().equals("0")) return;

        if (cl.setClear) {
            cl.displayLabel.setText("" + index);
            cl.setClear = false;
        } else {
            cl.displayLabel.setText(cl.displayLabel.getText() + index);
        }
    }
}

////////////////////////////////////////////////

class MyOperatorButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyOperatorButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
        double font = MyCalculator.autosetFont(width, height, cap);
        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 10));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        String opText = ((MyOperatorButton) ev.getSource()).getText();

        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        if (opText.equals("1/x")) {
            try {
                double tempd = 1 / temp;
                cl.displayLabel.setText(MyCalculator.getFormattedText(tempd));
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }
        if (opText.equals("sqrt")) {
            try {
                double tempd = Math.sqrt(temp);
                cl.displayLabel.setText(MyCalculator.getFormattedText(tempd));
            } catch (ArithmeticException excp) {
                cl.displayLabel.setText("Divide by 0.");
            }
            return;
        }

        if (!opText.equals("=")) {
            cl.number[0] = temp;
            cl.op = opText;
            return;
        }

        // Perform the operation
        switch (cl.op) {
            case "+":
                temp += cl.number[0];
                break;
            case "-":
                temp = cl.number[0] - temp;
                break;
            case "*":
                temp *= cl.number[0];
                break;
            case "%":
                try {
                    temp = cl.number[0] % temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
            case "/":
                try {
                    temp = cl.number[0] / temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
        }

        cl.displayLabel.setText(MyCalculator.getFormattedText(temp));
    }
}

////////////////////////////////////////////////

class MyMemoryButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyMemoryButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
        double font = MyCalculator.autosetFont(width, height, cap);
        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 10));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
        char memOp = ((MyMemoryButton) ev.getSource()).getText().charAt(1);
        cl.setClear = true;
        double temp = Double.parseDouble(cl.displayLabel.getText());

        switch (memOp) {
            case 'C':
                cl.memLabel.setText(" ");
                cl.memValue[0] = 0.0;
                break;
            case 'R':
                cl.displayLabel.setText(MyCalculator.getFormattedText(cl.memValue[0]));
                break;
            case 'S':
                cl.memValue[0] = 0.0;
                break;
            case '+':
                cl.memValue[0] += temp;
                cl.memLabel.setText(cl.displayLabel.getText().equals("0") ? " " : "M");
                break;
        }
    }
}

////////////////////////////////////////////////

class MySpecialButton extends JButton implements ActionListener {
    private MyCalculator cl;
    
    private Layout layoutInfo;

    public MySpecialButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
        double font = MyCalculator.autosetFont(width, height, cap);
        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 18));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

	static String backSpace(String s) {
        return s.length() > 1 ? s.substring(0, s.length() - 1) : "0";
    }

    public void actionPerformed(ActionEvent ev) {
        String opText = ((MySpecialButton) ev.getSource()).getText();

        if (opText.equals("Backspc")) {
            cl.displayLabel.setText(backSpace(cl.displayLabel.getText()));
            return;
        }

        if (opText.equals("C")) {
            cl.number[0] = 0.0;
            cl.op = " ";
            cl.memValue[0] = 0.0;
            cl.memLabel.setText(" ");
        } else if (opText.equals("CE")) {
            cl.displayLabel.setText("0");
            cl.setClear = true;
        }
    }
}

////////////////////////////////////////////////

class MyTranscendButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyTranscendButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
        double font = MyCalculator.autosetFont(width, height, cap);
        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 8));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
    	String transText = ((MyTranscendButton) ev.getSource()).getText();

        switch (transText) {
	        case "arccos(x)":
	            break;
	        case "ab^x":
	        	System.out.println("Please input value of a b x");
//	        	double a = input.nextDouble(), b = input.nextDouble(), x = input.nextDouble();
//	        	System.out.println(f.abx(a, b, x));
	            break;
	        case "log_b(x)":
	        	System.out.println("Please input value of base b and value x");
//	        	double b_3 = input.nextDouble(), x_3 = input.nextDouble();
//	        	System.out.println(f.log(b_3, x_3));
	            break;
	        case "gamma":
	            break;
	        case "MAD":
	        	System.out.println("Please input value of X myu N");
//	        	double X = input.nextDouble(), myu = input.nextDouble(), N = input.nextDouble();
//	        	System.out.println(f.MAD(X, myu, N));
	            break;
	        case "Std Deviation":
	        	System.out.println("Please input data values with a space between each (e.g. 5 1 3)");
//	            input.nextLine();
//	            String dataString = input.nextLine();
//	            String[] dataStrArray = dataString.split("\\s+");
//	            double[] data = new double[dataStrArray.length];
//	            for (int i = 0; i < data.length; i++) {
//	                data[i] = Double.parseDouble(dataStrArray[i]);
//	            }
//	            System.out.println(f.stdDeviation(data));
	            break;
	        case "sinh(x)":
	            break;
	        case "x^y":
	        	System.out.println("Please input data values with a space between each (e.g. 5 1 3)");
	        	cl.instructLabel.setText("Please input data values with a space between each (e.g. 5 1 3)");
	        	Function.functionChoice = 8;
	            break;
	
	        default:
	            System.err.println("Transcendental function does not exist!");
	            break;
        }
    }
}

////////////////////////////////////////////////

// Record class to hold information of button & button layout
class Layout {
	public static int layout[][] = new int[100][100];
	
	int posX, posY;
	double font;
	Color color;
	
	/*
	 * @param posX
	 * @param posY
	 * @param font
	 * @param color
	 */
	public Layout(int posX, int posY, double font, Color color) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.font = font;
		this.color = color;
	}
	
	public Layout() {
		this(-1, -1, -1, Color.BLACK);
	}
}
