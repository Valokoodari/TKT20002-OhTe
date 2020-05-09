package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Config;
import logic.Game;
import towers.Tower;
import viruses.Virus;

public class GameGUI {
	private Text healthText;
	private Text moneyText;
	private Text roundText;
	private Game game;
	private Scene menuScene;
	private Stage stage;
	private Config config;
	
	private int tileSize;
	private Canvas mapCanvas;
	private AnimationTimer animationTimer;
	
	/**
	 * Luo uuden graafisen käyttöliittymän ja liittää siihen pelin päälogiikan halutuilla ominaisuuksilla
	 * 
	 * @param mapNumber  Valitun kartan numero
	 * @param difficulty  Valitun vaikeustason nimi
	 * @param menuScene  Scene johon palataan pelin loputtua
	 * @param stage  Stage johon Scene asetetaan
	 * @param config  Ohjelman configuraatio
	 */
	public GameGUI(int mapNumber, String difficulty, Scene menuScene, Stage stage, Config config) {
		this.menuScene = menuScene;
		this.stage = stage;
		this.config = config;
		
		this.game = new Game(mapNumber, this.convertDifficulty(difficulty));
		
		int[][] map = this.game.getMap();
		this.tileSize = this.config.displayHeight / map.length;
		this.mapCanvas = new Canvas(this.tileSize * map[0].length, this.tileSize * map.length);
		this.mapCanvas.setOnMouseClicked(e -> this.handleMapClick((int)e.getScreenX(), (int)e.getScreenY()));
		
		this.healthText = new Text("Health: " + this.game.getHealth());
		this.moneyText = new Text("Money: " + this.game.getMoney() + " Ð");
		this.roundText = new Text("Round: " + this.game.getRound() + "/" + this.game.getLength());
	}
	
	/**
	 * Käyttöliittymän päivitys, joka kutsuu myös päälogiikan päivitystä
	 * 
	 * @param elapsedTime  edellisestä kutsusta kulunut aika
	 */
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
		this.moneyText.setText("Money: " + this.game.getMoney() + " Ð");
		this.roundText.setText("Round: " + this.game.getRound() + "/" + this.game.getLength());
		
		// Update graphics
		GraphicsContext gc = this.mapCanvas.getGraphicsContext2D();
		gc.setLineWidth(1);
		
		drawMap(gc);
		drawTowers(gc);
		drawViruses(gc);
	}

	private void drawMap(GraphicsContext gc) {
		int[][] map = this.game.getMap();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				gc.setFill((map[i][j] != 0)? Color.RED : Color.DARKRED);
				gc.fillRect(tileSize*j, tileSize*i, tileSize, tileSize);
			}
		}
	}

	private void drawTowers(GraphicsContext gc) {
		Tower[][] towers = this.game.getTowers();
		int[][] map = this.game.getMap();

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
	}

	private void drawViruses(GraphicsContext gc) {
		Virus[] viruses = this.game.getViruses();
		
		gc.setFill(Color.YELLOW);
		for (int i = 0; i < viruses.length; i++) {
			if (viruses[i].alive()) {
				double[] pos = viruses[i].getPos();
				gc.fillOval(tileSize*(pos[0]+0.4), tileSize*(pos[1]+0.4), tileSize*0.2, tileSize*0.2);
				gc.fillText("" + viruses[i].getHealth(), tileSize*(pos[0]+0.4), tileSize*pos[1]);
			}
		}
	}
	
	// Game loop
	public void startGame() {
		this.animationTimer = new AnimationTimer() {
			private long previousNanoTime = -1;
			
			public void handle(long currentNanoTime) {
				if (this.previousNanoTime < 0) this.previousNanoTime = currentNanoTime;
				double elapsedTime = (currentNanoTime - this.previousNanoTime) / 1e9;
				this.previousNanoTime = currentNanoTime;

				elapsedTime = Math.min(elapsedTime, 1.0/60);

				update(elapsedTime);
			}
		};
		this.animationTimer.start();
	}
	
	/**
	 * Asettaa näkymäksi pelinäkymän ja piilottaa päävalikon pelin ajaksi
	 */
	public void setScene() {
		Group gameRoot = new Group();
		Scene gameScene = new Scene(gameRoot);
		
		// Set the background
		Rectangle background = new Rectangle(this.config.displayWidth, this.config.displayHeight, Color.CORNFLOWERBLUE);
		gameRoot.getChildren().add(background);
		
		// Add map canvas to root
		gameRoot.getChildren().add(this.mapCanvas);
		
		// Draw the HUD
		HBox top = new HBox();
		gameRoot.getChildren().add(top);
		
		Button menuButton = new Button("<- Back");
		menuButton.setOnAction(e -> {
			this.stage.setScene(this.menuScene);
			this.stage.setFullScreen(true);
		});
		top.setLayoutX(this.game.getMap()[0].length * this.tileSize + 10);

		VBox hud = new VBox();
		top.getChildren().add(hud);
		hud.getChildren().add(menuButton);
		hud.getChildren().add(this.healthText);
		hud.getChildren().add(this.moneyText);
		hud.getChildren().add(this.roundText);
		
    	this.stage.setScene(gameScene);
    	this.stage.setFullScreen(true);
	}
	
	/**
	 * Kutsuu pelilogiikkaa karttaa klikatessa ja antaa
	 * tälle kartan koordinaateiksi muunnetut koordinaatit
	 * 
	 * @param x  Klikkauksen x-koordinaatti pikseleissä
	 * @param y  Klikkauksen y-koordinaatti pikseleissä
	 */
	public void handleMapClick(int x, int y) {
		x /= this.tileSize;
		y /= this.tileSize;
		
		this.game.click(x, y);
	}

	/**
	 * Muuttaa vaikeustason nimen vaikeustason numeroksi
	 */
	private int convertDifficulty(String difficulty) {
		switch (difficulty) {
			case "Easy":
				return 0;
			case "Medium":
				return 1;
			case "Hard":
				return 2;
			default:
				return 1;
		}
	}
}
