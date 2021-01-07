package pieces;

import board.Board;

public class Cannon extends Piece {
	
	/** constructor for Cannon
	  * 
	  * @param player true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public Cannon(boolean player, int x, int y, Board board) {		//constructor for Cannon
		super(player, x, y, board);
	}
	
	/** function to test whether a move is valid or not
	  * 
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return boolean Return true if it can move to the destination; otherwise,return false. 
	*/
	

	/** constructor for chess piece
	  * 
	  * @param true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	  * @param is_visible determine whether the piece is visible or not
	*/
	public Cannon(boolean player, int x, int y, Board board, Boolean is_visible) {
		super(player, x, y, board,is_visible);
	}
	
	public boolean canMove(int destX, int destY) {
		if (this.outOfBoundary(destX, destY)) {  	//check for out of boundary
			return false;
		}
		if (this.getX()==destX && this.getY()==destY) { 	//check for the case when cannon doesn't move
			return false;
		}
		if (this.getBoard()[destX][destY]!=null && this.getBoard()[destX][destY].getPlayer()==this.getPlayer()) {//can't kill piece that belongs to the same player
			return false;
		}
		if (Math.abs(this.getX()-destX)!=0 && Math.abs(this.getY()-destY)!=0) { //check for basic moving rule of cannon
			return false;
		}
		if (this.numberOfObstacles(this.getX(), this.getY(), destX, destY)==0 && this.getBoard()[destX][destY]==null) //move like a Chariot
			return true;
		if (this.numberOfObstacles(this.getX(), this.getY(), destX, destY)==1 && this.getBoard()[destX][destY]!=null) //kill other player's piece
			return true;
		return false;
	}
	
	/** function to get the name of a piece
	  * will be called when drawing the pieces in the gui class.
	  * @return String Return the name of a piece. 
	*/
	public 	String getName() {
		if (this.getPlayer()) {
			return "炮";
		}
		else
			return "砲";
	}
	
	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	public Piece copyPiece() {
		return new Cannon(this.getPlayer(),this.getX(),this.getY(),this.getChessBoard());
	}
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	public String move_to_string(int destX, int destY) {
		if (this.getPlayer()) {
			if (destX==this.getX()) {
				if (this.getY()>destY) {
					return "炮"+convert_to_chinese_number(1+this.getX())+"退"+convert_to_chinese_number(this.getY()-destY)+"   ";
				}
				else{
					return "炮"+convert_to_chinese_number(1+this.getX())+"进"+convert_to_chinese_number(destY-this.getY())+"   ";
				}
			}
			else {
				return "炮"+convert_to_chinese_number(1+this.getX())+"平"+convert_to_chinese_number(1+destX)+"   ";
			}
		}
		else {
			if (destX==this.getX()) {
				if (this.getY()<destY) {
					return "砲"+Integer.toString(9-this.getX())+"退"+Integer.toString(destY-this.getY())+"  ";
				}
				else{
					return "砲"+Integer.toString(9-this.getX())+"进"+Integer.toString(this.getY()-destY)+"  ";
				}
			}
			else {
				return "砲"+Integer.toString(9-this.getX())+"平"+Integer.toString(9-destX)+"  ";
			}
		}
	}
	//get the index of the piece in the killed_pieces array in the gui class.
	public int getIndex() {
		return 4;
	}
}



