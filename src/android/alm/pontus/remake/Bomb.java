package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Bomb extends Movable {

	public Bomb(int x, int y, int deltaX, int deltaY, Bitmap bitmap) {
		super(x, y, 0, 0, deltaX, deltaY, bitmap, false);
		CollisionManager.INSTANCE.addBomb(this);
	}
	
	@Override
	public void updatePosition() {
		if(this.getY() > Panel.screenHeight) {
			this.deleted = true;
		}
		super.updatePosition();
	}

}
