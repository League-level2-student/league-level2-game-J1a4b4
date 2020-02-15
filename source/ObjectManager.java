import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {

	Crosshair crosshair;
	ArrayList<Torpedo> torpedoes = new ArrayList<Torpedo>();
	ArrayList<Ship> enemies = new ArrayList<Ship>();
	ArrayList<Ship> friends = new ArrayList<Ship>();
	Random random = new Random();
	int hits = 5;
	int score = 0;
	int friend;
	int enemy;

	ObjectManager(Crosshair crosshair) {
		this.crosshair = crosshair;
	}

	void addTorpedo(Torpedo t) {
		torpedoes.add(t);
	}

	void addEnemy() {
		enemy = random.nextInt(2);
		if (enemy == 0) {
			enemies.add(new EnemyDestroyer(Runner.WIDTH, 100, 100, 20));
		} else if (enemy == 1) {
			enemies.add(new EnemyCarrier(0, 100, 140, 30));
		}
	}

	void addFriend() {
		friend = random.nextInt(2);
		if (friend == 0) {
			friends.add(new AlliedDestroyer(Runner.WIDTH, 100, 100, 20));
		} else if (friend == 1) {
			friends.add(new AlliedCarrier(0, 100, 140, 30));
		}
	}

	void update() {
		for (int i = 0; i < torpedoes.size(); i++) {
			torpedoes.get(i).update();
			if (torpedoes.get(i).y < 0 - torpedoes.get(i).height) {
				torpedoes.get(i).isActive = false;
			}
		}
		for (int i = 0; i < friends.size(); i++) {
			friends.get(i).update();
			if (friends.get(i).y < 0 - friends.get(i).height) {
				friends.get(i).isActive = false;
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			if (enemies.get(i).y < 0 - enemies.get(i).height) {
				enemies.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}

	void draw(Graphics g) {
		crosshair.draw(g);
		for (int i = 0; i < torpedoes.size(); i++) {
			torpedoes.get(i).draw(g);
		}
		for (int i = 0; i < friends.size(); i++) {
			friends.get(i).draw(g);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
	}

	void checkCollision() {
		for (int i = 0; i < enemies.size(); i++)
			for (int j = 0; j < torpedoes.size(); j++) {
				if (torpedoes.get(j).collisionBox.intersects(enemies.get(i).collisionBox)) {
					enemies.get(i).isActive = false;
					torpedoes.get(j).isActive = false;
					score = score + 1;
				}
			}
		for (int i = 0; i < friends.size(); i++)
			for (int j = 0; j < torpedoes.size(); j++) {
				if (torpedoes.get(j).collisionBox.intersects(friends.get(i).collisionBox)) {
					friends.get(i).isActive = false;
					torpedoes.get(j).isActive = false;
					hits = hits - 1;
				}
			}

	}

	void purgeObjects() {
		for (int i = 0; i < torpedoes.size(); i++) {
			if (torpedoes.get(i).isActive == false) {
				torpedoes.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		friend = random.nextInt(2);
		if (friend == 0) {
			addEnemy();
		}else if (friend == 1) {
			addFriend();
		}

	}

}
