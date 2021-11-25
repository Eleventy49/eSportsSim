package Game;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

public class TeamTenTeams {
	static int mode = 0;
	static double statCounter = 0;
	static String output = "";
	static ArrayList<Team> temp = new ArrayList<Team>();
	static Team tdd;
	//0 = Skill
	//1 = Dynasty
	//2 = Earnings
	//3 = Tournament Wins
	public static  void changeMode(int b)
	{
		mode = b;
	}
	
	public static void render()
	{temp.clear();
	output = "";
		for(Team b : Database.teamdatabase)
		{
			temp.add(b);
		}
		switch (mode) {
		case 0: // Skill Rating
		for (int i = 0; i < 10; i++) {
			for (Team x : temp)
				if (x.getTeamStrength() > statCounter) {
					tdd = x;
					statCounter = tdd.getTeamStrength();
				}
			try {
				if (tdd.equals(null))
					tdd = temp.get(0);
			} catch (NullPointerException e) {
				tdd = temp.get(0);
			}

			output += "" + tdd.name + " (" + tdd.getTeamStrength() + ")\n";
			temp.remove(tdd);
			statCounter = 0;
			tdd = null;
		}
		break;
		case 1: // Dynasty Factor
		for (int i = 0; i < 10; i++) {
			for (Team x : temp)
				if (x.dynasty > statCounter) {
					tdd = x;
					statCounter = tdd.dynasty;
				}
			try {
				if (tdd.equals(null))
					tdd = temp.get(0);
			} catch (NullPointerException e) {
				tdd = temp.get(0);
			}

			output += "" + tdd.name + " (" + tdd.dynasty + ")\n";
			temp.remove(tdd);
			statCounter = 0;
			tdd = null;
		}
		break;
		case 2: // Earnings
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

		for (int i = 0; i < 10; i++) {
			for (Team x : temp)
				if (x.tournamentEarnings > statCounter) {
					tdd = x;
					statCounter = tdd.tournamentEarnings;
				}
			try {
				if (tdd.equals(null))
					tdd = temp.get(0);
			} catch (NullPointerException e) {
				tdd = temp.get(0);
			}
			String s = n.format(tdd.tournamentEarnings);
			output += "" + tdd.name + " (" + s + ")\n";
			temp.remove(tdd);
			statCounter = 0;
			tdd = null;
		}
		break;
		case 3: // Tournament Wins
		for (int i = 0; i < 10; i++) {
				for (Team x : temp)
					if ((x.MajorTitlesWon + x.MinorTitlesWon + x.WorldTitlesWon) > statCounter) {
						tdd = x;
						statCounter = (tdd.MajorTitlesWon + tdd.MinorTitlesWon + tdd.WorldTitlesWon);
					}
				try {
					if (tdd.equals(null))
						tdd = temp.get(0);
				} catch (NullPointerException e) {
					tdd = temp.get(0);
				}
				String s = "World: " + tdd.WorldTitlesWon + ", Major: " + tdd.MajorTitlesWon +  ", Minor: " + tdd.MinorTitlesWon;
				output += "" + tdd.name + " (" + s + ")\n";
				temp.remove(tdd);
				statCounter = 0;
				tdd = null;
			}
			break;
		}
		Graphical.drawString2(output, Application.WIDTH/2, 50);
	}
	public static void clear()
	{
		temp.clear();
	}
	
}
/*
for (team x : database.teamdatabase)
temp.add(x);
String output = "";
switch (result) {
case 0: // Skill Rating
for (int i = 0; i < 10; i++) {
	for (team x : temp)
		if (x.getTeamStrength() > statCounter) {
			tdd = x;
			statCounter = tdd.getTeamStrength();
		}
	try {
		if (tdd.equals(null))
			tdd = temp.get(0);
	} catch (NullPointerException e) {
		tdd = temp.get(0);
	}

	output += "" + tdd.name + " (" + tdd.getTeamStrength() + ")\n";
	temp.remove(tdd);
	statCounter = 0;
	tdd = null;
}
break;
case 1: // Dynasty Factor
for (int i = 0; i < 10; i++) {
	for (team x : temp)
		if (x.dynasty > statCounter) {
			tdd = x;
			statCounter = tdd.dynasty;
		}
	try {
		if (tdd.equals(null))
			tdd = temp.get(0);
	} catch (NullPointerException e) {
		tdd = temp.get(0);
	}

	output += "" + tdd.name + " (" + tdd.dynasty + ")\n";
	temp.remove(tdd);
	statCounter = 0;
	tdd = null;
}
break;
case 2: // Earnings
NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

for (int i = 0; i < 10; i++) {
	for (team x : temp)
		if (x.tournamentEarnings > statCounter) {
			tdd = x;
			statCounter = tdd.tournamentEarnings;
		}
	try {
		if (tdd.equals(null))
			tdd = temp.get(0);
	} catch (NullPointerException e) {
		tdd = temp.get(0);
	}
	String s = n.format(tdd.tournamentEarnings);
	output += "" + tdd.name + " (" + s + ")\n";
	temp.remove(tdd);
	statCounter = 0;
	tdd = null;
}
break;
}

JOptionPane.showConfirmDialog(null, output, "Top Ten Teams", 2);
*/