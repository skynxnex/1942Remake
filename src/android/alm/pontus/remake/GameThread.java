package android.alm.pontus.remake;

import java.lang.Thread.UncaughtExceptionHandler;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread implements UncaughtExceptionHandler {

	SurfaceHolder surfaceHolder;
	public static boolean running;
	private Waves waves;

	private final int MAX_FPS = 50;
	private final int FRAME_PERIOD = 1000 / MAX_FPS;

	public GameThread(SurfaceHolder holder) {
		super();
		this.surfaceHolder = holder;
		this.waves = new Waves();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		Canvas canvas = null;
		long beginTime;
		long timeDiff = 20;
		int MAX_FRAMES_SKIPPED = 5;
		int framesSkipped;
		int sleepTime;
		Panel.newWave = true;

		while(this.running) {
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;

					waves.createWave();
					EntityManager.INSTANCE.drawEntities(canvas);
					EntityManager.INSTANCE.updatePosition();
					EntityManager.INSTANCE.removeDeleted();
					CollisionManager.INSTANCE.removeDeleted();
					CollisionManager.INSTANCE.collision();
					CounterManager.INSTANCE.updateScore(canvas);

					timeDiff = System.currentTimeMillis() - beginTime;
					sleepTime = (int) (FRAME_PERIOD - timeDiff);
					while(sleepTime <= 0 && framesSkipped < MAX_FRAMES_SKIPPED) {
						sleepTime += FRAME_PERIOD;
						framesSkipped++;
					}
				}
			} finally {
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
		if(!running) {
			Log.i("GO", "spelet Šr slut");
			Gameover go = new Gameover();
			try {
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					go.onDraw(canvas);
				}
			} finally {
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}

			try {
				this.sleep(1000);
				Panel.showHighscore();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("static-access")
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		ex.printStackTrace();

	}
}
