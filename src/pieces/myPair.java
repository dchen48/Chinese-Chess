package pieces;

//meta data to store the number of red/black pieces in the game.
public class myPair {
	private int red;		//number of red pieces
	private int black;	//number of black pieces
	
	public myPair(int red, int black) {
		this.red=red;
		this.black=black;
	}
	
	public int get_red() {
		return this.red;
	}
	
	public int get_black() {
		return this.black;
	}
}
