package Game;

import java.util.ArrayList;

public class NotificationHandler {
	
	public static ArrayList<Notification> coll = new ArrayList<Notification>();
	public static boolean editing = false;

	public static void delete(int n)
	{
		editing = true;
		coll.remove(n);
		Notification.id--;
		for(int i = n; i < coll.size(); i++)
			coll.get(i).uniqueid--;
			
			
		editing = false;	
	}
	public static void add(Notification n)
	{
		editing = true;
		coll.add(n);
		editing = false;
	}
	public static void render()
	{	
		editing = true;
		if(Application.State == Application.STATE.SpectatorMode)
		{Team champ;
		ArrayList<Player> p1 = new ArrayList<Player>();
		
		for(Player x: Database.playerdatabase)
			if(x.wChampion)
				p1.add(x);
		
		
		for(Team x: Database.teamdatabase)
			if (x.wChampion)
				champ = x;
		
		
			
			for(int i = 0; i < 14; i++)
			{	if(coll.size() > i) {
				if(coll.get(i) instanceof MultiColorNotification)
				{
					MultiColorNotification temp = (MultiColorNotification) coll.get(i);
					temp.render(i);
				}
				else
				{
				Application.g.drawString(coll.get(i).txt, 200, (coll.get(i).uniqueid + 3)* 50);
				}
			}
			}
		}
		editing = false;
	}
	public static void clear() {
		coll.clear();
		Notification.resetID();
		
	}
}
