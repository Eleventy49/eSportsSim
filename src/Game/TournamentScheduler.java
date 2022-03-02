package Game;

import java.util.ArrayList;

public class TournamentScheduler {
	
	public static ArrayList<Tournament> collection = new ArrayList<Tournament>();
	private static Tournament temp = null;
	
	public static String getMonth(int month)
	{
		switch(month)
		{
		case 1:
			return "January "; 
		case 2:
			return "February ";
		case 3:
			return "March ";
		case 4:
			return "April ";
		case 5:
			return "May ";
		case 6:
			return "June ";
		case 7:
			return "July ";
		case 8:
			return "August ";
		case 9:
			return "September ";
		case 10:
			return "October ";
		case 11:
			return "November ";
		case 12:
			return "December ";
			
		default: return "December ";
		
	}
		
	}
	
	
	
	public static void pulse()
	{
		if(Application.isInGame)
		if(Timer.month == 2)
		{
			
			collection.add(new Tournament(Tournament.FORMAT.World, Database.getStrongBois(32), 8, Timer.year));					//World
			NotificationHandler.add(new Notification("World tournament has been scheduled for " + getMonth(8)));
		}
		else if(Timer.month == 8)
		{
			collection.add(new Tournament(Tournament.FORMAT.Major, Database.getStrongBois(8), 2, Timer.year + 1));			//Major in February next year
			NotificationHandler.add(new Notification("Major tournament has been scheduled for next " + getMonth(2)));
		}
		else if(Timer.month == 5)
		{
			collection.add(new Tournament(Tournament.FORMAT.Major, Database.getStrongBois(8), 11, Timer.year));				//Major in November
			NotificationHandler.add(new Notification("Major tournament has been scheduled for " + getMonth(11)));
		}
		else if(Timer.month == 11)
		{
			collection.add(new Tournament(Tournament.FORMAT.Major, Database.getStrongBois(8), 5, Timer.year + 1));			//Major in May of next year
			NotificationHandler.add(new Notification("Major tournament has been scheduled for next " + getMonth(5)));
		}
		else
		{
			if(Timer.month > 6)
			{	if(Math.random() > .5)
			{
				collection.add(new Tournament(Tournament.FORMAT.Minor4, Database.getWeakBois(4), Timer.month - 6, Timer.year + 1));	//4 team minor 6 months from now
				NotificationHandler.add(new Notification("Minor tournament has been scheduled for next " + getMonth(Timer.month - 6)));
			}
			else {
				collection.add(new Tournament(Tournament.FORMAT.Minor8, Database.getWeakBois(4), Timer.month - 6, Timer.year + 1));  //8 team minor 6 months from now
				NotificationHandler.add(new Notification("Minor tournament has been scheduled for next " + getMonth(Timer.month - 6)));
		}
			}
			else
			{
				if(Math.random() > .5) {
					collection.add(new Tournament(Tournament.FORMAT.Minor4, Database.getWeakBois(4), Timer.month + 6, Timer.year));   //4 team minor 6 months from now
					NotificationHandler.add(new Notification("Minor tournament has been scheduled for " + getMonth(Timer.month + 6)));
				}
				else {
					collection.add(new Tournament(Tournament.FORMAT.Minor8, Database.getWeakBois(4), Timer.month + 6, Timer.year));   //8 team minor 6 months from now
					NotificationHandler.add(new Notification("Minor tournament has been scheduled for " + getMonth(Timer.month + 6)));
				}
			}
				
		}
		for(Tournament x: collection)
		{
			if(x.Month == Timer.month)
			{
				if(x.type == x.type.Major) {
				SimTournament.Major(x.participating);
				for(int i = x.participating.size(); i != 0; i--)
				{
					x.participating.remove(x.participating.get(i - 1));
				}
				temp = x;
				}
				else if(x.type == x.type.Minor4) {
					SimTournament.Minor4(x.participating);
					for(int i = x.participating.size(); i != 0; i--)
					{
						x.participating.remove(x.participating.get(i - 1));
					}
					temp = x;
				}
				else if(x.type == x.type.Minor8) {
					SimTournament.Minor8(x.participating);
					for(int i = x.participating.size(); i != 0; i--)
					{
						x.participating.remove(x.participating.get(i -1));
					}
					temp = x;
				}
				else {
					SimTournament.World(x.participating);	
					for(int i = x.participating.size(); i != 0; i--)
					{
						x.participating.remove(x.participating.get(i - 1));
					}
					temp = x;
				
				
				
				}
			
				
				
				
			}
			
			if(x.Month - 1 == Timer.month)
			{
				if(x.type == Tournament.FORMAT.Minor8)
				{
				x.runQualifiers();		
				
				}
				if(x.type == Tournament.FORMAT.Major)
				{
				x.runQualifiers();		
				
				}
				
				
				
				
			}
			else if(x.Month == 1 && Timer.month == 12 && x.Year == Timer.year + 1)
			{
				if(x.type == Tournament.FORMAT.Minor8)
				{
				x.runQualifiers();		
				
				
				}
				if(x.type == Tournament.FORMAT.Major)
				x.runQualifiers();		
				
				}
				
				
				
				
				
				
			
			
			
			
			
		}
		collection.remove(temp);
		temp = null;
		
	}
	public void qualifierTeam()
	{
		
		
		
	}
	
}
