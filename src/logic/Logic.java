package logic;
import pieces.*;
import board.*;

public class Logic {
	private Board board;
	private boolean turn; 
	
	//constructor for Logic
	public Logic(Board board,boolean turn) {	// custom constructor
		this.board=board;
		this.turn=turn;
	}
	
	/** function to get the piece at (x,y)
	  * 
	  * @param x the x coordinate for the destination
	  * @param y the y coordinate for the destination
	  * @return Piece The Piece at (x,y).
	*/
	public Piece getPiece(int x, int y) {
		return this.board.getBoard()[x][y];
	}
	
	/** function to get the general of a player
	  * 
	  * @param player true for player1, false for player2
	  * @return General If there is a general of this player on the board, then return it. Otherwise, return null.
	*/
	public Piece getGeneral(boolean player) {
		for (int i=0; i<9; i++) {
			for (int j=0; j<10; j++) {
				if (this.getPiece(i, j) instanceof General && this.getPiece(i, j).getPlayer()==player) {
					return this.getPiece(i, j);
				}	
			}
		}
		return null;
	}
	
	/** function to check whether two generals are facing each other directly.
	  * 
	  * @return Boolean Return true if two generals are facing directly, return false otherwise.
	*/
	public boolean isGeneralFacingDirectly() {
		Piece general1=this.getGeneral(true);
		Piece general2=this.getGeneral(false);
		if (general1.getX()!=general2.getX())
			return false;
		for (int i=general1.getY()+1; i<=general2.getY()-1;i++) {
			if (this.getPiece(general1.getX(), i)!=null)
				return false; 
		}
		return true; 
	}
	 
	/** function to check the state of the game.
	  * 
	  * @return int Return 1 if player1 wins, return 0 if player2 wins, return 2 if game can continue normally. 
	*/
	public int gameState() {
		if (this.getGeneral(true)==null)
			return 0;
		if (this.getGeneral(false)==null)
			return 1;
		if (this.isGeneralFacingDirectly()) {
			if (turn)
				return 1;
			else
				return 0;
		}
		return 2;
	}	
}

 