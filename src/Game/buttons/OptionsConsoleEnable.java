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
		Application.getGraphical().addMouseListener(this);;
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
	
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setText(String s)
	{
		txt = s;
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
		}
		
	}

	@Override
	public void mouseMiddleClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseRightClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void draw(Graphics g, Graphics2D g2d)
	{
	
		super.draw(g, g2d);	
		//Set the correct color for the button
	/*	if(Hovered)
		{
			g.setColor(cHovered);
		}
		else {
			g.setColor(cDefault);	}
		
	//Actually do the drawing to the screen	

		FontMetrics fm   = g.getFontMetrics(Game.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(txt, g);

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= (int) bounds.getHeight();
		int panelWidth = (int) bounds.getWidth();

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2) + bounds.x;
		int y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + bounds.y;

		g.drawString(txt, x, y);  // Draw the string.	
		g2d.draw(bounds);
//		g.drawString(txt, bounds.x + 5, bounds.y + 40);		*/
		}
}

