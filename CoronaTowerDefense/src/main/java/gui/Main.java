package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        System.out.println("Test");
        stage.setTitle("Hello world");
        stage.show();
    }
}
