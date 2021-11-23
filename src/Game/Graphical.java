package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Game.Application.STATE;
import Game.buttons.NormalButton;
import Game.buttons.Slider;
import Game.buttons.SpecialButton;

public class Graphical extends Canvas {
	private static Font bitoperatorfont36;
	private static Font bitoperatorfont13;
	private static Graphics g;
	private BufferedImage background = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage player;
	private GraphicsEnvironment graphicsenvironment;
	public static int frameCounter = 0;
	private static float alpha = 1;
	private boolean CountingFrames = false;

	public void Graphical() {
		requestFocus();
		graphicsenvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try { // Get the font from the file and create one at 36 point and one at 13 point
			bitoperatorfont36 = Font
					.createFont(Font.TRUETYPE_FONT,
							getClass().getClassLoader().getResourceAsStream("8bitOperatorPlusSC-Regular.ttf"))
					.deriveFont(36f);
			graphicsenvironment.registerFont(getLargeFont());
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		try { // Get the font from the file and create one at 36 point and one at 13 point
			bitoperatorfont13 = Font
					.createFont(Font.TRUETYPE_FONT,
							getClass().getClassLoader().getResourceAsStream("8bitOperatorPlusSC-Regular.ttf"))
					.deriveFont(13f);
			graphicsenvironment.registerFont(bitoperatorfont13);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/SpriteSheet.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			background = loader.loadImage("/BackGround.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		BracketHandler.setGraphics(g);
	}

	// public BufferedImage getSpriteSheet() { //Get the spritesheet (That I have
	// yet to use.)
	// return spriteSheet;
	// }
	public void drawString2(String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	public static void drawViewPlayers(int i) {
		Player p = Database.playerdatabase.get((PlayerViewer.page * 10) - 11 + i);

		g.setFont(bitoperatorfont13);
		try {
			Application.getGraphical().drawString2(p.toStringN(), 700, 100);
			Application.getGraphical().drawString2(p.toStringN2(), 700, 100);
			g.setColor(Color.WHITE);
		} catch (IndexOutOfBoundsException e) {

		}
	}

	public static void drawViewPlayers(Player i) {
		g.setFont(bitoperatorfont13);
		try {
			Application.getGraphical().drawString2(i.toStringN(), 700, 100);
			Application.getGraphical().drawString2(i.toStringN2(), 700, 100);

			g.setColor(Color.WHITE);
		} catch (IndexOutOfBoundsException e) {

		}
	}

	public static void drawViewTeams(int i) {
		if (Application.viewingTeam == null)
			Application.viewingTeam = Database.teamdatabase.get((TeamViewer.page * 10) - 11 + i);

		g.setFont(bitoperatorfont36);
		try {
			Application.getGraphical().drawString2(Application.viewingTeam.toStringN(), 700, 100);
			g.setColor(Color.white);
			Application.getGraphical().drawString2(Application.viewingTeam.toStringN2(), 700, 150);

			for (int j = 0; j < Application.viewingTeam.roster.size(); j++) {
				Application.getGraphical().drawString2(Application.viewingTeam.toStringN3(j), 700, 550 + (50 * j));
				g.setColor(Color.white);
			}
		} catch (IndexOutOfBoundsException e) {

		}
	}
	
	public Graphics getGraphics() {
		return g;
	}

	public static void drawViewTeams(Team i) {
		g.setFont(bitoperatorfont13);
		try {
			Application.getGraphical().drawString2(i.toString(), 700, 100);
		} catch (IndexOutOfBoundsException e) {

		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // Establishing the bufferimage strategy
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics(); // Making sure the graphics environment is in check.
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this); // The background is always the first thing drawn
																		// to the screen.
		g.setFont(getLargeFont()); // Set the default font and color
		g.setColor(Color.WHITE);

		for (Object x : Application.buttons.getCollection()) {
			NormalButton y = null;
			Slider z = null;
			if (x instanceof NormalButton) {
				y = (NormalButton) x;
				y.draw(g, (Graphics2D) g);
			}
			if (x instanceof SpecialButton) {
				y = (SpecialButton) x;
				y.draw(g, (Graphics2D) g);
			}
			if (x instanceof Slider) {
				z = (Slider) x;
				z.draw(g, (Graphics2D) g);
			}
			// if(gameStateIsPartOf(Game.State, y.prereq))
			;
		}

		if (Application.State == STATE.ManagerMode) {
			//////// Image zone

			// g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

			// p.render(g);
			// Render the Text in the main menu
		} else if (Application.State == STATE.Menu) {
			Application.getMenu().render(g); // Render the menu
		}
		// Draw Buttons

		if (Application.State == STATE.SpectatorModeAddPlayer) {
			AddPlayer.AddPlayer(g);
		}
		if (Application.State == STATE.Options) {
			OptionsMenu.render();
		}
		if (Application.State == STATE.SpectatorModeAddTeam) {
			AddTeam.Team(g);
		}
		if (Application.State == STATE.Tournaments) {

			BracketHandler.setGraphics(g);
			Bracket32.clearTournament();
			Bracket16.clearTournament();
			Application.runTournament = true;

		}
		if ((Application.State == STATE.TournamentsWorldTournament)) {

			if (Application.runTournament) {
				Bracket32.clearTournament();
				BracketHandler.setGraphics(g);
				// SimTournament.World(Database.teamdatabase);
				Application.runTournament = false;
				Bracket32.printBracket();
			} else {
				Bracket32.load();
				Bracket32.printBracket();
			}

		}

		if ((Application.State == STATE.TournamentsMajorTournament)) {

			if (Application.runTournament) {
				Bracket16.clearTournament();
				BracketHandler.setGraphics(g);
				// SimTournament.Major(Database.getStrongBois(16));
				Application.runTournament = false;
				Bracket16.printBracket();
			} else {
				Bracket16.load();
				Bracket16.printBracket();
			}

		}
		if ((Application.State == STATE.TournamentsMinor4Tournament)) {

			if (Application.runTournament) {
				Bracket4.clearTournament();
				BracketHandler.setGraphics(g);
				// SimTournament.Minor4(Database.getWeakBois(4));
				Application.runTournament = false;
				Bracket4.printBracket();
			} else {
				Bracket4.load();
				Bracket4.printBracket();
			}

		}

		if ((Application.State == STATE.TournamentsMinor8Tournament)) {

			if (Application.runTournament) {
				Bracket8.clearTournament();
				BracketHandler.setGraphics(g);
				// SimTournament.Minor8(Database.getWeakBois(8));
				Application.runTournament = false;
				Bracket8.printBracket();
			} else {
				Bracket8.load();
				Bracket8.printBracket();
			}

		}
		if (Application.State == STATE.SpectatorModeViewPlayer) {
			PlayerViewer.defaultdisplay();
		}
		if (Application.State == STATE.SpectatorModeViewPlayerB) {
			drawViewPlayers(Application.ourversionofi);
		}

		if (Application.State == STATE.SpectatorModeViewTeam) {
			TeamViewer.defaultdisplay();
		}
		if (Application.State == STATE.SpectatorModeViewTeamB) {
			drawViewTeams(Application.ourversionofi);
		}

		if (Application.State == STATE.SpectatorModeViewTop10Team) {
			TeamTenTeams.render();
			TeamTenTeams.clear();
		}
		if (Application.State == STATE.SpectatorModeViewTop10Player) {
			TeamTenPlayers.render();
			TeamTenPlayers.clear();
		}

		if (Application.WarningQuery) {
			CountingFrames = true;

			if (CountingFrames) {
				frameCounter++;
				alpha = 1 - (float) (0.01 * frameCounter);
				new WarningText(Application.WarningMessage, alpha);
			}
			if (frameCounter == 100) {
				Application.WarningQuery = false;
				Application.WarningMessage = "";
				frameCounter = 0;
			}

		}

		Timer.render();
		NotificationHandler.render();
		///////
		// windowSize = frame.getSize();
		// WIDTH = windowSize.width;
		// HEIGHT = windowSize.height;
		g.dispose(); // We are done drawing this frame.
		bs.show(); // Display the frame.

	}

	public static void drawCenteredText(String txt, int x1, int y1, int x2, int y2) {
		FontMetrics fm = g.getFontMetrics(bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(txt, g);

		int textHeight = y1;
		int textWidth = x2;
		int panelHeight = y2;
		int panelWidth = x1;

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2);
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent());

		g.drawString(txt, x, y); // Draw the string.
	}

	public void drawButton(NormalButton button) {
		if(Application.gameStateIsPartOf(Application.State, button.prereq))
		{
		Graphics2D g2d = (Graphics2D) g;
		if (button.hovered) {
			g.setColor(button.getHoveredColor());
		} else {
			g.setColor(button.getDefaultcolor());
		}

		// Actually do the drawing to the screen

		FontMetrics fm = g.getFontMetrics(bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(button.txt, g);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());
		int panelHeight = (int) button.bounds.getHeight();
		int panelWidth = (int) button.bounds.getWidth();

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

		g.drawString(button.txt, x, y); // Draw the string.
		g2d.draw(button.bounds);
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);	
		}

	}

	public static void drawButton(SpecialButton button) {
		if(!Application.gameStateIsPartOf(Application.State, button.prereq))
		{
		Graphics2D g2d = (Graphics2D) g;
		if (button.hovered) {
			g.setColor(button.getHoveredColor());
		} else {
			g.setColor(button.getDefaultcolor());
		}

		// Actually do the drawing to the screen

		FontMetrics fm = g.getFontMetrics(bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(button.txt, g);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());
		int panelHeight = (int) button.bounds.getHeight();
		int panelWidth = (int) button.bounds.getWidth();

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2) + button.bounds.x;
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent()) + button.bounds.y;

		g.drawString(button.txt, x, y); // Draw the string.
		g2d.draw(button.bounds);
		}
	}

	public void resetAlpha() {
		alpha = 1;
	}

	public Font getLargeFont() {
		return bitoperatorfont36;
	}
	public Font getSmallFont() {
		return bitoperatorfont13;
	}

	public void setFrameCounter(int i) {
		frameCounter = i;
	}

}
