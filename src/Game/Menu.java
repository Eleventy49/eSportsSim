package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu {
	public static Color playColor = Color.white;
	public void render(Graphics g) {
		//Draw the main splash text.
		g.setFont(Application.getGame().getLargeFont());
		g.setColor(Color.WHITE);
		
		
		FontMetrics fm   = g.getFontMetrics(Application.getGame().getLargeFont());
		Rectangle2D rect = fm.getStringBounds("{Virtually} Pro Dota", g);

	//	int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
	//	int panelHeight= (int) bounds.getHeight();
		int panelWidth = (int) Application.WIDTH;

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2);
	//	int y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + bounds.y;
		
		
		g.drawString("{Virtually} Pro Dota", x, 100);
	}
}
