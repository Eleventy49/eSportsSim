package Game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class MultiColorNotification extends Notification {
	String txt;
	static int id = 0;
	int uniqueid;
	ArrayList<Color> c = new ArrayList<Color>();
	ArrayList<String> s = new ArrayList<String>();
	
	public MultiColorNotification(String text, Color start)
	{
	super(text);
	s.add(text);
	c.add(start);
	}
	
	public void addPart(String text, Color nu)
	{
		txt += "" + text;
		s.add(text);
		c.add(nu);	
	}
	
	public void reset()
	{
		for(int i = 0; i < c.size(); i++) {
			if(Database.teamHasName(s.get(i)) != null && Database.teamHasName(s.get(i)).wChampion )
			{
			c.remove(i);
			c.add(i,Color.yellow);
			} else
			if(Database.playerHasName(s.get(i)) != null && Database.playerHasName(s.get(i)).wChampion)
			{
				c.remove(i);
				c.add(i,Color.yellow);
			}
			
			else if(Database.teamHasName(s.get(i)) != null && Database.teamHasName(s.get(i)).maChampion )
			{
			c.remove(i);
			c.add(i,Color.gray);
			} 
				else if(Database.playerHasName(s.get(i)) != null && Database.playerHasName(s.get(i)).maChampion)
			{
				c.remove(i);
				c.add(i,Color.gray);
			}
		
		else if(Database.teamHasName(s.get(i)) != null && Database.teamHasName(s.get(i)).miChampion )
			{
			c.remove(i);
			c.add(i,Color.pink);
			} else
			if(Database.playerHasName(s.get(i)) != null && Database.playerHasName(s.get(i)).miChampion)
			{
				c.remove(i);
				c.add(i,Color.pink);
			}else
			if(Database.playerHasName(s.get(i)) != null && Database.playerHasName(s.get(i)).bestPlayer)
			{
				c.remove(i);
				c.add(i,Color.blue);
				}
			else
				if(Database.playerHasName(s.get(i)) != null && Database.playerHasName(s.get(i)).worstPlayer)
				{
					c.remove(i);
					c.add(i,Color.red);
					}
				else
			{
				c.remove(i);
				c.add(i,Color.white);
			}
		}
	}
	
	public void render(int pos)
	{
		double running = 0;
		FontMetrics fm   = Application.g.getFontMetrics(Application.bitoperatorfont36);
		for(int i = 0; i < c.size(); i++)
		{
			Rectangle2D rect = fm.getStringBounds(s.get(i), Application.g);
			
			int textHeight = pos * 50;
			int panelHeight = (pos * 50) - 40;
			// Center text horizontally and vertically
			
			int y = ((panelHeight - textHeight) / 2  + fm.getAscent());
			
			Application.g.setColor(c.get(i));
			Application.g.drawString(s.get(i), 200 + (int )running, (NotificationHandler.coll.get(pos).uniqueid + 3)* 50);
			running = rect.getMaxX() + running;
			Application.g.setColor(Color.white);
		//	System.out.println("We are using the right render method");
			
			
		}
	}
	
	
	
	public String toString()
	{
		return txt + "\t" + uniqueid + "\t" + id; 
	}

	public static void resetAll() {
		for(Player x: Database.playerdatabase)
		{
			x.bestPlayer = false;
			x.worstPlayer = false;
		}
		MultiColorNotification tempN = null;
		Player bestPlayer  = Database.sortPlayersGeneral(Database.playerdatabase).get(0);
		bestPlayer.bestPlayer = true;
		
		Player worstPlayer  = Database.sortPlayersGeneral(Database.playerdatabase).get(Database.playerdatabase.size()-1); //.get(Database.playerdatabase.size()-1);
		System.out.println("Worst player is" + worstPlayer.getFullName());
		worstPlayer.worstPlayer = true;
		for(Notification b: NotificationHandler.coll)
		{
			if(b instanceof MultiColorNotification) {
				tempN = (MultiColorNotification) b;
				tempN.reset();
			}
		}
		
	}
	
	
}
