package Game;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.*;

public class Player implements java.lang.Comparable<Player> {
	final static int reactionTime = 0;				//These are just the indeces in an array that correspond to each of these stats.
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

	public String name = "";
	int statsTotal = 0;
	int[] stats = new int[18];
	String team = "";
	int role;
	Team org;
	int MinorTitles = 0;
	int MajorTitles = 0;
	int WorldTitles = 0;
	public int earnings = 0;
	public boolean wChampion;
	public boolean maChampion;
	public boolean miChampion;
	public boolean bestPlayer;
	public boolean worstPlayer;

	//Sort players by their total stats.
	public int compareTo(Player p) {
		return (this.statsTotal - p.statsTotal) * -1;
	}

	//Get the total statistical value for all stats.
	public int getStatsTotal() {
		int counter = 0;
		for (int x : stats)
			counter += x;
		return counter;
	}

	//Return total tournament wins
	public int getTournamentWins() {
	
		return (MajorTitles + MinorTitles + WorldTitles);
	}

	//The string that will be written to the CSV file that will be how we save each player.
	public String save() {
		return name + "," + role + "," + WorldTitles + "," + MajorTitles + "," + MinorTitles + "," + stats[reactionTime]
				+ "," + stats[farmingLane] + "," + stats[farmingPattern] + "," + stats[fightingPriority] + ","
				+ stats[calmness] + "," + stats[communication] + "," + stats[consistency] + "," + stats[attention] + ","
				+ stats[patchUnderstanding] + "," + stats[cheeseAbility] + "," + stats[wardPlacement] + ","
				+ stats[dewarding] + "," + stats[heroPool] + "," + stats[gankAwareness] + "," + stats[gankAbility] + ","
				+ stats[statGrowth] + "," + stats[lanePressure] + "," + stats[starFactor] + "," + earnings + "," + wChampion + "," + 
				maChampion + "," + miChampion +"," + bestPlayer +"," + worstPlayer +"\n";
	}

	//Generate a number between l and h
	public int generateNumbers(int l, int h) {
		Random r = new Random();
		return r.nextInt(h - l) + l;
	}

	//Constructor for a player with just the name passed.
	public Player(String n) {
		if (!(n == null || n.isEmpty())) {
			for (int i = 0; i < stats.length; i++)
				stats[i] = generateNumbers(0, 512);

			name = "" + n;

			// role = generateNumbers(1, 5);

			for (int x : stats)
				statsTotal += x;

			org = null;
			Database.playerdatabase.add(this);
			MinorTitles = 0;
			MajorTitles = 0;
			WorldTitles = 0;
			wChampion = false;
			maChampion = false;
			miChampion = false;
			bestPlayer = false;
			worstPlayer = false;
		}
	}

	//Doing the stat growth thing.
	public void update() {
		Random r = new Random();
		for (int i = stats[statGrowth]; i > 0; i--)
			stats[r.nextInt(stats.length - 1)]++;
		for (int i = 0; i < stats.length - 1; i++)
			if (stats[i] >= 512) {
				stats[i] -= (int) Math.sqrt(stats[i]) - r.nextInt((int) Math.sqrt(stats[i]));
			}
		if (stats[statGrowth] >= 512)
			stats[statGrowth] = 15;

	}

	//Constructor used when loading player from the file.
	public Player(String n, int r, int World, int Major, int Minor, int[] s, int e, boolean champ, boolean maChamp, boolean miChamp, boolean bP, boolean wP) {
		if (!(n == null || n.isEmpty())) {

			for (int i = 0; i < stats.length; i++)
				stats[i] = s[i];

			name = "" + n;

			// role = generateNumbers(1, 5);

			for (int x : stats)
				statsTotal += x;
			earnings = e;
			org = null;
			role = r;
			// database.playerdatabase.add(this);
			MinorTitles = Minor;
			MajorTitles = Major;
			WorldTitles = World;
			wChampion = champ;
			maChampion = maChamp;
			miChampion = miChamp;
			bestPlayer = bP;
			worstPlayer = wP;
		}
	}

	//Get name with included abbreviation
	public String getFullName() {
		return org.Abbreviation + "." + name;
	}

	//For displaying the player in the console
	public String toString() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(earnings);
		System.out.println(WorldTitles);
		if (org == null)
			return "\tName: " + name + "\nRole: " + role + "\n\tCurrent Team: None" + "\nEarnings: " + s
					+ "\nSkill Ranking: " + Database.getPlayerPosition(-1, this) + " / " + Database.playerdatabase.size()
					+ "\n\n\tWorld Titles won: " + WorldTitles + "\n\tMajor Titles won: " + MajorTitles
					+ "\n\tMinor Titles won: " + MinorTitles + "\n\n\tReaction Time: " + stats[reactionTime]
					+ "\n\tLast Hitting Ability: " + stats[farmingLane] + "\n\tFarming Pattern: "
					+ stats[farmingPattern] + "\n\tFighting Priority: " + stats[fightingPriority] + "\n\tCalmness: "
					+ stats[calmness] + "\n\tCommunication: " + stats[communication] + "\n\tConsistency: "
					+ stats[consistency] + "\n\tAttention Span: " + stats[attention] + "\n\tPatch Understanding: "
					+ stats[patchUnderstanding] + "\n\tAbility to pull off Cheese: " + stats[cheeseAbility]
					+ "\n\tWard Placement: " + stats[wardPlacement] + "\n\tDe-warding the enemy: " + stats[dewarding]
					+ "\n\tHero Pool: " + stats[heroPool] + "\n\tAwareness to Ganks: " + stats[gankAwareness]
					+ "\n\tAbility to pull off Ganks: " + stats[gankAbility] + "\n\tPlayer Growth Factor: "
					+ stats[statGrowth] + "\n\tAbility to Pressure enemy lane: " + stats[lanePressure]
					+ "\n\tStar Factor: " + stats[starFactor];
		return "\tName: " + name + "\nRole: " + role + "\n\tCurrent Team: " + org.name + "\nEarnings: " + s
				+ "\nSkill Ranking: " + Database.getPlayerPosition(-1, this)  + " / " + Database.playerdatabase.size()
				+ "\n\n\tWorld Titles won: " + WorldTitles + "\n\tMajor Titles won: " + MajorTitles
				+ "\n\tMinor Titles won: " + MinorTitles + "\n\n\tReaction Time: " + stats[reactionTime]
				+ "\n\tLast Hitting Ability: " + stats[farmingLane] + "\n\tFarming Pattern: " + stats[farmingPattern]
				+ "\n\tFighting Priority: " + stats[fightingPriority] + "\n\tCalmness: " + stats[calmness]
				+ "\n\tCommunication: " + stats[communication] + "\n\tConsistency: " + stats[consistency]
				+ "\n\tAttention Span: " + stats[attention] + "\n\tPatch Understanding: " + stats[patchUnderstanding]
				+ "\n\tAbility to pull off Cheese: " + stats[cheeseAbility] + "\n\tWard Placement: "
				+ stats[wardPlacement] + "\n\tDe-warding the enemy: " + stats[dewarding] + "\n\tHero Pool: "
				+ stats[heroPool] + "\n\tAwareness to Ganks: " + stats[gankAwareness]
				+ "\n\tAbility to pull off Ganks: " + stats[gankAbility] + "\n\tPlayer Growth Factor: "
				+ stats[statGrowth] + "\n\tAbility to Pressure enemy lane: " + stats[lanePressure] + "\n\tStar Factor: "
				+ stats[starFactor];

	}

	public String toStringN() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(earnings);
		if (org == null) {
			if(wChampion) Application.getGame().getGraphics().setColor(Color.yellow);
			return "\tName: " + name;
		}
		if(wChampion) Application.getGame().getGraphics().setColor(Color.yellow);
		else if(maChampion) Application.getGame().getGraphics().setColor(Color.lightGray);
		else if(miChampion) Application.getGame().getGraphics().setColor(Color.orange);
		else if(bestPlayer) Application.getGame().getGraphics().setColor(Color.blue);
		else if(worstPlayer) Application.getGame().getGraphics().setColor(Color.red);
		return "\tName: " + name;
		
		
		
		
	}
	public String toStringN2() {
		NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
		// double doublePayment = 100.13;
		String s = n.format(earnings);
		if (org == null) {
		
			return "\nRole: " + role + "\n\tCurrent Team: None" + "\nEarnings: " + s
					+ "\nSkill Ranking: " + Database.getPlayerPosition(-1, this) + " / " + Database.playerdatabase.size()
					+ "\n\n\tWorld Titles won: " + WorldTitles + "\n\tMajor Titles won: " + MajorTitles
					+ "\n\tMinor Titles won: " + MinorTitles + "\n\n\tReaction Time: " + stats[reactionTime]
					+ "\n\tLast Hitting Ability: " + stats[farmingLane] + "\n\tFarming Pattern: "
					+ stats[farmingPattern] + "\n\tFighting Priority: " + stats[fightingPriority] + "\n\tCalmness: "
					+ stats[calmness] + "\n\tCommunication: " + stats[communication] + "\n\tConsistency: "
					+ stats[consistency] + "\n\tAttention Span: " + stats[attention] + "\n\tPatch Understanding: "
					+ stats[patchUnderstanding] + "\n\tAbility to pull off Cheese: " + stats[cheeseAbility]
					+ "\n\tWard Placement: " + stats[wardPlacement] + "\n\tDe-warding the enemy: " + stats[dewarding]
					+ "\n\tHero Pool: " + stats[heroPool] + "\n\tAwareness to Ganks: " + stats[gankAwareness]
					+ "\n\tAbility to pull off Ganks: " + stats[gankAbility] + "\n\tPlayer Growth Factor: "
					+ stats[statGrowth] + "\n\tAbility to Pressure enemy lane: " + stats[lanePressure]
					+ "\n\tStar Factor: " + stats[starFactor];
		}
		
		return "\nRole: " + role + "\n\tCurrent Team: " + org.name + "\nEarnings: " + s
				+ "\nSkill Ranking: " + Database.getPlayerPosition(-1, this)  + " / " + Database.playerdatabase.size()
				+ "\n\n\tWorld Titles won: " + WorldTitles + "\n\tMajor Titles won: " + MajorTitles
				+ "\n\tMinor Titles won: " + MinorTitles + "\n\n\tReaction Time: " + stats[reactionTime]
				+ "\n\tLast Hitting Ability: " + stats[farmingLane] + "\n\tFarming Pattern: " + stats[farmingPattern]
				+ "\n\tFighting Priority: " + stats[fightingPriority] + "\n\tCalmness: " + stats[calmness]
				+ "\n\tCommunication: " + stats[communication] + "\n\tConsistency: " + stats[consistency]
				+ "\n\tAttention Span: " + stats[attention] + "\n\tPatch Understanding: " + stats[patchUnderstanding]
				+ "\n\tAbility to pull off Cheese: " + stats[cheeseAbility] + "\n\tWard Placement: "
				+ stats[wardPlacement] + "\n\tDe-warding the enemy: " + stats[dewarding] + "\n\tHero Pool: "
				+ stats[heroPool] + "\n\tAwareness to Ganks: " + stats[gankAwareness]
				+ "\n\tAbility to pull off Ganks: " + stats[gankAbility] + "\n\tPlayer Growth Factor: "
				+ stats[statGrowth] + "\n\tAbility to Pressure enemy lane: " + stats[lanePressure] + "\n\tStar Factor: "
				+ stats[starFactor];
		

}

	public String toStringN3() {
		// TODO Auto-generated method stub
		return null;
	}
}
