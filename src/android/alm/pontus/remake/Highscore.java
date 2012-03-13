package android.alm.pontus.remake;

import alm.pontus.android.remake.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Highscore extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
	}

	public void buttonClicked(View view){
		Intent intent = new Intent();
		intent.setClass(getApplicationContext(), Menu.class);
		startActivity(intent);
	}
}
