package android.alm.pontus.remake;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Pattern {
	
	public Map<Integer, BreakManager> patterns = new HashMap<Integer, BreakManager>();


	public Pattern() {
		this.generatePatterns();
		
	}
	
	private void generatePatterns() {
		// A new breakmanager for the map
		BreakManager breakManager = new BreakManager();
		breakManager.nrOfPlanes = 6;
		breakManager.deltaX = 0;
		breakManager.deltaY = 1;
		breakManager.startX = 50;
		breakManager.startY = 1;
		breakManager.Xmod = 1;
		breakManager.Ymod = -60;
		// A break to put in the breakmanager list
		Break break1 = new Break(50,100,2,0, false);
		Break break2 = new Break(200, 100, 0, -1, false);
		Break break3 = new Break(200, -100, 0, 0, true);
		// Add the break to the breakmanager list
		breakManager.breakList.add(break1);
		breakManager.breakList.add(break2);
		breakManager.breakList.add(break3);
		patterns.put(0, breakManager);
		
		// put the pattern in the pattern map
		BreakManager br = new BreakManager();
		br.nrOfPlanes = 5;
		br.deltaX = 1;
		br.deltaY = 0;
		br.startX = -30;
		br.startY = 100;
		br.Xmod = -60;
		br.Ymod = 1;
		Break break4 = new Break(Panel.screenWidth + 50, 100, 1, 1, true);
		br.breakList.add(break4);
		patterns.put(1, br);
		
		br.breakList.clear();
		br.nrOfPlanes = 6;
		br.deltaX = -1;
		br.deltaY = 0;
		br.startX = Panel.screenWidth + 30;
		br.startY = 100;
		br.Xmod = 60;
		br.Ymod = 1;
		Break break5 = new Break(-50, 100, 1, 1, true);
		br.breakList.add(break5);
		patterns.put(2, br);
		
//		Log.e("MAP", ""+patterns.keySet());
//		Log.e("MAP ENTRY", ""+patterns.entrySet());
	}

	public int getRandomPattern() {
		Random gen = new Random();
		int value = gen.nextInt(patterns.size());
//		Log.e("RAND VAL", ""+value);
		return value;
	}
	
	public void setUpdatedPosition(EnemyPlane plane) {
		BreakManager br = Panel.pattern.patterns.get(plane.getMovingPattern());
		for(Break breaker : br.breakList) {
			if(plane.getX() == breaker.X && plane.getY() == breaker.Y) {
				plane.deltaX = breaker.deltaX;
				plane.deltaY = breaker.deltaY;
				if(breaker.last) {
					plane.deleted = true;
				}
			}
		}
	}
}
