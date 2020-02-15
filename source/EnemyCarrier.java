import java.awt.Color;
import java.awt.Graphics;

public class EnemyCarrier extends EnemyShip{

	EnemyCarrier(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
	}
	
	void update() {
		x = x + speed;
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}