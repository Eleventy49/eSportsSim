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
		String ree = "Week " + week + ", ";	
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
		Application.g.setFont(Application.bitoperatorfont36);
		Application.g.setColor(Color.white);
		Application.g.drawLine(1, 1, length, 1);
		Application.g.drawLine(1, 2, length, 2);
		Application.g.drawLine(1, 3, length, 3);
		Application.g.drawLine(1, 4, length, 4);
		Application.g.drawLine(1, 5, length, 5);	
		
		FontMetrics fm   = Application.g.getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(dateAsString(), Application.g);
		int textWidth  = (int)(rect.getWidth());
	
		int panelWidth = (int) Application.WIDTH;

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2);
		
		if(Application.isInGame)
		Application.g.drawString(dateAsString(), x, 45);  // Draw the string.	

	}
	public static void pulse()
	{}
	public static void oldTick()
	{}
	public static void tick()
	{}
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
}
