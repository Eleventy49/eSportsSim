package Game.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Application;

public class SpecialButton extends NormalButton{
	
	public SpecialButton(Rectangle r, String t, Color co, Color c02, Application.STATE[] statestoEXCLUDE, Application.STATE gs, String n) {
		super(r,t,co,c02,statestoEXCLUDE,gs,n);
	}
	
	public void init()
	{
		
	}
	
	public void draw(Graphics g, Graphics2D g2d)
	{
		if(!Application.gameStateIsPartOf(Application.State, prereq))
		{
		if(Hovered)
		{
			g.setColor(cHovered);
		}
		else {
			g.setColor(cDefault);	}
		
	//Actually do the drawing to the screen	

		FontMetrics fm   = g.getFontMetrics(Application.bitoperatorfont36);
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
//		g.drawString(txt, bounds.x + 5, bounds.y + 40);		


		}

	}

}
