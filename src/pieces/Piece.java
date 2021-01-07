package pieces;
import java.awt.Point;
import java.util.ArrayList;

import board.*;


public abstract class Piece {
	private boolean player; 	
	private int x;
	private int y;
	private Board board;
	public Boolean is_visible;
	
	/** constructor for chess piece
	  * 
	  * @param true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	*/
	public Piece(boolean player, int x, int y, Board board) {
		this.player=player;
		this.x=x;
		this.y=y;
		this.board=board;
		this.is_visible=true;
	}
	
	/** constructor for chess piece
	  * 
	  * @param true for player1, false for player2
	  * @param x The x coordinate of the object
	  * @param y The y coordinate of the object
	  * @param board The board which the object is on
	  * @param is_visible determine whether the piece is visible or not
	*/
	public Piece(boolean player, int x, int y, Board board, Boolean is_visible) {
		this.player=player;
		this.x=x;
		this.y=y;
		this.board=board;
		this.is_visible=is_visible;
	}
	
	//getter for the field player
	public boolean getPlayer() {
		return this.player;
	}
	
	//getter for the field x
	public int getX() {
		return this.x;
	}
	
	//getter for the field y
	public int getY() {
		return this.y;
	}
	
	//set the position for the piece
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	//getter for the field board
	public Piece[][] getBoard() {
		return this.board.getBoard();
	}
	
	//getter for the field chess board
	public Board getChessBoard() {
			return this.board;
	}

	
	/** function to test whether a move is out of boundary or not
	  * 
	  * @param i the x coordinate for the destination
	  * @param j the y coordinate for the destination
	  * @return boolean Return true if it the (i,j) is out of boundary, otherwise return false.
	*/
	public boolean outOfBoundary(int i, int j) {
		return ((i<0) || (i>8) || (j<0) || (j>9));  		
	}
	
	/** abstract function to test whether a move is valid or not,
	  * will be implemented in the child class. 
	  * 
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return boolean Return true if it can move to the destination; otherwise,return false. 
	*/
	public abstract boolean canMove(int destX, int destY); 	
	
	/** function to check if the destination is outside of the palace.
	  * 
	  * will be called by Advisor and General.
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return boolean Return true if the destination is outside of the palace, return false otherwise.
	*/
	public boolean isOutsidePalace(int destX, int destY){
		if (this.getPlayer() && destX>=3 && destX<=5 && destY>=0 && destY<=2)
			return false;
		if (!this.getPlayer() && destX>=3 && destX<=5 && destY>=7 && destY<=9)
			return false;
		return true;
	}
	
	//return valid moves
	public ArrayList<Point> getValidMoves(){
		ArrayList<Point> validMoves=new ArrayList<Point>();
		for (int i=0; i<9; i++){
			for (int j=0;j<10;j++) {
				if (this.canMove(i, j)) {
					validMoves.add(new Point(i,j));
				}
			}
		}
		return validMoves;
	}

	/** function to count the number of obstacles in a straight route.
	  * will be called by Cannon class and Chariot class.
	  * 
	  * @param initX the x coordinate for the staring point.
	  * @param initY the x coordinate for the starting point.
	  * @param destX the x coordinate for the destination
	  * @param destY the y coordinate for the destination
	  * @return int Return the number of obstacles in a straight route. 
	*/
	public int numberOfObstacles(int initX, int initY, int destX, int destY) {
		Piece[][] board=this.getBoard();
		int obstacles=0;
		if (initX>destX) {			//moving backward
			for (int i=initX-1; i>destX; i--) {
				if (board[i][initY]!=null)
					obstacles++;
			}
			return obstacles;
		}	
		else if (initX<destX) {			//moving forward
			for (int i=initX+1; i<destX; i++) {
				if (board[i][initY]!=null)
					obstacles++;
			}
			return obstacles;
		}		
		else if (initY>destY) {			//moving downward
			for (int j=initY-1; j>destY; j--) {
				if (board[initX][j]!=null)
					obstacles++;
			}
			return obstacles;
		}		 
		else if (initY<destY) {			//moving upward
			for (int j=initY+1; j<destY; j++) {
				if (board[initX][j]!=null)
					obstacles++;
			}
			return obstacles;
		}	
		else {
			return obstacles;
		}
	}
	
	/** function to get the name of a piece
	  * will be called when drawing the pieces in the gui class.
	  * @return String Return the name of a piece. 
	*/
	abstract public 	String getName();
	
	/** function to get the deep copy of a piece
	  * @return Piece Return the deep copy of a piece. 
	*/
	abstract public Piece copyPiece();
	
	/** function to get the string representation of a move.
	  * @return String Return the string representation of a move.
	*/
	abstract public String move_to_string(int destX, int destY);
	
	//get the index of the piece in the killed_pieces array in the gui class.
	abstract public int getIndex();

	//setter for the field board. 
	public void setBoard(Board newBoard) {
		this.board=newBoard;		
	}
	
	//convert integer to the corresponding Chinese word representation.
	public String convert_to_chinese_number(int number) {
		if (number==1) 
			return "一";
		else if (number==2) 
			return "二";
		else if (number==3) 
			return "三";
		else if (number==4) 
			return "四";
		else if (number==5) 
			return "五";
		else if (number==6) 
			return "六";
		else if (number==7) 
			return "七";
		else if (number==8) 
			return "八";
		else if (number==9) 
			return "九";
		else
			return "";
	}
}