package Game.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

import Game.Application;

import Game.MusicHandler;
import Game.Application.STATE;

public class NormalButton {
	public Rectangle bounds;				//The phyisical button
	public String txt;						//What the button displays as text
	Color cDefault;					//The default color when the button is NOT highlighted
	Color cHovered;					//The color of the button when the color is highlighted
	public Application.STATE[] prereq;		//The gamestate that allows this button to show up  [SPECIAL CASES EXIST FOR "CORNERMENUBUTTON"]
	public Application.STATE gameStateOnClick;	//The gamestate that the game will be set to when the button is pushed
	public boolean Hovered = false;		//Whether or not this button is being hovered over by the mouse.
	public String name;					//The name of the button. I don't think this will have any use but it exists just in case.
	static boolean isMouse = true;
	MusicHandler Music = new MusicHandler();	
	
	//The constructor. These are all invoked in ButtonCollection
	public NormalButton(Rectangle b, String t, Color co, Color co2, Application.STATE[] states ,Application.STATE gs, String n)
	{
		bounds = b;
		txt = t;
		cDefault = co;
		cHovered = co2;
	    prereq = states;
		gameStateOnClick = gs;
		name = n;

	}
	
	public NormalButton(Rectangle b, String t, Color co, Color co2, ArrayList<Application.STATE> states ,Application.STATE gs, String n)
	{
		Object[] temp = states.toArray();
		
		Application.STATE[] temp2 = new Application.STATE[temp.length];
		for(int i = 0; i < temp.length; i++)
		{
			temp2[i] = (STATE) temp[i];
		}
		bounds = b;
		txt = t;
		cDefault = co;
		cHovered = co2;
	    prereq = temp2;
		gameStateOnClick = gs;
		name = n;

	}
	
	public void draw(Graphics g, Graphics2D g2d)
	{
		if(Application.gameStateIsPartOf(Application.State, prereq))
		{	
			//Set the correct color for the button
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
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);		






		}
		}
		
	public void init()
	{
		
	}
	
	public void mouseLeftClicked() {
		
	}
	public void mouseRightClicked() {
		// TODO Auto-generated method stub
		
	}
	public void mouseMiddleClicked() {
		// TODO Auto-generated method stub
		
	}
	
	
}
