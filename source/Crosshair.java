import java.awt.Color;
import java.awt.Graphics;

public class Crosshair {

	int cx;
	int speed;
	int width = 10;
	int height = 20;
	
	Crosshair(){
		cx = 500 - width/2;
		speed = 10;
	}
	
	void update(){
		
	}
	
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(cx, 480, width, height);
	}
	
	void left() {
		cx = cx - speed;
	}
	
	void right() {
		cx = cx + speed;
	}
}
