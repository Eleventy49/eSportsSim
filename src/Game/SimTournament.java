package Game;

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.plaf.metal.*;
import java.awt.Graphics;

public class SimTournament {

	public static double determine(Team x, Team y) {
		double teamFactor = 0;
		double CarryFactor = 0;
		double MidFactor = 0;
		double OfflaneFactor = 0;
		double Support4Factor = 0;
		double Support5Factor = 0;
		double Average = 0;

		System.out.println(x);
		System.out.println(y);

		Player carry1 = null;
		Player carry2 = null;
		Player mid1 = null;
		Player mid2 = null;
		Player offlane1 = null;
		Player offlane2 = null;
		Player support41 = null;
		Player support42 = null;
		Player support51 = null;
		Player support52 = null;

		for (Player a : x.roster) {
			if (a.role == 1) {
				carry1 = a;
				System.out.println(carry1.name + "carry 1");
			} else if (a.role == 2) {
				mid1 = a;
				System.out.println(mid1.name + "mid 1");
			} else if (a.role == 3) {
				offlane1 = a;
				System.out.println(offlane1.name + " offlane 1");
			} else if (a.role == 4) {
				support41 = a;

				System.out.println(support41.name + " support 4 1");
			} else if (a.role == 5) {
				support51 = a;
				System.out.println(support51.name + " support 5 1");
			} else
				throw new NullPointerException();
		}

		for (Player a : y.roster) {
			if (a.role == 1) {
				carry2 = a;
				System.out.println(carry2.name + "carry 2");
			} else if (a.role == 2) {
				mid2 = a;
				System.out.println(mid2.name + " mid 2");

			} else if (a.role == 3) {
				offlane2 = a;
				System.out.println(offlane2.name + " offlane 2" );
			} else if (a.role == 4) {
				support42 = a;
				System.out.println(support42.name + " support 42");
			}

			else if (a.role == 5) {
				support52 = a;
				System.out.println(support52.name + "support 52");
			} else
				throw new NullPointerException();
		}

		System.out.println(x.name + " " + y.name + "ARE THE BASTARDS");

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

		if (Application.ConsoleOutput)
			System.out.print(x.name + " VS. " + y.name + " :: ");
		if (Application.ConsoleOutput)
			System.out.println(
					(teamFactor + CarryFactor + OfflaneFactor + MidFactor + Support4Factor + Support5Factor) / 6);

		return Math.abs((teamFactor + CarryFactor + OfflaneFactor + MidFactor + Support4Factor + Support5Factor) / 6);
	}

	public static void Major(ArrayList<Team> Teams) {
		
		if(Teams.size() != 16)
		{
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		}
		
		for(Team x: Database.teamdatabase)
		{
			x.maChampion = false;
		}
		for(Player x: Database.playerdatabase)
		{
			x.maChampion = false;
		}
		double probability;
		double reel;
		ArrayList<Team> LBR1 = new ArrayList<Team>();
		ArrayList<Team> LBR2 = new ArrayList<Team>();
		ArrayList<Team> LBR3 = new ArrayList<Team>();
		ArrayList<Team> LBR4 = new ArrayList<Team>();
		ArrayList<Team> LBR5 = new ArrayList<Team>();
		ArrayList<Team> LBR6 = new ArrayList<Team>();
		ArrayList<Team> UBR2 = new ArrayList<Team>();
		ArrayList<Team> UBR3 = new ArrayList<Team>();
		ArrayList<Team> SEMI = new ArrayList<Team>();
		ArrayList<Team> FINAL = new ArrayList<Team>();
		Team T1 = null;
		Team T2;

		BracketHandler BH;

		for (int i = 8; i > 0; i--) { // first round
			T1 = Teams.get((i * 2) - 1);
			T2 = Teams.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(T1); // first Team is winner
				System.out.println(T1.name + " beat " + T2.name + " in Round 1");
				BH = new BracketHandler(T1, T2, "Round1", i, 1);
				LBR1.add(T2); // second Team is loser
			} else {
				UBR2.add(T2); // first Team is winner
				System.out.println(T2.name + " beat " + T1.name + " in Round 1");
				BH = new BracketHandler(T2, T1, "Round1", i, 1);
				LBR1.add(T1); // second Team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(T1); // first Team is winner
				System.out.println(T1.name + " beat " + T2.name + " in LBR1");
				T2.positionInTournament = 13;
				BH = new BracketHandler(T1, T2, "LBR1", i, 1);
				LBR1.remove(T2); // second Team is loser
			} else {
				LBR2.add(T2); // first Team is winner
				T1.positionInTournament = 13;
				System.out.println(T2.name + " beat " + T1.name + " in LBR1");
				BH = new BracketHandler(T2, T1, "LBR1", i, 1);
				LBR1.remove(T1); // second Team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // upperbracket Round 2
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR3.add(T1); // first Team is winner
				LBR2.add(T2);
				System.out.println(T1.name + " beat " + T2.name + " in UBR2");
				BH = new BracketHandler(T1, T2, "UBR2", i, 1);

			} else {
				UBR3.add(T2); // first Team is winner
				LBR2.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in UBR2");
				BH = new BracketHandler(T2, T1, "UBR2", i, 1);
			}
		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 2
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(T1); // first Team is winner
				T2.positionInTournament = 9;
				System.out.println(T1.name + " beat " + T2.name + " in LBR2");
				BH = new BracketHandler(T1, T2, "LBR2", i, 1);
				LBR2.remove(T2); // second Team is loser
			} else {
				LBR3.add(T2); // first Team is winner
				T1.positionInTournament = 9;
				System.out.println(T2.name + " beat " + T1.name + " in LBR2");
				BH = new BracketHandler(T2, T1, "LBR2", i, 1);
				LBR2.remove(T1); // second Team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 3
			T1 = UBR3.get((i * 2) - 1);
			T2 = UBR3.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(T1); // first Team is winner
				LBR4.add(T2);
				System.out.println(T1.name + " beat " + T2.name + " in UBR3");
				BH = new BracketHandler(T1, T2, "UBR3", i, 1);
				SEMI.remove(T2); // second Team is loser

			} else {
				SEMI.add(T2); // first Team is winner
				LBR4.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in UBR3");
				BH = new BracketHandler(T2, T1, "UBR3", i, 1);
				SEMI.remove(T1); // second Team is loser

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 3
			T1 = LBR3.get((i * 2) - 1);
			T2 = LBR3.get((i * 2) - 2);
			probability = determine(T1, T1);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(T1); // first Team is winner
				T2.positionInTournament = 7;
				System.out.println(T1.name + " beat " + T2.name + " in LBR3");
				BH = new BracketHandler(T1, T2, "LBR3", i, 1);
				LBR3.remove(T2); // second Team is loser
			} else {
				LBR4.add(T2); // first Team is winner
				T1.positionInTournament = 7;
				System.out.println(T2.name + " beat " + T1.name + " in LBR3");
				BH = new BracketHandler(T2, T1, "LBR3", i, 1);
				LBR3.remove(T1); // second Team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 4
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			probability = determine(T1, T1);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR5.add(T1); // first Team is winner
				T2.positionInTournament = 5;
				System.out.println(T1.name + " beat " + T2.name + " in LBR4");
				BH = new BracketHandler(T1, T2, "LBR4", i, 1);
				LBR4.remove(T2); // second Team is loser
			} else {
				LBR5.add(T2); // first Team is winner
				T1.positionInTournament = 5;
				System.out.println(T2.name + " beat " + T1.name + " in LBR4");
				BH = new BracketHandler(T2, T1, "LBR4", i, 1);
				LBR4.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				System.out.println(T1.name + " beat " + T2.name + " in SEMI");
				BH = new BracketHandler(T1, T2, "SEMI", i, 1);
				LBR6.add(T2);

			} else {
				FINAL.add(T2); // first Team is winner
				LBR6.add(T1);
				System.out.println(T2.name + " beat " + T1.name + " in SEMI");
				BH = new BracketHandler(T2, T1, "SEMI", i, 1);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 5
			T1 = LBR5.get((i * 2) - 1);
			T2 = LBR5.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR6.add(T1); // first Team is winner
				T2.positionInTournament = 4;
				System.out.println(T1.name + " beat " + T2.name + " in LBR5");
				BH = new BracketHandler(T1, T2, "LBR6", i, 1);
				LBR5.remove(T2); // second Team is loser
			} else {
				LBR6.add(T2); // first Team is winner
				T1.positionInTournament = 4;
				System.out.println(T2.name + " beat " + T1.name + " in LBR5");
				BH = new BracketHandler(T2, T1, "LBR6", i, 1);
				LBR5.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 6
			T1 = LBR6.get((i * 2) - 1);
			T2 = LBR6.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				T2.positionInTournament = 3;
				System.out.println(T1.name + " beat " + T2.name + " in LBR6");
				BH = new BracketHandler(T1, T2, "LBR6", i, 1);
				LBR6.remove(T2); // second Team is loser
			} else {
				FINAL.add(T2); // first Team is winner
				T1.positionInTournament = 3;
				System.out.println(T2.name + " beat " + T1.name + " in LBR6");
				BH = new BracketHandler(T2, T1, "LBR6", i, 1);
				LBR6.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // final
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				T1.positionInTournament = 1;
				T1.MajorTitlesWon++;
				for(Player x: T1.roster)
					x.maChampion = true;
				T1.maChampion = true;
				T2.positionInTournament = 2;
				System.out.println(T1.name + " beat " + T2.name + " in FINAL");
				BH = new BracketHandler(T1, T2, "FINAL", i, 1);
				for(Player x: T1.roster)
				{
					x.MajorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T1.name, Color.gray);
				temp.addPart(" has won the Major Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();

			} else {
				T1.positionInTournament = 2;
				System.out.println(T2.name + " beat " + T1.name + " in FINAL");
				BH = new BracketHandler(T2, T1, "FINAL", i, 1);
				T2.positionInTournament = 1;
				T2.MajorTitlesWon++;
				for(Player x: T2.roster)
					x.maChampion = true;
				T2.maChampion = true;
				for(Player x: T2.roster)
				{
					x.MajorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T2.name, Color.gray);
				temp.addPart(" has won the Major Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();
			}

		}
		
		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();
		LBR5.clear();
		LBR6.clear();
		giveEarnings(1);
		UBR2.clear();
		UBR3.clear();

		SEMI.clear();
		FINAL.clear();
	//	TournamentScheduler.collection.remove(0);
	}

	public static void World(ArrayList<Team> Teams) {
		if(Teams.size() != 32)
		{
			Teams = Database.getStrongBois(32);
		}
		double probability;
		double reel;
		ArrayList<Team> LBR1 = new ArrayList<Team>();
		ArrayList<Team> LBR2 = new ArrayList<Team>();
		ArrayList<Team> LBR3 = new ArrayList<Team>();
		ArrayList<Team> LBR4 = new ArrayList<Team>();
		ArrayList<Team> LBR5 = new ArrayList<Team>();
		ArrayList<Team> LBR6 = new ArrayList<Team>();
		ArrayList<Team> LBR7 = new ArrayList<Team>();
		ArrayList<Team> LBR8 = new ArrayList<Team>();
		ArrayList<Team> UBR2 = new ArrayList<Team>();
		ArrayList<Team> UBR3 = new ArrayList<Team>();
		ArrayList<Team> UBR4 = new ArrayList<Team>();
		ArrayList<Team> SEMI = new ArrayList<Team>();
		ArrayList<Team> FINAL = new ArrayList<Team>();
		Team T1 = null;
		Team T2;
		Team temp1;
		Team temp2;
		int zebra = 0;
		boolean ITellYouToKeepGoing = true;

		BracketHandler BH;

		for(Team x: Database.teamdatabase)
		{
			x.wChampion = false;
		}
		for(Player x: Database.playerdatabase)
		{
			x.wChampion = false;
		}
		
		while (ITellYouToKeepGoing) {
			for (int i = 0; i < 31; i++) {
				if (Teams.get(i).getTeamStrength() < Teams.get(i + 1).getTeamStrength()) {
					zebra++;

					temp1 = Teams.get(i);
					temp2 = Teams.get(i + 1);
					Teams.remove(temp1);
					Teams.remove(temp2);
					Teams.add(i, temp2);
					Teams.add(i + 1, temp1);

				}
			}
			if (zebra == 0) {
				ITellYouToKeepGoing = false;
			}
			zebra = 0;
		} // Now the teams are sorted by skill
		int a;
		ArrayList<Team> temporary = new ArrayList<Team>();
		for (int i = 0; i < 16; i++) {
			temp1 = Teams.get(i);
			temp2 = Teams.get(-i + 31);

			temporary.add(temp1);
			temporary.add(temp2);

		} // Now the teams are seeded

		if (Teams.get(31) != null) {
			for (int i = 16; i > 0; i--) { // first round
				T1 = Teams.get((i * 2) - 1);
				T2 = Teams.get((i * 2) - 2);
				// System.out.println(i);

				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "Round1", i, 0);
					UBR2.add(0, T1); // first Team is winner
					LBR1.add(0, T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "Round1", i, 0);
					UBR2.add(0, T1); // first Team is winner
					LBR1.add(0, T2); // second Team is loser

				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 1
				T1 = LBR1.get((i * 2) - 1);
				T2 = LBR1.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR1", i, 0);
					LBR2.add(0, T1); // first Team is winner
					T2.positionInTournament = 25;
					LBR1.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR1", i, 0);
					LBR2.add(0, T2); // first Team is winner

					T1.positionInTournament = 25;
					LBR1.remove(T1); // second Team is loser

				}

			}
			for (int i = 8; i > 0; i--) { // upperbracket Round 2
				T1 = UBR2.get((i * 2) - 1);
				T2 = UBR2.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "UBR2", i, 0);

					UBR3.add(0, T1); // first Team is winner
					LBR2.add(0, T2);
					UBR2.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "UBR2", i, 0);
					UBR3.add(0, T2); // first Team is winner
					LBR2.add(0, T1);
					UBR2.remove(T1); // second Team is loser

				}

			}
			for (int i = 8; i > 0; i--) { // lowerbracket Round 2
				T1 = LBR2.get((i * 2) - 1);
				T2 = LBR2.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR2", i, 0);
					LBR3.add(0, T1); // first Team is winner
					T2.positionInTournament = 17;
					LBR2.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR2", i, 0);
					LBR3.add(0, T2); // first Team is winner
					T1.positionInTournament = 17;
					LBR2.remove(T1); // second Team is loser

				}

			}
			for (int i = 4; i > 0; i--) { // upperbracket Round 3
				T1 = UBR3.get((i * 2) - 1);
				T2 = UBR3.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "UBR3", i, 0);
					UBR4.add(0, T1); // first Team is winner
					LBR4.add(0, T2);
					UBR4.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "UBR3", i, 0);
					UBR4.add(0, T2); // first Team is winner
					LBR4.add(0, T1);
					UBR4.remove(T1); // second Team is loser

				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 3
				T1 = LBR3.get((i * 2) - 1);
				T2 = LBR3.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR3", i, 0);
					LBR4.add(0, T1); // first Team is winner
					T2.positionInTournament = 13;
					LBR3.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR3", i, 0);
					LBR4.add(0, T2); // first Team is winner
					T1.positionInTournament = 13;
					LBR3.remove(T1); // second Team is loser

				}

			}
			for (int i = 4; i > 0; i--) { // lowerbracket Round 4
				T1 = LBR4.get((i * 2) - 1);
				T2 = LBR4.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR4", i, 0);
					LBR5.add(0, T1); // first Team is winner
					T2.positionInTournament = 9;
					LBR4.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR4", i, 0);
					LBR5.add(0, T2); // first Team is winner
					T1.positionInTournament = 9;
					LBR4.remove(T1); // second Team is loser

				}

			}
			for (int i = 2; i > 0; i--) { // upperbracket Round 4
				T1 = UBR4.get((i * 2) - 1);
				T2 = UBR4.get((i * 2) - 2);
				probability = determine(T1, T2);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "UBR4", i, 0);
					SEMI.add(0, T1); // first Team is winner
					LBR6.add(T2);

				} else {
					BH = new BracketHandler(T2, T1, "UBR4", i, 0);
					SEMI.add(0, T2); // first Team is winner
					LBR6.add(T1);

				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 5
				T1 = LBR5.get((i * 2) - 1);
				T2 = LBR5.get((i * 2) - 2);
				probability = determine(T1, T2);
				// System.out.println("[LBR5] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR5", i, 0);
					LBR6.add(0, T1); // first Team is winner
					T2.positionInTournament = 7;
					// System.out.println("[LBR5] " + T2.name + " has placed 7-8.");
					LBR5.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR5", i, 0);
					LBR6.add(0, T2); // first Team is winner
					T1.positionInTournament = 7;
					// System.out.println("[LBR5] " + T1.name + " has placed 7-8.");
					LBR5.remove(T1); // second Team is loser

				}

			}
			for (int i = 2; i > 0; i--) { // lowerbracket Round 6
				T1 = LBR6.get((i * 2) - 1);
				T2 = LBR6.get((i * 2) - 2);
				probability = determine(T1, T2);
				// System.out.println("[LBR6] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR6", i, 0);
					LBR7.add(0, T1); // first Team is winner
					T2.positionInTournament = 5;
					// System.out.println("[LBR6] " + T2.name + " has placed 5-6.");
					LBR6.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR6", i, 0);
					LBR7.add(0, T2); // first Team is winner
					T1.positionInTournament = 5;
					// System.out.println("[LBR6] " + T1.name + " has placed 5-6.");
					LBR6.remove(T1); // second Team is loser

				}

			}
			for (int i = 1; i > 0; i--) { // Semi
				T1 = SEMI.get((i * 2) - 1);
				T2 = SEMI.get((i * 2) - 2);
				probability = determine(T1, T2);
				// System.out.println("[SEMI] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "SEMI", i, 0);
					FINAL.add(T1); // first Team is winner
					LBR8.add(0, T2);

				} else {
					BH = new BracketHandler(T2, T1, "SEMI", i, 0);
					FINAL.add(T2); // first Team is winner
					LBR8.add(0, T1);

				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 7
				T1 = LBR7.get(0);
				T2 = LBR7.get(1);
				probability = determine(T1, T2);
				// System.out.println("[LBR7] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR7", i, 0);
					LBR8.add(0, T1); // first Team is winner
					T2.positionInTournament = 4;
					// System.out.println("[LBR7] " + T2.name + " has placed 4th.");
					LBR7.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR7", i, 0);
					LBR8.add(0, T2); // first Team is winner
					T1.positionInTournament = 4;
					// System.out.println("[LBR7] " + T1.name + " has placed 4th.");
					LBR7.remove(T1); // second Team is loser

				}

			}
			for (int i = 1; i > 0; i--) { // lowerbracket Round 8
				T1 = LBR8.get((i * 2) - 1);
				T2 = LBR8.get((i * 2) - 2);
				probability = determine(T1, T2);
				// System.out.println("[LBR8] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "LBR8", i, 0);
					FINAL.add(0, T1); // first Team is winner
					T2.positionInTournament = 3;
					// System.out.println("[LBR8] " + T2.name + " has placed 3rd.");
					LBR8.remove(T2); // second Team is loser

				} else {
					BH = new BracketHandler(T2, T1, "LBR8", i, 0);
					FINAL.add(0, T2); // first Team is winner
					T1.positionInTournament = 3;
					// System.out.println("[LBR8] " + T1.name + " has placed 3rd.");
					LBR8.remove(T1); // second Team is loser

				}

			}
			for (int i = 1; i > 0; i--) { // FINAL
				T1 = FINAL.get((i * 2) - 1);
				T2 = FINAL.get((i * 2) - 2);
				probability = determine(T1, T2);
				// System.out.println("[FINAL] The chances of " + T1.name + " beating " +
				// T2.name + " are " + probability);
				reel = Math.random() * 100;
				if (reel < probability) {
					BH = new BracketHandler(T1, T2, "FINAL", i, 0);
					// FINAL.add(LBR1.get((i * 2)+1)); //first Team is winner
					T2.positionInTournament = 2;
					T2.dynasty += 0.5;
					// System.out.println("[FINAL] " + T2.name + " has placed 2nd.");
					T1.positionInTournament = 1;
					T1.dynasty += 1;
					T1.WorldTitlesWon++;
					for(Player x: T1.roster)
					{
						x.WorldTitles++;
					}
					for(Player x: T1.roster)
						x.wChampion = true;
					T1.wChampion = true;
				
				
					MultiColorNotification temp = new MultiColorNotification(T1.name, Color.yellow);
					temp.addPart(" has won the World Tournament!", Color.white);
					NotificationHandler.add(temp);
					System.out.println(temp);
					MultiColorNotification.resetAll();

					// System.out.println("[FINAL] " + T1.name + " has placed 1st.");
				} else {
					BH = new BracketHandler(T2, T1, "FINAL", i, 0);
					// FINAL.add(LBR1.get((i * 2)+1)); //first Team is winner
					T1.positionInTournament = 2;
					T1.dynasty += 0.5;
					// System.out.println("[FINAL] " + T1.name + " has placed 2nd.");
					T2.positionInTournament = 1;
					T2.dynasty += 1;
					T2.WorldTitlesWon++;
					for(Player x: T2.roster)
					{
						x.WorldTitles++;
					}
					// System.out.println("[FINAL] " + T2.name + " has placed 1st.");
					
					MultiColorNotification temp = new MultiColorNotification(T2.name, Color.yellow);
					temp.addPart(" has won the World Tournament!", Color.white);
					NotificationHandler.add(temp);
					System.out.println(temp);
					
					for(Player x: T2.roster)
						x.wChampion = true;
					T2.wChampion = true;
					MultiColorNotification.resetAll();
				}

			}
			
			LBR1.clear();
			LBR2.clear();
			LBR3.clear();
			LBR4.clear();
			LBR5.clear();
			LBR6.clear();
			LBR7.clear();
			LBR8.clear();
			UBR2.clear();
			UBR3.clear();
			UBR4.clear();
			SEMI.clear();
			giveEarnings(0);
			FINAL.clear();
		//	TournamentScheduler.collection.remove(0);
		}

	}

	private static void giveEarnings(int i) {
		switch(i) {
		case 0: //World
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 1) {
					x.tournamentEarnings += 1666666;		
					for (Player y : x.roster) {		
						y.earnings += 1666666 / 5;
					}
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 2) {
					
					x.tournamentEarnings += 1111111;
					for (Player y : x.roster)
						y.earnings += 1111111 / 5;

				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 3) {
				
					x.tournamentEarnings += 370000;
					for (Player y : x.roster)
						y.earnings += 370000 / 5;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 4) {
					
					x.tournamentEarnings += 123450;
					for (Player y : x.roster)
						y.earnings += 123450 / 5;
				}
			}
			
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 5) {
					
					x.tournamentEarnings += 22000;
					for (Player y : x.roster)
						y.earnings += 22000 / 5;
				}
			}
		
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 7) {
					
					x.tournamentEarnings += 3425;
					for (Player y : x.roster)
						y.earnings += 3425 / 5;
				}
			}
	break;
		case 1: //Major
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 1) {
					
					x.tournamentEarnings += 1666666 / 5;
				
					for (Player y : x.roster) {
						y.MajorTitles += 1;
						y.earnings += (1666666 / 5) / 5;

					}
					x.positionInTournament = 0;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 2) {
					
					x.tournamentEarnings += 1111111 / 5;
					for (Player y : x.roster)
						y.earnings += (1111111 / 5) / 5;
					x.positionInTournament = 0;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 3) {
					
					x.tournamentEarnings += 370000 / 5;
					for (Player y : x.roster)
						y.earnings += (370000 / 5) / 5;
					x.positionInTournament = 0;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 4) {
					
					x.tournamentEarnings += 123450 / 5;
					for (Player y : x.roster)
						y.earnings += (123450 / 5) / 5;
					x.positionInTournament = 0;
				}
			}
			
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 5) {
					
					x.tournamentEarnings += 22000 / 5;
					for (Player y : x.roster)
						y.earnings += (22000 / 5) / 5;
					x.positionInTournament = 0;
				}
			}
			break;
			
		case 2: //Minor
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 1) {
				
					x.tournamentEarnings += 1666666 / 10;
					for (Player y : x.roster) {
						y.MinorTitles += 1;
						y.earnings += (1666666 / 10) / 5;
					}
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 2) {
				
					x.tournamentEarnings += 1111111 / 10;
					for (Player y : x.roster)
						y.earnings += (1111111 / 10) / 5;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 3) {
				
					x.tournamentEarnings += 370000 / 10;
					for (Player y : x.roster)
						y.earnings += (370000 / 10) / 5;
				}
			}
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 4) {
				
					x.tournamentEarnings += 123450 / 10;
					for (Player y : x.roster)
						y.earnings += (123450 / 10) / 5;
				}
			}
		
			for (Team x : Database.teamdatabase) {
				if (x.positionInTournament == 5) {
					
					x.tournamentEarnings += 22000 / 10;
					for (Player y : x.roster)
						y.earnings += (22000 / 10) / 5;
				}
			}
		break;	
		}
		
		
		for(Team x : Database.teamdatabase)
			x.positionInTournament = 0;
		System.out.println("WE have people money. They should enjoy that.");
		
		
	}

	public static void Minor8(ArrayList<Team> Teams) {
		if(Teams.size() != 8)
		{
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		}
		if(Teams.size() != 8)
		{
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		}
		
		System.out.println(Teams.size());
		
		for(Team x: Database.teamdatabase)
		{
			x.miChampion = false;
		}
		for(Player x: Database.playerdatabase)
		{
			x.miChampion = false;
		}
		double probability;
		double reel;
		ArrayList<Team> LBR1 = new ArrayList<Team>();
		ArrayList<Team> LBR2 = new ArrayList<Team>();
		ArrayList<Team> LBR3 = new ArrayList<Team>();
		ArrayList<Team> LBR4 = new ArrayList<Team>();
		ArrayList<Team> UBR2 = new ArrayList<Team>();
		ArrayList<Team> SEMI = new ArrayList<Team>();
		ArrayList<Team> FINAL = new ArrayList<Team>();
		Team T1 = null;
		Team T2;
		System.out.println(Teams.size());
		BracketHandler BH;
		System.out.println("There are " + Teams.size() + " Teams in ROUND 1");
		for (Team x : Teams)
			System.out.println("[ROUND 1] " + x.name);

		for (int i = 4; i > 0; i--) { // first round
			T1 = Teams.get((i * 2) - 1);
			T2 = Teams.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				UBR2.add(T1); // first Team is winner
				System.out.println(T1.name + " won first round against " + T2.name);
				LBR1.add(T2); // second Team is loser
				System.out.println(T2.name + " lost first round against " + T1.name);
				BH = new BracketHandler(T1, T2, "Round1", i, 2);
			} else {
				UBR2.add(T2); // first Team is winner
				System.out.println(T2.name + " won first round against " + T1.name);
				LBR1.add(T1); // second Team is loser
				System.out.println(T1.name + " lost first round against " + T2.name);
				BH = new BracketHandler(T2, T1, "Round1", i, 2);
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 1
			System.out.println("There are " + LBR1.size() + " Teams in LBR1");
			for (Team x : LBR1)
				System.out.println("[LBR1] " + x.name);
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR2.add(T1); // first Team is winner
				T2.positionInTournament = 7;
				System.out.println(T2.name + " placed 7th");
				LBR1.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR1", i, 2);
			} else {
				LBR2.add(T2); // first Team is winner
				T1.positionInTournament = 7;
				System.out.println(T1.name + " placed 7th");
				LBR1.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR1", i, 2);
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 2
			System.out.println("There are " + UBR2.size() + " Teams in UBR2");
			for (Team x : UBR2)
				System.out.println("[UBR2] " + x.name);
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				SEMI.add(T1); // first Team is winner
				LBR2.add(T2);
				SEMI.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "UBR2", i, 2);

			} else {
				SEMI.add(T2); // first Team is winner
				LBR2.add(T1);
				SEMI.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "UBR2", i, 2);
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 2
			System.out.println("There are " + LBR2.size() + " Teams in LBR2");
			for (Team x : LBR2)
				System.out.println("[LBR2] " + x.name);
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR3.add(T1); // first Team is winner
				T2.positionInTournament = 5;
				System.out.println(T2.name + " placed 5th");
				LBR2.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR2", i, 2);
			} else {
				LBR3.add(T2); // first Team is winner
				T1.positionInTournament = 5;
				System.out.println(T1.name + " placed 5th");
				LBR2.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR2", i, 2);
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			System.out.println("There are " + SEMI.size() + " Teams in SEMI");
			for (Team x : SEMI)
				System.out.println("[SEMI] " + x.name);
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				LBR4.add(T2);
				System.out.println(T2.name + " has been sent to the lower bracket");
				SEMI.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "SEMI", i, 2);

			} else {
				FINAL.add(T2); // first Team is winner
				LBR4.add(T1);
				System.out.println(T1.name + " has been sent to the lower bracket");
				SEMI.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "SEMI", i, 2);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 3
			System.out.println("There are " + LBR3.size() + " Teams in LBR3");
			for (Team x : LBR3)
				System.out.println("[LBR3] " + x.name);
			T1 = LBR3.get((i * 2) - 1);
			System.out.println(T1.name);
			T2 = LBR3.get((i * 2) - 2);
			System.out.println(T2.name);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				LBR4.add(T1); // first Team is winner
				System.out.println(T1.name + " has moved foward to LBR4");
				T2.positionInTournament = 4;
				System.out.println(T2.name + " placed 4th");
				LBR3.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR3", i, 2);
			} else {
				LBR4.add(T2); // first Team is winner
				System.out.println(T2.name + " has moved foward to LBR4");
				T1.positionInTournament = 4;
				BH = new BracketHandler(T2, T1, "LBR3", i, 2);
				System.out.println(T1.name + " placed 4th");
				LBR3.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 4
			System.out.println("There are " + LBR4.size() + " Teams in LBR4");
			for (Team x : LBR4)
				System.out.println("[LBR4] " + x.name);
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				T2.positionInTournament = 3;
				System.out.println(T2.name + " placed 3rd");
				LBR4.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR4", i, 2);

			} else {
				FINAL.add(T2); // first Team is winner
				T1.positionInTournament = 3;
				System.out.println(T1.name + " placed 3rd");
				LBR4.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR4", i, 2);
			}

		}
		for (int i = 1; i > 0; i--) { // final
			System.out.println("There are " + FINAL.size() + " Teams in FINAL");
			for (Team x : FINAL)
				System.out.println("[FINAL] " + x.name);
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			if (reel < probability) {
				T1.positionInTournament = 1;
				T1.MinorTitlesWon++;
				for(Player x: T1.roster)
					x.miChampion = true;
				T1.miChampion = true;
				System.out.println(T1.name + " placed 1st");
				BH = new BracketHandler(T1, T2, "FINAL", i, 2);
				T2.positionInTournament = 2;
				System.out.println(T2.name + " placed 2nd");
				for(Player x: T1.roster)
				{
					x.MinorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T1.name, Color.pink);
				temp.addPart(" has won the Minor[8] Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();

			} else {
				T2.positionInTournament = 1;
				System.out.println(T2.name + " placed 1st");
				BH = new BracketHandler(T2, T1, "FINAL", i, 2);
				T1.positionInTournament = 2;
				System.out.println(T1.name + " placed 2nd");
				T2.MinorTitlesWon++;
				for(Player x: T2.roster)
					x.miChampion = true;
				T2.miChampion = true;
				for(Player x: T2.roster)
				{
					x.MinorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T2.name, Color.pink);
				temp.addPart(" has won the Minor[8] Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();
			}

		}
		
		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();
		UBR2.clear();
		SEMI.clear();
		FINAL.clear();
		giveEarnings(2);
	//	TournamentScheduler.collection.remove(0);
	}

	public static void Minor4(ArrayList<Team> Teams) {
		if(Teams.size() != 4)
		{
			Teams.add(Database.getRandomBoi(Teams));
			Teams.add(Database.getRandomBoi(Teams));
			Teams.add(Database.getRandomBoi(Teams));
			Teams.add(Database.getRandomBoi(Teams));
		}
		for(Team x: Database.teamdatabase)
		{
			x.miChampion = false;
		}
		for(Player x: Database.playerdatabase)
		{
			x.miChampion = false;
		}
		double probability;
		double reel;
		ArrayList<Team> LBR1 = new ArrayList<Team>();
		ArrayList<Team> LBR2 = new ArrayList<Team>();
		ArrayList<Team> LBR3 = new ArrayList<Team>();
		ArrayList<Team> LBR4 = new ArrayList<Team>();
		ArrayList<Team> UBR2 = new ArrayList<Team>();
		ArrayList<Team> SEMI = new ArrayList<Team>();
		ArrayList<Team> FINAL = new ArrayList<Team>();
		Team T1 = null;
		Team T2 = null;
		BracketHandler BH;
		for (int i = 2; i > 0; i--) { // first round
			T1 = Teams.get((i * 2) - 1);
			T2 = Teams.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[ROUND 1] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[ROUND 1] The number generated was: " + reel);
			if (reel < probability) {
				SEMI.add(T1); // first Team is winner
				LBR1.add(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "Round1", i, 3);
			} else {
				SEMI.add(T2); // first Team is winner
				LBR1.add(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "Round1", i, 3);
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[SEMI] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[SEMI] The number generated was: " + reel);
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				LBR2.add(T2);
				BH = new BracketHandler(T1, T2, "SEMI", i, 3);

			} else {
				FINAL.add(T2); // first Team is winner
				LBR2.add(T1);
				BH = new BracketHandler(T1, T2, "SEMI", i, 3);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[LBR1] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[LBR1] The number generated was: " + reel);
			if (reel < probability) {
				LBR2.add(T1); // first Team is winner
				T2.positionInTournament = 4;
				// System.out.println(T2.name + " placed 4th");
				LBR1.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR1", i, 3);
			} else {
				LBR2.add(T2); // first Team is winner
				T1.positionInTournament = 4;
				// System.out.println(T1.name + " placed 4th");
				LBR1.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR1", i, 3);
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round2
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			probability = determine(T1, T2);
			reel = Math.random() * 100;
			// System.out.println("[LBR2] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[LBR2] The number generated was: " + reel);
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				T2.positionInTournament = 3;
				// System.out.println(T2.name + " placed 3rd");
				LBR2.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR2", i, 3);
			} else {
				FINAL.add(T2); // first Team is winner
				T1.positionInTournament = 3;
				// System.out.println(T1.name + " placed 3rd");
				LBR2.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR2", i, 3);
			}

		}
		for (int i = 1; i > 0; i--) { // final
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			probability = determine(T1, T2);

			reel = Math.random() * 100;

			// System.out.println("[FINAL] Probability for " + T1.name + " to beat " +
			// T2.name + " is: " + probability);
			// System.out.println("[FINAL] The number generated was: " + reel);
			if (reel < probability) {
				T2.positionInTournament = 2;
				T1.positionInTournament = 1;
				for(Player x: T1.roster)
					x.miChampion = true;
				T1.miChampion = true;
				T1.MinorTitlesWon++;
				BH = new BracketHandler(T1, T2, "FINAL", i, 3);
				for(Player x: T1.roster)
				{
					x.MinorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T1.name, Color.pink);
				temp.addPart(" has won the Minor[4] Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();

			} else {
				T1.positionInTournament = 2;
				T2.positionInTournament = 1;
				for(Player x: T2.roster)
					x.miChampion = true;
				T2.miChampion = true;
				T2.MinorTitlesWon++;
				BH = new BracketHandler(T2, T1, "FINAL", i, 3);
				for(Player x: T2.roster)
				{
					x.MinorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T2.name, Color.pink);
				temp.addPart(" has won the Minor[4] Tournament!", Color.white);
				NotificationHandler.add(temp);
				System.out.println(temp);
				MultiColorNotification.resetAll();
			}

		}

		
		LBR1.clear();
		LBR2.clear();
		LBR3.clear();
		LBR4.clear();

		UBR2.clear();
		giveEarnings(2);
		SEMI.clear();
		FINAL.clear();
	//	TournamentScheduler.collection.remove(0);
	}
}
