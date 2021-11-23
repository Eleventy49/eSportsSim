package Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import sun.*;

public class WarningText{

	public WarningText(String text, float alpha)
	{
		try {
	    
		Application.getGraphical().getGraphics().setColor(new Color(1, 0, 0, alpha));
		FontMetrics fm   = Application.getGraphical().getGraphics().getFontMetrics(Application.getGraphical().getSmallFont());
		Rectangle2D rect = fm.getStringBounds(text, Application.getGraphical().getGraphics());
		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= (int) Application.HEIGHT - 50;
		int panelWidth = (int) Application.WIDTH;
		int x = ((panelWidth  - textWidth));
		int y = ((panelHeight - textHeight) /16  + fm.getAscent());
		Application.getGraphical().getGraphics().setFont(Application.getGraphical().getSmallFont());
		Application.getGraphical().getGraphics().drawString(text, x, y);
		Application.getGraphical().getGraphics().setColor(Color.white);
		Application.getGraphical().getGraphics().setFont(Application.getGraphical().getLargeFont());
		}
		catch(NullPointerException e)
		{
			System.out.println("haha");
		}
		
	}
	}

