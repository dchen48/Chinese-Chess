package test;
import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class CannonTest {

	@Test
	void test() {
				Board board=new Board();
				Piece b1 = new Cannon(true,1,2,board);
				board.addPiece(b1);
				assertFalse(b1.canMove(3, 3),"Move fail");
				assertFalse(b1.canMove(1, 2),"Move fail");
				assertFalse(b1.canMove(-1, 0),"Move fail");
				assertTrue(b1.canMove(1, 5),"Move success");
				assertTrue(b1.canMove(3, 2),"Move success");  
				Piece b2 = new Horse(false,1,6,board);
				board.addPiece(b2);	
				assertFalse(b1.canMove(1, 6),"Move fail");
				Piece b3 = new Horse(false,1,4,board);
				board.addPiece(b3);	
				assertTrue(b1.canMove(1, 6),"Move fail");
				assertTrue(b1.getName()=="炮","correct name"); 
				Piece b4 = new Cannon(false,1,2,board);
				board.addPiece(b4);
				assertTrue(b4.getName()=="砲","correct name"); 
				
	}
}