package android.alm.pontus.remake;

import android.graphics.Bitmap;
import android.util.Log;

public class Shot extends Movable {

	public Shot(int x, int y, int health, int points, int deltaX, int deltaY,
			Bitmap bitmap, boolean last) {
		super(x, y, health, points, deltaX, deltaY, bitmap, last);
		CollisionManager.INSTANCE.addEntity(this);
	}

	@Override
	public void collision() {
		this.deleted = true;
//		EntityManager.INSTANCE.removeEntity(this);
//		CollisionManager.INSTANCE.removeEntity(this);
	}
}
