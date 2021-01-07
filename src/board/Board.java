package board;

import java.awt.Point;
import java.util.ArrayList;
import javafx.util.Pair;
import pieces.*;
public class Board {
	private Piece[][] board;
	
	//constructor for board.
	public Board() {
		this.board=new Piece[9][10];
		for (int i=0; i<9; i++){
			for (int j=0;j<10;j++) {
				this.board[i][j]=null;
			}
		}
	}
	
	//set up the initial configuration for a Chinese chess game.
	public void constructDefaultBoard() {
		//Soldier
		for(int i = 0; i < 9; i=i+2){
			Soldier soldier1=new Soldier(true, i, 3, this);	
			this.addPiece(soldier1);
			Soldier soldier2=new Soldier(false, i, 6, this);	
			this.addPiece(soldier2);
	    }
		//Chariot
		Chariot chariot1=new Chariot(true, 0, 0, this);	
		this.addPiece(chariot1);
		Chariot chariot2=new Chariot(true, 8, 0, this);	
		this.addPiece(chariot2);		
		Chariot chariot3=new Chariot(false, 0, 9, this);	
		this.addPiece(chariot3);
		Chariot chariot4=new Chariot(false, 8, 9, this);	
		this.addPiece(chariot4);	
			
		//Cannon
		Cannon cannon1=new Cannon(true, 1, 2, this);	
		this.addPiece(cannon1);
		Cannon cannon2=new Cannon(true, 7, 2, this);	
		this.addPiece(cannon2);		
		Cannon cannon3=new Cannon(false, 1, 7, this);	
		this.addPiece(cannon3);
		Cannon cannon4=new Cannon(false, 7, 7, this);	
		this.addPiece(cannon4);	
			
		//Horse
		Horse horse1=new Horse(true, 1, 0, this);	
		this.addPiece(horse1);
		Horse horse2=new Horse(true, 7, 0, this);	
		this.addPiece(horse2);	
			
		Horse horse3=new Horse(false, 1, 9, this);	
		this.addPiece(horse3);
		Horse horse4=new Horse(false, 7, 9, this);	
		this.addPiece(horse4);	
			
		//Elephant
		Elephant elephant1=new Elephant(true, 2, 0, this);	
		this.addPiece(elephant1);
		Elephant elephant2=new Elephant(true, 6, 0, this);	
		this.addPiece(elephant2);	
			
		Elephant elephant3=new Elephant(false, 2, 9, this);	
		this.addPiece(elephant3);
		Elephant elephant4=new Elephant(false, 6, 9, this);	
		this.addPiece(elephant4);	
			
		//Advisor
		Advisor advisor1=new Advisor(true, 3, 0, this);	
		this.addPiece(advisor1);
		Advisor advisor2=new Advisor(true, 5, 0, this);	
		this.addPiece(advisor2);	
			
		Advisor advisor3=new Advisor(false, 3, 9, this);	
		this.addPiece(advisor3);
		Advisor advisor4=new Advisor(false, 5, 9, this);	
		this.addPiece(advisor4);	
			
		//General
		General general1=new General(true, 4, 0, this);	
		this.addPiece(general1);
			
		General general2=new General(false, 4, 9, this);	
		this.addPiece(general2);	
	}
	
	//add the red pieces on board with random positions 
	public void constructBlindChessBoardRed() {
		ArrayList<myPair> redPositions=RandomPositions.getRandomPositionsRed();
		Soldier soldier1=new Soldier(true, redPositions.get(0).get_red(), redPositions.get(0).get_black(), this, false);
		this.addPiece(soldier1);
		
		Soldier soldier2=new Soldier(true, redPositions.get(1).get_red(), redPositions.get(1).get_black(), this, false);
		this.addPiece(soldier2);
		
		Soldier soldier3=new Soldier(true, redPositions.get(2).get_red(), redPositions.get(2).get_black(), this, false);
		this.addPiece(soldier3);
	
		Soldier soldier4=new Soldier(true, redPositions.get(3).get_red(), redPositions.get(3).get_black(), this, false);
		this.addPiece(soldier4);
		Soldier soldier5=new Soldier(true, redPositions.get(4).get_red(), redPositions.get(4).get_black(), this, false);
		this.addPiece(soldier5);
		Cannon cannon1=new Cannon(true, redPositions.get(5).get_red(), redPositions.get(5).get_black(), this, false);	
		this.addPiece(cannon1);
		Cannon cannon2=new Cannon(true, redPositions.get(6).get_red(), redPositions.get(6).get_black(), this, false);	
		this.addPiece(cannon2);	
		Chariot chariot1=new Chariot(true, redPositions.get(7).get_red(), redPositions.get(7).get_black(), this, false);	
		this.addPiece(chariot1);
		Chariot chariot2=new Chariot(true, redPositions.get(8).get_red(), redPositions.get(8).get_black(), this, false);	
		this.addPiece(chariot2);	
		Horse horse1=new Horse(true, redPositions.get(9).get_red(), redPositions.get(9).get_black(), this, false);	
		this.addPiece(horse1);
		Horse horse2=new Horse(true, redPositions.get(10).get_red(), redPositions.get(10).get_black(), this, false);	
		this.addPiece(horse2);
		Elephant elephant1=new Elephant(true, redPositions.get(11).get_red(), redPositions.get(11).get_black(), this, false);	
		this.addPiece(elephant1);
		Elephant elephant2=new Elephant(true, redPositions.get(12).get_red(), redPositions.get(12).get_black(), this, false);	
		this.addPiece(elephant2);	
		
		//General
		General general1=new General(true, 4, 0, this);	
		this.addPiece(general1);
		
		//Advisor
		Advisor advisor1=new Advisor(true, 3, 0, this);	
		this.addPiece(advisor1);
		Advisor advisor2=new Advisor(true, 4, 1, this);	
		this.addPiece(advisor2);	
	}
		
	//add the black pieces on board with random positions 
	public void constructBlindChessBoardBlack() {
		ArrayList<myPair> blackPositions=RandomPositions.getRandomPositionsBlack();
		Soldier soldier1=new Soldier(false, blackPositions.get(0).get_red(), blackPositions.get(0).get_black(), this, false);
		this.addPiece(soldier1);
		
		Soldier soldier2=new Soldier(false, blackPositions.get(1).get_red(), blackPositions.get(1).get_black(), this, false);
		this.addPiece(soldier2);
		
		Soldier soldier3=new Soldier(false, blackPositions.get(2).get_red(), blackPositions.get(2).get_black(), this, false);
		this.addPiece(soldier3);
	
		Soldier soldier4=new Soldier(false, blackPositions.get(3).get_red(), blackPositions.get(3).get_black(), this, false);
		this.addPiece(soldier4);
		Soldier soldier5=new Soldier(false, blackPositions.get(4).get_red(), blackPositions.get(4).get_black(), this, false);
		this.addPiece(soldier5);
		Cannon cannon1=new Cannon(false, blackPositions.get(5).get_red(), blackPositions.get(5).get_black(), this, false);	
		this.addPiece(cannon1);
		Cannon cannon2=new Cannon(false, blackPositions.get(6).get_red(), blackPositions.get(6).get_black(), this, false);	
		this.addPiece(cannon2);	
		Chariot chariot1=new Chariot(false, blackPositions.get(7).get_red(), blackPositions.get(7).get_black(), this, false);	
		this.addPiece(chariot1);
		Chariot chariot2=new Chariot(false, blackPositions.get(8).get_red(), blackPositions.get(8).get_black(), this, false);	
		this.addPiece(chariot2);	
		Horse horse1=new Horse(false, blackPositions.get(9).get_red(), blackPositions.get(9).get_black(), this, false);	
		this.addPiece(horse1);
		Horse horse2=new Horse(false, blackPositions.get(10).get_red(), blackPositions.get(10).get_black(), this, false);	
		this.addPiece(horse2);
		Elephant elephant1=new Elephant(false, blackPositions.get(11).get_red(), blackPositions.get(11).get_black(), this, false);	
		this.addPiece(elephant1);
		Elephant elephant2=new Elephant(false, blackPositions.get(12).get_red(), blackPositions.get(12).get_black(), this, false);	
		this.addPiece(elephant2);	
		
		//General
		General general2=new General(false, 4, 9, this);	
		this.addPiece(general2);	
		
		//Advisor
		Advisor advisor3=new Advisor(false, 4, 8, this);	
		this.addPiece(advisor3);
		Advisor advisor4=new Advisor(false, 5, 9, this);	
		this.addPiece(advisor4);	
	}
	
	//add the all pieces on board with random positions 
	public void constructBlindChessBoard() {	
		this.constructBlindChessBoardRed();
		this.constructBlindChessBoardBlack();
	}
		
	//construct 14 temporary pieces. 7 red pieces and 7 black pieces
	//in the order of soldier,horse,cannon,chariot,elephant,advisor,general.
	public ArrayList<Piece> constructTempPieces() {	
		ArrayList<Piece> temp_pieces=new ArrayList<Piece>();
		Board temp_board=this;
		temp_pieces.add(new General(true, 0, 3, temp_board));
		temp_pieces.add(new General(false, 0, 3, temp_board));
		temp_pieces.add(new Advisor(true, 0, 3, temp_board));
		temp_pieces.add(new Advisor(false, 0, 3, temp_board));
		temp_pieces.add(new Elephant(true, 0, 3, temp_board));
		temp_pieces.add(new Elephant(false, 0, 3, temp_board));
		temp_pieces.add(new Chariot(true, 0, 3, temp_board));
		temp_pieces.add(new Chariot(false, 0, 3, temp_board));
		temp_pieces.add(new Cannon(true, 0, 3, temp_board));
		temp_pieces.add(new Cannon(false, 0, 3, temp_board));
		temp_pieces.add(new Horse(true, 0, 3, temp_board));
		temp_pieces.add(new Horse(false, 0, 3, temp_board));
		temp_pieces.add(new Soldier(true, 0, 3, temp_board));
		temp_pieces.add(new Soldier(false, 0, 3, temp_board));
		return temp_pieces;
	}
	
	
	/** function to get the remaining of each piece on board
	  * will be called when drawing the killed pieces in the gui class.
	  * @return myPair[] Return the remaining of each piece on board. 
	*/
	public myPair[] get_remaining_pieces(){
		myPair[] ret=new myPair[7];
		for (int i=0; i<7; i++) {
			ret[i]=new myPair(0,0);
		}	
		for (int i=0; i<9; i++) {
			for (int j=0; j<10; j++) {
				Piece p=this.board[i][j];
				if (p==null)
					continue;
				else {
					if (p.getPlayer()) {
						ret[p.getIndex()]=new myPair(ret[p.getIndex()].get_red()+1,ret[p.getIndex()].get_black());
					}
					else {
						ret[p.getIndex()]=new myPair(ret[p.getIndex()].get_red(),ret[p.getIndex()].get_black()+1);
					}
				}
			}
		}
		return ret;
	}
		
	// get the deep copy of a board
	public Board CopyChessBoard() {
		Board newBoard=new Board();
		for (int i=0; i<9; i++) {
			for (int j=0; j<10; j++) {
				if (this.board[i][j]!=null) {
					newBoard.board[i][j]=this.board[i][j].copyPiece();
					newBoard.board[i][j].setBoard(newBoard);
				}
				else	{
					newBoard.board[i][j]=null;
				}
			}
		}
		return newBoard;		
	}

	//getter for the field board
	public Piece[][] getBoard() {
		return this.board;
	}
	
	//add piece on the board
	public void addPiece(Piece p) {
		this.board[p.getX()][p.getY()]=p;		
	}
	
}
