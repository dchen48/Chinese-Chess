package test;

import pieces.*;
import logic.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class LogicTest {

	@Test
	void test() {
				Board board=new Board();
				Piece b1 = new Horse(true,1,0,board);
				board.addPiece(b1);
				Logic logic=new Logic(board,true);
				assertTrue(logic.getGeneral(true)==null,"genereal not found for player1");
				assertTrue(logic.getGeneral(false)==null,"genereal not found for player2");
				Piece b2 = new General(true,4,0,board);
				board.addPiece(b2);
				assertTrue(logic.getGeneral(true)!=null,"genereal found for player1");
				assertTrue(logic.gameState()==1,"player1 wins");
				Piece b3 = new General(false,4,9,board);
				board.addPiece(b3); 
				 
				assertTrue(logic.gameState()==1,"general face each other directly");
				Board board2=new Board();
				Piece b4 = new General(false,4,9,board2);
				board2.addPiece(b4);  
				Logic logic2=new Logic(board2,false);
				assertTrue(logic2.gameState()==0,"player2 wins");
				board2.getBoard()[4][0]=null;
				assertTrue(logic2.gameState()==0,"player2 wins");
				Piece b5 = new General(true,5,0,board2);
				board2.addPiece(b5); 
				Logic logic3=new Logic(board2,false);
				assertTrue(logic3.gameState()==2,"game continues");
	} 

} 
