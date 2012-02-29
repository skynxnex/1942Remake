package android.alm.pontus.remake;

import alm.pontus.android.remake.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
	
	public static int screenWidth;
	public static int screenHeight;
	private GameThread thread;
	public static Pattern pattern;
	public static Bitmap eplane;
	public static boolean newWave;
	private Bitmap shot;

	@SuppressWarnings("static-access")
	public Panel(Context context, int screenWidth, int screenHeight) {
		super(context);
		
		getHolder().addCallback(this);
		Panel.screenWidth = screenWidth;
		Panel.screenHeight = screenHeight;
		pattern = new Pattern();
		
		Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		Bitmap smallplanes = BitmapFactory.decodeResource(getResources(), R.drawable.smallplanes);
		this.shot = BitmapFactory.decodeResource(getResources(), R.drawable.shot);
		
		
		Bitmap hplane = smallplanes.createBitmap(smallplanes, 327, 46, 45, 30);
		this.eplane = smallplanes.createBitmap(smallplanes, 327, 46, 45, 30); 
			
		
		// Initialize starter Entities
		@SuppressWarnings("unused")
		Background background1 = new Background(0,0,0,0,0,1,background);
		@SuppressWarnings("unused")
		Background background2 = new Background(0,background.getHeight() * -1,0,0,0,1,background);
		@SuppressWarnings("unused")
		Player player = new Player(screenWidth / 2, screenHeight -50, 1, 0, hplane);
	
		//TODO set Bitmap info for each Entity
		
	}
	
	//TODO touch or accelerometer change function
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int eventAction = event.getAction();
		
		switch(eventAction){
		case MotionEvent.ACTION_DOWN:
			new Shot((int) event.getX(), (int) event.getY(), 1, 1, 0, -10, shot, false);
			break;
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new GameThread(getHolder());
		thread.setRunning(true);
		thread.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while(retry == true) {
			try {
				thread.join();
				retry = false;
			} catch(InterruptedException e) {
				
			}
		}
	}

}
