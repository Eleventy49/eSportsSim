package Game.buttons;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.AddPlayer;
import Game.ButtonInterface;
import Game.Application;
import Game.Application.STATE;
import Game.Name;

public class PlayersAddPlayer extends NormalButton implements ButtonInterface,MouseListener {

	public PlayersAddPlayer()
	{
		super(new Rectangle((Application.WIDTH / 2) - 50, 200, 100, 50), "Add Player", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.SpectatorModePlayers},  Application.STATE.SpectatorModeAddPlayer, "PlayersAddPlayer");
	
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}
	
	public void mouseClicked(MouseEvent e) {
		
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if(bounds.contains(x,y) && Application.gameStateIsPartOf(Application.State, prereq)) {
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			mouseLeftClicked(e);
		}
		else if(e.getButton() == MouseEvent.BUTTON2)
		{
			mouseRightClicked(e);
		}
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			mouseMiddleClicked(e);
		}
		}
	}
	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State); 
		Application.State = gameStateOnClick;
		AddPlayer.text = new Name().user;
		Music.mouseClick();
		Application.isInGame = true;
	}

	@Override
	public void mouseMiddleClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Music.quickFunction();
		
	}

	@Override
	public void mouseRightClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Music.quickFunction();
		
	}
		
}
