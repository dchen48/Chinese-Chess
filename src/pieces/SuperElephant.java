package pieces;
 
import board.Board;

public class SuperElephant extends Piece {
	
	/** constructor for SuperElephant
	  * 
	  * @param player true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public SuperElephant(boolean player, int x, int y, Board board) {		//constructor for SuperElephant
		super(player, x, y, board);
	}
		
	/** function to test whether a move is valid or not
	  * 
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return boolean Return true if it can move to the destination; otherwise,return false. 
	*/
	public boolean canMove(int destX, int destY) {
		if (this.outOfBoundary(destX, destY)) {  	//check for out of boundary  
			return false;
		}
		if (this.getX()==destX && this.getY()==destY) { 	//check for the case when Elephant doesn't move
			return false;
		}
		if (this.getBoard()[destX][destY]!=null && this.getBoard()[destX][destY].getPlayer()==this.getPlayer()) {//can't kill piece that belongs to the same player
			return false;
		}
		if (Math.abs(this.getX()-destX)!=2 || Math.abs(this.getY()-destY)!=2) { //check for basic moving rule of elephant
			return false;
		}
		return true;
	}	
	
	
	/** function to get the name of a piece
	  * will be called when drawing the pieces in the gui class.
	  * @return String Return the name of a piece. 
	*/
	public 	String getName() {
		if (this.getPlayer()) {
			return "超相";
		}
		else
			return "超象";
	}

	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	public Piece copyPiece() {
		return new SuperElephant(this.getPlayer(),this.getX(),this.getY(),this.getChessBoard());
	}
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	public String move_to_string(int destX, int destY) {
		if (this.getPlayer()) {
			if (this.getY()>destY) {
				return "超相"+convert_to_chinese_number(1+this.getX())+"退"+convert_to_chinese_number(1+destX)+"   ";
			}
			else return "超相"+convert_to_chinese_number(1+this.getX())+"进"+convert_to_chinese_number(1+destX)+"   ";
		}
		else {
			if (this.getY()>destY) {
				return "超象"+Integer.toString(9-this.getX())+"进"+Integer.toString(9-destX)+"  ";
			}
			else return "超象"+Integer.toString(9-this.getX())+"退"+Integer.toString(9-destX)+"  ";
		}
	}
	//get the index of the piece in the killed_pieces array in the gui class.
	public int getIndex() {
		return 7;
	}
}