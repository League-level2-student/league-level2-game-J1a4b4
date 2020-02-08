import java.awt.Dimension;

import javax.swing.JFrame;

public class Runner {

	JFrame frame;
	final static int WIDTH = 1000;
	final static int HEIGHT = 500;
	GamePanel gamePanel;
	
	Runner() {
		frame = new JFrame();
		gamePanel = new GamePanel();
	}
	
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.setup();
	}
	
	void setup() {
		frame.add(gamePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.addKeyListener(gamePanel);
		frame.pack();
		gamePanel.startGame();
	}
}
