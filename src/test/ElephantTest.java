package test;
import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class ElephantTest {

	@Test 
	void test() {
		//tests for red elephant
				Board board=new Board();
				Piece b1 = new Elephant(true,2,0,board);
				board.addPiece(b1);
				assertFalse(b1.canMove(2, 0),"Move fail");
				assertFalse(b1.canMove(-1, 0),"Move fail");
				assertTrue(b1.canMove(4, 2),"Move success");
				assertTrue(b1.canMove(0, 2),"Move success");  
				Piece b2 = new General(true,3,1,board);
				board.addPiece(b2);	
				assertFalse(b1.canMove(4, 2),"Move fail");
				Piece b5 = new Horse(true,0,2,board);
				board.addPiece(b5);
				assertFalse(b1.canMove(0, 2),"Move fail");
				assertFalse(b1.canMove(6, 3),"Move fail");
				assertTrue(b1.getName()=="相","correct name"); 
				//tests for black elephant
				Board board2=new Board();
				Piece b3 = new Elephant(false,2,9,board2);
				board2.addPiece(b3);
				assertFalse(b3.canMove(2, 9),"Move fail");
				assertFalse(b3.canMove(-1, 9),"Move fail");
				assertTrue(b3.canMove(4, 7),"Move success");
				assertTrue(b3.canMove(0, 7),"Move success");  
				Piece b4 = new General(false,3,8,board2);
				board2.addPiece(b4);	
				assertFalse(b3.canMove(4, 7),"Move fail");
				Piece b6 = new Horse(true,0,7,board);
				board2.addPiece(b6);	
				assertFalse(b2.canMove(0, 7),"Move fail");
				assertFalse(b2.canMove(6, 6),"Move fail");
				assertTrue(b3.getName()=="象","correct name"); 
	}

}
