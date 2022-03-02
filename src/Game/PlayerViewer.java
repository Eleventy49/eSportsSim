package Game;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JOptionPane;

public class PlayerViewer {

	double statCounterD = 0;
	int statCounter = 0;
	Player tdd = null;
	public static int page = 1;
	ArrayList<Player> temp = new ArrayList<Player>();
	static int x = 1;

	public static void defaultdisplay() {
		for (int i = 0; i <= 10; i++) {

			x = ((page * 10) + i) - 11;
			try {
				if (!Database.playerdatabase.get(x).equals(null)) {
					if (Database.playerdatabase.get(x).wChampion)
						Application.g.setColor(Color.yellow);
					Application.g.drawString(Database.playerdatabase.get(x).name, 200, i * 60);
					Application.g.setColor(Color.white);
				}
				Application.g.drawString("" + page, 550, 700);
			} catch (IndexOutOfBoundsException e) {

			}
		}

	}

	public static void tournamentWins() {
		for (Player x : Database.playerdatabase) {
		}
	}

	public static void earnings() {
		for (Player x : Database.playerdatabase) {
		}
	}

	public static void increasePage() {
		if (((Database.playerdatabase.size() / 10.0)) % 1 == 0) {
			System.out.println("Its an even 10");
			if (page < (int) Database.playerdatabase.size() / 10) {
				page++;
			}
		}
		if (((Database.playerdatabase.size() / 10.0)) % 1 != 0) {
			System.out.println("Its not an even 10");
			if (page < (int) (Database.playerdatabase.size() / 10) + 1) {
				page++;

			}
		}
	}

	public static void decreasePage() {
		if (page >= 2) {
			page--;
		}
	}

	public static void render(Player i) {
		// Store the default color the application had before reaching this subroutine
				Color color = Application.getGame().getGraphics().getColor();

				// Set the current font to the small font
				Application.getGame().getGraphics().setFont(Application.bitoperatorfont13);

				// Set the color to white, and then draw the information associated with player
				// I
				// drawString2 is a method to draw a string to the screen with line splits.
				Application.getGame().getGraphics().setColor(Color.WHITE);
				try {
					Graphical.drawStringWithLineBreaks(i.toStringN(), 700, 100);
					Graphical.drawStringWithLineBreaks(i.toStringN2(), 700, 100);

				} catch (IndexOutOfBoundsException e) {

				}
				
				//Reset application color.
				Application.getGame().getGraphics().setColor(color);
	}

}
