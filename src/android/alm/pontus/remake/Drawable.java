package android.alm.pontus.remake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Drawable extends Entity {

	private Bitmap bitmap;
	Rect sourceRect;
	private Rect destinationRect;
	protected Matrix mtx = new Matrix();
	
	@SuppressWarnings("unused")
	private String TAG = getClass().getSimpleName();
	
	public Drawable(int x, int y, int health, int points, Bitmap bitmap, boolean last) {
		super(x, y, health, points, last);
		this.bitmap = bitmap;
		sourceRect = new Rect(0, 0, getBitmapWidth(), getBitmapWidth());
		destinationRect = new Rect(getX(), getY(), getX()+getBitmapWidth(), getY()+getBitmapHeight());
		
//		Log.e("WIDTH", ""+sourceRect.width());
//		Log.e("HEIGHT", ""+sourceRect.height());
	}

	@Override
	public void onDraw(Canvas canvas) {
		destinationRect = new Rect(getX(), getY(), getX()+getBitmapWidth(), getY()+getBitmapHeight());
		canvas.drawBitmap(bitmap, sourceRect, destinationRect, null);
	}

	@Override
	public Rect getRect() {
		return destinationRect;
	}
	
	public int getCenterX() {
		return getX() + getBitmapWidth() / 2;
	}
	
	public int getCenterY() {
		return getY() + getBitmapHeight() / 2;
	}

	public int getBitmapHeight() {
		return this.bitmap.getHeight();
	}

	public int getBitmapWidth() {
		return this.bitmap.getWidth();
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public void rotateBitmap(Bitmap bitmap) {
		mtx.setRotate((float)direction, getCenterX(), getCenterY());
		Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mtx, true);
		this.bitmap = rotatedBitmap;
	}
}
