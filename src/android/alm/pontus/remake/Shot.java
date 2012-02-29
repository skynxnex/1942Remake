package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Shot extends Movable {

	public Shot(int x, int y, int health, int points, int deltaX, int deltaY,
			Bitmap bitmap, boolean last) {
		super(x, y, health, points, deltaX, deltaY, bitmap, last);
		CollisionManager.INSTANCE.addShot(this);
	}

	@Override
	public void collision() {
		this.deleted = true;
	}
	
	@Override
	public void updatePosition() {
		if(getX() < 0) {
			this.deleted = true;
		}
		super.updatePosition();
	}
}
