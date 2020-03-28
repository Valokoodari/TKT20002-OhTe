package gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        stage.setTitle("Corona Tower Defense");

        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, 1920, 1080);
        stage.setScene(menuScene);
        
        HBox startBox = new HBox();
        startBox.setLayoutY(300);
        startBox.setLayoutX(450);
        startBox.setSpacing(250);
        menuRoot.getChildren().add(startBox);

        // Error display
        Text mapError = new Text();
        Text diffError = new Text();
        mapError.setLayoutY(420);
        diffError.setLayoutY(440);
        mapError.setLayoutX(800);
        diffError.setLayoutX(800);
        
        menuRoot.getChildren().addAll(mapError, diffError);
        
        // Map selection
        Button map0 = new Button("Start\nMap 0");
        map0.setLayoutX(200);
        map0.setLayoutY(300);
        startBox.getChildren().add(map0);

        Button map1 = new Button("Start\nMap 1");
        map1.setLayoutX(200);
        map1.setLayoutY(300);
        startBox.getChildren().add(map1);

        Button map2 = new Button("Start\nMap 2");
        map2.setLayoutX(200);
        map2.setLayoutY(300);
        startBox.getChildren().add(map2);
        
        // Difficulty selection
        VBox diffBox = new VBox();
        startBox.getChildren().add(diffBox);
        
        RadioButton diff0 = new RadioButton("Easy");
        RadioButton diff1 = new RadioButton("Medium");
        RadioButton diff2 = new RadioButton("Hard");

        final ToggleGroup diffGroup = new ToggleGroup();
        diff0.setToggleGroup(diffGroup);
        diff1.setToggleGroup(diffGroup);
        diff2.setToggleGroup(diffGroup);
        
        diff0.setSelected(true);
        
        diffBox.getChildren().addAll(diff0, diff1, diff2);
        
        // Exit button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> Platform.exit());
        exitButton.setStyle("-fx-font: 24 Arial; -fx-base: #ff3344");
        exitButton.setLayoutX(920);
        exitButton.setLayoutY(700);
        menuRoot.getChildren().add(exitButton);
        
        // Map button actions
        map0.setOnAction(e -> {
        	mapError.setText("Map map0 not implemented yet.");
        	RadioButton selectedButton = (RadioButton)diffGroup.getSelectedToggle();
        	diffError.setText("Difficulty " + selectedButton.getText() + " not implemented yet.");
        });
        map1.setOnAction(e -> {
        	mapError.setText("Map map1 not implemented yet.");
        	RadioButton selectedButton = (RadioButton)diffGroup.getSelectedToggle();
        	diffError.setText("Difficulty " + selectedButton.getText() + " not implemented yet.");
        });
        map2.setOnAction(e -> {
        	mapError.setText("Map map2 not implemented yet.");
        	RadioButton selectedButton = (RadioButton)diffGroup.getSelectedToggle();
        	diffError.setText("Difficulty " + selectedButton.getText() + " not implemented yet.");
        });
        
        // Make the window visible
        stage.show();
    }
}