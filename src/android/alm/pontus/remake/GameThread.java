package android.alm.pontus.remake;

import java.lang.Thread.UncaughtExceptionHandler;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread implements UncaughtExceptionHandler {

	SurfaceHolder surfaceHolder;
	private boolean running;
	private Waves waves;

	public GameThread(SurfaceHolder holder) {
		super();
		this.surfaceHolder = holder;
		this.waves = new Waves();
	}

	@Override
	public void run() {
		Canvas canvas;
		Panel.newWave = true;

		while(this.running) {
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					waves.createWave();
					EntityManager.INSTANCE.drawEntities(canvas);
					EntityManager.INSTANCE.updatePosition();
					EntityManager.INSTANCE.removeDeleted();
					CollisionManager.INSTANCE.removeDeleted();
					CollisionManager.INSTANCE.collision();
					CounterManager.INSTANCE.updateScore(canvas);
				}
			} finally {
				if(canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		ex.printStackTrace();

	}

}
