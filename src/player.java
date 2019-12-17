import java.util.*;

public class player implements java.lang.Comparable<player> {
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

	int MajorTitlesWon;
	int MinorTitlesWon;
	int WorldTitlesWon;
	String name = "";
	int statsTotal = 0;
	int[] stats = new int[18];
	String team = "";
	int role;
	team org;
	int MinorTitles = 0;
	int MajorTitles = 0;
	int WorldTitles = 0;
	int earnings = 0;

	public int compareTo(player p) {
		return (this.statsTotal - p.statsTotal) * -1;
	}

	public String save() {
		return name + "," + role + "," + WorldTitles + "," + MajorTitles + "," + MinorTitles + "," + stats[reactionTime]
				+ "," + stats[farmingLane] + "," + stats[farmingPattern] + "," + stats[fightingPriority] + ","
				+ stats[calmness] + "," + stats[communication] + "," + stats[consistency] + "," + stats[attention] + ","
				+ stats[patchUnderstanding] + "," + stats[cheeseAbility] + "," + stats[wardPlacement] + ","
				+ stats[dewarding] + "," + stats[heroPool] + "," + stats[gankAwareness] + "," + stats[gankAbility] + ","
				+ stats[statGrowth] + "," + stats[lanePressure] + "," + stats[starFactor] + "," + earnings + "\n";
	}

	public int generateNumbers(int l, int h) {
		Random r = new Random();
		return r.nextInt(h - l) + l;
	}

	public player(String n) {
		if (!(n == null || n.isEmpty())) {
			for (int i = 0; i < stats.length; i++)
				stats[i] = generateNumbers(0, 512);

			name = "" + n;

			// role = generateNumbers(1, 5);

			for (int x : stats)
				statsTotal += x;

			System.out.println("Done");
			org = null;
			database.playerdatabase.add(this);
			MinorTitles = 0;
			MajorTitles = 0;
			WorldTitles = 0;
		}
	}

	public player(String n, int r, int World, int Major, int Minor, int[] s) {
		if (!(n == null || n.isEmpty())) {

			for (int i = 0; i < stats.length; i++)
				stats[i] = s[i];

			name = "" + n;

			// role = generateNumbers(1, 5);

			for (int x : stats)
				statsTotal += x;

			System.out.println("Done");
			org = null;
			role = r;
			// database.playerdatabase.add(this);
			MinorTitles = Minor;
			MajorTitles = Major;
			WorldTitles = World;
		}
	}

	public String toString() {
		if (org == null)
			return "\tName: " + name + "\n\tCurrent Team: None" + "\n\n\tWorld Titles won: " + WorldTitles
					+ "\n\tMajor Titles won: " + MajorTitles + "\n\tMinor Titles won: " + MinorTitles
					+ "\n\n\tReaction Time: " + stats[reactionTime] + "\n\tLast Hitting Ability: " + stats[farmingLane]
					+ "\n\tFarming Pattern: " + stats[farmingPattern] + "\n\tFighting Priority: "
					+ stats[fightingPriority] + "\n\tCalmness: " + stats[calmness] + "\n\tCommunication: "
					+ stats[communication] + "\n\tConsistency: " + stats[consistency] + "\n\tAttention Span: "
					+ stats[attention] + "\n\tPatch Understanding: " + stats[patchUnderstanding]
					+ "\n\tAbility to pull off Cheese: " + stats[cheeseAbility] + "\n\tWard Placement: "
					+ stats[wardPlacement] + "\n\tDe-warding the enemy: " + stats[dewarding] + "\n\tHero Pool: "
					+ stats[heroPool] + "\n\tAwareness to Ganks: " + stats[gankAwareness]
					+ "\n\tAbility to pull off Ganks: " + stats[gankAbility] + "\n\tPlayer Growth Factor: "
					+ stats[statGrowth] + "\n\tAbility to Pressure enemy lane: " + stats[lanePressure]
					+ "\n\tStar Factor: " + stats[starFactor];
		return "\tName: " + name + "\n\tCurrent Team: " + org.name + "\n\n\tWorld Titles won: " + WorldTitles
				+ "\n\tMajor Titles won: " + MajorTitles + "\n\tMinor Titles won: " + MinorTitles
				+ "\n\n\tReaction Time: " + stats[reactionTime] + "\n\tLast Hitting Ability: " + stats[farmingLane]
				+ "\n\tFarming Pattern: " + stats[farmingPattern] + "\n\tFighting Priority: " + stats[fightingPriority]
				+ "\n\tCalmness: " + stats[calmness] + "\n\tCommunication: " + stats[communication]
				+ "\n\tConsistency: " + stats[consistency] + "\n\tAttention Span: " + stats[attention]
				+ "\n\tPatch Understanding: " + stats[patchUnderstanding] + "\n\tAbility to pull off Cheese: "
				+ stats[cheeseAbility] + "\n\tWard Placement: " + stats[wardPlacement] + "\n\tDe-warding the enemy: "
				+ stats[dewarding] + "\n\tHero Pool: " + stats[heroPool] + "\n\tAwareness to Ganks: "
				+ stats[gankAwareness] + "\n\tAbility to pull off Ganks: " + stats[gankAbility]
				+ "\n\tPlayer Growth Factor: " + stats[statGrowth] + "\n\tAbility to Pressure enemy lane: "
				+ stats[lanePressure] + "\n\tStar Factor: " + stats[starFactor];

	}

}
