package Game;

import java.util.*;

import java.io.*;
import javax.swing.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.plaf.metal.*;
import java.awt.Graphics;

	

public class SimTournament {
	
	final static double teamStrengthBias = 60.0; //Increasing this number decreases the probability
												  //of the stronger team winning
	final static double dynastyBias = 0.125;		  //Increasing this number increases the effect
												  //of dynasty4
	final static double upperCap = 98.0;
	final static double lowerCap = 2.0;
	public static double determineTeamFactor(int x, int y, double z, double w) {
		double temp = 50 + (((double)x - (double)y)/teamStrengthBias) - (w-z)/Math.sqrt(dynastyBias);
		if(temp > upperCap)
		{
			return upperCap;
		}
		else if(temp < lowerCap)
		{
			return lowerCap;
		}
		else return temp;
		
	}
	
	public static double determinePlayerFactor(int x, int y) {
		double temp = 50 + (((double)x - (double)y)/50)/Math.sqrt(1);
		if(temp > upperCap)
		{
			return upperCap;
		}
		else if(temp < lowerCap)
		{
			return lowerCap;
		}
		else return temp;
	}
	
	public static double determineLaneFactor(double a, double b, double c, double d, double e) {
		double astar = a;
		double bstar = b;
		double cstar = c;
		double dstar = d;
		double estar = e;
		double[] array = {astar,bstar,cstar,dstar,estar};
		for(int x = 0; x<4; x++)
		{
			for(int i=x; i<4; i++)
			if(array[i] < array[i+1])
			{
				double temp = array[i+1];
				array[i+1] = array[i];
				array[i] = temp;
			}
		}
		
		return (array[0]*2 + array[1] + array[2])/4;
	}

	public static double determine(Team x, Team y) {
		Team max;
		Team min;
		int maxi = Math.max(x.getTeamStrength(), y.getTeamStrength());
		if(x.getTeamStrength() == maxi)
		{
			max = x;
			min = y;
		}
		else
		{
			max = y;
			min = x;
		}
		
		
		double teamFactor = 0;
		double CarryFactor = 0;
		double MidFactor = 0;
		double OfflaneFactor = 0;
		double Support4Factor = 0;
		double Support5Factor = 0;
		double Average = 0;
		double TotalLaneFactor = 0;

		Player carry1 = null;
		Player carry2 = null;
		Player offlane1 = null;
		Player offlane2 = null;
		Player mid1 = null;
		Player mid2 = null;
		Player support41 = null;
		Player support42 = null;
		Player support51 = null;
		Player support52 = null;

		for (Player a : max.roster) {
			if (a.role == 1) {
				carry1 = a;
			} else if (a.role == 2) {
				mid1 = a;
			} else if (a.role == 3) {
				offlane1 = a;
			} else if (a.role == 4) {
				support41 = a;
			} else if (a.role == 5) {
				support51 = a;
			} else
				throw new NullPointerException();
		}

		for (Player a : min.roster) {
			if (a.role == 1) {
				carry2 = a;
			} else if (a.role == 2) {
				mid2 = a;
			} else if (a.role == 3) {
				offlane2 = a;
			} else if (a.role == 4) {
				support42 = a;
			}
			else if (a.role == 5) {
				support52 = a;
			} else
				throw new NullPointerException();
		}

		
		teamFactor = determineTeamFactor(max.getTeamStrength(), 
				min.getTeamStrength(), max.dynasty, min.dynasty);
		
		CarryFactor = determinePlayerFactor(carry1.getStatsTotal(), carry2.getStatsTotal());
		MidFactor = determinePlayerFactor(mid1.getStatsTotal(), mid2.getStatsTotal());
		OfflaneFactor = determinePlayerFactor(offlane1.getStatsTotal(), offlane2.getStatsTotal());
		Support4Factor = determinePlayerFactor(support41.getStatsTotal(), support42.getStatsTotal());
		Support5Factor = determinePlayerFactor(support51.getStatsTotal(), support52.getStatsTotal());
		
		TotalLaneFactor = determineLaneFactor(CarryFactor, MidFactor, OfflaneFactor, Support4Factor, Support5Factor);

		if(Application.ConsoleOutput)
			System.out.println("Team factor between " + max.name + " (" + max.getTeamStrength() + ") and " + min.name
					+ " (" + min.getTeamStrength() + ") is " + teamFactor + "\n" + CarryFactor + "\n" + MidFactor + "\n" + OfflaneFactor + "\n" + Support4Factor
					+"\n" + Support5Factor);
		Double temp = Math.abs((teamFactor + TotalLaneFactor) / 2.0);

		if(Application.ConsoleOutput)
		System.out.println("The chances including lanes is " + temp);
		if(temp >= 98.0)
			return 98.0;
		else if(temp < 2.0)
			return 2.0;
		return temp / 100;
	
	}

	public static void Major(ArrayList<Team> Teams) {
		
		if(Teams.size() != 16)
		{
			System.out.println("Your qualifier fix didn't work" + Teams.size());
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		Teams.add(Database.getRandomBoi(Teams));
		}
		System.out.println(Teams.size());
		Collections.shuffle(Teams);
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				UBR2.add(T1); // first Team is winner
				BH = new BracketHandler(T1, T2, "Round1", i, 1);
				LBR1.add(T2); // second Team is loser
			} else {
				UBR2.add(T2); // first Team is winner
				BH = new BracketHandler(T2, T1, "Round1", i, 1);
				LBR1.add(T1); // second Team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR2.add(T1); // first Team is winner
				T2.positionInTournament = 13;
				BH = new BracketHandler(T1, T2, "LBR1", i, 1);
				LBR1.remove(T2); // second Team is loser
			} else {
				LBR2.add(T2); // first Team is winner
				T1.positionInTournament = 13;
				BH = new BracketHandler(T2, T1, "LBR1", i, 1);
				LBR1.remove(T1); // second Team is loser
			}

		}
		for (int i = 4; i > 0; i--) { // upperbracket Round 2
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				UBR3.add(T1); // first Team is winner
				LBR2.add(T2);
				BH = new BracketHandler(T1, T2, "UBR2", i, 1);

			} else {
				UBR3.add(T2); // first Team is winner
				LBR2.add(T1);
				BH = new BracketHandler(T2, T1, "UBR2", i, 1);
			}
		}
		for (int i = 4; i > 0; i--) { // lowerbracket Round 2
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR3.add(T1); // first Team is winner
				T2.positionInTournament = 9;
				BH = new BracketHandler(T1, T2, "LBR2", i, 1);
				LBR2.remove(T2); // second Team is loser
			} else {
				LBR3.add(T2); // first Team is winner
				T1.positionInTournament = 9;
				BH = new BracketHandler(T2, T1, "LBR2", i, 1);
				LBR2.remove(T1); // second Team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 3
			T1 = UBR3.get((i * 2) - 1);
			T2 = UBR3.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				SEMI.add(T1); // first Team is winner
				LBR4.add(T2);
				BH = new BracketHandler(T1, T2, "UBR3", i, 1);

			} else {
				SEMI.add(T2); // first Team is winner
				LBR4.add(T1);
				BH = new BracketHandler(T2, T1, "UBR3", i, 1);		

			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 3
			T1 = LBR3.get((i * 2) - 1);
			T2 = LBR3.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T1);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR4.add(T1); // first Team is winner
				T2.positionInTournament = 7;
				BH = new BracketHandler(T1, T2, "LBR3", i, 1);
				LBR3.remove(T2); // second Team is loser
			} else {
				LBR4.add(T2); // first Team is winner
				T1.positionInTournament = 7;
				BH = new BracketHandler(T2, T1, "LBR3", i, 1);
				LBR3.remove(T1); // second Team is loser
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 4
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T1);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR5.add(T1); // first Team is winner
				T2.positionInTournament = 5;
				BH = new BracketHandler(T1, T2, "LBR4", i, 1);
				LBR4.remove(T2); // second Team is loser
			} else {
				LBR5.add(T2); // first Team is winner
				T1.positionInTournament = 5;
				BH = new BracketHandler(T2, T1, "LBR4", i, 1);
				LBR4.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				BH = new BracketHandler(T1, T2, "SEMI", i, 1);
				LBR6.add(T2);

			} else {
				FINAL.add(T2); // first Team is winner
				LBR6.add(T1);
				BH = new BracketHandler(T2, T1, "SEMI", i, 1);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 5
			T1 = LBR5.get((i * 2) - 1);
			T2 = LBR5.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR6.add(T1); // first Team is winner
				T2.positionInTournament = 4;
				BH = new BracketHandler(T1, T2, "LBR6", i, 1);
				LBR5.remove(T2); // second Team is loser
			} else {
				LBR6.add(T2); // first Team is winner
				T1.positionInTournament = 4;
				BH = new BracketHandler(T2, T1, "LBR6", i, 1);
				LBR5.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 6
			T1 = LBR6.get((i * 2) - 1);
			T2 = LBR6.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				T2.positionInTournament = 3;
				BH = new BracketHandler(T1, T2, "LBR6", i, 1);
				LBR6.remove(T2); // second Team is loser
			} else {
				FINAL.add(T2); // first Team is winner
				T1.positionInTournament = 3;
				BH = new BracketHandler(T2, T1, "LBR6", i, 1);
				LBR6.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // final
			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				T1.positionInTournament = 1;
				T1.MajorTitlesWon++;
				for(Player x: T1.roster)
					x.maChampion = true;
				T1.maChampion = true;
				T2.positionInTournament = 2;
				BH = new BracketHandler(T1, T2, "FINAL", i, 1);
				for(Player x: T1.roster)
				{
					x.MajorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T1.name, Color.gray);
				temp.addPart(" has won the Major Tournament!", Color.white);
				NotificationHandler.add(temp);
			
				MultiColorNotification.resetAll();
				T1.recentWins[23][1] += 1;

			} else {
				T1.positionInTournament = 2;
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
		
				MultiColorNotification.resetAll();
				T2.recentWins[23][1] += 1;
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
		Collections.shuffle(Teams);
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}

				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[LBR5] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[LBR6] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[SEMI] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[LBR7] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[LBR8] The chances of " + T1.name + " beating " + T2.name
				// + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
				if(T2.getTeamStrength() > T1.getTeamStrength())
				{
					Team temp = T2;
					T2=T1;
					T1=temp;
				}
				probability = determine(T1, T2);
				// System.out.println("[FINAL] The chances of " + T1.name + " beating " +
				// T2.name + " are " + probability);
				reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
		
					MultiColorNotification.resetAll();
					T1.recentWins[23][0] += 1;

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
	
					
					for(Player x: T2.roster)
						x.wChampion = true;
					T2.wChampion = true;
					MultiColorNotification.resetAll();
					T2.recentWins[23][0] += 1;
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
		

		
		for(Team x: Database.teamdatabase)
		{
			x.miChampion = false;
		}
		for(Player x: Database.playerdatabase)
		{
			x.miChampion = false;
		}
		Collections.shuffle(Teams);
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

		BracketHandler BH;

		for (int i = 4; i > 0; i--) { // first round
			T1 = Teams.get((i * 2) - 1);
			T2 = Teams.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				UBR2.add(T1); // first Team is winner
				LBR1.add(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "Round1", i, 2);
			} else {
				UBR2.add(T2); // first Team is winner
				LBR1.add(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "Round1", i, 2);
			}

		}
		for (int i = 2; i > 0; i--) { // lowerbracket Round 1
			T1 = LBR1.get((i * 2) - 1);
			T2 = LBR1.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR2.add(T1); // first Team is winner
				T2.positionInTournament = 7;
				LBR1.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR1", i, 2);
			} else {
				LBR2.add(T2); // first Team is winner
				T1.positionInTournament = 7;
				LBR1.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR1", i, 2);
			}

		}
		for (int i = 2; i > 0; i--) { // upperbracket Round 2
			T1 = UBR2.get((i * 2) - 1);
			T2 = UBR2.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
			T1 = LBR2.get((i * 2) - 1);
			T2 = LBR2.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR3.add(T1); // first Team is winner
				T2.positionInTournament = 5;
				LBR2.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR2", i, 2);
			} else {
				LBR3.add(T2); // first Team is winner
				T1.positionInTournament = 5;
				LBR2.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR2", i, 2);
			}

		}
		for (int i = 1; i > 0; i--) { // semi
			T1 = SEMI.get((i * 2) - 1);
			T2 = SEMI.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				LBR4.add(T2);;
				SEMI.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "SEMI", i, 2);

			} else {
				FINAL.add(T2); // first Team is winner
				LBR4.add(T1);
	
				SEMI.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "SEMI", i, 2);

			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 3

			T1 = LBR3.get((i * 2) - 1);

			T2 = LBR3.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}

			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				LBR4.add(T1); // first Team is winner

				T2.positionInTournament = 4;

				LBR3.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR3", i, 2);
			} else {
				LBR4.add(T2); // first Team is winner

				T1.positionInTournament = 4;
				BH = new BracketHandler(T2, T1, "LBR3", i, 2);

				LBR3.remove(T1); // second Team is loser
			}

		}
		for (int i = 1; i > 0; i--) { // lowerbracket Round 4
			T1 = LBR4.get((i * 2) - 1);
			T2 = LBR4.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				FINAL.add(T1); // first Team is winner
				T2.positionInTournament = 3;

				LBR4.remove(T2); // second Team is loser
				BH = new BracketHandler(T1, T2, "LBR4", i, 2);

			} else {
				FINAL.add(T2); // first Team is winner
				T1.positionInTournament = 3;

				LBR4.remove(T1); // second Team is loser
				BH = new BracketHandler(T2, T1, "LBR4", i, 2);
			}

		}
		for (int i = 1; i > 0; i--) { // final

			T1 = FINAL.get((i * 2) - 1);
			T2 = FINAL.get((i * 2) - 2);
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
			if (reel < probability) {
				T1.positionInTournament = 1;
				T1.MinorTitlesWon++;
				for(Player x: T1.roster)
					x.miChampion = true;
				T1.miChampion = true;

				BH = new BracketHandler(T1, T2, "FINAL", i, 2);
				T2.positionInTournament = 2;

				for(Player x: T1.roster)
				{
					x.MinorTitles++;
				}
				MultiColorNotification temp = new MultiColorNotification(T1.name, Color.pink);
				temp.addPart(" has won the Minor[8] Tournament!", Color.white);
				NotificationHandler.add(temp);

				MultiColorNotification.resetAll();
				T1.recentWins[23][2] += 1;

			} else {
				T2.positionInTournament = 1;
	
				BH = new BracketHandler(T2, T1, "FINAL", i, 2);
				T1.positionInTournament = 2;
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

				MultiColorNotification.resetAll();
				T2.recentWins[23][2] += 1;
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
			System.out.println("Your calendar fix didn't work");
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
		Collections.shuffle(Teams);
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);
			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");
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
			if(T2.getTeamStrength() > T1.getTeamStrength())
			{
				Team temp = T2;
				T2=T1;
				T1=temp;
			}
			probability = determine(T1, T2);

			reel = Math.random(); if(Application.ConsoleOutput) System.out.println("The random chance was: " + reel + "\n");

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

				MultiColorNotification.resetAll();
				T1.recentWins[23][2] += 1;

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

				MultiColorNotification.resetAll();
				T2.recentWins[23][2] += 1;
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
