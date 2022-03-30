package game_event_loop;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bomb extends GameObject {

	/**
	 * Class: Bomb
	 * 
	 * @author Carson Batt, Max Li
	 *         
	 *       
	 */
	private static final long serialVersionUID = 1L;
	public Bomb(int x, int y, int dx, int dy, int width, int height, Color color) {
		super(x, y, dx, dy, width, height, color);
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillOval(x, y, width, height);
	}
}
