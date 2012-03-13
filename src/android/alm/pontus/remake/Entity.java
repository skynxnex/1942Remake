package android.alm.pontus.remake;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;


public class Entity {
	
	private int x, y, health, points;
	public boolean deleted, last;
	
	protected double direction, directionRad, xSpeed, ySpeed, speed;
	
	public Entity(int x, int y, int health, int points, boolean last) {
		super();
		this.x = x;
		this.y = y;
		this.health = health;
		this.points = points;			
		this.last = last;
		EntityManager.INSTANCE.addEntity(this);
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
	
	public void movement(int deltaX, int deltaY) {
		setX(getX() + deltaX);
		setY(getY() + deltaY);
	}
	
	protected void calculateDirection(float sourceX, float sourceY, float destX, float destY ) {
		float trigX = destX - sourceX;
		float trigY = destY - sourceY;
		this.directionRad = Math.atan2(trigY, trigX);
		this.direction = Math.toDegrees(directionRad);
		
		xSpeed = Math.cos(directionRad) * speed;
		ySpeed = Math.sin(directionRad) * speed;
	}
	
	public void onDraw(Canvas canvas) {}
	
	public void updatePosition() {}

	public void collision() {}

	public Rect getRect() { Log.e("ENT", "should not see this"); return null; }
	
	public void test() {}
	
}
