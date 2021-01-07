package pieces;

import board.Board;

public class SuperSoldier extends Piece {
	/** constructor for superSoldier
	  * 
	  * @param player true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public SuperSoldier(boolean player, int x, int y, Board board) {		//constructor for superSoldier
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
		if (this.getX()==destX && this.getY()==destY) { 	//check for the case when soldier doesn't move
			return false;
		}
		if (this.getBoard()[destX][destY]!=null && this.getBoard()[destX][destY].getPlayer()==this.getPlayer()) {//can't kill piece that belongs to the same player
			return false;
		}
		if (this.getPlayer()) {	//player one 
			if ((destY-this.getY()==1 && this.getX()-destX==0) || (destY-this.getY()==0 && Math.abs(this.getX()-destX)==1))
				return true;
			else 
				return false;
		}
		else {	//player two 
			if ((destY-this.getY()==-1 && this.getX()-destX==0) || (destY-this.getY()==0 && Math.abs(this.getX()-destX)==1))
				return true;
			else 
				return false;
		}
	}
	
	/** function to get the name of a piece
	  * will be called when drawing the pieces in the gui class.
	  * @return String Return the name of a piece. 
	*/
	public 	String getName() {
		if (this.getPlayer()) {
			return "大兵";
		}
		else
			return "大卒";
	}

	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	public Piece copyPiece() {
		return new SuperSoldier(this.getPlayer(),this.getX(),this.getY(),this.getChessBoard());
	}
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	public String move_to_string(int destX, int destY) {
		if (this.getPlayer()) {
			if (destX==this.getX()) {
				if (this.getY()>destY) {
					return "大兵"+convert_to_chinese_number(1+this.getX())+"退"+convert_to_chinese_number(this.getY()-destY)+"   ";
				}
				else{
					return "大兵"+convert_to_chinese_number(1+this.getX())+"进"+convert_to_chinese_number(destY-this.getY())+"   ";
				}
			}
			else {
				return "大兵"+convert_to_chinese_number(1+this.getX())+"平"+convert_to_chinese_number(1+destX)+"   ";
			}
		}
		else {
			if (destX==this.getX()) {
				if (this.getY()<destY) {
					return "大卒"+Integer.toString(9-this.getX())+"退"+Integer.toString(destY-this.getY())+"   ";
				}
				else{
					return "大卒"+Integer.toString(9-this.getX())+"进"+Integer.toString(this.getY()-destY)+"   ";
				}
			}
			else {
				return "大卒"+Integer.toString(9-this.getX())+"平"+Integer.toString(9-destX)+"   ";
			}
		}
	}
	//get the index of the piece in the killed_pieces array in the gui class.
	public int getIndex() {
		return 8;
	}
}

