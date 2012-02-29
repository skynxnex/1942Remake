package android.alm.pontus.remake;

public class Break {
	public int X, Y, deltaX, deltaY;
	public Boolean last;

	public Break (int x, int y, int deltaX, int deltaY, Boolean last){
		this.X = x;
		this.Y = y;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		this.last = last;
	}

}
