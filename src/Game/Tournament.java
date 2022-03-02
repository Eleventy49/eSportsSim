package Game;

import java.util.ArrayList;

public class Tournament {
	public static enum FORMAT {World, Major, Minor4, Minor8};
	public FORMAT type;
	public ArrayList<Team> participating = new ArrayList<Team>();
	public int Month;
	public int Year;
	
	
	public Tournament(FORMAT f, ArrayList<Team> invites, int M, int Y)
	{
		Month = M;
		Year = Y;
		type = f;
		for(Team x: invites)
		{
			participating.add(x);
		}
	}
	public String toString()
	{
		String john = "";
		for(Team x: participating)
		{
			john += x.name + "\t";
		}
		
		return "Format: " + type + "\tMonth: " + Month + "\tYear: " + Year + "\nTeams: " + john;
	}
	public void runQualifiers() {
		if (type == FORMAT.Major)
		{
			if(participating.size() <= 8)
			for(int i = 0; i < 8; i++)
			participating.add(Database.getRandomBoi(participating));
			else
			{
				while(participating.size() >= 9)
				participating.remove(8);
				
			}
		}
		else if (type == FORMAT.Minor8)
		{
			if(participating.size() <= 4)
			for(int i = 0; i < 4; i++) {
				participating.add(Database.getRandomBoi(participating));
			}
			else
			{
				while(participating.size() >= 5)
				participating.remove(4);
				
			}
		}
		
	}
	public String save() {
		
		String sub = "";
		System.out.println(sub);
		for(Team b: participating)
			sub += b.name + ",";
		return Month + "," + Year + "," + type + "," + participating.size() + "," +  sub + "\n";
	}
	
}
