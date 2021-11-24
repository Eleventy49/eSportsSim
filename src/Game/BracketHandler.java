package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BracketHandler {
	static int Counter = 0;
	static Graphics g;
	public static void setGraphics(Graphics g1)
	{
		g = g1;
	}
	public BracketHandler(Team winner, Team notWinner, String section, int match, int type)
	{ 	
		switch(type)
		{
		case 0:
		Counter++;
		Bracket32.save(winner,notWinner,section, Counter);
		Application.getGame().getGraphics().setColor(Color.red);
		Application.getGame().getGraphics().setFont(Application.getGame().getSmallFont());
		Application.getGame().getGraphics().setColor(Color.white);
		break;
		case 1:
			Counter++;
			Bracket16.save(winner,notWinner,section, Counter);
			Application.getGame().getGraphics().setColor(Color.red);
			Application.getGame().getGraphics().setFont(Application.getGame().getSmallFont());
			Application.getGame().getGraphics().setColor(Color.white);
			break;
		case 2:
			Counter++;
			Bracket8.save(winner,notWinner,section, Counter);
			Application.getGame().getGraphics().setColor(Color.red);
			Application.getGame().getGraphics().setFont(Application.getGame().getSmallFont());
			Application.getGame().getGraphics().setColor(Color.white);
			break;
		case 3:
			Counter++;
			Bracket4.save(winner,notWinner,section, Counter);
			Application.getGame().getGraphics().setColor(Color.red);
			Application.getGame().getGraphics().setFont(Application.getGame().getSmallFont());
			Application.getGame().getGraphics().setColor(Color.white);
			break;
		
	//	g.setFont(Game.bitoperatorfont13);
		
		

		
		
		
		}
	}
}
