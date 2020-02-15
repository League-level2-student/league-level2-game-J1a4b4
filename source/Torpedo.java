import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Torpedo {

	int x;
	int y;
	int width;
	int height;
	int speed;
	boolean isActive = true;
	Rectangle collisionBox;
	
	Torpedo(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		speed = 10;
		collisionBox = new Rectangle (x, y, width, height);
	}
	
	void update() {
		y = y - speed;
		collisionBox.setBounds(x, y, width, height);
	}
	
	void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, width, height);
	}
}
