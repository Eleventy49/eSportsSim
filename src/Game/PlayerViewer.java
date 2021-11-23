package Game;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

public class PlayerViewer {

	double statCounterD = 0;
	int statCounter = 0;
	Player tdd = null;
	static int page = 1;
	ArrayList<Player> temp = new ArrayList<Player>();
	static int x = 1;


	public static void defaultdisplay()
	{
		for(int i = 0; i <= 10; i++)
		{
			
			x = ((page * 10) + i) - 11;
			try {
			if(!Database.playerdatabase.get(x).equals(null)) {
			if(Database.playerdatabase.get(x).wChampion)
				Application.getGraphical().getGraphics().setColor(Color.yellow);
			Application.getGraphical().getGraphics().drawString(Database.playerdatabase.get(x).name, 200, i * 60);
			Application.getGraphical().getGraphics().setColor(Color.white);
			}
			Application.getGraphical().getGraphics().drawString("" + page, 550, 700);}
			catch(IndexOutOfBoundsException e)
			{
				
			}
		}
		
		
	}
	
	public static void tournamentWins()
	{
		for(Player x : Database.playerdatabase)
		{}
	}
	
	public static void earnings()
	{
		for(Player x : Database.playerdatabase)
		{}
	}
	public static void increasePage()
	{
		if(((Database.playerdatabase.size() / 10.0)) % 1 == 0)
		{System.out.println("Its an even 10");
			if(page < (int)Database.playerdatabase.size()/ 10) {
			page++;
			
			
			}
		}
		if(((Database.playerdatabase.size() / 10.0) ) % 1 != 0)
		{System.out.println("Its not an even 10");
			if(page < (int)(Database.playerdatabase.size() / 10) + 1) {
				page++;
				
			}
		}
	}
	public static void decreasePage()
	{
		if(page>=2)
		{
			page--;
		}
	}
	
}
