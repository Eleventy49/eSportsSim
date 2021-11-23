package Game.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Application;

public class Slider {
	
	public Rectangle outside;
	public String txt;
	public Color color1;
	public Color color2;
	public Application.STATE[] gamestates;
	public int coordx;
	
	public static void drawCenteredCircle(int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  Application.getGraphical().getGraphics().fillOval(x,y,r,r);
		}
	public Slider(Rectangle r, String t, Color co, Color co2, Application.STATE[] states, int startpoint)
	{
		outside = r;
		txt = t;
		color1 = co;
		color2 = co2;
		gamestates = states;
		coordx = startpoint;
		
	}
	public void draw(Graphics g, Graphics2D g2d)
	{
		if(Application.gameStateIsPartOf(Application.State, gamestates))
		{
		
	//Actually do the drawing to the screen	

		FontMetrics fm   = g.getFontMetrics(Application.getGraphical().getLargeFont());
		Rectangle2D rect = fm.getStringBounds(txt, g);

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= (int) outside.getHeight();
		int panelWidth = (int) outside.getWidth();

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2) + outside.x;
		int y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + outside.y;

		
		g.setColor(color1);
		g.drawString(txt, 430, y);  // Draw the string.	
		
		g.drawLine(outside.x, outside.height / 2 + outside.y ,(int)outside.getMaxX(),outside.height / 2 + outside.y);
		drawCenteredCircle(coordx,outside.height / 2 + outside.y,15);
		
		//g.setColor(color2); //TODO Option to see slider boundaries
		//g2d.draw(outside);
//		
}
	}
}
