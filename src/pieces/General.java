package pieces;
import board.*;

public class General extends Piece {
	
	/** constructor for General
	  * 
	  * @param player true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public General(boolean player, int x, int y, Board board) {		//constructor for General
		super(player, x, y, board);
	}
	
	/** constructor for chess piece
	  * 
	  * @param true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	  * @param is_visible determine whether the piece is visible or not
	*/
	public General(boolean player, int x, int y, Board board, Boolean is_visible) {
		super(player, x, y, board,is_visible);
	}
	
	/** function to test whether a move is valid or not
	  * 
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return boolean Return true if it can move to the destination; otherwise,return false. 
	*/
	public boolean canMove(int destX, int destY) {
		if (this.isOutsidePalace(destX, destY)) {  	//check for out of boundary
			return false;
		}
		if (this.getX()==destX && this.getY()==destY) { 	//check for the case when general doesn't move
			return false;
		}
		if (this.getBoard()[destX][destY]!=null && this.getBoard()[destX][destY].getPlayer()==this.getPlayer()) {//can't kill piece that belongs to the same player
			return false;
		}
		if (Math.abs(this.getX()-destX)+Math.abs(this.getY()-destY)==1) { //check for basic moving rule of general
			return true;
		}
		return false;
	}
	
	/** function to get the name of a piece
	  * will be called when drawing the pieces in the gui class.
	  * @return String Return the name of a piece. 
	*/
	public 	String getName() {
		if (this.getPlayer()) {
			return "帥";
		}
		else
			return "將";
	}
	
	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	public Piece copyPiece() {
		return new General(this.getPlayer(),this.getX(),this.getY(),this.getChessBoard());
	}
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	public String move_to_string(int destX, int destY) {
		if (this.getPlayer()) {
			if (destX==this.getX()) {
				if (this.getY()>destY) {
					return "帥"+convert_to_chinese_number(1+this.getX())+"退"+convert_to_chinese_number(this.getY()-destY)+"   ";
				}
				else{
					return "帥"+convert_to_chinese_number(1+this.getX())+"进"+convert_to_chinese_number(destY-this.getY())+"   ";
				}
			}
			else {
				return "帥"+convert_to_chinese_number(1+this.getX())+"平"+convert_to_chinese_number(1+destX)+"   ";
			}
		}
		else {
			if (destX==this.getX()) {
				if (this.getY()<destY) {
					return "將"+Integer.toString(9-this.getX())+"退"+Integer.toString(destY-this.getY())+"  ";
				}
				else{
					return "將"+Integer.toString(9-this.getX())+"进"+Integer.toString(this.getY()-destY)+"  ";
				}
			}
			else {
				return "將"+Integer.toString(9-this.getX())+"平"+Integer.toString(9-destX)+"  ";
			}
		}
	}
	//get the index of the piece in the killed_pieces array in the gui class.
	public int getIndex() {
		return 0;
	}
}

