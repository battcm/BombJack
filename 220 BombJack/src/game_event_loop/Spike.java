package game_event_loop;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Class: Spike
 * 
 * @author Carson Batt, Max Li
 *         
 *       
 */
public class Spike extends GameObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Spike(int x, int y, int dx, int dy, int width, int height, Color color) {
		super(x, y, dx, dy, width, height, color);
	}
	
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		int[] xPoints = {x, x+10, x+20};
		int[] yPoints = {y+20, y, y+20};
		g2.fillPolygon(xPoints, yPoints, 3);
	}
}
