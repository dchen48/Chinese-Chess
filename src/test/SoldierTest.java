package test;

import board.*;
import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SoldierTest {
 
	@Test
	void test() {		
		//tests for red soldier
		Board board=new Board();
		Piece b1 = new Soldier(true,2,3,board);
		board.addPiece(b1);
		assertFalse(b1.canMove(-1, 0),"Move fail");
		assertFalse(b1.canMove(2, 3),"Move fail");
		assertFalse(b1.canMove(2, 2),"Move fail");
		assertTrue(b1.canMove(2, 4),"Move success");  
		Piece b2 = new Horse(true,2,4,board);
		board.addPiece(b2);	
		assertFalse(b1.canMove(2, 4),"Move fail");
		Piece b3 = new Soldier(true,2,6,board);
		board.addPiece(b3);	
		assertTrue(b3.canMove(3, 6),"Move success");  
		assertTrue(b3.canMove(2, 7),"Move success"); 
		assertFalse(b3.canMove(4, 6),"Move success");  
		assertTrue(b1.getName()=="兵","correct name"); 
		//tests for black soldier 
		Board board2=new Board();
		Piece b4 = new Soldier(false,2,6,board2);
		board2.addPiece(b4);
		assertFalse(b4.canMove(-1, 0),"Move fail");
		assertFalse(b1.canMove(2, 6),"Move fail");
		assertFalse(b4.canMove(2, 7),"Move fail");
		assertTrue(b4.canMove(2, 5),"Move success");  
		Piece b5 = new Horse(false,2,5,board2);
		board2.addPiece(b5);	
		assertFalse(b4.canMove(2, 5),"Move fail");
		Piece b6 = new Soldier(false,2,3,board2);
		board2.addPiece(b6);	
		assertTrue(b6.canMove(3, 3),"Move success");
		assertTrue(b6.canMove(2, 2),"Move success"); 
		assertFalse(b6.canMove(4, 3),"Move success"); 
		assertTrue(b4.getName()=="卒","correct name"); 
	}
}
