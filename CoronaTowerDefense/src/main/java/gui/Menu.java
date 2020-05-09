package gui;

import dao.ConfigDao;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.Config;

public class Menu extends Application {
	private Config config;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
    	stage.setTitle("Corona Tower Defense");
    	
    	// Force full screen and prevent escape
    	stage.setFullScreen(true);
    	stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    	
    	// Measure the display
    	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    	this.config = new Config((int)screenBounds.getWidth(), (int)screenBounds.getHeight());

    	// Menu GUI
        Pane menuRoot = new Pane();
        Scene menuScene = new Scene(menuRoot);
        stage.setScene(menuScene);
        
        Rectangle menuBg = new Rectangle(this.config.displayWidth, this.config.displayHeight, Color.SKYBLUE);
        menuRoot.getChildren().add(menuBg);
        
        HBox startBox = new HBox();
        startBox.setLayoutY(0.278 * this.config.displayHeight);
        startBox.setLayoutX(0.234 * this.config.displayWidth);
        startBox.setSpacing(0.130 * this.config.displayWidth);
        menuRoot.getChildren().add(startBox);

        // Error display
        Text mapError = new Text();
        Text diffError = new Text();
        mapError.setLayoutY(0.389 * this.config.displayHeight);
        diffError.setLayoutY(0.407 * this.config.displayHeight);
        mapError.setLayoutX(0.417 * this.config.displayWidth);
        diffError.setLayoutX(0.417 * this.config.displayWidth);
        
        menuRoot.getChildren().addAll(mapError, diffError);
        
        // Map selection
        Button map0 = new Button("Start\nMap 0");
        startBox.getChildren().add(map0);

        Button map1 = new Button("Start\nMap 1");
        startBox.getChildren().add(map1);

        Button map2 = new Button("Start\nMap 2");
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
        exitButton.setLayoutX(0.479 * this.config.displayWidth);
        exitButton.setLayoutY(0.648 * this.config.displayHeight);
        menuRoot.getChildren().add(exitButton);
        
        // Map button actions
        map0.setOnAction(e -> {
        	RadioButton selectedButton = (RadioButton) diffGroup.getSelectedToggle();
        	String difficulty = selectedButton.getText();
        	
        	GameGUI gameGui = new GameGUI(0, difficulty, menuScene, stage, config);
        	gameGui.setScene();
        	gameGui.startGame();
        });
        map1.setOnAction(e -> {
        	mapError.setText("Map map1 not implemented yet.");
        });
        map2.setOnAction(e -> {
        	mapError.setText("Map map2 not implemented yet.");
        });
        
        // Make the window visible
        stage.show();
    }
}
