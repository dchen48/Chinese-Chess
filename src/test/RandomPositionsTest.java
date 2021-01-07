package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.*;
import pieces.*;

import org.junit.jupiter.api.Test;

class RandomPositionsTest {

	@Test
	void test() {
		//test for random positions of red pieces.
		ArrayList<myPair> redPositions=board.RandomPositions.getRandomPositionsRed();
		assertTrue(redPositions.size()==13, "the length of the array returned is correct");
		int redValue=-1;
		for (int i=0; i<13; i++){
			assertTrue(redPositions.get(i).get_red()*9+redPositions.get(i).get_black()!=redValue, "correct return value for the "+Integer.toString(i)+"th value");
			redValue=redPositions.get(i).get_red()*9+redPositions.get(i).get_black();
		}
			
		//test for random positions of black pieces.
		ArrayList<myPair> blackPositions=board.RandomPositions.getRandomPositionsBlack();
		assertTrue(blackPositions.size()==13, "the length of the array returned is correct");
		int blackValue=-1;
		for (int i=0; i<13; i++){
			assertTrue(blackPositions.get(i).get_red()*9+blackPositions.get(i).get_black()!=blackValue, "correct return value for the "+Integer.toString(i)+"th value");
			blackValue=blackPositions.get(i).get_red()*9+blackPositions.get(i).get_black();
		}
		
	} 

}
