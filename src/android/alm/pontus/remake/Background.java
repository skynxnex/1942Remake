package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Background  extends Movable {

	public Background(int x, int y, int health, int points, int deltaX, int deltaY, Bitmap bitmap) {
		super(x, y, health, points, deltaX, deltaY, bitmap, false);	
	}

	@Override
	public void updatePosition() {
		super.updatePosition();
		if(getY() >= Panel.screenHeight) {
			setY(getBitmapHeight() * -1);
		}
	}
}
