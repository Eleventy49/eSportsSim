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
import Game.buttons.Slider;
import Game.buttons.SpecialButton;

public class Graphical{

	public static void drawViewPlayers(Player i) {
		Color color = Application.g.getColor();
		Application.g.setFont(Application.bitoperatorfont13);
		System.out.println(i);
		try {
			drawString2(i.toStringN(), 700, 100);
			drawString2(i.toStringN2(), 700, 100);

			Application.g.setColor(Color.WHITE);
		} catch (IndexOutOfBoundsException e) {

		}
		Application.g.setColor(color);
	}

	public static void drawString2(String text, int x, int y) {
		Color color = Application.g.getColor();
		//Application.g.setFont(Application.bitoperatorfont36);
		for (String line : text.split("\n"))
			Application.g.drawString(line, x, y += Application.g.getFontMetrics().getHeight());
		Application.g.setColor(color);
	}
	
	public static void drawViewTeams(int i) {
		Color color = Application.g.getColor();
		if (Application.viewingTeam == null)
			Application.viewingTeam = Database.teamdatabase.get((TeamViewer.page * 10) - 11 + i);

		Application.g.setFont(Application.bitoperatorfont36);
		Application.g.setColor(Color.white);
		try {
			drawString2(Application.viewingTeam.toStringN(), 700, 100);
			Application.g.setColor(Color.white);
			drawString2(Application.viewingTeam.toStringN2(), 700, 150);

			for (int j = 0; j < Application.viewingTeam.roster.size(); j++) {
				drawString2(Application.viewingTeam.toStringN3(j), 700, 550 + (50 * j));
				Application.g.setColor(Color.white);
			}
		} catch (IndexOutOfBoundsException e) {

		}
		Application.g.setColor(color);
	}

	public static void drawViewTeams(Team i) {
		Color color = Application.g.getColor();
		Application.g.setFont(Application.bitoperatorfont13);
		try {
			drawString2(i.toString(), 700, 100);
		} catch (IndexOutOfBoundsException e) {

		}
		Application.g.setColor(color);
	}

	public void render() {
		BufferStrategy bs = Application.game.getBufferStrategy(); // Establishing the bufferimage strategy
		if (bs == null) {
			Application.game.createBufferStrategy(3);
			return;
		}

		Application.g = bs.getDrawGraphics(); // Making sure the graphics environment is in check.
		Application.g.drawImage(Application.background, 0, 0, Application.WIDTH, Application.HEIGHT, Application.getImageObserver()); // The background is always the first thing drawn
																		// to the screen.
		Application.g.setFont(getLargeFont()); // Set the default font and color
		Application.g.setColor(Color.WHITE);
		for (Object x : Application.buttons.getCollection()) {
			NormalButton y = null;
			SpecialButton w = null;
			Slider z = null;
			//System.out.println(x);
			if (x instanceof SpecialButton) {
				//System.out.println("SpecialButton");
				w = (SpecialButton) x;
				w.draw();
			}
			else if (x instanceof NormalButton) {
				//System.out.println("NormalButton");
				y = (NormalButton) x;
				y.draw();
			}
			
			else if (x instanceof Slider) {
				//System.out.println("SliderButton");
				z = (Slider) x;
				z.draw(Application.g, (Graphics2D) Application.g);
			}
		}
		Timer.render();
		NotificationHandler.render();
			// if(gameStateIsPartOf(Game.State, y.prereq))
			
		if (Application.State == STATE.ManagerMode) {
			//////// Image zone

			// g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

			// p.render(g);
			// Render the Text in the main menu
		} else if (Application.State == STATE.Menu) {
			Application.getMenu().render(Application.g); // Render the menu
		}
		// Draw Buttons

		if (Application.State == STATE.SpectatorModeAddPlayer) {
			AddPlayer.AddPlayer(Application.g);
		}
		if (Application.State == STATE.Options) {
			OptionsMenu.render();
		}
		if (Application.State == STATE.SpectatorModeAddTeam) {
			AddTeam.Team(Application.g);
		}
		if (Application.State == STATE.Tournaments) {

			BracketHandler.setGraphics(Application.g);
			Bracket32.clearTournament();
			Bracket16.clearTournament();
			Application.runTournament = true;

		}
		if ((Application.State == STATE.TournamentsWorldTournament)) {

			if (Application.runTournament) {
				Bracket32.clearTournament();
				BracketHandler.setGraphics(Application.g);
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
				BracketHandler.setGraphics(Application.g);
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
				BracketHandler.setGraphics(Application.g);
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
				BracketHandler.setGraphics(Application.g);
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
			drawViewPlayers(Application.trackingPlayer);
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
		// windowSize = frame.getSize();
		// WIDTH = windowSize.width;
		// HEIGHT = windowSize.height;
		
		
		Application.g.dispose(); // We are done drawing this frame.
		bs.show(); // Display the frame.

	}

	public static void drawCenteredText(String txt, int x1, int y1, int x2, int y2) {
		Color color = Application.g.getColor();
		FontMetrics fm = Application.g.getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(txt, Application.g);

		int textHeight = y1;
		int textWidth = x2;
		int panelHeight = y2;
		int panelWidth = x1;

		// Center text horizontally and vertically
		int x = ((panelWidth - textWidth) / 2);
		int y = ((panelHeight - textHeight) / 2 + fm.getAscent());

		Application.g.drawString(txt, x, y); // Draw the string.
		Application.g.setColor(color);
	}

	public void drawButton(NormalButton button) {
		Color color = Application.g.getColor();
		if(Application.gameStateIsPartOf(Application.State, button.prereq))
		{
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
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);	
		}

	}

	public void drawButton(SpecialButton button) {
		Color color = Application.g.getColor();
		if(!Application.gameStateIsPartOf(Application.State, button.prereq))
		{
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
}
