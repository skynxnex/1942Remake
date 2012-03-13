package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Player extends Movable {

	public Player(int x, int y, int health, int points, Bitmap bitmap) {
		super(x, y, health, points, 0, 0, bitmap, false);
		CollisionManager.INSTANCE.addPlane(this);
	}

	@Override
	public void updatePosition() {
		if(getY() >= Panel.screenHeight - getBitmapHeight()) { 
			deltaY = 0;
			setY(Panel.screenHeight - getBitmapHeight());
		}
		if(getY() <= 0 + getBitmapHeight()) {
			setY(0);
		}
		if(getX() >= Panel.screenWidth - getBitmapWidth()) {
			deltaX = 0;
			setX(Panel.screenWidth - getBitmapWidth());
		}
		if(getX() <= 0 + getBitmapWidth()) {
			setX(0);
		}
	}
}
