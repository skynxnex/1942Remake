package android.alm.pontus.remake;

import java.util.ArrayList;

public class BreakManager {
	public ArrayList<Break> breakList;
	public int nrOfPlanes, deltaX, deltaY, startX, startY, Xmod, Ymod;

	public BreakManager() {
		this.breakList = new ArrayList<Break>();
	}
}
