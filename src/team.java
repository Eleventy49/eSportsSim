import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class team {
	int MajorTitlesWon;
	int MinorTitlesWon;
	int WorldTitlesWon;
	String name = "";
	int players;
	ArrayList<player> roster;
	int tournamentEarnings;
	int positionInTournament;

	public team(String n) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = 0;
			MinorTitlesWon = 0;
			WorldTitlesWon = 0;
			tournamentEarnings = 0;
			roster = new ArrayList<player>();
			// players = 0;
			database.teamdatabase.add(this);
		}
	}

	public team(String n, int major, int minor, int world, int earnings) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = major;
			MinorTitlesWon = minor;
			WorldTitlesWon = world;
			roster = new ArrayList<player>();
			tournamentEarnings = earnings;
			// players = 0;
			database.teamdatabase.add(this);
		}
	}

	public String subSave() {
		String re = "";
		for (player x : roster)
			re += x.name + ",";

		return re;
	}

	public String save() {
		return name + "," + MajorTitlesWon + "," + MinorTitlesWon + "," + WorldTitlesWon + "," + tournamentEarnings
				+ "," /* + subSave() */ + "\n";

	}

	public int getTeamStrength() {
		if (this != null) {
			int counter = 0;
			for (player x : roster)
				for (int i = 0; i < 18; i++)
					counter += x.stats[i];

			return counter;
		} else
			return 0;

	}

	public String toString() {
		switch (roster.size()) {
		default:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tThis team does not have anyone on its roster.";
		case 1:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tRoster: \n\t\t" + roster.get(0).name;

		case 2:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tRoster: \n" + roster.get(0).name + "\n\t" + roster.get(1).name;

		case 3:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tRoster: \n" + roster.get(0).name + "\n\t" + roster.get(1).name + "\n\t"
					+ roster.get(2).name;
		case 4:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tRoster: \n" + roster.get(0).name + "\n\t" + roster.get(1).name + "\n\t"
					+ roster.get(2).name + "\n\t" + roster.get(3).name;
		case 5:
			return "\tTeam Name: " + name + "\nEarnings: " + tournamentEarnings + "\n\tWorld Championships Won: "
					+ WorldTitlesWon + "\n\tMajor Championships Won: " + MajorTitlesWon + "\n\tMinor Championships Won"
					+ MinorTitlesWon + "\n\n\tRoster: \n" + roster.get(0).name + "\n\t" + roster.get(1).name + "\n\t"
					+ roster.get(2).name + "\n\t" + roster.get(3).name + "\n\t" + roster.get(4).name;

		}
	}
}
