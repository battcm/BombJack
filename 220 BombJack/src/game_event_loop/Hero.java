package game_event_loop;

import java.awt.Color;
import java.util.ArrayList;

public class Hero extends GameObject {
	/**
	 * Class: Hero
	 * 
	 * @author Carson Batt, Max Li
	 * 
	 * 
	 */

	private int jumpDelay;
	private static final long serialVersionUID = 1L;

	public Hero(int x, int y, int dx, int dy, int width, int height, Color color) {
		super(x, y, dx, dy, width, height, color);

	}

	public void goLeft() {
		this.dx = -5;
	}

	public void goRight() {
		this.dx = 5;
	}

	public void goNeutral() {
		this.dx = 0;
	}

	public void goJump() {

		if (flippedG) {

			this.dy = 5;
		} else {

			this.dy = -5;
		}

		this.jumpDelay = 10;
	}

	@Override
	public void updateGameObject() {
		this.x += dx;
		this.y += dy;
		if (jumpDelay == 0) {
			if (flippedG) {
				this.dy = -5;
			} else {
				this.dy = 5;
			}
		} else {
			jumpDelay--;
		}

		if (this.x > 760) {
			this.x = 760;
		} else if (this.x < 20) {
			this.x = 20;
		}
		if (this.y > 360) {
			this.y = 360;
		} else if (this.y < 40) {
			this.y = 40;
		}
	}

}
