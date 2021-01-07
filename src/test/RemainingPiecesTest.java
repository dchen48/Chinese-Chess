package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.*;
import pieces.*;

import org.junit.jupiter.api.Test;

class RemainingPiecesTest {

	@Test
	void test() {
		Board board=new Board();
		board.constructDefaultBoard();
		myPair[] remainingPieces=board.get_remaining_pieces();
		//tests for the intial board.
		assertTrue(remainingPieces.length==7, "the length of the array returned is correct");
		//tests for number of each kinds of piece 
		assertTrue(remainingPieces[0].get_red()==1, "correct number of red general");
		assertTrue(remainingPieces[0].get_black()==1, "correct number of black general");
		for (int i=1; i<6; i++) {
			assertTrue(remainingPieces[i].get_red()==2);
			assertTrue(remainingPieces[i].get_black()==2);
		}
		assertTrue(remainingPieces[6].get_red()==5, "correct number of red soldier");
		assertTrue(remainingPieces[6].get_black()==5, "correct number of black soldier");
		//tests for the scenario when some pieces are killed.
		board.getBoard()[0][0]=null;
		board.getBoard()[0][3]=null;
		remainingPieces=board.get_remaining_pieces();
		assertTrue(remainingPieces[3].get_red()==1, "correct number of red chariot");
		assertTrue(remainingPieces[6].get_red()==4, "correct number of red soldier");
	}

}
