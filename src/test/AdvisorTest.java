package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class AdvisorTest {

	@Test
	void test() {
		//tests for red advisor
		Board board=new Board();
		Piece b1 = new Advisor(true,3,0,board);
		board.addPiece(b1);
		assertFalse(b1.canMove(2, 0),"Move fail");
		assertFalse(b1.canMove(3, 0),"Move fail");
		assertTrue(b1.canMove(4, 1),"Move success");  
		Piece b2 = new General(true,4,1,board);
		board.addPiece(b2);	
		assertFalse(b1.canMove(4, 1),"Move fail");
		assertFalse(b1.canMove(3, 1),"Move fail");
		assertTrue(b1.getName()=="仕","correct name"); 
		//tests for black advisor
		Board board2=new Board();
		Piece b3 = new Advisor(false,3,9,board2);
		board2.addPiece(b3);
		assertFalse(b3.canMove(2, 9),"Move fail");
		assertFalse(b3.canMove(3, 9),"Move fail");
		assertTrue(b3.canMove(4, 8),"Move success");  
		Piece b4 = new General(false,4,8,board2);
		board2.addPiece(b4);	
		assertFalse(b3.canMove(4, 8),"Move fail");
		assertFalse(b3.canMove(3, 8),"Move fail");
		assertTrue(b3.getName()=="士","correct name"); 
	}

}
