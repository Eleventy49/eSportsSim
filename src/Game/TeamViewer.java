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

		public static void render (int i) {
			Color color = Application.getGame().getGraphics().getColor();
			if (Application.viewingTeam == null)
				Application.viewingTeam = Database.teamdatabase.get((TeamViewer.page * 10) - 11 + i);

			Application.getGame().getGraphics().setFont(Application.bitoperatorfont36);
			Application.getGame().getGraphics().setColor(Color.white);
			try {
				Graphical.drawStringWithLineBreaks(Application.viewingTeam.toStringN(), 700, 100);
				Application.getGame().getGraphics().setColor(Color.white);
				Graphical.drawStringWithLineBreaks(Application.viewingTeam.toStringN2(), 700, 150);

				for (int j = 0; j < Application.viewingTeam.roster.size(); j++) {
					Graphical.drawStringWithLineBreaks(Application.viewingTeam.toStringN3(j), 700, 650 + (50 * j));
					Application.getGame().getGraphics().setColor(Color.white);
				}
			} catch (IndexOutOfBoundsException e) {

			}
			Application.getGame().getGraphics().setColor(color);
		}
		public static void render(Team i) {
			Color color = Application.getGame().getGraphics().getColor();
			Application.getGame().getGraphics().setFont(Application.bitoperatorfont13);
			try {
				Graphical.drawStringWithLineBreaks(i.toString(), 700, 100);
			} catch (IndexOutOfBoundsException e) {

			}
			Application.getGame().getGraphics().setColor(color);
		}

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

