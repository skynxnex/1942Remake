package android.alm.pontus.remake;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

public class RemakeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display 	= getWindowManager().getDefaultDisplay();
        int screenWidth 	= display.getWidth();
        int screenHeight 	= display.getHeight();
        
        Panel panel = new Panel(this, screenWidth, screenHeight);
        setContentView(panel);
    }
}