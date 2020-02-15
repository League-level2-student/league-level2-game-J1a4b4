import java.awt.Color;
import java.awt.Graphics;

public class AlliedCarrier extends AlliedShip{

	AlliedCarrier(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}
	
	void update() {
		x = x + speed;
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

}