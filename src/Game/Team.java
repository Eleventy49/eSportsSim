package Game;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.*;

public class Team {
	int MajorTitlesWon;
	int MinorTitlesWon;
	int WorldTitlesWon;
	public String name = "";
	int players;
	ArrayList<Player> roster;
	public int tournamentEarnings;
	int positionInTournament;
	public double dynasty;
	String Abbreviation;
	public boolean wChampion;
	public boolean maChampion;
	public boolean miChampion;
	
	public int PerformanceTrainers;
	public boolean TeamCoach;
	public int FinancialManagers;
	
	public int TradeNegotiations;
	public int TeamScouts;
	public Cash cash;
	
	public ArrayList<Staff> staff = new ArrayList<Staff>();
	

	final static int reactionTime = 0;			//The same indeces from the player class.
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
	

	//Default constructor with just a name.
	public Team(String n) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = 0;
			MinorTitlesWon = 0;
			WorldTitlesWon = 0;
			tournamentEarnings = 0;
			roster = new ArrayList<Player>();
			// players = 0;
			Database.teamdatabase.add(this);
			dynasty = 0;
			Abbreviation = "null";
			wChampion = false;
			maChampion = false;
			miChampion =false;
		}
	}
	
	public void Tick()
	{
		int fmanagertracker = 0;
		int coachTracker = 0;
		int trainerTracker = 0;
		for(Staff x: staff)
		{
			
			switch(x.type)
			{
			case PerformanceTrainer:
				trainerTracker++;
				break;
			case TeamCoach:
				coachTracker++;
				break;
			case FinancialManager:
				fmanagertracker++;
				break;
			}
			
		}
		for(Staff x: staff)
		{
		x.tick(fmanagertracker);
		}
	}

	public Team(String n, String a) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = 0;
			MinorTitlesWon = 0;
			WorldTitlesWon = 0;
			tournamentEarnings = 0;
			roster = new ArrayList<Player>();
			// players = 0;
			Database.teamdatabase.add(this);
			dynasty = 0;
			Abbreviation = a;
			cash = new Cash(100,99);
			maChampion = false;
			miChampion =false;
			wChampion = false;
		}
	}
	//Constructor that is used to load part of a player? I actually have no idea where this is used if it even is.
	public Team(String n, int major, int minor, int world, int earnings, double d, String a, boolean wChamp, boolean maChamp, boolean miChamp, Cash c) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = major;
			MinorTitlesWon = minor;
			WorldTitlesWon = world;
			roster = new ArrayList<Player>();
			tournamentEarnings = earnings;
			dynasty = d;
			// players = 0;
			Database.teamdatabase.add(this);
			Abbreviation = a;
			wChampion = wChamp;
			maChampion = maChamp;
			miChampion = miChamp;
			cash = c;
		}
	}

	//Return the player on this team that has the name passed as a parameter.
	public static Player hasPlayerName(String pn) {
		for (Player x : Database.playerdatabase)
			if (x.name.equals(pn))
				return x;

		return null;
	}

	//Full constructor from the file.
	public Team(String n, int major, int minor, int world, int earnings, double d, String p1, String p2, String p3,
			String p4, String p5, String a, int r1, int r2, int r3, int r4, int r5, boolean champ, boolean maChamp, boolean miChamp, Cash c) {
		if (!(n == null || n.isEmpty())) {
			name = n;
			MajorTitlesWon = major;
			MinorTitlesWon = minor;
			WorldTitlesWon = world;
			roster = new ArrayList<Player>();
			tournamentEarnings = earnings;
			dynasty = d;
			// players = 0;
			Database.teamdatabase.add(this);

			if(!p1.equals("null"))
			roster.add(hasPlayerName(p1));
			if(!p2.equals("null"))
			roster.add(hasPlayerName(p2));
			if(!p3.equals("null"))
			roster.add(hasPlayerName(p3));
			if(!p4.equals("null"))
			roster.add(hasPlayerName(p4));
			if(!p5.equals("null"))
			roster.add(hasPlayerName(p5));

			if(!p1.equals("null"))
			roster.get(0).org = this;
			if(!p2.equals("null"))
			roster.get(1).org = this;
			if(!p3.equals("null"))
			roster.get(2).org = this;
			if(!p4.equals("null"))
			roster.get(3).org = this;
			if(!p5.equals("null"))
			roster.get(4).org = this;

			if(!p1.equals("null"))
			roster.get(0).role = r1;
			if(!p2.equals("null"))
			roster.get(1).role = r2;
			if(!p3.equals("null"))
			roster.get(2).role = r3;
			if(!p4.equals("null"))
			roster.get(3).role = r4;
			if(!p5.equals("null"))
			roster.get(4).role = r5;

		//	System.out.println(roster.get(4));
			Abbreviation = a;
			wChampion = champ;
			maChampion = false;
			miChampion =false;
			cash = c;

		}
	}

	//Generates part of the string for the players and their roles for the save funciton
	public String subSave() {
		String re = "";
		for (int i = 0; i < 5; i++)
		{
			try {
			re += roster.get(i).name + "," + roster.get(i).role + ",";
			}
			catch(IndexOutOfBoundsException e)
			{
				re += "null,0,";
			}
			
			
		}

		return re;
	}

	//The string written to the CSV to save teams to the file.
	public String save() {

		return name + "," + MajorTitlesWon + "," + MinorTitlesWon + "," + WorldTitlesWon + "," + tournamentEarnings
				+ "," + subSave() + "," + dynasty + "," + Abbreviation + "," + wChampion + "," + maChampion + "," + miChampion + "," + cash.getAmount() + "," 
				+ staff.size() + "," + subSave2() + "\n";

	}

	private String subSave2() {
		String s = "";
		for(Staff b: staff)
		{
			s += b.save();
		}
		return s;
	}

	//Get overall statistical team strength.
	public int getTeamStrength() {
		if (this != null) {
			int counter = 0;
			for (Player x : roster)
				for (int i = 0; i < 18; i++)
					counter += x.stats[i];

			return counter;
		} else
			return 0;

	}

	//Give roles to players.
	public void giveRoles() {
		ArrayList<Player> temp = new ArrayList<Player>();
		for (Player x : roster) {
			temp.add(x);

		}
		int statCounter1 = 0;
		int statCounter2 = 0;
		int statCounter3 = 0;
		int statCounter4 = 0;
		int statCounter5 = 0;
		int CurrentMax = 0;
		Player CurrentMid = null;
		Player CurrentCarry = null;
		Player CurrentSupport4 = null;
		Player CurrentSupport5 = null;

		for (Player x : temp) { // Mid
			statCounter1 = 0;
			statCounter2 = 0;
			statCounter3 = 0;
			statCounter4 = 0;
			statCounter5 = 0;

			if (x.stats[heroPool] > statCounter1)
				statCounter1 = x.stats[heroPool];
			if (x.stats[farmingLane] > statCounter1)
				statCounter2 = x.stats[farmingLane];
			if (x.stats[gankAwareness] > statCounter1)
				statCounter3 = x.stats[gankAwareness];
			if (x.stats[gankAbility] > statCounter1)
				statCounter4 = x.stats[gankAbility];

			statCounter5 = statCounter1 + statCounter2 + statCounter3 + statCounter4;
			if (statCounter5 > CurrentMax) {
				CurrentMid = x;
				CurrentMax = statCounter5;
			//	System.out.println(x.name);
				
			}
			
		}
	//System.out.println(CurrentMid.name);
		CurrentMid.role = 2;
		statCounter1 = 0;
		statCounter2 = 0;
		statCounter3 = 0;
		statCounter4 = 0;
		statCounter5 = 0;
		CurrentMax = 0;
		temp.remove(CurrentMid);

		for (Player x : temp) { // Carry
			statCounter1 = 0;
			statCounter2 = 0;
			statCounter3 = 0;
			statCounter4 = 0;
			statCounter5 = 0;

			if (x.stats[farmingLane] > statCounter1)
				statCounter1 = x.stats[farmingLane] * 25;
			if (x.stats[farmingPattern] > statCounter1)
				statCounter2 = x.stats[farmingPattern] * 50;
			if (x.stats[fightingPriority] > statCounter1)
				statCounter3 = x.stats[fightingPriority] * 25;

			statCounter5 = statCounter1 + statCounter2 + statCounter3 + statCounter4;
			if (statCounter5 > CurrentMax) {
				CurrentCarry = x;
				CurrentMax = statCounter5;
			}

		}
		CurrentCarry.role = 1;
		statCounter1 = 0;
		statCounter2 = 0;
		statCounter3 = 0;
		statCounter4 = 0;
		statCounter5 = 0;
		CurrentMax = 0;
		temp.remove(CurrentCarry);
		for (Player x : temp) { // Support4
			statCounter1 = 0;
			statCounter2 = 0;
			statCounter3 = 0;
			statCounter4 = 0;
			statCounter5 = 0;

			if (x.stats[wardPlacement] > statCounter1)
				statCounter1 = x.stats[wardPlacement] * 10;
			if (x.stats[dewarding] > statCounter1)
				statCounter2 = x.stats[dewarding] * 10;
			if (x.stats[gankAbility] > statCounter1)
				statCounter3 = x.stats[gankAbility] * 50;
			if (x.stats[gankAwareness] > statCounter1)
				statCounter4 = x.stats[gankAwareness] * 30;

			statCounter5 = statCounter1 + statCounter2 + statCounter3 + statCounter4;
			if (statCounter5 > CurrentMax) {
				CurrentSupport4 = x;
				CurrentMax = statCounter5;
			}
		}
		CurrentSupport4.role = 4;
		statCounter1 = 0;
		statCounter2 = 0;
		statCounter3 = 0;
		statCounter4 = 0;
		statCounter5 = 0;
		CurrentMax = 0;
		temp.remove(CurrentSupport4);
		for (Player x : temp) { // Support5
			statCounter1 = 0;
			statCounter2 = 0;
			statCounter3 = 0;
			statCounter4 = 0;
			statCounter5 = 0;

			if (x.stats[wardPlacement] > statCounter1)
				statCounter1 = x.stats[wardPlacement] * 20;
			if (x.stats[dewarding] > statCounter1)
				statCounter2 = x.stats[dewarding] * 20;
			if (x.stats[gankAwareness] > statCounter1)
				statCounter3 = x.stats[gankAwareness] * 30;
			if (x.stats[gankAbility] > statCounter1)
				statCounter4 = x.stats[gankAbility] * 10;
			if (x.stats[communication] > statCounter1)
				statCounter5 = x.stats[communication] * 20;

			statCounter5 = statCounter5 + statCounter1 + statCounter2 + statCounter3 + statCounter4;
			if (statCounter5 > CurrentMax) {
				CurrentSupport5 = x;
				CurrentMax = statCounter5;
			}
		}
		CurrentSupport5.role = 5;
		statCounter1 = 0;
		statCounter2 = 0;
		statCounter3 = 0;
		statCounter4 = 0;
		statCounter5 = 0;
		CurrentMax = 0;
		temp.remove(CurrentSupport5);

		temp.get(0).role = 3;
		
		
		roster.clear();
		roster.add(CurrentCarry);
		roster.add(CurrentMid);
		roster.add(temp.get(0));
		roster.add(CurrentSupport4);
		roster.add(CurrentSupport5);

	}

	//For displaying teams.
	public String toString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(tournamentEarnings);
		switch (roster.size()) {
		default:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) + "\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon 
					+ "\n\n\tThis team does not have anyone on its roster.";
		case 1:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) + "\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon +
					"\n\n\tRoster: \n\t\t"
					+ roster.get(0).name + " (" + roster.get(0).getStatsTotal() + ")";

		case 2:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) + "\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon 
					+ "\n\n\tRoster: \n"
					+ roster.get(0).name + " (" + roster.get(0).getStatsTotal() + ")" + "\n\t" + roster.get(1).name
					+ " (" + roster.get(1).getStatsTotal() + ")";

		case 3:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) +"\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon + 
					"\n\n\tRoster: \n"
					+ roster.get(0).name + " (" + roster.get(0).getStatsTotal() + ")" + "\n\t" + roster.get(1).name
					+ " (" + roster.get(1).getStatsTotal() + ")" + "\n\t" + roster.get(2).name + " ("
					+ roster.get(2).getStatsTotal() + ")";
		case 4:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) + "\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon 
					+ "\n\n\tRoster: \n"
					+ roster.get(0).name + " (" + roster.get(0).getStatsTotal() + ")" + "\n\t" + roster.get(1).name
					+ " (" + roster.get(1).getStatsTotal() + ")" + "\n\t" + roster.get(2).name + " ("
					+ roster.get(2).getStatsTotal() + ")" + "\n\t" + roster.get(3).name + " ("
					+ roster.get(3).getStatsTotal() + ")";
		case 5:
			return "\tTeam Name: " + name + "\nTournament Earnings: " + s +  "\nDynasty Multiplier: " + dynasty
					+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
					+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon 
					+ "\n\n\tRoster: \n"
					+ roster.get(0).name + " (" + roster.get(0).getStatsTotal() + ")" + "\n\t" + roster.get(1).name
					+ " (" + roster.get(1).getStatsTotal() + ")" + "\n\t" + roster.get(2).name + " ("
					+ roster.get(2).getStatsTotal() + ")" + "\n\t" + roster.get(3).name + " ("
					+ roster.get(3).getStatsTotal() + ")" + "\n\t" + roster.get(4).name + " ("
					+ roster.get(4).getStatsTotal() + ")";

		}
	}

	public String toStringN() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(tournamentEarnings);
		switch (roster.size()) {
		default:
			if(wChampion) Application.getGame().getGraphics().setColor(Color.yellow);
			else if(maChampion) Application.getGame().getGraphics().setColor(Color.lightGray);
			else if(miChampion) Application.getGame().getGraphics().setColor(Color.orange);
			return "\tTeam Name: " + name;
	}
}

	public String toStringN2() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(tournamentEarnings);
	
		return "\nTournament Earnings: " + s + "\nMoney On Hand: " + n.format(cash.getAmount()) + "\nDynasty Multiplier: " + dynasty
		+ "\n\n\tWorld Championships Won: " + WorldTitlesWon + "\n\tMajor Championships Won: "
		+ MajorTitlesWon + "\n\tMinor Championships Won: " + MinorTitlesWon ;
	}

	public String toStringN3(int j) {

		if(roster.get(j).wChampion)
			Application.getGame().getGraphics().setColor(Color.yellow);
		return roster.get(j).name + " (" + roster.get(j).getStatsTotal() + ")";
	}

	public void updateStaff() {
		for(Staff x: staff)
			x.setIsOwnedBy(this);
		
	}
}