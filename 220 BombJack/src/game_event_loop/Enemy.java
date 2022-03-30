package game_event_loop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JComponent;

public class Enemy extends GameObject {
	
	/**
	 * Class: Enemy
	 * 
	 * @author Carson Batt, Max Li
	 *         
	 *       
	 */
	private static final long serialVersionUID = 1L;
	public Enemy(int x, int y, int dx, int dy, int width, int height, Color color) {
		super(x, y, dx, dy, width, height, color);
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillRect(x, y, width, height);
		g2.setColor(Color.BLACK);
		g2.fillOval(x+6, y+5, 4, 4);
		g2.fillOval(x+14, y+5, 4, 4);
		
	}
	
	@Override
	public void updateGameObject() {
		this.x += dx;
		this.y += dy;
		if (this.x > 760 || this.x < 20) {
			this.flipDX();
		}
		if (this.y > 380 || this.y < 40) {
			this.flipDY();
		}
	}
}
