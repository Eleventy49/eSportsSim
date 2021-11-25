package Game;
import java.awt.Color;
import java.text.NumberFormat;
	import java.util.ArrayList;
	import java.util.Locale;

	import javax.swing.JOptionPane;
public class TeamViewer {

		double statCounterD = 0;
		int statCounter = 0;
		Player tdd = null;
		static int page = 1;
		ArrayList<Team> temp = new ArrayList<Team>();
		static int x = 1;


		public static void defaultdisplay()
		{
			for(int i = 0; i <= 10; i++)
			{
				
				x = ((page * 10) + i) - 11;
				try {
				if(!Database.teamdatabase.get(x).equals(null)) {
					if(Database.teamdatabase.get(x).wChampion)
						Application.g.setColor(Color.yellow);
					else if(Database.teamdatabase.get(x).maChampion)
						Application.g.setColor(Color.gray);
					else if(Database.teamdatabase.get(x).miChampion)
						Application.g.setColor(Color.pink);
					
				Application.g.drawString(Database.teamdatabase.get(x).name, 200, i * 60);
				Application.g.setColor(Color.white);
				}
				Application.g.drawString("" + page, 550, 700);}
				catch(IndexOutOfBoundsException e)
				{
					
				}
			}
			
			
		}
		
		public static void tournamentWins()
		{
			for(Team x : Database.teamdatabase)
			{}
		}
		
		public static void earnings()
		{
			for(Team x : Database.teamdatabase)
			{}
		}
		public static void increasePage()
		{
			if(((Database.teamdatabase.size() / 10.0)) % 1 == 0)
			{
				if(page < (int)Database.teamdatabase.size()/ 10) {
				page++;
				
				
				}
			}
			if(((Database.teamdatabase.size() / 10.0) ) % 1 != 0)
			{
				if(page < (int)(Database.teamdatabase.size() / 10) + 1) {
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

