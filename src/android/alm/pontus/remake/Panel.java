package android.alm.pontus.remake;

import alm.pontus.android.remake.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener {
	
	public static 	int 		screenWidth, screenHeight;
	private 		GameThread 	thread;
	public static 	Pattern 	pattern;
	public static 	Bitmap 		eplane;
	public static 	Bitmap 		hplane;
	public static 	boolean 	newWave;
	private 		Bitmap		shot;
	private static 	Context 	context;
	public static 	Bitmap 		explosion;
	public static 	Bitmap 		bomb;
	public static 	Player 		player;
	public static 	Bitmap 		go;

	@SuppressWarnings("static-access")
	public Panel(Context context, int screenWidth, int screenHeight) {
		super(context);
		this.context = context;
		getHolder().addCallback(this);
		Panel.screenWidth = screenWidth;
		Panel.screenHeight = screenHeight;
		pattern = new Pattern();
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//		options.inDither = false;
//		options.inPurgeable = true;
//	    options.inInputShareable = true;
//	    options.inPreferQualityOverSpeed = true;
		
		Bitmap background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background), screenWidth, screenHeight, false);
		Bitmap smallplanes = BitmapFactory.decodeResource(getResources(), R.drawable.smallplanes);
		this.shot = BitmapFactory.decodeResource(getResources(), R.drawable.shot);
		this.explosion = BitmapFactory.decodeResource(getResources(), R.drawable.expl);
		this.bomb = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
//		explosion = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.expl, options), BitmapFactory.decodeResource(getResources(), R.drawable.expl, options).getWidth() /5, BitmapFactory.decodeResource(getResources(), R.drawable.expl, options).getWidth() /5, false);
		
		
		this.go = BitmapFactory.decodeResource(getResources(), R.drawable.go2);
		this.hplane = smallplanes.createBitmap(smallplanes, 230, 40, 45, 30);
		this.eplane = smallplanes.createBitmap(smallplanes, 180, 40, 45, 30); 
			
		
		// Initialize starter Entities
		@SuppressWarnings("unused")
		Background background1 = new Background(0,0,0,0,0,1,background);
		@SuppressWarnings("unused")
		Background background2 = new Background(0,background.getHeight() * -1,0,0,0,1,background);
		
		this.player = new Player(screenWidth / 2, screenHeight -50, 1, 0, hplane);
	
		//TODO set Bitmap info for each Entity
		
	}
	
	//TODO touch or accelerometer change function
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int eventAction = event.getAction();
		
		switch(eventAction){
		case MotionEvent.ACTION_DOWN:
			new Shot((int) player.getX(), (int) player.getY() - hplane.getHeight() -1, 1, 0, 0, -10, shot, false);
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			float[] values = event.values;
			float xValue = values[0] *-1;
			float yValue = values[1];
			
			player.movement((int)xValue, (int)yValue);
//			Log.e("MOVE", "X: "+xValue+"Y: "+yValue);
		}
		
	}
	
	public void setRunning(boolean run) {
		thread.setRunning(run);
	}
	
	public static void showHighscore(){
		EntityManager.INSTANCE.clearLists();
		CollisionManager.INSTANCE.clearLists();
		GameThread.running = true;
		Intent intent = new Intent();
		intent.setClass(Panel.context, Highscore.class);
		context.startActivity(intent);
	}
}
