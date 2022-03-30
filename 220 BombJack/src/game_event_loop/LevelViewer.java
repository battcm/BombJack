package game_event_loop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;
/**
 * Class: LevelViewer
 * 
 * @author Carson Batt, Max Li
 *         
 *       
 */
public class LevelViewer {
	private void driverMain() {
		final int frameWidth = 820;
		final int frameHeight = 460;
		final int frameXLoc = 300;
		final int frameYLoc = 100;
		JFrame frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation(frameXLoc, frameYLoc);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		LevelComponent component = new LevelComponent();
		frame.add(component);
		frame.add(component.getGameComponent());
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO document why this method is empty
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'u' || e.getKeyChar() == 'U') {
					component.nextLevel();
					component.updateLvl();
				} else if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D') {
					component.previousLevel();
					component.updateLvl();
				} else if (e.getKeyCode() == 37) {
					component.hero.goLeft();
				} else if (e.getKeyCode() == 39) {
					component.hero.goRight();
				} else if (e.getKeyCode() == 38) {
					component.hero.goJump();
				} else if (e.getKeyCode() == 40) {
					if (component.hero.flippedG) {
						component.hero.flippedG = false;
					}else {
						component.hero.flippedG = true;
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
					component.hero.goNeutral();
				}

			}
		});

		Timer timer = new Timer(20, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				component.updateLevelComponent();
				frame.repaint();
			}
		});
		timer.start();

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		LevelViewer levelViewer = new LevelViewer();
		levelViewer.driverMain();
	}
}// main
