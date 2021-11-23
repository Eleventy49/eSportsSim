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

	public static double determine(team x, team y) {
		double teamFactor = 0;
		double CarryFactor = 0;
		double MidFactor = 0;
		double OfflaneFactor = 0;
		double Support4Factor = 0;
		double Support5Factor = 0;
		double Average = 0;

		player carry1 = null;
		player carry2 = null;
		player mid1 = null;
		player mid2 = null;
		player offlane1 = null;
		player offlane2 = null;
		player support41 = null;
		player support42 = null;
		player support51 = null;
		player support52 = null;

		for (player a : x.roster) {
			if (a.role == 1)
				carry1 = a;
			if (a.role == 1)
				mid1 = a;
			if (a.role == 1)
				offlane1 = a;
			if (a.role == 1)
				support41 = a;
			if (a.role == 1)
				support51 = a;
		}

		for (player a : x.roster) {
			if (a.role == 1)
				carry2 = a;
			if (a.role == 1)
				mid2 = a;
			if (a.role == 1)
				offlane2 = a;
			if (a.role == 1)
				support42 = a;
			if (a.role == 1)
				support52 = a;
		}

		teamFactor = 50 + ((x.getTeamStrength() - y.getTeamStrength()) * 0.03);

		if (x.dynasty > y.dynasty) {
			teamFactor = teamFactor * (1 + Math.sqrt((1.0 / 8 * x.dynasty)));
		} else {
			teamFactor = teamFactor * (1 + Math.sqrt((1.0 / 8 * y.dynasty)));
		}

		CarryFactor = 50 + ((carry1.getStatsTotal() - carry2.getStatsTotal()) * 0.5);

		if (Math.abs(CarryFactor - 50.0) < 0.1)
			CarryFactor--;

		MidFactor = 50 + ((mid1.getStatsTotal() - mid2.getStatsTotal()) * 0.5);

		if (Math.abs(MidFactor - 50.0) < 0.1)
			MidFactor--;

		OfflaneFactor = 50 + ((offlane1.getStatsTotal() - offlane2.getStatsTotal()) * 0.5);

		if (Math.abs(OfflaneFactor - 50.0) < 0.1)
			OfflaneFactor--;

		Support4Factor = 50 + ((support41.getStatsTotal() - support42.getStatsTotal()) * 0.5);

		if (Math.abs(Support4Factor - 50.0) < 0.1)
			Support4Factor--;

		Support5Factor = 50 + ((support51.getStatsTotal() - support52.getStatsTotal()) * 0.5);

		if (Math.abs(Support5Factor - 50.0) < 0.1)
			Support5Factor--;

		System.out.print(x.name + " VS. " + y.name + " :: ");
		System.out
				.println((teamFactor + CarryFactor + OfflaneFactor + MidFactor + Support4Factor + Support5Factor) / 6);

		return Math.abs((teamFactor + CarryFactor + OfflaneFactor + MidFactor + Support4Factor + Support5Factor) / 6);
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

		goi.displayload();

	}
}
