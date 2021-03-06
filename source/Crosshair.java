import java.awt.Color;
import java.awt.Graphics;

public class Crosshair {

	int cx = 380;
	int speed = 20;
	int width = 10;
	int height = 25;
	
	
	Crosshair(){
		cx = 495;
		speed = 20;
	}
	
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(cx, 475, width, height);
	}
	
	void left() {
		cx = cx - speed;
	}
	
	void right() {
		cx = cx + speed;
	}
	
	public Torpedo getTorpedo() {
		return new Torpedo(cx, 475, 10, 50);
	}
}
