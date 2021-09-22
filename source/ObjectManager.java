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
	int score = 0;
	int hits = 5;
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
		/*if (enemy == 0) {
			enemies.add(new EnemyDestroyer(HunterKiller.WIDTH, 147, 100, 20));
		} else if (enemy == 1) {
			enemies.add(new EnemyCarrier(1, 137, 140, 30));
		}*/
		enemies.add(new EnemyDestroyer(HunterKiller.WIDTH, 147, 100, 20));
	}

	void addFriend() {
		friend = random.nextInt(2);
		/*if (friend == 0) {
			friends.add(new AlliedDestroyer(HunterKiller.WIDTH, 147, 100, 20));
		} else if (friend == 1) {
			friends.add(new AlliedCarrier(1, 137, 140, 30));
		}*/
		friends.add(new AlliedDestroyer(HunterKiller.WIDTH, 147, 100, 20));
	}

	void update() {
		for (int i = 0; i < torpedoes.size(); i++) {
			torpedoes.get(i).update();
			if (torpedoes.get(i).y < 157) {
				torpedoes.get(i).isActive = false;
			}
		}
		for (int i = 0; i < friends.size(); i++) {
			friends.get(i).update();
			if (friends.get(i).x < 0) {
				friends.get(i).isActive = false;
			}else if (friends.get(i).x > HunterKiller.WIDTH) {
				friends.get(i).isActive = false;
			}
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			if (enemies.get(i).x < 0) {
				enemies.get(i).isActive = false;
			}else if (enemies.get(i).x > HunterKiller.WIDTH) {
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
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isActive == false) {
				enemies.remove(i);
			}
		}
		for (int i = 0; i < friends.size(); i++) {
			if (friends.get(i).isActive == false) {
				friends.remove(i);
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
