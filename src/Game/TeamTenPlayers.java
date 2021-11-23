
package Game;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

public class TeamTenPlayers {
	static int mode = 0;
	static double statCounter = 0;
	static int statInter = 0;
	static String output = "";
	static ArrayList<Player> temp = new ArrayList<Player>();
	static Player tdd;
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
		for(Player b : Database.playerdatabase)
		{
			temp.add(b);
		}
		switch (mode) {
		case 0: // Skill Rating
		for (int i = 0; i < 10; i++) {
			for (Player x : temp)
				if (x.getStatsTotal() > statCounter) {
					tdd = x;
					statCounter = tdd.getStatsTotal();
				}
			try {
				if (tdd.equals(null))
					tdd = temp.get(0);
			} catch (NullPointerException e) {
				tdd = temp.get(0);
			}

			output += "" + tdd.org.Abbreviation + "." + tdd.name + " (" + tdd.getStatsTotal() + ")\n";
			temp.remove(tdd);
			statCounter = 0;
			tdd = null;
		}
		break;
		case 1: // Tournament Wins Rating
			for (int i = 0; i < 10; i++) {
				for (Player x : temp)
					if (x.getTournamentWins() > statCounter) {
						tdd = x;
						statCounter = tdd.getTournamentWins();
					}
				try {
					if (tdd.equals(null))
						tdd = temp.get(0);
				} catch (NullPointerException e) {
					tdd = temp.get(0);
				}
				String s = ""+(tdd.getTournamentWins());
				output += "" + tdd.name + " (" + s + ")\n";
				temp.remove(tdd);
				statCounter = 0;
				tdd = null;
			}
			break;
		case 2: // Earnings
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);

		for (int i = 0; i < 10; i++) {
			for (Player x : temp)
				if (x.earnings > statCounter) {
					tdd = x;
					statCounter = tdd.earnings;
				}
			try {
				if (tdd.equals(null))
					tdd = temp.get(0);
			} catch (NullPointerException e) {
				tdd = temp.get(0);
			}
			String s = n.format(tdd.earnings);
			output += "" + tdd.name + " (" + s + ")\n";
			temp.remove(tdd);
			statCounter = 0;
			tdd = null;
		}
		break;
		}
		Application.getGraphical().drawString2(output, Application.WIDTH/2, 50);
	}
	public static void clear()
	{
		temp.clear();
	}
	
}
/*
for (Player x : database.Playerdatabase)
temp.add(x);
String output = "";
switch (result) {
case 0: // Skill Rating
for (int i = 0; i < 10; i++) {
	for (Player x : temp)
		if (x.getPlayerStrength() > statCounter) {
			tdd = x;
			statCounter = tdd.getPlayerStrength();
		}
	try {
		if (tdd.equals(null))
			tdd = temp.get(0);
	} catch (NullPointerException e) {
		tdd = temp.get(0);
	}

	output += "" + tdd.name + " (" + tdd.getPlayerStrength() + ")\n";
	temp.remove(tdd);
	statCounter = 0;
	tdd = null;
}
break;
case 1: // Dynasty Factor
for (int i = 0; i < 10; i++) {
	for (Player x : temp)
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
	for (Player x : temp)
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

JOptionPane.showConfirmDialog(null, output, "Top Ten Players", 2);
*/