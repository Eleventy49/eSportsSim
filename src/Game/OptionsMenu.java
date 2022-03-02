
package Game;

import java.awt.Color;

public class OptionsMenu {
	static int mousex = 300;
	static int mousex2 = 300;
	static float songVolume = 0;
	static float effectVolume = 0;
	public static void drawCenteredCircle(int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  Application.getGame().getGraphics().fillOval(x,y,r,r);
		}
	
	public static void render()
	{
		
		Application.getGame().getGraphics().drawString("Console Output [Developer Intended]", 430, 215);

	
	}
}
