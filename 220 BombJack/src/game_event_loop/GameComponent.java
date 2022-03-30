package game_event_loop;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

/**
 * Class: GameComponent
 * 
 * @author Carson Batt, Max Li
 * 
 * 
 */
public class GameComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	private Hero hero;
	private ArrayList<Enemy> enemies;
	private ArrayList<Bomb> bombs;
	private ArrayList<GameObject> walls;
	private ArrayList<Spike> spikes;
	private int heroLives;
	private int iFrameDelay;
	private int colliderDelay;

	public GameComponent(Hero hero, ArrayList<Enemy> enemies, ArrayList<Bomb> bombs, ArrayList<GameObject> walls,
			ArrayList<Spike> spikes) {
		this.hero = hero;
		this.enemies = enemies;
		this.bombs = bombs;
		this.walls = walls;
		this.spikes = spikes;
		this.setHeroLives(3);
		this.iFrameDelay = 0;
	}
	// if all bombs for a level are completed,
	// "winner" is printed and next level is loaded
	public void wonLvl() {
		System.out.println("winner");
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_U);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void updateGameComponent() {
		if (bombs.isEmpty()) {
			wonLvl();
		}
		if (iFrameDelay > 0)
			iFrameDelay--;
		if (colliderDelay > 0)
			colliderDelay--;
		hero.updateGameObject();

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).updateGameObject();
		}
		checkCollisions();
		repaint();
	}
	//if the hero hits an enemy block, he looses a life,
	// if he's out of lives, "lost" is printed to the console.
	public void heroDies() {
		if (this.iFrameDelay <= 0) {
			if (this.getHeroLives() > 0) {
				this.setHeroLives(this.getHeroLives() - 1);
				this.iFrameDelay = 30;
			} else {
				System.out.println("lost");
			}
		}
	}

	// determines what action each object should take if the following
	// collisions occur
	public void checkCollisions() {
		for (int i = 0; i < enemies.size(); i++) {
			// enemies to walls
			Enemy enemy = enemies.get(i);
			for (GameObject wall : walls) {
				if (enemy.findIntersects(wall)) {

					enemy.flipDX();
					enemy.flipDY();
				}
			}
			// hero to enemies
			if (this.hero.findIntersects(enemy) && iFrameDelay == 0) {
				if (hero.y < enemy.y - enemy.height) {
					enemies.remove(enemy);
				} else {
					heroDies();
				}

			}
		}

		// hero to spikes
		for (Spike spike : spikes) {
			if (this.hero.findIntersects(spike) && iFrameDelay < 5)
				heroDies();
		}

		// hero to bombs
		for (int i = 0; i < bombs.size(); i++) {
			if (this.hero.findIntersects(bombs.get(i))) {
				bombs.remove(bombs.get(i));
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		this.hero.drawOn(g2);
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).drawOn(g2);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).drawOn(g2);
		}
		for (int i = 0; i < bombs.size(); i++) {
			bombs.get(i).drawOn(g2);
		}
		for (int i = 0; i < spikes.size(); i++) {
			spikes.get(i).drawOn(g2);
		}
		g2.setColor(Color.green);
		for (int i = 0; i <= this.getHeroLives() - 1; i++) {
			if (this.getHeroLives() > i) {
				g2.fillOval(10 + (i * 40), 10, 40, 40);
			}
		}
	}
	/**
	 * @return the heroLives
	 */
	public int getHeroLives() {
		return heroLives;
	}
	/**
	 * @param heroLives the heroLives to set
	 */
	public void setHeroLives(int heroLives) {
		this.heroLives = heroLives;
	}

}
