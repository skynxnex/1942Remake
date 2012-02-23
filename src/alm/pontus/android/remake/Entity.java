package alm.pontus.android.remake;

public class Entity {
	
	private int x, y, health, points;
	
	public Entity(int x, int y, int health, int points) {
		super();
		this.x = x;
		this.y = y;
		this.health = health;
		this.points = points;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
