package board;
import java.util.ArrayList;
import java.util.Random;
import board.*;
import pieces.*;

public class RandomPositions {
	/**
	 * function to get the random positions for red pieces 
	 * @return ArrayList<myPair> an array of random positions. (stored in pair)
	 */
    public static ArrayList<myPair> getRandomPositionsRed() {
    		ArrayList<myPair> ret=new ArrayList<myPair>();
    		int size = 45;
    		ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size; i++) {
        		list.add(i);
        }
        Random rand = new Random();
        while(ret.size()<13) {
            int index = rand.nextInt(list.size());
            int temp=list.get(index);
            while (temp==3 || temp==4 || temp==5 || temp==12 || temp==13 || temp==14 || temp==21 || temp==22 || temp==23) {
            		index = rand.nextInt(list.size());
            		temp=list.get(index);
            }
            ret.add(new myPair(temp%9,temp/9));
            list.remove(index);
        }
    		return ret;		
    }
    
    /**
	 * function to get the random positions for black pieces 
	 * @return ArrayList<myPair> an array of random positions. (stored in pair)
	 */
    public static ArrayList<myPair> getRandomPositionsBlack() {
		ArrayList<myPair> ret=new ArrayList<myPair>();
		int size = 45;
		ArrayList<Integer> list = new ArrayList<Integer>();
    for(int i = 45; i < size+45; i++) {
    		list.add(i);
    }
    Random rand = new Random();
    while(ret.size()<13) {
        int index = rand.nextInt(list.size());
        int temp=list.get(index);
        while (temp==66 || temp==67 || temp==68 || temp==75 || temp==76 || temp==77 || temp==84 || temp==85 || temp==86) {
        		index = rand.nextInt(list.size());
        		temp=list.get(index);
        }
        ret.add(new myPair(temp%9,temp/9));
        list.remove(index);
    }
	return ret;		
}
}