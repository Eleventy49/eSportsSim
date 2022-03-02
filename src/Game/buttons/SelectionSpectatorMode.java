package Game.buttons;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import Game.ButtonInterface;
import Game.Filehandler;
import Game.Application;
import Game.Timer;


public class SelectionSpectatorMode extends NormalButton implements ButtonInterface,MouseListener{

	public SelectionSpectatorMode()
	{
		super(new Rectangle(((Application.WIDTH / 4) * 3) - 150, 450, 300, 50), "Spectator Mode", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.GameSelect},  Application.STATE.SpectatorMode, "SelectionSpectatorMode");
	
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
		
		Application.prevState.add(Application.State);  
		Music.mouseClick();
		Application.SpectatorMode=true;
		Filehandler.newSpectatorGame();
		Timer.enable();
		Application.isInGame = true;
		Application.State = gameStateOnClick;
	}

		}



	

