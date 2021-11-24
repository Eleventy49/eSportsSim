package Game;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Bracket16 {
	static ArrayList<String> winners = new ArrayList<String>();
	static ArrayList<String> notWinners = new ArrayList<String>();

	static ArrayList<String> section = new ArrayList<String>();
	static ArrayList<Integer> counter = new ArrayList<Integer>();
	static int match = 0;
	static int indexArray;
	static int r1 = 0;
	static int l1 = 0;
	static int l2 = 0;
	static int l3 = 0;
	static int l4 = 0;
	static int l5 = 0;
	static int l6 = 0;
	static int l7 = 0;
	static int l8 = 0;
	static int u1 = 0;
	static int u2 = 0;
	static int u3 = 0;
	static int u4 = 0;
	static int s = 0;
	static int f = 0;
	static int hoveredPlayer = -1;
	public static void setHoveredPlayer(int i)
	{
		hoveredPlayer = i;
	}
	
	public static void save(Team winner, Team notWinner, String Section, int Counter)
	{
		winners.add(winner.name);
		notWinners.add(notWinner.name);

		section.add(Section);
		counter.add(Counter);
	}
	
	public static void load() {
		Application.getGame().getGraphics().setFont(Application.getGame().getSmallFont());
		Application.getGame().getGraphics().setColor(Color.white);

		for(Integer x: counter)
		{	
			indexArray = x - 1;
			
			if(section.get(indexArray).equals("Round1"))
			{
				r1++;
				if(hoveredPlayer == indexArray ) {
					Application.getGame().getGraphics().setColor(Color.DARK_GRAY);}
				else Application.getGame().getGraphics().setColor(Color.white);
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 125, (r1 * 40) -25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 125, ((r1 * 40) - 30 + 20));
				if(r1 == 8)
					r1 = 0;
				Application.getGame().getGraphics().setColor(Color.white);
				
			}
			if(section.get(indexArray).equals("LBR1"))
			{ l1++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 275, ((l1 * 40) -25 + 600));
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 275, (((l1 * 40) -30 + 20) + 600));
				if(l1 == 4)
					l1 = 0;
			}
			if(section.get(indexArray).equals("UBR2"))
			{ u2++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 275, (u2 * 40)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 275, ((u2 * 40) -30 + 20));
				
				if(u2 == 4)
					u2 = 0;
				
			}
			if(section.get(indexArray).equals("LBR2"))
			{l2++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 425, ((l2 * 40)-25 + 600));
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 425, (((l2 * 40) + 20)-30 + 600));
				if(l2 == 4)
					l2 = 0;
			}
			if(section.get(indexArray).equals("UBR3"))
			{u3++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 425, (u3 * 40)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 425, ((u3 * 40) -30 + 20));
				if(u3 == 2)
					u3 = 0;
			}
			if(section.get(indexArray).equals("LBR3"))
			{l3++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 575, ((l3 * 40) + 600)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 575, (((l3 * 40) + 20) -30 + 600));
				if(l3 == 2)
					l3 = 0;
			}
			if(section.get(indexArray).equals("LBR4"))
			{l4++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 725, ((l4 * 40) + 600)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 725, (((l4 * 40) + 20) -30 + 600));
				if(l4 == 2)
					l4 = 0;
			}

			if(section.get(indexArray).equals("LBR5"))
			{l5++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 875, ((l5 * 40) + 600)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 875, (((l5 * 40) + 20) -30+ 600));
				if(l5 == 1)
				l5 = 0;
			}
			if(section.get(indexArray).equals("LBR6"))
			{l6++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 1025, ((l6 * 40) + 600)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 1025, (((l6 * 40) + 20) -30+ 600));
				if(l6 == 1)
					l6 = 0;
			}
			if(section.get(indexArray).equals("SEMI"))
			{s++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 725, (s * 40)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 725, ((s * 40) -30+ 20));
				if(s == 1)
					s = 0;
			}

			if(section.get(indexArray).equals("FINAL"))
			{f++;
				Application.getGame().getGraphics().drawString(winners.get(indexArray), 1450, (f * 40)-25);
				Application.getGame().getGraphics().drawString(notWinners.get(indexArray), 1450, ((f * 40) -30+ 20));
				if(f ==1)
					f = 0;
			}
			
			
			
		
		}
		match = 0 ;
	}
	public static void printBracket()
	{
		Application.getGame().getGraphics().drawRect(120, 1, 130, 35);
		Application.getGame().getGraphics().drawRect(120, 40, 130, 35);
	}
	public static void clearTournament()
	{
		winners.clear();
		notWinners.clear();

		section.clear();
		counter.clear();
		match = 0;
		 indexArray = 0;
		 r1 = 0;
 l1 = 0;
		 l2 = 0;
		 l3 = 0;
		 l4 = 0;
	 l5 = 0;
		 l6 = 0;
	 l7 = 0;
		 l8 = 0;
		 u1 = 0;
		 u2 = 0;
		 u3 = 0;
		 u4 = 0;
		 s = 0;
		 f = 0;
		 BracketHandler.Counter = 0;
	}
	
}
