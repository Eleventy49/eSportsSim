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
import Game.Timer;
import Game.Application.STATE;
import Game.Filehandler;

public class SelectionManagerMode extends NormalButton implements ButtonInterface,MouseListener {

	public SelectionManagerMode()
	{
		super( new Rectangle((Application.WIDTH / 4) - 150, 450, 300, 50) , "Manager Mode", Color.red, Color.GRAY,new Application.STATE[]{Application.STATE.GameSelect}, Application.STATE.ManagerMode, "SelectionManagerMode");
	
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);;
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
	@Override
	public void mouseLeftClicked(MouseEvent e) {
		
		Application.prevState.add(Application.State); 
		Music.mouseClick();
		Application.SpectatorMode = false;
		Filehandler.newManagerGame();
		Timer.enable();
		Application.isInGame = true;
	}

	@Override
	public void mouseMiddleClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseRightClicked(MouseEvent e) {
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
		
		

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
