import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	Timer shipSpawn;
	final int MENU_STATE = 0;
	final int INSTRUCTIONS_STATE = 1;
	final int GAME_STATE = 2;
	final int END_STATE = 3;
	int currentState = MENU_STATE;
	Font titleFont;
	Crosshair crosshair = new Crosshair();
	ObjectManager manager = new ObjectManager(crosshair);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;

	GamePanel() {
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		if(needImage) {
			loadImage("Title.png");
		}
		JOptionPane.showMessageDialog(null, "Press Space for Instructions or Enter to Start.");
	}

	void startGame() {
		timer.start();
		shipSpawn = new Timer(1000, manager);
		shipSpawn.start();
	}

	void updateMenuState() {

	}

	void updateGameState() {
		manager.update();
		if (manager.hits <= 0) {
			currentState = END_STATE;
			shipSpawn.stop();
			needImage = true;
			gotImage = false;
			loadImage("End1.png");
			JOptionPane.showMessageDialog(null, "Your score was: " + manager.score);
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
			if (gotImage) {
				g.drawImage(image, 0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT, null);
			} else {
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT);
				g.setFont(titleFont);
				g.setColor(Color.WHITE);
				g.drawString("Hunter-Killer", 350, 250);
			}
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT, null);
		}else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT);
		}
		manager.draw(g);
	}
	
	void drawInstructionsState(Graphics g) {
		if(gotImage) {
			g.drawImage(image, 0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT, null);
		}else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT);
		}
	}

	void drawEndState(Graphics g) {
		if(gotImage) {
			g.drawImage(image, 0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT, null);
		}else {
			g.setColor(Color.RED);
			g.fillRect(0, 0, HunterKiller.WIDTH, HunterKiller.HEIGHT);
			g.setFont(titleFont);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", 350, 250);
		}
	}

	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == MENU_STATE) {
				currentState = GAME_STATE;
				gotImage = false;
				needImage = true;
				loadImage("Background.png");
				startGame();
			} else if (currentState == INSTRUCTIONS_STATE) {
				currentState = GAME_STATE;
				gotImage = false;
				needImage = true;
				loadImage("Background.png");
				startGame();
			} else if (currentState == END_STATE) {
				currentState = MENU_STATE;
				crosshair = new Crosshair();
				manager = new ObjectManager(crosshair);
				needImage = true;
				gotImage = false;
				loadImage("Title.png");
			}
		}else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (currentState == MENU_STATE) {
				currentState = INSTRUCTIONS_STATE;
				needImage = true;
				gotImage = false;
				loadImage("Instructions2.png");
				JOptionPane.showMessageDialog(null, "The small green line at the bottom of the screen is your crosshair.  Use it to aim torpedoes, and fire with the up arrow key.");
				JOptionPane.showMessageDialog(null, "You will receive points for destroying enemies, but the game will end after you shoot 5 allies.");
			}else if (currentState == INSTRUCTIONS_STATE) {
				currentState = MENU_STATE;
				needImage = true;
				gotImage = false;
				loadImage("Title.png");
			}
		} else if (currentState == GAME_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				crosshair.left();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				crosshair.right();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				manager.addTorpedo(crosshair.getTorpedo());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		if (currentState == MENU_STATE) {
			updateMenuState();
		} else if (currentState == GAME_STATE) {
			updateGameState();
		} else if (currentState == END_STATE) {
			updateEndState();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
			drawEndState(g);
		} else if (currentState == INSTRUCTIONS_STATE) {
			drawInstructionsState(g);
		}
	}
}
