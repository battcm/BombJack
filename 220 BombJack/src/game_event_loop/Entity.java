package game_event_loop;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;



public class Entity extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> walls;
	private Rectangle2D.Double north;
	private Rectangle2D.Double south;
	private Rectangle2D.Double east;
	private Rectangle2D.Double west;
	public Entity(int x, int y, int dx, int dy, int width, int height, Color color) {
		super(x, y, dx, dy, width, height, color);
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.north = new Rectangle2D.Double(this.x, this.y - 5, this.width, 5);
		this.south = new Rectangle2D.Double(this.x, this.y + this.height, this.width, 5);
		this.west = new Rectangle2D.Double(this.x - 5, this.y, 5,this.height);
		this.east = new Rectangle2D.Double(this.x + this.width, this.y, 5, this.height);

	}

	public int getArea(Rectangle2D r) {
		return (int) (r.getWidth() * r.getHeight());
	}

//	@Override
//	public void updateGameObject() {
//		this.x += dx;
//		this.y += dy;
//		this.north = new Rectangle2D.Double(this.x, this.y - 5, this.width, 5);
//		this.south = new Rectangle2D.Double(this.x, this.y + this.height, this.width, 5);
//		this.west = new Rectangle2D.Double(this.x - 5, this.y, 5,this.height);
//		this.east = new Rectangle2D.Double(this.x + this.width, this.y, 5, this.height);
//
//		for (int i = 0; i < walls.size(); i++) {
//			int n = getClosestSide(north.createIntersection(walls.get(i).makeHitbox()),
//					south.createIntersection(walls.get(i).makeHitbox()),
//					east.createIntersection(walls.get(i).makeHitbox()),
//					west.createIntersection(walls.get(i).makeHitbox()));
//
//			if (n == 1) {
//				this.y = walls.get(i).y + walls.get(i).height + 1;
//			} else if (n == 2) {
//				this.y = walls.get(i).y - this.height - 1;
//			} else if (n == 3) {
//				this.x = walls.get(i).x - 1;
//			} else if (n == 4) {
//				this.x = walls.get(i).x + walls.get(i).width + 1;
//			}
//		}
//
//	}

	public int getClosestSide(Rectangle2D a, Rectangle2D b, Rectangle2D c, Rectangle2D d) {
		int n = 0;
		int aArea = getArea(a);
		int bArea = getArea(b);
		int cArea = getArea(c);
		int dArea = getArea(d);
		if (aArea > bArea && aArea > cArea && aArea > dArea) {
			n = 1;
		}else if (bArea > aArea && bArea > cArea && bArea > dArea) {
			n = 2;
		}else if (cArea > aArea && cArea > bArea && cArea > dArea) {
			n = 3;
		}else if (dArea > aArea && dArea > cArea && dArea > bArea) {
			n = 4;
		}else {
			n = 0;
		}
		return n;
	}

	public void setWalls(ArrayList<GameObject> walls) {
		this.walls = walls;
	}

}
