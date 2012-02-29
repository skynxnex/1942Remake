package android.alm.pontus.remake;

import java.util.ArrayList;

import android.util.Log;

public class Waves {

	private int counter;
	private ArrayList<EnemyPlane>enemyPlanes;
	
	public Waves() {
		super();
		this.counter = 0;
		this.enemyPlanes = new ArrayList<EnemyPlane>();
	}

	public void createWave() {
		if(Panel.newWave) {
			if(counter == 4 || counter == 8) {
				miniBoss();
				Log.d("WAVE", "miniboss");
			}else if(counter == 12) {
				bigBoss();
				Log.e("WAVE", "bigboss");
			} else {
				normalWave();
			}
			if(counter >= 12){
				Log.e("WAVE", "resetting counter");
				counter = 1;
			} else {
				counter ++;
			}
		}
	}

	private void normalWave(){
		boolean last = false;
		Panel.newWave = false;
		int pattern = Panel.pattern.getRandomPattern();
		BreakManager br = Panel.pattern.patterns.get(pattern);
		int value = br.nrOfPlanes;
		int Xmodifier = 0, Ymodifier = 0;
		
		for(int i = 1; i <= value; i++) {
			if(i == value) {
				last = true;
			}
			if(br.Xmod == 1) {
				Xmodifier = 0;
			} else {
				Xmodifier = i;
			}
			if(br.Ymod == 1) {
				Ymodifier = 0;
			} else {
				Ymodifier = i;
			}
			enemyPlanes.add(new EnemyPlane(br.startX + (Xmodifier * br.Xmod), br.startY + (Ymodifier * br.Ymod), 1, 1, br.deltaX, br.deltaY, Panel.eplane, pattern, last));
		}	
	}

	private static void miniBoss() {

	}

	private static void bigBoss() {

	}
	
	public boolean done() {
		if(enemyPlanes.isEmpty()) {
			return true;
		}
		return false;
	}

}
