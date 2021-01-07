package test;
import board.*;
import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GeneralTest {
 
	@Test
	void test() {		
		//tests for red general
		Board board=new Board();
		Piece b1 = new General(true,3,0,board);
		board.addPiece(b1);
		assertFalse(b1.canMove(2, 0),"Move fail");
		assertFalse(b1.canMove(3, 0),"Move fail");
		assertTrue(b1.canMove(4, 0),"Move success");  
		Piece b2 = new Advisor(true,4,0,board);
		board.addPiece(b2);	
		assertFalse(b1.canMove(4, 0),"Move fail");
		assertFalse(b1.canMove(4, 1),"Move fail");
		assertTrue(b1.getName()=="帥","correct name"); 
		
		//tests for black general
		Board board2=new Board();
		Piece b3 = new General(false,3,9,board2);
		board2.addPiece(b3);
		assertFalse(b3.canMove(2, 9),"Move fail");
		assertFalse(b3.canMove(3, 9),"Move fail");
		assertTrue(b3.canMove(4, 9),"Move success");  
		Piece b4 = new Advisor(false,4,9,board2);
		board2.addPiece(b4);	
		assertFalse(b3.canMove(4, 9),"Move fail");
		assertFalse(b3.canMove(4, 8),"Move fail");
		assertTrue(b3.getName()=="將","correct name"); 
		
	}
}
