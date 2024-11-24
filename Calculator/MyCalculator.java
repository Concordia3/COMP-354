package Calculator;

/*********************************************
SOURCE: https://www.javatpoint.com/calculator-in-java
**********************************************/
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import Functions.Function;
import Utilities.Utilities;
/*********************************************/

@SuppressWarnings("serial")
public class MyCalculator extends JFrame {

    boolean setClear = true;
    ArrayList<Double> number = new ArrayList<Double>(10);
    double memValue[] = new double[10];
	public String input="", op="";

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
    JLabel instructLabel = new JLabel(" :^)  ", JLabel.RIGHT);
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
        
        getContentPane().setBackground(new Color(45, 45, 45));
        
        int tempX = TOPX, y = TOPY;

        // Display Label
        displayLabel.setBounds(tempX, y, 412, HEIGHT);
        displayLabel.setBackground(new Color(153, 255, 153));
        displayLabel.setForeground(Color.black);
        displayLabel.setOpaque(true);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        add(displayLabel);
        
        // Instruction Label
        instructLabel.setBounds(tempX, FRAME_HEIGHT - y - HEIGHT/2, 412, HEIGHT/2);
        instructLabel.setBackground(Color.BLACK);
        instructLabel.setForeground(Color.white);
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
            memoryButton[i].setFocusable(false);
            memoryButton[i].setBackground(new Color(54, 69, 79));
            memoryButton[i].setForeground(Color.white);
            y += HEIGHT + V_SPACE;
        }

        // Special Buttons
        tempX = TOPX + (WIDTH + H_SPACE) - 70;
        y = TOPY + HEIGHT + V_SPACE;
        for (int i = 0; i < specialButton.length; i++) {
            specialButton[i] = new MySpecialButton(tempX, y, WIDTH * 2, HEIGHT, specialButtonText[i], this);
            specialButton[i].setFocusable(false);
            specialButton[i].setBackground(new Color(54, 69, 79));
            specialButton[i].setForeground(Color.white);
            tempX += 2 * WIDTH + H_SPACE + 15;
        }

        // Digit Buttons
        tempX = TOPX + WIDTH + H_SPACE;
        y = TOPY + 2 * (HEIGHT + V_SPACE);
        for (int i = 0; i < digitButton.length; i++) {
            digitButton[i] = new MyDigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
            digitButton[i].setFocusable(false);
            digitButton[i].setBackground(new Color(32, 32, 32));
            digitButton[i].setForeground(Color.white);
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
            operatorButton[i].setFocusable(false);
            operatorButton[i].setBackground(new Color(54, 69, 79));
            operatorButton[i].setForeground(Color.white);
            opsX += WIDTH + H_SPACE;
            if ((i + 1) % 2 == 0) {
                opsX = tempX + 3 * (WIDTH + H_SPACE);
                y += HEIGHT + V_SPACE;
            }
        }
        
        // Transcend Buttons
        int transX = TOPX + WIDTH + H_SPACE - 70;
        y += V_SPACE;
        for (int i = 0; i < transcendButton.length; i++) {
            transcendButton[i] = new MyTranscendButton(transX, y, WIDTH + 12, HEIGHT, transcendButtonText[i], this);
            transcendButton[i].setFocusable(false);
            transcendButton[i].setBackground(new Color(54, 69, 79));
            transcendButton[i].setForeground(Color.white);
            transX += WIDTH + H_SPACE + 15;
            if ((i + 1) % 5 == 0) {
            	transX = TOPX + WIDTH + H_SPACE - 70;
                y += HEIGHT + V_SPACE;
            }
        }

        // Show the window
        setVisible(true);
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
    
    public void reset() {
		number.clear();
		setClear = true;
		op = "";
		input = "";
    }
}

////////////////////////////////////////////////

@SuppressWarnings("serial")
class MyDigitButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyDigitButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
//        double font = MyCalculator.autosetFont(width, height, cap);
//        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
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
        
        int index;
        try {
            index = Integer.parseInt(tempText);
            
            cl.input += index;
        } 
        catch (NumberFormatException e) {
            if (tempText.equals(".")) {
            	cl.input += tempText;
            	
            	cl.displayLabel.setText(cl.input);
                return;
            }
            
            if (tempText.equals("_"))
            {
                try {
                    cl.number.add(Double.parseDouble(cl.input));
                    cl.displayLabel.setText(cl.displayLabel.getText() + " ");
                } 
                catch (NumberFormatException e1) {
                    return;
                }
                finally {
					cl.input = "";
				}
            } 
        	
        	return;
        }

        // update display label
        if (cl.setClear) {
            cl.displayLabel.setText("" + index);
            cl.setClear = false;
        } else {
            cl.displayLabel.setText(cl.displayLabel.getText() + index);
        }
    }
}

////////////////////////////////////////////////

@SuppressWarnings("serial")
class MyOperatorButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyOperatorButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
//        double font = MyCalculator.autosetFont(width, height, cap);
//        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 14));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent ev) {
    	// End digit input
    	cl.input = "";
    	
        String opText = ((MyOperatorButton) ev.getSource()).getText();
        
        // Functions
        if (opText.equals("="))
        {
        	if (Function.isValidEnum(cl.op, Function.functions.class))
        	{
                String input = cl.displayLabel.getText();
                String[] tokens = input.split("\\s+");
                cl.number.add(Double.parseDouble(tokens[cl.number.size()]));
        		Function f = new Function();
        		double result = 0;
				try {
					result = f.input(cl.number);
				} catch (Exception e) {
					cl.instructLabel.setText(e.toString());
					e.printStackTrace();
				}
        		
        		cl.displayLabel.setText(Double.toString(result));
        		cl.reset();
        	}
        }

        double temp = Double.parseDouble(cl.displayLabel.getText());

        if (opText.equals("1/X")) {
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
            cl.number.add(temp);
            cl.op = opText;
            cl.setClear = true;
            return;
        }

        // Perform the operation
        if (cl.number.isEmpty()) return;
        switch (cl.op) {
            case "+":
                temp += cl.number.get(0);
                break;
            case "-":
                temp = cl.number.get(0) - temp;
                break;
            case "*":
                temp *= cl.number.get(0);
                break;
            case "%":
                try {
                    temp = cl.number.get(0) % temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
            case "/":
                try {
                    temp = cl.number.get(0) / temp;
                } catch (ArithmeticException excp) {
                    cl.displayLabel.setText("Divide by 0.");
                    return;
                }
                break;
        }

        cl.displayLabel.setText(MyCalculator.getFormattedText(temp));
        
        cl.number.remove(cl.number.size() - 1);
    }
}

////////////////////////////////////////////////

@SuppressWarnings("serial")
class MyMemoryButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyMemoryButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
//        double font = MyCalculator.autosetFont(width, height, cap);
//        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 14));  // Adjust the font size as needed

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

@SuppressWarnings("serial")
class MySpecialButton extends JButton implements ActionListener {

	private MyCalculator cl;

    public MySpecialButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
//        double font = MyCalculator.autosetFont(width, height, cap);
//        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
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
            cl.input = cl.input.length() > 1 ? cl.input.substring(0, cl.input.length() - 1) : "0";
            return;
        }

        if (opText.equals("C")) {
        	cl.number.add(0.0);
            cl.op = "";
            cl.input = "";
            cl.memValue[0] = 0.0;
            cl.memLabel.setText(" ");
        } else if (opText.equals("CE")) {
            cl.displayLabel.setText("0");
            cl.setClear = true;
            cl.reset();
        }
    }
}

////////////////////////////////////////////////

@SuppressWarnings("serial")
class MyTranscendButton extends JButton implements ActionListener {
    private MyCalculator cl;

    public MyTranscendButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
//        double font = MyCalculator.autosetFont(width, height, cap);
//        Layout layoutInfo = new Layout(x, y, font, Color.BLACK);
        
        // Set the font size for the text
        setFont(new Font("Arial", Font.PLAIN, 12));  // Adjust the font size as needed

        // Optional: Set button size explicitly, depending on the layout you are using
        setPreferredSize(new Dimension(width, height)); 
        
        addActionListener(this);
    }

    @Override
	public void actionPerformed(ActionEvent ev) {
	    String transText = ((MyTranscendButton) ev.getSource()).getText();
	
	    /*
	     * 1. Set the calculator instruction text (bottom text box)
	     * 2. Set the Function class which function is currently in progress
	     * 3. Set how many input needed (Khang removed this requirement for StdDeviation so only that can have inf input)
	     * 4. Set the calculator's function choice to reflect the Function class settings (By default is already set)
	     * 5. Set the calculator display text (top text box)
	     */
	    switch (transText) {
	        case "cos-1":
	            // 1. Set the instruction text
	            cl.instructLabel.setText("Input a single value x for arccos(x).");
	            // 2. Set the Function class function in progress
	            Function.functionChoice = Function.functions.arccos.toString();
	            // 3. Set how many input values are needed
	            Function.inputNeeded = 1;
	            // 4. Set the calculator's function choice
	            cl.op = Function.functionChoice;
	            // 5. Set the calculator display text
	            cl.displayLabel.setText("arccos(x)");
	            break;
	
	        case "ab^x":
	            cl.instructLabel.setText("Input base a and exponent b, separated by an underscore (e.g., 2_3).");
	            Function.functionChoice = Function.functions.abx.toString();
	            Function.inputNeeded = 2;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("ab^x");
	            break;
	
	        case "log":
	            cl.instructLabel.setText("Input the base and value x with a space between using underscore (e.g., 5_3).");
	            Function.functionChoice = Function.functions.log_b.toString();
	            Function.inputNeeded = 2;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("log_b(x)");
	            break;
	
	        case "Γ(x)":
	            cl.instructLabel.setText("Input a single value x for Gamma function calculation.");
	            Function.functionChoice = Function.functions.gamma.toString();
	            Function.inputNeeded = 1;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("Γ(x)");
	            break;
	
	        case "MAD":
	            cl.instructLabel.setText("Input a list of values separated by underscores (e.g., 1_2_3).");
	            Function.functionChoice = Function.functions.MAD.toString();
	            Function.inputNeeded = Integer.MAX_VALUE; // Allows multiple inputs
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("MAD");
	            break;
	
	        case "StdDev":
	            cl.instructLabel.setText("Input a list of values separated by underscores (e.g., 1_2_3).");
	            Function.functionChoice = Function.functions.StdDeviation.toString();
	            Function.inputNeeded = Integer.MAX_VALUE; // Allows multiple inputs
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("Std Dev");
	            break;
	
	        case "sinh(x)":
	            cl.instructLabel.setText("Input a single value x for sinh(x).");
	            Function.functionChoice = Function.functions.sinh.toString();
	            Function.inputNeeded = 1;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("sinh(x)");
	            break;
	
	        case "x^y":
	            cl.instructLabel.setText("Input 2 values with a space between each using underscore (e.g., 5_3).");
	            Function.functionChoice = Function.functions.xy.toString();
	            Function.inputNeeded = 2;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("x^y");
	            break;
	
            case "n!":
                cl.instructLabel.setText("Input a single value for factorial.");
                Function.functionChoice = Function.functions.factorial.toString();
                Function.inputNeeded = 1;
                cl.op = Function.functionChoice;
                cl.displayLabel.setText("n!");
                break;

            case "+/-":
                cl.instructLabel.setText("Input a single value for sign change");
                Function.functionChoice = Function.functions.signChange.toString();
                Function.inputNeeded = 1;
                cl.op = Function.functionChoice;
                cl.displayLabel.setText("+/-");
                break;

	        default:
	            System.err.println("Transcendental function does not exist!");
	            break;
	    }
	
	    cl.setClear = true;
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

/*class Rounded implements Border {

    private int radius;

    public Rounded(int radius) {

        this.radius = radius;

    }

    @Override
    public Insets getBorderInsets(Component c) {

        return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);

    }

    @Override
    public boolean isBorderOpaque(){return true;}

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

        Graphics2D gnew = (Graphics2D) g.create();
        gnew.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gnew.setColor(c.getBackground());
        gnew.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        gnew.dispose();

    }

}*/