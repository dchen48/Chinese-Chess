package move;
import board.Board;

//meta data to store moves of the game
public class Move {
	private boolean flag;	//whether the move kill an enemy piece or not
	private String move;		//the actual move (represented by a String)
	public Move(boolean flag, String move) {
		this.move=move;
		this.flag=flag;
	}
	
	public boolean get_flag() {
		return this.flag;
	}
	
	public String get_move() {
		return this.move;
	}
}
