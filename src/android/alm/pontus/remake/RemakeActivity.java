package android.alm.pontus.remake;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;

public class RemakeActivity extends Activity {
    private Panel panel;
    private SensorManager sensorManager;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display 	= getWindowManager().getDefaultDisplay();
        int screenWidth 	= display.getWidth();
        int screenHeight 	= display.getHeight();
        
        panel = new Panel(this, screenWidth, screenHeight);
        setContentView(panel);
    }
    
    @Override
    public void onResume () {
    	super.onResume();
    	
    	sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    	sensorManager.registerListener(panel, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	sensorManager.unregisterListener(panel);
    	this.finish();
    }
}