package game_event_loop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class GameObject extends JComponent {
	/**
	 * Class: GameObject
	 * 
	 * @author Carson Batt, Max Li
	 *         
	 *       
	 */
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected Color color;
	protected int dx;
	protected int dy;
	protected int width;
	protected int height;
	protected boolean flippedG;

	public GameObject(int x, int y, int dx, int dy, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.flippedG = false;
	}
	
	//makes invisible rectangle "hitbox" around objects
	public Rectangle2D.Double makeHitbox() {
		return new Rectangle2D.Double(this.x, this.y, this.width, this.height);
	}
	
	//returns true if object hitboxes intersect
	public boolean findIntersects(GameObject otherObject) {
		return makeHitbox().intersects(otherObject.makeHitbox());
	}
	
	//replaces object location as frames update
	public void updateGameObject() {
		this.x += dx;
		this.y += dy;
		
	}
	
	//draws object
	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillRect(x, y, width, height);
	}
	
	//inverses x velocity
	public void flipDX() {
		this.dx *= -1;
	}
	
	//inverses y velocity
	public void flipDY() {
		this.dy *= -1;
	}
	

}
