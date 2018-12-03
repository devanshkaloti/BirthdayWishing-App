import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.io.InputStream;

public class DKHelpers {

    // Capitalize first letter
    public static String capitalize(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
    }


    public static AudioStream playWAV(String filename) {
        try {
            // Get File
            InputStream INPUTSTREAM = mainVC.class.getClassLoader().getResourceAsStream(filename);
            AudioStream audioStream = new AudioStream(INPUTSTREAM);
            AudioPlayer.player.start(audioStream);
            return audioStream;

        } catch (Exception e) {
            System.out.println("Error: " + e);
            JOptionPane.showMessageDialog(null, "Error");
        }
        return null;
    }

}