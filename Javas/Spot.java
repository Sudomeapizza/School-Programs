package minegrid;

public class Spot{
	private boolean bomb;
	private int x;
	private int y;
	private String localvalue;

	// creates a new copy of a spot
	Spot (int tx, int ty) {
		x = tx;
		y = ty;
	}
	// returns the X value as a int
	public int getX(){
		return x;
	}
	// returns the X value as a int
	public void makeX(int xx) {
		x = xx;
	}
	// makes the X value as an int
	public void makeY(int yy){
		y = yy;
	}
	// makes the Y value as an int
	public int getY(){
		return y;
	}
	// returns if the spot is a bomb as a boolean
	public boolean isBomb(){
		return bomb;
	}

	// makes that spot a bomb
	public void makeABomb(boolean bommb) {
		bomb = bommb;
	}
	
	/* sets the value of the string
	 * In other words, if it is a empty space, it is " X "
	 * else it is " O "
	*/
	public void setvalue(String value) {
		localvalue = (" " + value + " ");
	}
	// returns the value from above
	public String getvalue() {
		return localvalue;
	}
	// returns the spot
	public Spot getSpot(int a, int b) {
		return getSpot(x,y);
	}
}