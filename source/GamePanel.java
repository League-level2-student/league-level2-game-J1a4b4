import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer timer;
	final int MENU_STATE = 0;
	 final int GAME_STATE = 1;
	 final int END_STATE = 2;
	 int currentState = MENU_STATE;
	 Font titleFont;
	 Crosshair crosshair = new Crosshair();
	 
		GamePanel(){
			timer = new Timer(1000/60, this);
			titleFont = new Font("Arial", Font.PLAIN, 48);
		}
		
		void startGame() {
			timer.start();
		}
		
		void updateMenuState() {
			
		}
		
		void updateGameState() {
			
		}
		
		void updateEndState() {
			
		}
		
		void drawMenuState(Graphics g) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
			g.setFont(titleFont);
			g.setColor(Color.YELLOW);
			g.drawString("League Invaders", 75, 300);
		}
		
		void drawGameState(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		}
		
		void drawEndState(Graphics g) {
			g.setColor(Color.RED);
			g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
			g.setFont(titleFont);
			g.setColor(Color.BLACK);
			g.drawString("Game Over", 125, 300);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(currentState == MENU_STATE) {
					currentState = GAME_STATE;
					startGame();
				}else if (currentState == END_STATE) {
					currentState = MENU_STATE;
				}
			}else if (currentState == GAME_STATE) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					crosshair.left();
				}else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					crosshair.right();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			 if(currentState == MENU_STATE){
	             updateMenuState();
			 }else if(currentState == GAME_STATE){
	             updateGameState();
			 }else if(currentState == END_STATE){
	             updateEndState();
			 }
		}
		
		@Override
	 	public void paintComponent(Graphics g){
	        if(currentState == MENU_STATE){
                drawMenuState(g);
	        }else if(currentState == GAME_STATE){
                drawGameState(g);
        	}else if(currentState == END_STATE){
                drawEndState(g);
        	}
		}
}
