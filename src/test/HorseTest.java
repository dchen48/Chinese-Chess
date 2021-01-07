package test;
import pieces.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import board.Board;
import pieces.Advisor;
import pieces.General;
import pieces.Piece;

class HorseTest {

	@Test
	void test() {
				Board board=new Board();
				Piece b1 = new Horse(true,1,0,board);
				board.addPiece(b1);
				assertFalse(b1.canMove(1, 0),"Move fail");
				assertFalse(b1.canMove(-1, 0),"Move fail");
				assertTrue(b1.canMove(2, 2),"Move success");
				assertTrue(b1.canMove(3, 1),"Move success");  
				Piece b2 = new General(true,3,1,board);
				board.addPiece(b2);	
				assertFalse(b1.canMove(3, 1),"Move fail");
				Piece b3 = new General(true,1,1,board);
				board.addPiece(b3);	
				assertFalse(b1.canMove(2, 2),"Move fail");
				assertTrue(b1.getName()=="傌","correct name"); 
				Piece b4 = new Horse(false,1,0,board);
				board.addPiece(b4);
				assertTrue(b4.getName()=="馬","correct name"); 
	}

}
