package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;

import Game.Application.STATE;
import Game.buttons.NormalButton;
import Game.buttons.ScalingButton;
import Game.buttons.Slider;
import Game.buttons.SpecialButton;

public class Graphical {

	//Draws strings with line splits
	public static void drawStringWithLineBreaks(String text, int x, int y) {
		//Store default application color
		Color color = Application.getGame().getGraphics().getColor();
		
		//Splits the string by newlines and draws each with a vertical offset from the previous
		for (String line : text.split("\n"))
			Application.getGame().getGraphics().drawString(line, x,
					y += Application.getGame().getGraphics().getFontMetrics().getHeight());
		
		//Restore default application color.
		Application.getGame().getGraphics().setColor(color);
	}

	Runnable render = new Runnable() {
		public void run() {
		BufferStrategy bs = Application.game.getBufferStrategy(); // Establishing the bufferimage strategy
		if (bs == null) {
			Application.game.createBufferStrategy(3);
			return;
		}
		Application.getGame().setGraphics(bs.getDrawGraphics());
		Application.getGame().getGraphics().drawImage(Application.background, 0, 0, Application.WIDTH,
				Application.HEIGHT, Application.getImageObserver()); // The background is always the first thing drawn
		// to the screen.
		Application.getGame().getGraphics().setFont(getLargeFont()); // Set the default font and color
		Application.getGame().getGraphics().setColor(Color.WHITE);
		for (Object x : Application.buttons.getCollection()) {
			NormalButton y = null;
			SpecialButton w = null;
			ScalingButton a = null;
			Slider z = null;
			// System.out.println(x);
			if (x instanceof SpecialButton) {
				// System.out.println("SpecialButton");
				w = (SpecialButton) x;
				w.draw();
			} else if (x instanceof NormalButton) {
				// System.out.println("NormalButton");
				y = (NormalButton) x;
				y.draw();
			}
			else if (x instanceof ScalingButton) {
				// System.out.println("NormalButton");
				a = (ScalingButton) x;
				a.draw();
			}

			else if (x instanceof Slider) {
				// System.out.println("SliderButton");
				z = (Slider) x;
				z.draw(Application.g, (Graphics2D) Application.g);
			}
		}
		Timer.render();
		NotificationHandler.render();
		StateTracker.render();
		if (Application.WarningQuery) {
			Application.CountingFrames = true;

			if (Application.CountingFrames) {
				Application.frameCounter++;
				Application.alpha = 1 - (float) (0.01 * Application.frameCounter);
				new WarningText(Application.WarningMessage, Application.alpha);
			}
			if (Application.frameCounter == 100) {
				Application.WarningQuery = false;
				Application.WarningMessage = "";
				Application.frameCounter = 0;
			}

		}

		///////
	     Application.windowSize = Application.frame.getSize();
		 Application.WIDTH = Application.windowSize.width;
		 Application.HEIGHT = Application.windowSize.height;

		Application.getGame().getGraphics().dispose(); // We are done drawing this frame.
		bs.show(); // Display the frame.

		}
	};

	public static void drawCenteredText(String txt, int x1, int y1, int x2, int y2) {
		Color color = Application.getGame().getGraphics().getColor();
		FontMetrics fm = Application.getGame().getGraphics().getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(txt, Application.g);

		int textHeight = y1;
		int textWidth = x2;
		int panelHeight = y2;
		int panelWidth = x1;

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2);
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent());

		Application.getGame().getGraphics().drawString(txt, x, y); // Draw the string.
		Application.getGame().getGraphics().setColor(color);
	}

	public void drawButton(NormalButton button) {
		Color color = Application.getGame().getGraphics().getColor();
		if (Application.gameStateIsPartOf(Application.State, button.prereq)) {
			Graphics2D g2d = (Graphics2D) Application.g;
			if (button.hovered) {
				Application.getGame().getGraphics().setColor(button.getHoveredColor());
			} else {
				Application.getGame().getGraphics().setColor(button.getDefaultcolor());
			}

			// Actually do the drawing to the screen

			FontMetrics fm = Application.getGame().getGraphics().getFontMetrics(Application.bitoperatorfont36);
			Rectangle2D rect = fm.getStringBounds(button.txt, Application.g);

			int textHeight = (int) (rect.getHeight());
			int textWidth = (int) (rect.getWidth());
			int panelHeight = (int) button.bounds.getHeight();
			int panelWidth = (int) button.bounds.getWidth();

			// Center text horizontally and vertically
			int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
			int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

			Application.getGame().getGraphics().drawString(button.txt, x, y); // Draw the string.
			g2d.draw(button.bounds);
			Application.getGame().getGraphics().setColor(color);
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);	
		}

	}

	public void init() {
		BufferStrategy bs = Application.getGame().getBufferStrategy();
		if (bs == null) {
			Application.game.createBufferStrategy(3);
		}
		bs = Application.getGame().getBufferStrategy();
		Application.getGame().setGraphics(bs.getDrawGraphics());
	}

	public void drawButton(SpecialButton button) {
		Color color = Application.g.getColor();
		if (!Application.gameStateIsPartOf(Application.State, button.prereq)) {
			Graphics2D g2d = (Graphics2D) Application.g;
			if (button.hovered) {
				Application.g.setColor(button.getHoveredColor());
			} else {
				Application.g.setColor(button.getDefaultcolor());
			}

			// Actually do the drawing to the screen

			FontMetrics fm = Application.g.getFontMetrics(Application.bitoperatorfont36);
			Rectangle2D rect = fm.getStringBounds(button.txt, Application.g);

			int textHeight = (int) (rect.getHeight());
			int textWidth = (int) (rect.getWidth());
			int panelHeight = (int) button.bounds.getHeight();
			int panelWidth = (int) button.bounds.getWidth();

			// Center text horizontally and vertically
			int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
			int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

			Application.g.drawString(button.txt, x, y); // Draw the string.
			g2d.draw(button.bounds);
			Application.g.setColor(color);
		}
	}

	public static void resetAlpha() {
		Application.alpha = 1;
	}

	public Font getLargeFont() {
		return Application.bitoperatorfont36;
	}

	public Font getSmallFont() {
		return Application.bitoperatorfont13;
	}

	public static void setFrameCounter(int i) {
		Application.frameCounter = i;
	}

	public void drawButton(ScalingButton button) {
		Color color = Application.getGame().getGraphics().getColor();
		if (Application.gameStateIsPartOf(Application.State, button.prereq)) {
			Graphics2D g2d = (Graphics2D) Application.g;
			if (button.hovered) {
				Application.getGame().getGraphics().setColor(button.getHoveredColor());
			} else {
				Application.getGame().getGraphics().setColor(button.getDefaultcolor());
			}

			// Actually do the drawing to the screen

			FontMetrics fm = Application.getGame().getGraphics().getFontMetrics(Application.bitoperatorfont36);
			Rectangle2D rect = fm.getStringBounds(button.txt, Application.g);

			int textHeight = (int) (rect.getHeight());
			int textWidth = (int) (rect.getWidth());
			int panelHeight = (int) button.bounds.getHeight();
			int panelWidth = (int) button.bounds.getWidth();

			// Center text horizontally and vertically
			int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
			int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

			Application.getGame().getGraphics().drawString(button.txt, x, y); // Draw the string.
			g2d.draw(button.bounds);
			Application.getGame().getGraphics().setColor(color);
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);	
		
	}
}
}
