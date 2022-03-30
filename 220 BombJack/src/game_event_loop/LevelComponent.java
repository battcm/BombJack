package game_event_loop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

import src.fileIO.LevelIO;

public class LevelComponent extends JComponent {
	/**
	 * Class: LevelComponent
	 * 
	 * @author Carson Batt, Max Li
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int levelID;
	private int xCorner;
	private int yCorner;
	private String lvltxt;
	ArrayList<Enemy> enemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<GameObject> walls;
	private ArrayList<Spike> spikes;
	Hero hero;
	private GameComponent component;

	// loads the next level
	public void nextLevel() {
		if (levelID < 10)
			levelID += 1;
		this.lvltxt = LevelIO.readFile(levelID);
	}

	// loads the previous level
	public void previousLevel() {
		if (levelID > 1)
			levelID -= 1;
		this.lvltxt = LevelIO.readFile(levelID);
	}

	public void drawScreen() {
		this.repaint();
	}

	public LevelComponent() {
		this.levelID = 1;
		this.lvltxt = LevelIO.readFile(levelID);
		this.enemies = new ArrayList<>();
		this.bombs = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.spikes = new ArrayList<>();
		this.hero = new Hero(40, 360, 0, 5, 20, 40, Color.cyan);
		updateLvl();
	}

	// empties all of the objects from the updating object array lists
	public void clearAllObjects() {
		this.enemies.clear();
		this.bombs.clear();
		this.walls.clear();
		this.spikes.clear();
	}

	// creates new objects when a new level is loaded it took us a while to realize
	// that this should not be called every frame because if new objects are created
	// every frame, crash.
	public void updateLvl() {

		Random rand = new Random();
		clearAllObjects();
		xCorner = 0;
		yCorner = 0;
		for (int i = 0; i < lvltxt.length(); i++) {
			if (i % 40 == 0) {
				xCorner = 0;
				yCorner += 20;
			} else {
				xCorner += 20;
			}
			char txtChar = lvltxt.charAt(i);
			// "don't care" randomizes object in this location with an increased chance of
			// being empty space
			if (txtChar == 'X') {
				char[] a = {'O', 'E', 'B', 'S', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',
							'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 
							'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.',};
				int n = rand.nextInt(60);
				txtChar = a[n];
			}
			if (txtChar == 'O') {
				GameObject wallBlock = new GameObject(xCorner, yCorner, 0, 0, 20, 20, Color.red);
				this.walls.add(wallBlock);

			} else if (txtChar == 'E') {
				Enemy enemy = new Enemy(xCorner, yCorner, rand.nextInt(this.levelID + 1),
						rand.nextInt(this.levelID + 1), 20, 20, Color.ORANGE);
				this.enemies.add(enemy);

			} else if (txtChar == 'B') {
				Bomb bomb = new Bomb(xCorner, yCorner, 0, 0, 40, 40, Color.BLACK);
				this.bombs.add(bomb);

			} else if (txtChar == 'S') {
				Spike spike = new Spike(xCorner, yCorner, 0, 0, 20, 20, Color.GREEN);
				this.spikes.add(spike);
			}

		}
		this.hero.x = 40;
		this.hero.y = 360;
		this.hero.flippedG = false;

		this.component = new GameComponent(hero, enemies, bombs, walls, spikes);
		this.component.setHeroLives(3);
		this.component.updateGameComponent();

	}

	public void updateLevelComponent() {
		this.component.updateGameComponent();
	}

	public GameComponent getGameComponent() {
		return this.component;
	}

}
