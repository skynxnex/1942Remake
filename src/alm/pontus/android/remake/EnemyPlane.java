package alm.pontus.android.remake;

import android.graphics.Bitmap;

public class EnemyPlane extends Movable {
	
	private int xVelocity, yVelocity, movingPattern;

	public EnemyPlane(int x, int y, int health, int points, Bitmap bitmap) {
		super(x, y, health, points, bitmap);
		// TODO Auto-generated constructor stub
	}

	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVelocity) {
		this.xVelocity = xVelocity;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVelocity) {
		this.yVelocity = yVelocity;
	}

	public int getMovingPattern() {
		return movingPattern;
	}

	public void setMovingPattern(int movingPattern) {
		this.movingPattern = movingPattern;
	}
}
