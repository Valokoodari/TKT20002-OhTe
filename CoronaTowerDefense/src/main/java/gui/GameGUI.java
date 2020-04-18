package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Display;
import logic.Game;
import towers.Tower;
import viruses.Virus;

public class GameGUI {
	private Text healthText;
	private Text roundText;
	private Game game;
	private Scene menuScene;
	private Stage stage;
	private Display display;
	
	private int tileSize;
	private Canvas mapCanvas;
	private AnimationTimer animationTimer;
	
	public GameGUI(int mapNumber, String difficulty, Scene menuScene, Stage stage, Display display) {
		this.menuScene = menuScene;
		this.stage = stage;
		this.display = display;
		
		this.game = new Game(mapNumber, difficulty);
		
		int[][] map = this.game.getMap();
		this.tileSize = this.display.height / map.length;
		this.mapCanvas = new Canvas(this.tileSize * map[0].length, this.tileSize * map.length);
		this.mapCanvas.setOnMouseClicked(e -> this.handleMapClick((int)e.getScreenX(), (int)e.getScreenY()));
		
		this.healthText = new Text("Health: " + this.game.getHealth());
		this.roundText = new Text("Round: " + this.game.getRound() + "/" + this.game.getLength());
	}
	
	public void update(double elapsedTime) {
		// Run game updates
		int status = this.game.update(elapsedTime);
		
		if (status != 0) {
			this.animationTimer.stop();
			
			this.stage.setScene(this.menuScene);
			this.stage.setFullScreen(true);
		}
		
		// Update HUD
		this.healthText.setText("Health: " + this.game.getHealth());
		this.roundText.setText("Round: " + this.game.getRound() + "/" + this.game.getLength());
		
		// Draw map
		GraphicsContext gc = this.mapCanvas.getGraphicsContext2D();
		gc.setLineWidth(1);
		
		int[][] map = this.game.getMap();
		Tower[][] towers = this.game.getTowers();
		
		// Draw map
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				gc.setFill((map[i][j] != 0)? Color.RED : Color.DARKRED);
				gc.fillRect(tileSize*j, tileSize*i, tileSize, tileSize);
				
			}
		}
		
		// Draw towers
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (towers[i][j] != null) {
					gc.setFill(Color.AQUA);
					gc.fillOval(tileSize*(j+0.1), tileSize*(i+0.1), tileSize*0.8, tileSize*0.8);
					gc.setFill(Color.rgb(50, 50, 50, 0.1));
					double range = towers[i][j].getRange();
					gc.fillOval(tileSize*(j + 0.5 - range), tileSize*(i + 0.5 - range), range*2*tileSize, range*2*tileSize);
				}
			}
		}
		
		Virus[] viruses = this.game.getViruses();
		gc.setFill(Color.YELLOW);
		for (int i = 0; i < viruses.length; i++) {
			if (viruses[i].getHealth() != 0) {
				double[] pos = viruses[i].getPos();
				gc.fillOval(tileSize*(pos[0]+0.4), tileSize*(pos[1]+0.4), tileSize*0.2, tileSize*0.2);
			}
		}
	}
	
	// Game loop
	public void startGame() {
		this.animationTimer = new AnimationTimer() {
			private long previousNanoTime = -1;
			
			public void handle(long currentNanoTime) {
				if (this.previousNanoTime < 0) this.previousNanoTime = currentNanoTime;
				double elapsedNanoTime = (currentNanoTime - this.previousNanoTime) / 1e9;
				this.previousNanoTime = currentNanoTime;
				
				update(elapsedNanoTime);
			}
		};
		this.animationTimer.start();
	}
	
	public void setScene() {
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot);
		
		// Set the background
		Rectangle background = new Rectangle(this.display.width, this.display.height, Color.CORNFLOWERBLUE);
		gameRoot.getChildren().add(background);
		
		// Add map canvas to root
		gameRoot.getChildren().add(this.mapCanvas);
		
		// Draw the HUD
		HBox top = new HBox();
		gameRoot.getChildren().add(top);
		
		VBox hud = new VBox();
		top.getChildren().add(hud);
		hud.getChildren().add(this.healthText);
		hud.getChildren().add(this.roundText);
		
		Button menuButton = new Button("<-");
		top.getChildren().add(menuButton);
		menuButton.setOnAction(e -> {
			this.stage.setScene(this.menuScene);
			this.stage.setFullScreen(true);
		});
		top.setLayoutX(this.game.getMap()[0].length * this.tileSize + 10);
		
    	this.stage.setScene(gameScene);
    	this.stage.setFullScreen(true);
	}
	
	public void handleMapClick(int x, int y) {
		x /= this.tileSize;
		y /= this.tileSize;
		
		this.game.addTower(x, y);
	}
}
