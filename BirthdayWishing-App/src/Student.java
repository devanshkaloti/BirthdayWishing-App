import java.io.InputStream;

public class Student {
    public String name;

    // Play User's Message
    public void playMessage() {
        try {
            DKHelpers.playWAV(name + "/audio.wav");
        } catch(Exception e) {
            System.out.print("Error: " + e);
        }
    }


    // Get path of User's Picture
    public InputStream getPicturePath() {
        try {
            return (mainVC.class.getClassLoader().getResourceAsStream(name + "/photo.jpg"));
        } catch (Exception e) {
            System.out.print("Error: " + e);
            return null;
        }
    }

}
