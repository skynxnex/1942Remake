package android.alm.pontus.remake;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Explosion extends Drawable {
	private int currentFrame, frameCount, minusMod, posY;
	@SuppressWarnings("unused")
	private Bitmap bitmap;

	public Explosion(int x, int y, Bitmap bitmap, int frameCount) {
		super(x, y, 0, 0, bitmap, false);
		this.frameCount = frameCount;
		this.currentFrame = 0;
		this.bitmap = bitmap;
		this.minusMod = 0;
		this.posY = 0;
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		int height = getBitmapHeight() / 5;
		int width = getBitmapWidth() / 5;
		switch (currentFrame) {
		case 0:
			posY = 0;
			minusMod = 0;
			break;
		case 6:
			posY = height * 1;
			minusMod = 6;
			break;
		case 11:
			posY = height * 2;
			minusMod = 11;
			
			break;
		case 16:
			posY = height * 3;
			minusMod = 16;
			
			break;
		case 21:
			posY = height * 4;
			minusMod = 21;
			
			break;
		}
		
		if(currentFrame >= frameCount) {
			this.deleted = true;
		}
		this.sourceRect.top = posY;
		this.sourceRect.left = width * (currentFrame - minusMod);
		
		this.sourceRect.bottom = posY + height;
		this.sourceRect.right = (width * (currentFrame - minusMod)) + width;
		
//		Log.d("EXPL", "CURR:"+currentFrame+" POSX: "+posY+" minusMod: "+minusMod);
		currentFrame++;
		super.onDraw(canvas);
	}
	
//	private Bitmap resizeBitmap(Bitmap bitmapOrg) {
//		int width = bitmapOrg.getWidth();
//        int height = bitmapOrg.getHeight();
//        int newWidth = 100;
//        int newHeight = 100;
        
//        return Bitmap.createScaledBitmap(bitmapOrg, bitmapOrg.getWidth() /2, bitmapOrg.getWidth() /2, false);
       
//        // calculate the scale - in this case = 0.4f
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//       
//        // createa matrix for the manipulation
//        Matrix matrix = new Matrix();
//        // resize the bit map
//        matrix.postScale(scaleWidth, scaleHeight);
//        // rotate the Bitmap
////        matrix.postRotate(45);
// 
//        // recreate the new Bitmap
//        Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width, height, matrix, true);
//        return resizedBitmap;
//	}

}
