import java.util.*;
import java.io.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.plaf.metal.*;

public class main {

	final static int reactionTime = 0;
	final static int farmingLane = 1;
	final static int farmingPattern = 2;
	final static int fightingPriority = 3;
	final static int calmness = 4;
	final static int communication = 5;
	final static int consistency = 6;
	final static int attention = 7;
	final static int patchUnderstanding = 8;
	final static int cheeseAbility = 9;
	final static int wardPlacement = 10;
	final static int dewarding = 11;
	final static int heroPool = 12;
	final static int gankAwareness = 13;
	final static int gankAbility = 14;
	final static int statGrowth = 15;
	final static int lanePressure = 16;
	final static int starFactor = 17;

	// Remember you have to pass the stats you are comparing
	public static double determine(int x, int y) {

		double x3 = 50 + ((x - y) * 0.03);

		return x3;
	}

	public static void main(String[] args) {

		gui goi = new gui();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		goi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		goi.setTitle("ESports Sim");
		goi.pack();
		goi.setVisible(true);

		/*
		 * ArrayList<player> database = new ArrayList<player>();
		 * 
		 * for (int i = 0; i < 10; i++) { player abc = new player(i); database.add(abc);
		 * }
		 * 
		 * Collections.sort(database); ;
		 * 
		 * for (int i = 0; i < 10; i++) System.out.println(database.get(i).name + "\t" +
		 * database.get(i).statsTotal);
		 * 
		 * System.out.
		 * println("Player 1's chances of beating Player 2 by Star Factor are: " +
		 * determine(database.get(0).stats[starFactor],
		 * database.get(1).stats[starFactor]));
		 * 
		 * System.out.println(database.get(0).stats[starFactor]);
		 * System.out.println(database.get(1).stats[starFactor]);
		 */

	}
}
