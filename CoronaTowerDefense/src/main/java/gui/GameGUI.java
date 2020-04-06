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
	private Scene menu;
	private Stage stage;
	private Game game;
	
	public GameGUI(Game game, Scene menuScene, Stage stage) {
		this.game = game;
		this.menu = menuScene;
		this.stage = stage;
		this.healthText = new Text("Health: " + game.getHealth());
		this.roundText = new Text("Round: 0/0");
	}
	
	public void updateHealth() {
		this.healthText.setText("Health: " + game.getHealth()); 
	}
	
	public void setRound(int round, int maxRound) {
		this.roundText.setText("Round: " + round + "/" + maxRound);
	}
	
	public void setRoundFree(int round) {
		this.roundText.setText("Round: " + round);
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
