package alm.pontus.android.remake;

import android.graphics.Bitmap;

public class Drawable extends Entity {
	
	private Bitmap bitmap;
	private int bitmapHeight, bitmapWidth;
	
	public Drawable(int x, int y, int health, int points, Bitmap bitmap) {
		super(x, y, health, points);
		// TODO Auto-generated constructor stub
	}
	
	// TODO methods for drawing
	
	public int getBitmapHeight() {
		return bitmapHeight;
	}
	
	public void setBitmapHeight(int bitmapHeight) {
		this.bitmapHeight = bitmapHeight;
	}
	
	public int getBitmapWidth() {
		return bitmapWidth;
	}
	
	public void setBitmapWidth(int bitmapWidth) {
		this.bitmapWidth = bitmapWidth;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
}
