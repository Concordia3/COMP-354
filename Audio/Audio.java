package Audio;

// packages for File handeling and audio play
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

/**
 * The Audio aspect of the Eternity calculator
 * 
 * @author Cyrus Stonebanks
 * @author Tristan Szittner-Francis
 * @author Nick Taddio
 * @author Hy Khang Tran
 * @author Jeremy Tang
 * @author Minh Thien Tran
 * @author Minghe Sun
 */

 /**
  * Audio class that play audio for calculator buttons
  */
public class Audio {

    /**
     * 
     * @param fileName input for determining which audio clip to play
     * @throws UnsupportedAudioFileException if audio file does not exist
     * @throws LineUnavailableException resource restriction exception
     * @throws IOException for file cotrol
     */
        public static void playSound(String fileName) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
    
            switch (fileName) {
                case "7":
                    Clip sevenSound = AudioSystem.getClip();                                                        // initializes Clip
                    sevenSound.open(AudioSystem.getAudioInputStream(new File("Audio/seven.wav")));          // opens Clip
                    sevenSound.start();                                                                             // plays Clip and so on...
                break;
                case "8":
                    Clip eightSound = AudioSystem.getClip();
                    eightSound.open(AudioSystem.getAudioInputStream(new File("Audio/eight.wav")));
                    eightSound.start();
                break;
                case "9":
                    Clip nineSound = AudioSystem.getClip();
                    nineSound.open(AudioSystem.getAudioInputStream(new File("Audio/nine.wav")));
                    nineSound.start();
                break;
                case "4":
                    Clip fourSound = AudioSystem.getClip();
                    fourSound.open(AudioSystem.getAudioInputStream(new File("Audio/four.wav")));
                    fourSound.start();
                break;
                case "5":
                    Clip fiveSound = AudioSystem.getClip();
                    fiveSound.open(AudioSystem.getAudioInputStream(new File("Audio/five.wav")));
                    fiveSound.start();
                break;
                case "6":
                    Clip sixSound = AudioSystem.getClip();
                    sixSound.open(AudioSystem.getAudioInputStream(new File("Audio/six.wav")));
                    sixSound.start();
                break;
                case "1":
                    Clip oneSound = AudioSystem.getClip();
                    oneSound.open(AudioSystem.getAudioInputStream(new File("Audio/one.wav")));
                    oneSound.start();
                break;
                case "2":
                    Clip twoSound = AudioSystem.getClip();
                    twoSound.open(AudioSystem.getAudioInputStream(new File("Audio/two.wav")));
                    twoSound.start();
                break;
                case "3":
                    Clip threeSound = AudioSystem.getClip();
                    threeSound.open(AudioSystem.getAudioInputStream(new File("Audio/three.wav")));
                    threeSound.start();
                break;
                case "0":
                    Clip zeroSound = AudioSystem.getClip();
                    zeroSound.open(AudioSystem.getAudioInputStream(new File("Audio/zero.wav")));
                    zeroSound.start();
                break;
                case ".":
                    Clip dotSound = AudioSystem.getClip();
                    dotSound.open(AudioSystem.getAudioInputStream(new File("Audio/dot.wav")));
                    dotSound.start();
                break;
                case "_":
                    Clip spaceSound = AudioSystem.getClip();
                    spaceSound.open(AudioSystem.getAudioInputStream(new File("Audio/space.wav")));
                    spaceSound.start();
                break;
                case "/":
                    Clip divideSound = AudioSystem.getClip();
                    divideSound.open(AudioSystem.getAudioInputStream(new File("Audio/divide.wav")));
                    divideSound.start();
                break;
                case "sqrt":
                    Clip sqrtSound = AudioSystem.getClip();
                    sqrtSound.open(AudioSystem.getAudioInputStream(new File("Audio/square_root.wav")));
                    sqrtSound.start();
                break;
                case "*":
                    Clip timesSound = AudioSystem.getClip();
                    timesSound.open(AudioSystem.getAudioInputStream(new File("Audio/times.wav")));
                    timesSound.start();
                break;
                case "%":
                    Clip modSound = AudioSystem.getClip();
                    modSound.open(AudioSystem.getAudioInputStream(new File("Audio/mod.wav")));
                    modSound.start();
                break;
                case "-":
                    Clip subSound = AudioSystem.getClip();
                    subSound.open(AudioSystem.getAudioInputStream(new File("Audio/subtract.wav")));
                    subSound.start();
                break;
                case "+/-":
                    Clip signSound = AudioSystem.getClip();
                    signSound.open(AudioSystem.getAudioInputStream(new File("Audio/sign_change.wav")));
                    signSound.start();
                break;
                case "+":
                    Clip addSound = AudioSystem.getClip();
                    addSound.open(AudioSystem.getAudioInputStream(new File("Audio/add.wav")));
                    addSound.start();
                break;
                case "=":
                    Clip equalsSound = AudioSystem.getClip();
                    equalsSound.open(AudioSystem.getAudioInputStream(new File("Audio/equals.wav")));
                    equalsSound.start();
                break;
                case "MC":
                    Clip memClearSound = AudioSystem.getClip();
                    memClearSound.open(AudioSystem.getAudioInputStream(new File("Audio/memory_clear.wav")));
                    memClearSound.start();
                break;
                case "MR":
                    Clip memRecallSound = AudioSystem.getClip();
                    memRecallSound.open(AudioSystem.getAudioInputStream(new File("Audio/memory_recall.wav")));
                    memRecallSound.start();
                break;
                case "MS":
                    Clip memStoreSound = AudioSystem.getClip();
                    memStoreSound.open(AudioSystem.getAudioInputStream(new File("Audio/memory_store.wav")));
                    memStoreSound.start();
                break;
                case "M+":
                    Clip memAddSound = AudioSystem.getClip();
                    memAddSound.open(AudioSystem.getAudioInputStream(new File("Audio/memory_add.wav")));
                    memAddSound.start();
                break;
                case "Backspc":
                    Clip backSound = AudioSystem.getClip();
                    backSound.open(AudioSystem.getAudioInputStream(new File("Audio/backspace.wav")));
                    backSound.start();
                break;
                case "C":
                    Clip clearSound = AudioSystem.getClip();
                    clearSound.open(AudioSystem.getAudioInputStream(new File("Audio/clear.wav")));
                    clearSound.start();
                break;
                case "CE":
                    Clip clearEntrySound = AudioSystem.getClip();
                    clearEntrySound.open(AudioSystem.getAudioInputStream(new File("Audio/clear_entry.wav")));
                    clearEntrySound.start();
                break;
                case "cos-1":
                    Clip arccosSound = AudioSystem.getClip();
                    arccosSound.open(AudioSystem.getAudioInputStream(new File("Audio/arccos.wav")));
                    arccosSound.start();
                break;
                case "ab^x":
                    Clip baseSound = AudioSystem.getClip();
                    baseSound.open(AudioSystem.getAudioInputStream(new File("Audio/base_exponent.wav")));
                    baseSound.start();
                break;
                case "log":
                    Clip logSound = AudioSystem.getClip();
                    logSound.open(AudioSystem.getAudioInputStream(new File("Audio/log.wav")));
                    logSound.start();
                break;
                case "Gamma(x)":
                    Clip gammaSound = AudioSystem.getClip();
                    gammaSound.open(AudioSystem.getAudioInputStream(new File("Audio/gamma.wav")));
                    gammaSound.start();
                break;
                case "MAD":
                    Clip madSound = AudioSystem.getClip();
                    madSound.open(AudioSystem.getAudioInputStream(new File("Audio/mad.wav")));
                    madSound.start();
                break;
                case "StdDev":
                    Clip stdSound = AudioSystem.getClip();
                    stdSound.open(AudioSystem.getAudioInputStream(new File("Audio/std_dev.wav")));
                    stdSound.start();
                break;
                case "sinh(x)":
                    Clip hypSinSound = AudioSystem.getClip();
                    hypSinSound.open(AudioSystem.getAudioInputStream(new File("Audio/sinh.wav")));
                    hypSinSound.start();
                break;
                case "x^y":
                    Clip exponentSound = AudioSystem.getClip();
                    exponentSound.open(AudioSystem.getAudioInputStream(new File("Audio/exponent.wav")));
                    exponentSound.start();
                break;
                case "n!":
                    Clip factorialSound = AudioSystem.getClip();
                    factorialSound.open(AudioSystem.getAudioInputStream(new File("Audio/factorial.wav")));
                    factorialSound.start();
                break;
                case "1/x":
                    Clip fractionSound = AudioSystem.getClip();
                    fractionSound.open(AudioSystem.getAudioInputStream(new File("Audio/fraction.wav")));
                    fractionSound.start();
                break;
            default:
                break;
        }

    }

}
    

 

