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
		Color current = g.getColor();
		//Draw the main splash text.
		g.setFont(Application.bitoperatorfont36);
		g.setColor(Color.WHITE);
		
		
		FontMetrics fm   = g.getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds("{Virtually} Pro MobA", g);

	//	int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
	//	int panelHeight= (int) bounds.getHeight();
		int panelWidth = (int) Application.WIDTH;

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2);
	//	int y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + bounds.y;
		
		
		Graphical.drawCenteredText("{Virtually} Pro MobA", x, 100, x, 100);
		g.setFont(Application.bitoperatorfont13);
		g.setColor(Color.red);
		Graphical.drawStringWithLineBreaks("For testing purposes this window is resizable\n Resizing the window will not work as expected", textWidth, 50);
		g.setColor(current);
	}
}
