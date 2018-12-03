import com.sun.scenario.Settings;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;


public class mainVC extends Application {
    private Stage window; private AudioStream audioStream;


    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        launchV1();

    }

    // Page 1 - Main Screen
    private void launchV1() {
        window.setTitle(settings.v1Title);
        window.setOnCloseRequest(e -> {AudioPlayer.player.stop(audioStream); e.consume(); window.close();});

        // Set Layout and Defaults
        GridPane layout = new GridPane();
        layout.setHgap(40);
        layout.setVgap(10);
        layout.setBackground(getBackground(settings.v1BgPath));
        audioStream = DKHelpers.playWAV(settings.v1AudioPath);



        // Next Button
        Button goNext = new Button();
        goNext.setText(settings.v1BTN);
        goNext.setOnAction(e -> {launchV2(); AudioPlayer.player.stop(audioStream);});
        layout.getChildren().add(goNext);

        // Scene Setup and Launch
        Scene mainScreen = new Scene(layout, 800, 600);
        window.setScene(mainScreen);
        window.show();

    }


    // Page 2, Secondary Screen.
    private void launchV2() {
        String[] students = settings.students;
        window.setTitle(settings.v2Title);

        // Layout
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setBackground(getBackground(settings.v2BgPath));

        int x = 0; int y = 2;

        // Loop through each student, directory named relatively.
        for (String studentName : students) {
            Student student = new Student();
            student.name = studentName;
            y = (x == 4) ? y + 2 : y; x = (x == 4) ? 0 : x; // 4 students / row

            try { // Image row
                ImageView image = new ImageView(new Image(student.getPicturePath()));
                grid.add(image, x, y);
            } catch (Exception e) { // Just need to catch general exception, can't do anything. Trust this won't happen
                System.out.println("Error: " + e);
            } y++;

            // Play Button
            Button clickBtn = new Button();
            clickBtn.setText(DKHelpers.capitalize(studentName));
            clickBtn.setOnAction(e -> student.playMessage());

            // Add to Grid
            grid.add(clickBtn, x, y);
            grid.setAlignment(Pos.TOP_CENTER);
            y -= 1; x++;
        }

        // Back Button
        Button goBack = new Button(); goBack.setText("Go Back");
        goBack.setOnAction(e -> launchV1());
        grid.getChildren().add(goBack);

        // Scene Setup and Show
        Scene mainScreen = new Scene(grid, 800, 600);
        window.setScene(mainScreen);
        window.show();
    }



    // Set Background of VC
    private static Background getBackground(String name) {

        try {
            InputStream INPUTSTREAM = mainVC.class.getClassLoader().getResourceAsStream(name);
            Background bg = new Background(new BackgroundImage(new Image(INPUTSTREAM, 800, 600, false, true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT));
            return bg;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
}
