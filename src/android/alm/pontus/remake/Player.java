package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Player extends Movable {

	public Player(int x, int y, int health, int points, Bitmap bitmap) {
		super(x, y, health, points, 0, 0, bitmap, false);
		CollisionManager.INSTANCE.addPlane(this);
	}

}
