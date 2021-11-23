package Game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Timer {

	static int timer = 0;
	public static boolean running = false;
	public static int speed = 1;
	static int length = 0;
	static double delta = 0;
	static long lastTime = 0;
	static long now = 0;
	static int month = 0;
	static int week = 0;
	static int year = 0;
	static int requirement = Application.WIDTH / 60;
	
	public static void init()
	{
		
		month = 1;
		week = 1;
		year = 1;
	}
	public static void enable()
	{
		running = true;
		if(Application.ConsoleOutput)
			System.out.println("[Timer] We've been enbaled!");
		
	}
	public static void disable()
	{
		running = false;
		
	}
	private static String dateAsString()
	{
		String ree = "";
	
		ree += "Week " + week + ", ";
		
		switch(month)
		{
		case 1:
			ree += "January, "; break;
		case 2:
			ree += "February, ";break;
		case 3:
			ree += "March, ";break;
		case 4:
			ree += "April, ";break;
		case 5:
			ree += "May, ";break;
		case 6:
			ree += "June,";break;
		case 7:
			ree += "July, ";break;
		case 8:
			ree += "August, ";break;
		case 9:
			ree += "September, ";break;
		case 10:
			ree += "October, ";break;
		case 11:
			ree += "November, ";break;
		case 12:
			ree += "December, ";break;
			
			default: break;
		}
		
		ree += "Year " + year;
		
		return ree;
	}
	public static void render()
	{
		Application.getGraphical().getGraphics().setFont(Application.getGraphical().getLargeFont());
		Application.getGraphical().getGraphics().setColor(Color.white);
		Application.getGraphical().getGraphics().drawLine(1, 1, length, 1);
		Application.getGraphical().getGraphics().drawLine(1, 2, length, 2);
		Application.getGraphical().getGraphics().drawLine(1, 3, length, 3);
		Application.getGraphical().getGraphics().drawLine(1, 4, length, 4);
		Application.getGraphical().getGraphics().drawLine(1, 5, length, 5);
		
		
		FontMetrics fm   = Application.getGraphical().getGraphics().getFontMetrics(Application.getGraphical().getLargeFont());
		Rectangle2D rect = fm.getStringBounds(dateAsString(), Application.getGraphical().getGraphics());
		int textWidth  = (int)(rect.getWidth());
	
		int panelWidth = (int) Application.WIDTH;

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2);
		
		if(Application.isInGame)
		Application.getGraphical().getGraphics().drawString(dateAsString(), x, 45);  // Draw the string.	

	}
	public static void pulse()
	{
		if(timer == 0 && running)
		{
			for(Team x: Database.teamdatabase)
			{
				x.Tick();
			}
			
		}
		
		if(timer == 0 && week == 1 && running)
		{
			TournamentScheduler.pulse();
		oldTick();	
		// new Notification("This is a notification");
		 
		// System.out.println("Creating a notification");
		}
	}
	public static void oldTick()
	{
		Random r = new Random();

	

		if (((r.nextInt(2) + 1) == 2) && Database.teamdatabase.size() > 1) {
			int TeamSwap1 = (r.nextInt(Database.teamdatabase.size() - 1));

			int TeamSwap2 = (r.nextInt(Database.teamdatabase.size() - 1));

			if (TeamSwap2 == TeamSwap1)
				if (TeamSwap2 == Database.teamdatabase.size() - 1)
					TeamSwap2 -= 1;
				else if (TeamSwap2 == 0)
					TeamSwap2 += 1;
				else
					TeamSwap2 -= 1;
			if (TeamSwap2 == TeamSwap1)
				System.out.println("You are an idiot");
			int temp;
			for (int i = 0; i != 1;) {
				temp = r.nextInt(5 - 1) - 1;
				if (temp != TeamSwap1) {
					temp = TeamSwap2;
					i = 1;
				}
			}
			int PlayerSwap1 = (r.nextInt(Database.teamdatabase.get(TeamSwap1).roster.size() - 1)) + 1;
			int PlayerSwap2 = (r.nextInt(Database.teamdatabase.get(TeamSwap2).roster.size() - 1)) + 1;

			//
			Player Player1 = Database.teamdatabase.get(TeamSwap1).roster.get(PlayerSwap1);
			Player Player2 = Database.teamdatabase.get(TeamSwap2).roster.get(PlayerSwap2);
			Player1.org = Database.teamdatabase.get(TeamSwap2);
			Player2.org = Database.teamdatabase.get(TeamSwap1);
			Database.teamdatabase.get(TeamSwap1).roster.remove(Player1);
			Database.teamdatabase.get(TeamSwap2).roster.remove(Player2);
			Database.teamdatabase.get(TeamSwap1).roster.add(Player2);
			Database.teamdatabase.get(TeamSwap2).roster.add(Player1);
			MultiColorNotification.resetAll();
			MultiColorNotification tempNot = new MultiColorNotification("", Color.white);
			if(Player1.wChampion)
			{
			tempNot.addPart(Player1.name, Color.yellow);
			}
			else
			if(Player1.maChampion)
			{
			tempNot.addPart(Player1.name, Color.gray);
			}
			else
			if(Player1.miChampion)
			{
			tempNot.addPart(Player1.name, Color.pink);
			}
			else
			if(Player1.wChampion)
			{
			tempNot.addPart(Player1.name, Color.yellow);
			}
			else
			{
			tempNot.addPart(Player1.name, Color.white);
			}
			tempNot.addPart(" >> ", Color.white);
			if(Database.teamdatabase.get(TeamSwap2).wChampion)
			{
				tempNot.addPart(Database.teamdatabase.get(TeamSwap2).name, Color.yellow);
			}
			else
			tempNot.addPart(Database.teamdatabase.get(TeamSwap2).name, Color.white);
			tempNot.addPart(" : ", Color.white);
			if(Player2.wChampion)
			{
			tempNot.addPart(Player2.name, Color.yellow);
			}
			else
			tempNot.addPart(Player2.name, Color.white);
			tempNot.addPart(" >> ", Color.white);
			if(Database.teamdatabase.get(TeamSwap1).wChampion)
			{
				tempNot.addPart(Database.teamdatabase.get(TeamSwap1).name, Color.yellow);
			}
			else
				tempNot.addPart(Database.teamdatabase.get(TeamSwap1).name, Color.white);
			
			NotificationHandler.add(tempNot);
		
			for (Team x : Database.teamdatabase) {
				if (x.roster.size() != 5) {
					for (Player y : Database.playerdatabase)
						if ((y.org == null) && (x.roster.size() != 5)) {
							y.org = x;
							x.roster.add(y);
							//Add notification for team signing
						}

				}
			//	System.out.println(x.roster.size());
				if (x.roster.size() == 5) {
					x.giveRoles();
				}
			}
			//Display notification for team trade
		}
		for (Player x : Database.playerdatabase)
		{ if(Application.ConsoleOutput)
			System.out.println(x.name + " has had their skills increased");
			x.update();
		}
	}
	public static void tick()
	{
		if(Application.gameStateIsPartOf(Application.State, Application.SpectatorStates) || Application.gameStateIsPartOf(Application.State, Application.ManagerStates) ) {
		pulse();
		if(running) {
			
			
			timer += 1;
			
			
			if(timer % (Application.WIDTH / 480) == 0) {
				for(int i = speed; i >= 0; i--) {
			length++;
		
			}}
			
			
		if(length >= Application.WIDTH)
		{week += 1;
			if(week == 5)
			{
				week = 1;
				month ++;
				
			}
			if(month == 13)
			{
				month = 1;
				year++;
			}
			length = 0;
			timer = 0;
			if(month == 8)
			{
				Random r = new Random();
				int numberOfTrades = r.nextInt(8) + 3;
				for(int i = 0; i < numberOfTrades; i++)
				Timer.forceTrade();
			}
		}
		}
		}
	}
	public static void setWeek(int week2) {
		week = week2;
		
	}
	public static void setMonth(int month2) {
		month = month2;
		
	}
	public static void setYear(int year2) {
		year = year2;
		
	}
	public static void decrease() {
		if(speed > 1)
			speed--;
	}
	public static void increase() {
		
			speed++;
	}
	public static void forceTrade() {
		MultiColorNotification.resetAll();
		Random r = new Random();
		int TeamSwap1 = (r.nextInt(Database.teamdatabase.size() - 1));

		int TeamSwap2 = (r.nextInt(Database.teamdatabase.size() - 1));

		if (TeamSwap2 == TeamSwap1)
			if (TeamSwap2 == Database.teamdatabase.size() - 1)
				TeamSwap2 -= 1;
			else if (TeamSwap2 == 0)
				TeamSwap2 += 1;
			else
				TeamSwap2 -= 1;
		if (TeamSwap2 == TeamSwap1)
			System.out.println("You are an idiot");
		int temp;
		for (int i = 0; i != 1;) {
			temp = r.nextInt(5 - 1) - 1;
			if (temp != TeamSwap1) {
				temp = TeamSwap2;
				i = 1;
			}
		}
		int PlayerSwap1 = (r.nextInt(Database.teamdatabase.get(TeamSwap1).roster.size() - 1)) + 1;
		int PlayerSwap2 = (r.nextInt(Database.teamdatabase.get(TeamSwap2).roster.size() - 1)) + 1;

		//
		Player Player1 = Database.teamdatabase.get(TeamSwap1).roster.get(PlayerSwap1);
		Player Player2 = Database.teamdatabase.get(TeamSwap2).roster.get(PlayerSwap2);
		Player1.org = Database.teamdatabase.get(TeamSwap2);
		Player2.org = Database.teamdatabase.get(TeamSwap1);
		Database.teamdatabase.get(TeamSwap1).roster.remove(Player1);
		Database.teamdatabase.get(TeamSwap2).roster.remove(Player2);
		Database.teamdatabase.get(TeamSwap1).roster.add(Player2);
		Database.teamdatabase.get(TeamSwap2).roster.add(Player1);
		MultiColorNotification tempNot = new MultiColorNotification("", Color.white);
		if(Player1.wChampion)
		{
		tempNot.addPart(Player1.name, Color.yellow);
		}
		else
		{
		tempNot.addPart(Player1.name, Color.white);
		}
		tempNot.addPart(" >> ", Color.white);
		if(Database.teamdatabase.get(TeamSwap2).wChampion)
		{
			tempNot.addPart(Database.teamdatabase.get(TeamSwap2).name, Color.yellow);
		}
		else
		tempNot.addPart(Database.teamdatabase.get(TeamSwap2).name, Color.white);
		tempNot.addPart(" : ", Color.white);
		if(Player2.wChampion)
		{
		tempNot.addPart(Player2.name, Color.yellow);
		}
		else
		tempNot.addPart(Player2.name, Color.white);
		tempNot.addPart(" >> ", Color.white);
		if(Database.teamdatabase.get(TeamSwap1).wChampion)
		{
			tempNot.addPart(Database.teamdatabase.get(TeamSwap1).name, Color.yellow);
		}
		else
			tempNot.addPart(Database.teamdatabase.get(TeamSwap1).name, Color.white);
		
		NotificationHandler.add(tempNot);
		for(Team x: Database.teamdatabase)
		if (x.roster.size() == 5) {
			x.giveRoles();
		}
		
		
	}
}
