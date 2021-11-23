package Game;

import java.awt.Color;
import java.util.ArrayList;

public class Bracket32 {
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
		Application.getGraphical().getGraphics().setFont(Application.getGraphical().getSmallFont());
		Application.getGraphical().getGraphics().setColor(Color.white);

		for(Integer x: counter)
		{	
			indexArray = x - 1;
			
			if(section.get(indexArray).equals("Round1"))
			{
				r1++;
				if(hoveredPlayer == indexArray ) {
					Application.getGraphical().getGraphics().setColor(Color.DARK_GRAY);}
				else Application.getGraphical().getGraphics().setColor(Color.white);
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 125, (r1 * 40) -25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 125, ((r1 * 40) - 30 + 20));
				if(r1 == 16)
					r1 = 0;
				Application.getGraphical().getGraphics().setColor(Color.white);
				
			}
			if(section.get(indexArray).equals("LBR1"))
			{ l1++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 275, ((l1 * 40) -25 + 600));
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 275, (((l1 * 40) -30 + 20) + 600));
				if(l1 == 8)
					l1 = 0;
			}
			if(section.get(indexArray).equals("UBR2"))
			{ u2++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 275, (u2 * 40)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 275, ((u2 * 40) -30 + 20));
				
				if(u2 == 8)
					u2 = 0;
				
			}
			if(section.get(indexArray).equals("LBR2"))
			{l2++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 425, ((l2 * 40)-25 + 600));
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 425, (((l2 * 40) + 20)-30 + 600));
				if(l2 == 8)
					l2 = 0;
			}
			if(section.get(indexArray).equals("UBR3"))
			{u3++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 425, (u3 * 40)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 425, ((u3 * 40) -30 + 20));
				if(u3 == 4)
					u3 = 0;
			}
			if(section.get(indexArray).equals("LBR3"))
			{l3++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 575, ((l3 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 575, (((l3 * 40) + 20) -30 + 600));
				if(l3 == 4)
					l3 = 0;
			}
			if(section.get(indexArray).equals("LBR4"))
			{l4++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 725, ((l4 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 725, (((l4 * 40) + 20) -30 + 600));
				if(l4 == 4)
					l4 = 0;
			}
			if(section.get(indexArray).equals("UBR4"))
			{u4++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 575, (u4 * 40)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 575, ((u4 * 40) -30 + 20));
				if(u4 == 2)
					u4 = 0;
			}
			if(section.get(indexArray).equals("LBR5"))
			{l5++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 875, ((l5 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 875, (((l5 * 40) + 20) -30+ 600));
				if(l5 == 2)
				l5 = 0;
			}
			if(section.get(indexArray).equals("LBR6"))
			{l6++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 1025, ((l6 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 1025, (((l6 * 40) + 20) -30+ 600));
				if(l6 == 2)
					l6 = 0;
			}
			if(section.get(indexArray).equals("SEMI"))
			{s++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 725, (s * 40)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 725, ((s * 40) -30+ 20));
				if(s == 1)
					s = 0;
			}
			if(section.get(indexArray).equals("LBR7"))
			{l7++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 1175, ((l7 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 1175, (((l7 * 40) + 20) -30+ 600));
				if(l7 == 1)
					l7 = 0;
			}
			if(section.get(indexArray).equals("LBR8"))
			{ l8++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 1323, ((l8 * 40) + 600)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 1323, (((l8 * 40) + 20)-30 + 600));
				if(l8 == 1)
					l8 = 0;
			}
			if(section.get(indexArray).equals("FINAL"))
			{f++;
				Application.getGraphical().getGraphics().drawString(winners.get(indexArray), 1450, (f * 40)-25);
				Application.getGraphical().getGraphics().drawString(notWinners.get(indexArray), 1450, ((f * 40) -30+ 20));
				if(f ==1)
					f = 0;
			}
			
			
			
		
		}
		match = 0 ;
	}
	public static void printBracket()
	{
		Application.getGraphical().getGraphics().drawRect(120, 1, 130, 35);
		Application.getGraphical().getGraphics().drawRect(120, 40, 130, 35);
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
