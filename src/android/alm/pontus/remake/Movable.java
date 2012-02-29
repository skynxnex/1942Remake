package android.alm.pontus.remake;

import android.graphics.Bitmap;

public class Movable extends Drawable {
	
	public int deltaY;
	public int deltaX;
	
	public Movable(int x, int y, int health, int points, int deltaX, int deltaY, Bitmap bitmap, boolean last) {
		super(x, y, health, points, bitmap, last);
		
		this.deltaY = deltaY;
		this.deltaX = deltaX;
	}
	
	@Override
	public void updatePosition() {
		setX(this.getX() + deltaX);
		setY(this.getY() + deltaY);
	}
	
	// TODO methods for moving

}
