package android.alm.pontus.remake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



public class CounterManager {
	
	public static final CounterManager INSTANCE = new CounterManager();
	
	Paint scorePaint = new Paint();
	private int score;
	
	private CounterManager() {
		this.scorePaint.setColor(Color.WHITE);
		this.scorePaint.setTextSize(25);
		this.scorePaint.setShadowLayer(5, 0, 0, Color.MAGENTA);
	}
	
	public void updateScore(Canvas canvas) {
		String scoreToDisplay = Integer.toString(score);
		drawText(canvas, scoreToDisplay, 30, Panel.screenHeight - 40, scorePaint);
	}
	
	public void drawText(Canvas canvas, String text, int x, int y, Paint paint) {
		canvas.drawText(text, x, y, paint);
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	//TODO function for setting high score when game ends

}
