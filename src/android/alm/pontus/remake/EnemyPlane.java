package android.alm.pontus.remake;

import java.util.Random;

import android.graphics.Bitmap;

public class EnemyPlane extends Movable {
	
	private int movingPattern;
	public boolean last;
	private int shoot;

	public EnemyPlane(int x, int y, int health, int points, int deltaX, int deltaY, Bitmap bitmap, int movingPattern, boolean last) {
		super(x, y, health, points, deltaX, deltaY, bitmap, last);
		this.last = last;
		this.movingPattern = movingPattern;
		CollisionManager.INSTANCE.addPlane(this);
	}

	@Override
	public void updatePosition(){
		Panel.pattern.setUpdatedPosition(this);
		Random gen = new Random();
		int rand = gen.nextInt(400);
		if(this.getX() > 0 && this.getY() > 0) {
			if(rand == shoot) {
				new Bomb(this.getX(), this.getY(), 0, 2, Panel.bomb);
			}			
		}
		super.updatePosition();
	}

	public int getMovingPattern() {
		return movingPattern;
	}

	public void setMovingPattern(int movingPattern) {
		this.movingPattern = movingPattern;
	}
	
	@Override
	public void collision() {
		this.deleted = true;
	}
}
