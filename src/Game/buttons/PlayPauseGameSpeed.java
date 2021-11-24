package Game.buttons;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.ButtonInterface;
import Game.Application;
import Game.Timer;


public class PlayPauseGameSpeed extends SpecialButton implements ButtonInterface,MouseListener{

	public PlayPauseGameSpeed()
	{
		super(new Rectangle(150, 6, 50, 50), "||", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.Menu,Application.STATE.Menu}, Application.STATE.Menu, "DEFAULT");
	
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);;
	}
	
	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		if(Timer.running)
		{
			Timer.disable();
		}
		else
		{
			Timer.enable();
		}
		
		Music.mouseClick();
		
	}
}

