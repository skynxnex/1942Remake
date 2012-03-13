package android.alm.pontus.remake;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	String menuItems[] = { "New Game", "Highscore" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, menuItems));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String menuPosition = menuItems[position];

		try {
		Class<?> ourClass;
		Log.i("menupos", menuPosition);
		if(menuPosition == "New Game") {
			ourClass = Class.forName("android.alm.pontus.remake.RemakeActivity");
		} else {
			ourClass = Class.forName("android.alm.pontus.remake.Highscore");
		}
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
