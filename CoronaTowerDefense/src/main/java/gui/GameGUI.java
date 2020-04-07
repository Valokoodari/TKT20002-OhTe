package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Game;

public class GameGUI {
	private Text healthText;
	private Text roundText;
	private Game game;
	private Scene menu;
	private Stage stage;
	
	public GameGUI(int map, String difficulty, Scene menuScene, Stage stage) {
		this.menu = menuScene;
		this.stage = stage;
		
		this.game = new Game(map, difficulty);
		
		this.healthText = new Text("Health: " + this.game.getHealth());
		this.roundText = new Text("Round: " + this.game.getRound() + "/" + this.game.getLength());
	}
	
	public void update() {
		this.healthText.setText("Health: " + this.game.getHealth());
		this.roundText.setText("Round: " + this.game.getRound() + "/" + this.game.getLength());
	}
	
	public void setScene() {
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot);
		
		Rectangle background = new Rectangle(1920, 1080, Color.CORNFLOWERBLUE);
		gameRoot.getChildren().add(background);
		
		HBox top = new HBox();
		gameRoot.getChildren().add(top);
		
		VBox hud = new VBox();
		top.getChildren().add(hud);
		hud.getChildren().add(this.healthText);
		hud.getChildren().add(this.roundText);
		
		Button menuButton = new Button("<-");
		top.getChildren().add(menuButton);
		menuButton.setOnAction(e -> {
			this.stage.setScene(this.menu);
			this.stage.setFullScreen(true);
		});
		
    	this.stage.setScene(gameScene);
    	this.stage.setFullScreen(true);
	}
}
