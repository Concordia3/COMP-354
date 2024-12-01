package Calculator;

/*********************************************
SOURCE: https://www.javatpoint.com/calculator-in-java
**********************************************/

/**
 * Layout and Exception packages
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Audio and Swing packages
 */
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import Functions.Function;
import Utilities.Utilities;
import Audio.Audio;

/*********************************************/
/**
 * Main calculator class
 */
@SuppressWarnings("serial")
public class MyCalculator extends JFrame implements ActionListener {
    public static boolean isRunning = false;

    boolean setClear = true;
    ArrayList<Double> number = new ArrayList<Double>(10);
    double memValue[] = new double[10];
    public String input="", op="";

    // Button text arrays
    String digitButtonText[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "_"};
    String operatorButtonText[] = {"/", "sqrt", "*", "%", "-", "+/-", "+", "="};
    String memoryButtonText[] = {"MC", "MR", "MS", "M+"};
    String specialButtonText[] = {"Backspc", "C", "CE"};
    String transcendButtonText[] = Utilities.Constant.transcendButtonText;
    
    // Button arrays
    MyDigitButton[] digitButton = new MyDigitButton[digitButtonText.length];
    MyDigitButton[] bigDigitButton = new MyDigitButton[digitButtonText.length];
    MyOperatorButton[] operatorButton = new MyOperatorButton[operatorButtonText.length];
    MyOperatorButton[] bigOperatorButton = new MyOperatorButton[operatorButtonText.length];
    MyMemoryButton[] memoryButton = new MyMemoryButton[memoryButtonText.length];
    MyMemoryButton[] bigMemoryButton = new MyMemoryButton[memoryButtonText.length];
    MySpecialButton[] specialButton = new MySpecialButton[specialButtonText.length];
    MySpecialButton[] bigSpecialButton = new MySpecialButton[specialButtonText.length];
    MyTranscendButton[] transcendButton = new MyTranscendButton[transcendButtonText.length];
    MyTranscendButton[] bigTranscendButton = new MyTranscendButton[transcendButtonText.length];

    // Labels for display and memory
    JLabel displayLabel = new JLabel("0", JLabel.RIGHT);
    JLabel instructLabel = new JLabel(" :^)  ", JLabel.RIGHT);
    JLabel memLabel = new JLabel(" ", JLabel.RIGHT);

    // Constants for layout
    final int FRAME_WIDTH = Utilities.Constant.FRAME_WIDTH, FRAME_HEIGHT = Utilities.Constant.FRAME_HEIGHT;
    final int HEIGHT = Utilities.Constant.HEIGHT, WIDTH = Utilities.Constant.WIDTH;
    final int H_SPACE = Utilities.Constant.H_SPACE, V_SPACE = Utilities.Constant.V_SPACE;
    final int TOPX = Utilities.Constant.TOPX, TOPY = Utilities.Constant.TOPY;

    // Menu
    JMenuBar menuBar;
    JMenu settingsMenu;
    JMenuItem hoverItem;
    JMenuItem clickItem;
    static boolean hoverSet = false;
    static boolean clickSet = false;
    
    private MouseAdapter[] hoverListenersDigit;
    private MouseAdapter[] hoverListenersOper;
    private MouseAdapter[] hoverListenersMem;
    private MouseAdapter[] hoverListenersSpec;
    private MouseAdapter[] hoverListenersTran;
    
    private MouseAdapter[] audioListenersDigit;
    private MouseAdapter[] audioListenersOper;
    private MouseAdapter[] audioListenersMem;
    private MouseAdapter[] audioListenersSpec;
    private MouseAdapter[] audioListenersTran;
    /**
     * 
     * @param frameText input for window text
     * @throws Exception for any exception to restart calculator by default
     */
        public MyCalculator(String frameText) throws Exception {

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
            instructLabel.setBounds(tempX, FRAME_HEIGHT - y - HEIGHT/2 - 20, 412, HEIGHT/2);
            instructLabel.setBackground(Color.BLACK);
            instructLabel.setForeground(Color.white);
            instructLabel.setOpaque(true);
            instructLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            add(instructLabel);
    
            // Memory Label
            memLabel.setBounds(TOPX, TOPY + HEIGHT + V_SPACE, WIDTH, HEIGHT);
            add(memLabel);
    
            
    
            // Memory Buttons and zoomed in version
            tempX = TOPX;
            y = TOPY + 2 * (HEIGHT + V_SPACE);
            for (int i = 0; i < memoryButton.length; i++) {
                memoryButton[i] = new MyMemoryButton(tempX, y, WIDTH, HEIGHT, memoryButtonText[i], this);
                memoryButton[i].setFocusable(false);
                memoryButton[i].setBackground(new Color(54, 69, 79));
                memoryButton[i].setForeground(Color.white);
                bigMemoryButton[i] = new MyMemoryButton(tempX, y, WIDTH + 50, HEIGHT + 50, memoryButtonText[i], this);
                bigMemoryButton[i].setFocusable(false);
                bigMemoryButton[i].setBackground(new Color(54, 69, 79));
                bigMemoryButton[i].setForeground(Color.white);
                bigMemoryButton[i].setFont(new Font("Arial", Font.PLAIN, 28));
                bigMemoryButton[i].setVisible(false);
                y += HEIGHT + V_SPACE;
            }
    
            // Special Buttons and zoomed in version
            tempX = TOPX + (WIDTH + H_SPACE) - 70;
            y = TOPY + HEIGHT + V_SPACE;
            for (int i = 0; i < specialButton.length; i++) {
                
                specialButton[i] = new MySpecialButton(tempX, y, WIDTH * 2, HEIGHT, specialButtonText[i], this);
                specialButton[i].setFocusable(false);
                specialButton[i].setBackground(new Color(54, 69, 79));
                specialButton[i].setForeground(Color.white);
                if (i == 1) {
                    tempX -= 40;
                }
                else if (i == 2) {
                    tempX -= 20;
                }
                bigSpecialButton[i] = new MySpecialButton(tempX, y, (WIDTH * 2) + 100, HEIGHT + 50, specialButtonText[i], this);
                bigSpecialButton[i].setFocusable(false);
                bigSpecialButton[i].setBackground(new Color(54, 69, 79));
                bigSpecialButton[i].setForeground(Color.white);
                bigSpecialButton[i].setFont(new Font("Arial", Font.PLAIN, 28));
                bigSpecialButton[i].setVisible(false);
                if (i == 1) {
                    tempX += 40;
                }
                else if (i == 2) {
                    tempX += 20;
                }
                tempX += 2 * WIDTH + H_SPACE + 15;
            }
    
            // Digit Buttons and zoomed in version
            tempX = TOPX + WIDTH + H_SPACE;
            y = TOPY + 2 * (HEIGHT + V_SPACE);
            for (int i = 0; i < digitButton.length; i++) {
                digitButton[i] = new MyDigitButton(tempX, y, WIDTH, HEIGHT, digitButtonText[i], this);
                digitButton[i].setFocusable(false);
                digitButton[i].setBackground(new Color(32, 32, 32));
                digitButton[i].setForeground(Color.white);
                bigDigitButton[i] = new MyDigitButton(tempX - 20, y - 20, WIDTH + 50, HEIGHT + 50, digitButtonText[i], this);
                bigDigitButton[i].setFocusable(false);
                bigDigitButton[i].setBackground(new Color(32, 32, 32));
                bigDigitButton[i].setForeground(Color.white);
                bigDigitButton[i].setFont(new Font("Arial", Font.PLAIN, 28));
                bigDigitButton[i].setVisible(false);
                tempX += WIDTH + H_SPACE;
                if ((i + 1) % 3 == 0) {
                    tempX = TOPX + WIDTH + H_SPACE;
                    y += HEIGHT + V_SPACE;
                }
            }
    
            // Operator Buttons and zoomed in version
            int opsX = tempX + 3 * (WIDTH + H_SPACE);
            y = TOPY + 2 * (HEIGHT + V_SPACE);
            for (int i = 0; i < operatorButton.length; i++) {
                operatorButton[i] = new MyOperatorButton(opsX, y, WIDTH, HEIGHT, operatorButtonText[i], this);
                operatorButton[i].setFocusable(false);
                operatorButton[i].setBackground(new Color(54, 69, 79));
                operatorButton[i].setForeground(Color.white);
                if ((i + 1) % 2 == 0) {
                    opsX -= 20;
                }
                bigOperatorButton[i] = new MyOperatorButton(opsX - 20, y - 20, WIDTH + 50, HEIGHT + 50, operatorButtonText[i], this);
                bigOperatorButton[i].setFocusable(false);
                bigOperatorButton[i].setBackground(new Color(54, 69, 79));
                bigOperatorButton[i].setForeground(Color.white);
                bigOperatorButton[i].setFont(new Font("Arial", Font.PLAIN, 28));
                bigOperatorButton[i].setVisible(false);
                if ((i + 1) % 2 == 0) {
                    opsX += 20;
                }
                opsX += WIDTH + H_SPACE;
                if ((i + 1) % 2 == 0) {
                    opsX = tempX + 3 * (WIDTH + H_SPACE);
                    y += HEIGHT + V_SPACE;
                }
            }
            
            // Transcend Buttons and zoomed in version
            int transX = TOPX + WIDTH + H_SPACE - 70;
            y += V_SPACE;
            for (int i = 0; i < transcendButton.length; i++) {
                transcendButton[i] = new MyTranscendButton(transX, y, WIDTH + 12, HEIGHT, transcendButtonText[i], this);
                transcendButton[i].setFocusable(false);
                transcendButton[i].setBackground(new Color(54, 69, 79));
                transcendButton[i].setMargin(getInsets());
                transcendButton[i].setForeground(Color.white);
                bigTranscendButton[i] = new MyTranscendButton(transX - 20, y - 20, WIDTH + 80, HEIGHT + 50, transcendButtonText[i], this);
                bigTranscendButton[i].setFocusable(false);
                bigTranscendButton[i].setBackground(new Color(54, 69, 79));
                bigTranscendButton[i].setMargin(getInsets());
                bigTranscendButton[i].setForeground(Color.white);
                bigTranscendButton[i].setFont(new Font("Arial", Font.PLAIN, 28));
                bigTranscendButton[i].setVisible(false);
                transX += WIDTH + H_SPACE + 15;
                if ((i + 1) % 5 == 0) {
                    transX = TOPX + WIDTH + H_SPACE - 70;
                    y += HEIGHT + V_SPACE;
                }
            }
    
            // Bar to hold settings
            menuBar = new JMenuBar();

            // Settings
            settingsMenu = new JMenu("Settings");
            menuBar.add(settingsMenu);
            
            // Setting Toggles
            hoverItem = new JMenuItem("Hover Mode");
            clickItem = new JMenuItem("Text to Speech");
    
            hoverItem.addActionListener(this);
            clickItem.addActionListener(this);
            
            // adds items to the menu
            settingsMenu.add(hoverItem);
            settingsMenu.add(clickItem);
           
            // listeners for Hover Mode and Text to Speech
            hoverListenersDigit = new MouseAdapter[digitButton.length];
            hoverListenersOper = new MouseAdapter[operatorButton.length];
            hoverListenersMem = new MouseAdapter[memoryButton.length];
            hoverListenersSpec = new MouseAdapter[specialButton.length];
            hoverListenersTran = new MouseAdapter[transcendButton.length];

            audioListenersDigit = new MouseAdapter[digitButton.length];
            audioListenersOper = new MouseAdapter[operatorButton.length];
            audioListenersMem = new MouseAdapter[memoryButton.length];
            audioListenersSpec = new MouseAdapter[specialButton.length];
            audioListenersTran = new MouseAdapter[transcendButton.length];
    
            // makes Eternity visible
            setJMenuBar(menuBar);
            setVisible(true);
        } 
    
        /**
         * 
         * @param i index for digit buttons
         * @return gives hover capabilities
         */
        public MouseAdapter hoverFuncDigit(int i) {
    
            return new MouseAdapter() {
    
            @Override
            public void mouseEntered(MouseEvent evt) {                                  // when mouse enters button
                
                bigDigitButton[i].setVisible(true);                               // activates big button
                digitButton[i].setOpaque(false);                                // makes digit button transparent
                digitButton[i].setContentAreaFilled(false);                           // removes highlight
                digitButton[i].setBorderPainted(false);                                 // removes content border
                digitButton[i].setFont(new Font("Arial", Font.PLAIN, 1));       // makes font virtually invisible, but text is not erased
                
            }
    
            @Override
            public void mouseExited(MouseEvent evt) {                                   // when mouse exits button
                
                bigDigitButton[i].setVisible(false);                            // deactivates big button
                digitButton[i].setOpaque(true);                             // makes digit button opaque
                digitButton[i].setContentAreaFilled(true);                          // adds highlight
                digitButton[i].setBorderPainted(true);                              // adds content border 
                digitButton[i].setFont(new Font("Arial", Font.PLAIN, 18));  // reimplements font settings and so on...
    
            } };
    
        }
    
        /**
         * 
         * @param i index for operator buttons
         * @return gives hover capabilities
         */
        public MouseAdapter hoverFuncOper(int i) {
    
            return new MouseAdapter() {
    
            @Override
            public void mouseEntered(MouseEvent evt) {
                
                bigOperatorButton[i].setVisible(true);
                operatorButton[i].setOpaque(false);
                operatorButton[i].setContentAreaFilled(false);
                operatorButton[i].setBorderPainted(false);
                operatorButton[i].setFont(new Font("Arial", Font.PLAIN, 1));
    
            }
    
            @Override
            public void mouseExited(MouseEvent evt) {
                
                bigOperatorButton[i].setVisible(false);
                operatorButton[i].setOpaque(true);
                operatorButton[i].setContentAreaFilled(true);
                operatorButton[i].setBorderPainted(true);
                operatorButton[i].setFont(new Font("Arial", Font.PLAIN, 14));
    
            } };
    
        }
    
        /**
         * 
         * @param i index for memory buttons
         * @return gives hover capabilities
         */
        public MouseAdapter hoverFuncMem(int i) {
    
            return new MouseAdapter() {
    
            @Override
            public void mouseEntered(MouseEvent evt) {
                
                bigMemoryButton[i].setVisible(true);
                memoryButton[i].setOpaque(false);
                memoryButton[i].setContentAreaFilled(false);
                memoryButton[i].setBorderPainted(false);
                memoryButton[i].setFont(new Font("Arial", Font.PLAIN, 1));
    
            }
    
            @Override
            public void mouseExited(MouseEvent evt) {
                
                bigMemoryButton[i].setVisible(false);
                memoryButton[i].setOpaque(true);
                memoryButton[i].setContentAreaFilled(true);
                memoryButton[i].setBorderPainted(true);
                memoryButton[i].setFont(new Font("Arial", Font.PLAIN, 14));
    
            } };
    
        }
    
        /**
         * 
         * @param i index for function buttons
         * @return gives hover capabilities
         */
        public MouseAdapter hoverFuncSpec(int i) {
    
            return new MouseAdapter() {
    
                @Override
                public void mouseEntered(MouseEvent evt) {
        
                    bigSpecialButton[i].setVisible(true);
                    specialButton[i].setOpaque(false);
                    specialButton[i].setContentAreaFilled(false);
                    specialButton[i].setBorderPainted(false);
                    specialButton[i].setFont(new Font("Arial", Font.PLAIN, 1));
        
                }
        
                @Override
                public void mouseExited(MouseEvent evt) {
        
                    bigSpecialButton[i].setVisible(false);
                    specialButton[i].setOpaque(true);
                    specialButton[i].setContentAreaFilled(true);
                    specialButton[i].setBorderPainted(true);
                    specialButton[i].setFont(new Font("Arial", Font.PLAIN, 18));
    
                } };
    
        }
    
        /**
         * 
         * @param i index for transcendant buttons
         * @return gives hover capabilities
         */
        public MouseAdapter hoverFuncTran(int i) {
    
            return new MouseAdapter() {
    
                @Override
                public void mouseEntered(MouseEvent evt) {
        
                    bigTranscendButton[i].setVisible(true);
                    transcendButton[i].setOpaque(false);
                    transcendButton[i].setContentAreaFilled(false);
                    transcendButton[i].setBorderPainted(false);
                    transcendButton[i].setFont(new Font("Arial", Font.PLAIN, 1)); 
        
                }
        
                @Override
                public void mouseExited(MouseEvent evt) {
        
                    bigTranscendButton[i].setVisible(false);
                    transcendButton[i].setOpaque(true);
                    transcendButton[i].setContentAreaFilled(true);
                    transcendButton[i].setBorderPainted(true);
                    transcendButton[i].setFont(new Font("Arial", Font.PLAIN, 12)); 
    
                } };
    
        }
    
        /**
         * 
         * @param i index for digit buttons
         * @return gives text to speech capabilities
         */
        public MouseAdapter audioFuncDigit(int i) {

            return new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent ev) {                   // when button is pressed
                    
                    try {

                        Audio.playSound(digitButtonText[i]);                // plays digit button audio and so on...

                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException er) {
                        er.printStackTrace();
                    }

                    
                }
            };

        }

        /**
         * 
         * @param i index for operator buttons
         * @return gives text to speech capabilities
         */
        public MouseAdapter audioFuncOper(int i) {

            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    
                    try {
                        Audio.playSound(operatorButtonText[i]);
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException er) {
                        er.printStackTrace();
                    }

                    
                }
            };

        }

        /**
         * 
         * @param i index for memory buttons
         * @return gives text to speech capabilities
         */
        public MouseAdapter audioFuncMem(int i) {

            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    
                    try {
                        Audio.playSound(memoryButtonText[i]);
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException er) {
                        er.printStackTrace();
                    }
                    
                }
            };

        }

        /**
         * 
         * @param i index for special buttons
         * @return gives text to speech capabilities
         */
        public MouseAdapter audioFuncSpec(int i) {

            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    
                    try {
                        Audio.playSound(specialButtonText[i]);
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException er) {
                        er.printStackTrace();
                    } 
                }
            };

        }

        /**
         * 
         * @param i index for transcendant buttons
         * @return gives text to speech capabilities
         */
        public MouseAdapter audioFuncTran(int i) {

            return new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    
                    try {
                        Audio.playSound(transcendButtonText[i]);
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException er) {
                        er.printStackTrace();
                    }

                    
                }
            };

        }

        
        /**
         * 
         * @param ev mouse event
         */
        @Override
        public void actionPerformed(ActionEvent ev) {
    
            if (ev.getSource() == hoverItem) {                          // handles Hover Mode
    
                if (hoverSet) {                                         // Hover Mode disabled
    
                    hoverSet = false;                               
                    instructLabel.setText("Hover mode off");
                    
                    for (int i = 0; i < digitButton.length; i++) {
                        if (hoverListenersDigit[i] != null) {
                            digitButton[i].removeMouseListener(hoverListenersDigit[i]);   // removes Hover Mode capabilities on digit and so on...
                        }
                    }
    
                    for (int i = 0; i < operatorButton.length; i++) {
                        if (hoverListenersOper[i] != null) {
                            operatorButton[i].removeMouseListener(hoverListenersOper[i]);
                        }
                    }
    
                    for (int i = 0; i < memoryButton.length; i++) {
                        if (hoverListenersMem[i] != null) {
                            memoryButton[i].removeMouseListener(hoverListenersMem[i]);
                        }
                    }
    
                    for (int i = 0; i < specialButton.length; i++) {
                        if (hoverListenersSpec[i] != null) {
                            specialButton[i].removeMouseListener(hoverListenersSpec[i]);
                        }
                    }
    
                    for (int i = 0; i < transcendButton.length; i++) {
                        if (hoverListenersTran[i] != null) {
                            transcendButton[i].removeMouseListener(hoverListenersTran[i]);
                        }
                    }
    
                }
    
                else {                                                              // enabled Hover Mode
    
                    hoverSet = true;
                    instructLabel.setText("Hover mode on");
    
                    for (int i = 0; i < digitButton.length; i++) {

                        if (hoverListenersDigit[i] == null){

                            hoverListenersDigit[i] = hoverFuncDigit(i);                     // loads hover mouse settings

                        }
                        
                        digitButton[i].addMouseListener(hoverListenersDigit[i]);            // gives each digit button hover functionality and so on...

                    }    
                      
                    for (int i = 0; i < operatorButton.length; i++) {

                        if (hoverListenersOper[i] == null){

                            hoverListenersOper[i] = hoverFuncOper(i);

                        }
                        
                        operatorButton[i].addMouseListener(hoverListenersOper[i]);

                    }
    
                    for (int i = 0; i < memoryButton.length; i++) {

                        if (hoverListenersMem[i] == null){

                            hoverListenersMem[i] = hoverFuncMem(i);

                        }
                        
                        memoryButton[i].addMouseListener(hoverListenersMem[i]);

                    }
    
                    for (int i = 0; i < specialButton.length; i++) {

                        if (hoverListenersSpec[i] == null){

                            hoverListenersSpec[i] = hoverFuncSpec(i);

                        }
    
                        specialButton[i].addMouseListener(hoverListenersSpec[i]);

                    }
                    
                    for (int i = 0; i < transcendButton.length; i++) {

                        if (hoverListenersTran[i] == null){

                            hoverListenersTran[i] = hoverFuncTran(i);

                        }
                        
                        transcendButton[i].addMouseListener(hoverListenersTran[i]);

                    }
    
                }
    
            }
    
            if (ev.getSource() == clickItem) {                          // handles text to speech mode
    
                if (clickSet) {                                         // text to speech mode disabled
    
                    clickSet = false;
                    instructLabel.setText("Text to Speech mode off");
                    
                    for (int i = 0; i < digitButton.length; i++) {

                        if (audioListenersDigit[i] != null) {   

                            digitButton[i].removeMouseListener(audioListenersDigit[i]);   // removes audio capabilities from digit buttons and so on...
                            
                        }

                    }
    
                    for (int i = 0; i < operatorButton.length; i++) {

                        if (audioListenersOper[i] != null) {

                            operatorButton[i].removeMouseListener(audioListenersOper[i]);

                        }

                    }
    
                    for (int i = 0; i < memoryButton.length; i++) {

                        if (audioListenersMem[i] != null) {

                            memoryButton[i].removeMouseListener(audioListenersMem[i]);

                        }

                    }
    
                    for (int i = 0; i < specialButton.length; i++) {

                        if (audioListenersSpec[i] != null) {

                            specialButton[i].removeMouseListener(audioListenersSpec[i]);

                        }

                    }
    
                    for (int i = 0; i < transcendButton.length; i++) {

                        if (audioListenersTran[i] != null) {

                            transcendButton[i].removeMouseListener(audioListenersTran[i]);

                        }

                    }
    
                }
    
                else {                                                              // Text to Speech mode enabled
    
                    clickSet = true;
                    instructLabel.setText("Text to Speech mode on");

                    for (int i = 0; i < digitButton.length; i++) {

                        if (audioListenersDigit[i] == null){

                            audioListenersDigit[i] = audioFuncDigit(i);             // loads audio settings 

                        }

                        digitButton[i].addMouseListener(audioListenersDigit[i]);    // gives each digit button audio capabilities and so on...

                    }    
                      
                    for (int i = 0; i < operatorButton.length; i++) {

                        if (audioListenersOper[i] == null){

                            audioListenersOper[i] = audioFuncOper(i);

                        }
                        
                        operatorButton[i].addMouseListener(audioListenersOper[i]);

                    }
    
                    for (int i = 0; i < memoryButton.length; i++) {

                        if (audioListenersMem[i] == null){

                            audioListenersMem[i] = audioFuncMem(i);

                        }
                        
                        memoryButton[i].addMouseListener(audioListenersMem[i]);

                    }
    
                    for (int i = 0; i < specialButton.length; i++) {

                        if (audioListenersSpec[i] == null){

                            audioListenersSpec[i] = audioFuncSpec(i);

                        }
    
                        specialButton[i].addMouseListener(audioListenersSpec[i]);

                    }
                    
                    for (int i = 0; i < transcendButton.length; i++) {

                        if (audioListenersTran[i] == null){

                            audioListenersTran[i] = audioFuncTran(i);

                        }
                        
                        transcendButton[i].addMouseListener(audioListenersTran[i]);

                    }
                    
                }
    
    
            }
    
        }
    
        /**
         * 
         * @param temp 
         * @return
         */
        public static String getFormattedText(double temp) {
            String resText = "" + temp;
            if (resText.lastIndexOf(".0") > 0)
                resText = resText.substring(0, resText.length() - 2);
            return resText;
        }
        
        /**
         * 
         * @param width
         * @param height
         * @param cap
         * @return 15
         */
        public static int autosetFont(int width, int height, String cap) {
            // Set the font size for the text
    
            return 15;
        }
        
        /**
         *  resets calculations
         */
        public void reset() {
            number.clear();
            setClear = true;
            op = "";
            input = "";
        }
    }
    
    /////////////////////////////////////////////////////////
/**
 * Digit button implementation class
 */
@SuppressWarnings("serial")
class MyDigitButton extends JButton implements ActionListener {

        private MyCalculator cl;
    
        /**
         * Digit button constructor
         * 
         * @param x position on the x-axis
         * @param y position on the y-axis
         * @param width horizontal dimension for button
         * @param height vertical dimension for buytton
         * @param cap text on button
         * @param clc calculator framework
         */
        public MyDigitButton(int x, int y, int width, int height, String cap, MyCalculator clc) {

            super(cap);
            setBounds(x, y, width, height);
            this.cl = clc;
            cl.add(this);
            
            // Set the font size for the text
            setFont(new Font("Arial", Font.PLAIN, 18));  // Adjust the font size as needed
    
            // Optional: Set button size explicitly, depending on the layout you are using
            setPreferredSize(new Dimension(width, height)); 
            
            addActionListener(this);
        }
    
        /**
         * finds if in string
         * 
         * @param s string
         * @param ch character
         * @return
         */
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
    
/**
 * Operator button implementation class
 */    
@SuppressWarnings("serial")
class MyOperatorButton extends JButton implements ActionListener {

        private MyCalculator cl;
    
        /**
         * operator button constructor
         * @param x position on the x-axis
         * @param y position on the y-axis
         * @param width horizontal dimension for button
         * @param height vertical dimension for buytton
         * @param cap text on button
         * @param clc calculator framework
         */
        public MyOperatorButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
            super(cap);
            setBounds(x, y, width, height);
            this.cl = clc;
            cl.add(this);
            
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
                    if (input.charAt(input.length() - 1) != ' ') {
                        String[] tokens = input.split("\s+");
                        cl.number.add(Double.parseDouble(tokens[cl.number.size()]));          
                    } 
                   Function f = new Function();
                    double result = 0;
                    
                    // TODO ~
                    try {
                        result = f.input(cl.number);
                    } catch (IllegalArgumentException e) {
                        cl.instructLabel.setText(e.toString());
                        e.printStackTrace();
                    }
                    catch (ArithmeticException e) {
                        cl.instructLabel.setText(e.toString());
                        e.printStackTrace();
                    }
                    
                    cl.displayLabel.setText(Double.toString(result));
                    cl.reset();
                }
            }
    
            double temp = Double.parseDouble(cl.displayLabel.getText());
    
            if (opText.equals("+/-")) {
                try {
                    double tempd = -temp;
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

/**
 * Memory button implementation class
 */
@SuppressWarnings("serial")
class MyMemoryButton extends JButton implements ActionListener {

    private MyCalculator cl;

     /**
         * memory button constructor
         * @param x position on the x-axis
         * @param y position on the y-axis
         * @param width horizontal dimension for button
         * @param height vertical dimension for buytton
         * @param cap text on button
         * @param clc calculator framework
         */
    public MyMemoryButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
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

/**
 * Special button implementation class
 */
@SuppressWarnings("serial")
class MySpecialButton extends JButton implements ActionListener {

	private MyCalculator cl;

    /**
         * special button constructor
         * @param x position on the x-axis
         * @param y position on the y-axis
         * @param width horizontal dimension for button
         * @param height vertical dimension for buytton
         * @param cap text on button
         * @param clc calculator framework
         */

    public MySpecialButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
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

/**
 * Transcendant button implementation class
 */
@SuppressWarnings("serial")
class MyTranscendButton extends JButton implements ActionListener {

    private MyCalculator cl;

    /**
         * Transcendant button constructor
         * @param x position on the x-axis
         * @param y position on the y-axis
         * @param width horizontal dimension for button
         * @param height vertical dimension for buytton
         * @param cap text on button
         * @param clc calculator framework
         */
    public MyTranscendButton(int x, int y, int width, int height, String cap, MyCalculator clc) {
        super(cap);
        setBounds(x, y, width, height);
        this.cl = clc;
        cl.add(this);
        
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
	            cl.instructLabel.setText("Input base a, base b and exponent x, separated by an underscore (e.g., 2_3_7).");
	            Function.functionChoice = Function.functions.abx.toString();
	            Function.inputNeeded = 3;
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
	
	        case "Gamma(x)":
	            cl.instructLabel.setText("Input a single value x for Gamma function calculation.");
	            Function.functionChoice = Function.functions.gamma.toString();
	            Function.inputNeeded = 1;
	            cl.op = Function.functionChoice;
	            cl.displayLabel.setText("Gamma(x)");
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
                  if (true) {
                    cl.instructLabel.setText("Input caused a bit overflow");
                    
                }
                Function.inputNeeded = 1;
                cl.op = Function.functionChoice;
                cl.displayLabel.setText("n!");
                break;

            case "1/x":
                cl.instructLabel.setText("Input a single value for fraction");
                Function.functionChoice = Function.functions.fraction.toString();
                Function.inputNeeded = 1;
                cl.op = Function.functionChoice;
                cl.displayLabel.setText("1/x");
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
	
	/**
     * Layout constructor
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
    
	/**
     * Default layout
     */
	public Layout() {
		this(-1, -1, -1, Color.BLACK);
	}
}
