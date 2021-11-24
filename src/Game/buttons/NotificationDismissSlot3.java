package Game.buttons;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


import Game.ButtonInterface;
import Game.Application;
import Game.Application.STATE;
import Game.NotificationHandler;

public class NotificationDismissSlot3 extends NormalButton implements ButtonInterface,MouseListener {
	public NotificationDismissSlot3()
	{
		super( new Rectangle(125, (5 * 50) -40 , 50, 50),"", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.SpectatorMode},  Application.STATE.SpectatorMode, "TeamsViewTeam");
	
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);;
	}
	
	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		NotificationHandler.delete(2);
		Music.mouseClick();
	}

	public void draw() {
		if (NotificationHandler.coll.size() >= 3 && Application.State == Application.STATE.SpectatorMode) {
			super.draw();
		}
	}
}
