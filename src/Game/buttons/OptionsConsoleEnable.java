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

/*
 MouseInput
 
 	if(ButtonCollection.PlayersViewPlayer.bounds.contains(mx, my)
				&&  Game.State == ButtonCollection.PlayersViewPlayer.prereq[0])
					{
			ButtonCollection.PlayersViewPlayer.mouseLeftClicked(e);						
					}
 
 ButtonCollection:
 
 	public static BUTTON BUTTON = new BUTTON();
 
 
 
 
 collection.add(BUTTON );
 
 
 
 */

public class OptionsConsoleEnable extends NormalButton implements ButtonInterface,MouseListener{

	public OptionsConsoleEnable()
	{
		super(new Rectangle(200, 175, 200, 50), "Enabled", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.Options},  Application.STATE.Options, "OptionsConsoleEnable");
	
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
	//	Game.prevState.add(Game.State);  
	//	Game.State = ButtonCollection.DEFAULT.gameStateOnClick;
		Music.mouseClick();
		if(Application.ConsoleOutput)
		{
			Application.ConsoleOutput = false;
			txt = "Disabled";
		}
		else
		{
			Application.ConsoleOutput = true;
			txt = "Enabled";
			System.out.println("Hello World!");
		}
		
	}
}

