package test;

import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class ChariotTest {

	@Test
	void test() {
				Board board=new Board();
				Piece b1 = new Chariot(true,0,0,board);
				board.addPiece(b1);
				assertFalse(b1.canMove(1, 1),"Move fail");
				assertFalse(b1.canMove(0, 0),"Move fail");
				assertFalse(b1.canMove(-1, 0),"Move fail");
				assertTrue(b1.canMove(0, 2),"Move success");
				assertTrue(b1.canMove(2, 0),"Move success");  
				Piece b2 = new General(true,0,2,board);
				board.addPiece(b2);	
				assertFalse(b1.canMove(0, 2),"Move fail");
				assertFalse(b1.canMove(0, 5),"Move fail");
				assertTrue(b1.getName()=="俥","correct name"); 
				
				Board board2=new Board();
				Piece b3 = new Chariot(true,3,3,board2);
				board2.addPiece(b3);
				assertTrue(b3.canMove(1, 3),"Move Success");
				assertTrue(b3.canMove(3, 0),"Move Success");
				Piece b4 = new Chariot(true,2,3,board);
				board2.addPiece(b4);
				Piece b5 = new Chariot(true,3,1,board);
				board2.addPiece(b5);
				assertFalse(b3.canMove(1, 3),"Move fail");
				assertFalse(b3.canMove(3, 0),"Move fail");
				Piece b6 = new Chariot(false,3,3,board2);
				board2.addPiece(b6);
				assertTrue(b6.getName()=="車","correct name"); 
				
				
	}

}
