package pieces;

import board.Board;

public class Horse extends Piece {
	
	/** constructor for Horse
	  * 
	  * @param player true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public Horse(boolean player, int x, int y, Board board) {		//constructor for Elephant
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
	public Horse(boolean player, int x, int y, Board board, Boolean is_visible) {
		super(player, x, y, board,is_visible);
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
		if (this.getX()==destX && this.getY()==destY) { 	//check for the case when Horse doesn't move
			return false;
		}
		if (this.getBoard()[destX][destY]!=null && this.getBoard()[destX][destY].getPlayer()==this.getPlayer()) {//can't kill piece that belongs to the same player
			return false;
		}
		if (Math.abs(this.getX()-destX)==1 && Math.abs(this.getY()-destY)==2 && this.getBoard()[this.getX()][(int)((this.getY()+destY)/2)]==null) { //check for basic moving rule of Horse
			return true;
		}
		if (Math.abs(this.getX()-destX)==2 && Math.abs(this.getY()-destY)==1 && this.getBoard()[(int)((this.getX()+destX)/2)][this.getY()]==null) { //check for basic moving rule of Horse
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
			return "傌";
		}
		else
			return "馬";
	}
	
	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	public Piece copyPiece() {
		return new Horse(this.getPlayer(),this.getX(),this.getY(),this.getChessBoard());
	}
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	public String move_to_string(int destX, int destY) {
		if (this.getPlayer()) {
			if (this.getY()>destY) {
				return "傌"+convert_to_chinese_number(1+this.getX())+"退"+convert_to_chinese_number(1+destX)+"   ";
			}
			else return "傌"+convert_to_chinese_number(1+this.getX())+"进"+convert_to_chinese_number(1+destX)+"   ";
		}
		else {
			if (this.getY()>destY) {
				return "馬"+Integer.toString(9-this.getX())+"进"+Integer.toString(9-destX)+"  ";
			}
			else return "馬"+Integer.toString(9-this.getX())+"退"+Integer.toString(9-destX)+"  ";
		}
	}
	//get the index of the piece in the killed_pieces array in the gui class.
	public int getIndex() {
		return 5;
	}
}



