package Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import sun.*;

public class WarningText{

	public WarningText(String text, float alpha)
	{
		try {
	    
		Application.g.setColor(new Color(1, 0, 0, alpha));
		FontMetrics fm   = Application.g.getFontMetrics(Application.bitoperatorfont13);
		Rectangle2D rect = fm.getStringBounds(text, Application.g);
		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= (int) Application.HEIGHT - 50;
		int panelWidth = (int) Application.WIDTH;
		int x = ((panelWidth  - textWidth));
		int y = ((panelHeight - textHeight) /16  + fm.getAscent());
		Application.g.setFont(Application.bitoperatorfont13);
		Application.g.drawString(text, x, y);
		Application.g.setColor(Color.white);
		Application.g.setFont(Application.bitoperatorfont36);
		}
		catch(NullPointerException e)
		{
			System.out.println("haha");
		}
		
	}
	}

