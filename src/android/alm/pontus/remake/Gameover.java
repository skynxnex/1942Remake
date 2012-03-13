package android.alm.pontus.remake;

import android.util.Log;

public class Gameover extends Drawable {

	public Gameover() {
		super(30, 30, 0, 0, Panel.go, false);
		Log.i("GOB", ""+Panel.go.getWidth()+":"+Panel.go.getHeight());
	}
}
