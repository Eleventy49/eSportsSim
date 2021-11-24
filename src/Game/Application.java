/**
 * @author Eleventy 49
 * @version Alpha 0.5
 * 
 * Handles the initialization of the game loop, threading, and interfacing with other classes.
 */
package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Game.Application.STATE;
import Game.buttons.NormalButton;
import Game.buttons.OptionsConsoleEnable;
import Game.buttons.Slider;
import Game.buttons.SpecialButton;

public class Application extends Canvas implements Runnable {

	private static final long serialVersionUID = 1;
	public static int WIDTH = 1760;
	public static int HEIGHT = WIDTH / 16 * 9;
	public final String TITLE = "{Virtually} Pro Dota";
	private boolean running = false;
	private Thread thread;
	static JFrame frame;
	Dimension windowSize;
	static Application game;
	static Font bitoperatorfont36;
	static Font bitoperatorfont13;
	static Graphics g;
	private BufferedImage background = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage player;
	private GraphicsEnvironment graphicsenvironment;
	public static int frameCounter = 0;
	private static float alpha = 1;
	private boolean CountingFrames = false;

	private static Menu menu;
	public static ArrayList<ActionListener> garbage = new ArrayList<ActionListener>();

	public MusicHandler Music = new MusicHandler();

	public static Cursor mouseCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	public static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
	public static Cursor pointerCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	static boolean runTournament = true;
	static boolean gameLoaded = false;
	public static boolean WarningQuery;
	public static String WarningMessage;
	public static boolean ConsoleOutput = true;
	public static boolean isInGame = false;
	public static boolean SpectatorMode;
	public static boolean isMusic = false;
	public static ButtonCollection buttons;

	public static Menu getMenu() {
		return menu;
	}

	public static enum STATE {
		Menu, ManagerMode, Options, Credits, Tournaments, GameSelect, SpectatorMode, LoadGame, SpectatorModePlayers,
		SpectatorModeAddPlayer, Exit, SpectatorModeTeams, SpectatorModeAddTeam, TournamentsWorldTournament,
		SpectatorModeViewPlayer, SpectatorModeViewPlayerB, SpectatorModeViewTeam, SpectatorModeViewTeamB,
		SpectatorModeViewTop10Team, SpectatorModeViewTop10TeamB, SpectatorModeViewTop10Player,
		SpectatorModeViewTop10PlayerB, SpectatorQuickFunctionDefault, Tutorial, ManagerModePlayers,
		ManagerModeAddPlayer, ManagerModeTeams, ManagerModeAddTeam, ManagerModeViewTeamB, ManagerModeViewTop10PlayerB,
		ManagerModeViewTop10Team, ManagerQuickFunctionDefault, ManagerModeViewPlayer, ManagerModeViewPlayerB,
		ManagerModeViewTop10TeamB, ManagerModeViewTeam, TournamentsMajorTournament, TournamentsMinor4Tournament,
		TournamentsMinor8Tournament;
	}

	public static ArrayList<Application.STATE> prevState = new ArrayList<Application.STATE>();
	public static STATE State = STATE.Menu;
	public static ArrayList<STATE> SpectatorStates = new ArrayList<STATE>() {
		{
			add(STATE.SpectatorMode);
			add(STATE.SpectatorModePlayers);
			add(STATE.SpectatorModeAddPlayer);
			add(STATE.SpectatorModeTeams);
			add(STATE.SpectatorModeAddTeam);
			add(STATE.TournamentsWorldTournament);
			add(STATE.SpectatorModeViewPlayer);
			add(STATE.SpectatorModeViewPlayerB);
			add(STATE.SpectatorModeViewTeam);
			add(STATE.SpectatorModeViewTeamB);
			add(STATE.SpectatorModeViewTop10Team);
			add(STATE.SpectatorModeViewTop10TeamB);
			add(STATE.SpectatorModeViewPlayer);
			add(STATE.SpectatorModeViewTop10PlayerB);
			add(STATE.SpectatorQuickFunctionDefault);
			add(STATE.SpectatorModeViewTop10Player);
			add(STATE.Tournaments);
		}
	};

	public static ArrayList<STATE> ManagerStates = new ArrayList<STATE>() {
		{
			add(STATE.ManagerMode);
			add(STATE.ManagerModePlayers);
			add(STATE.ManagerModeAddPlayer);
			add(STATE.ManagerModeTeams);
			add(STATE.ManagerModeAddTeam);
			add(STATE.TournamentsWorldTournament);
			add(STATE.ManagerModeViewPlayer);
			add(STATE.ManagerModeViewPlayerB);
			add(STATE.ManagerModeViewTeam);
			add(STATE.ManagerModeViewTeamB);
			add(STATE.ManagerModeViewTop10Team);
			add(STATE.ManagerModeViewTop10TeamB);
			add(STATE.ManagerModeViewPlayer);
			add(STATE.ManagerModeViewTop10PlayerB);
			add(STATE.ManagerQuickFunctionDefault);
		}
	};

	public static ArrayList<Application.STATE> MenuStates = new ArrayList<STATE>() {
		{
			add(STATE.Menu);
			add(STATE.Options);
			add(STATE.Credits);
			add(STATE.Tutorial);
			add(STATE.Tutorial);
			add(STATE.GameSelect);
		}
	};
	public static int ourversionofi;
	public static Team viewingTeam = null;

	// Initializing all the things.
	public static boolean gameStateIsPartOf(STATE single, ArrayList<STATE> collection) {
		boolean temp = false;
		for (STATE x : collection) {
			if (x == single)
				temp = true;
		}

		return temp;
	}

	public static boolean gameStateIsPartOf(STATE single, STATE[] collection) {
		boolean temp = false;
		for (STATE x : collection) {
			if (x == single)
				temp = true;
		}

		return temp;
	}

	public static Application getGame() {
		return game;
	}

	public void init() {
		buttons = new ButtonCollection();
		for (Object x : Application.buttons.getCollection()) {
			NormalButton y = null;
			SpecialButton w = null;
			Slider z = null;
			if (x instanceof NormalButton) {
				y = (NormalButton) x;
				y.init();
			} else
			if (x instanceof SpecialButton) {
				w = (SpecialButton) x;
				w.init();
			}
			if (x instanceof Slider) {
				z = (Slider) x;
				z.init();
			}
			
			// if(gameStateIsPartOf(Game.State, y.prereq))
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
		menu = new Menu();
		BracketHandler.setGraphics(g);
		addKeyListener(new KeyInput(this));
	}

	public static void drawString2(String text, int x, int y) {
		Color color = g.getColor();
		g.setFont(bitoperatorfont36);
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
		g.setColor(color);
	}
	public void init2() {
		Music.init();
		Music.defaultSong();
		Timer.init();
	}

	// Start the game thread.
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	// Stop the game thread.
	private synchronized void stop() {
		Filehandler.Save(Database.playerdatabase, MusicHandler.songgainControl.getValue(),
				MusicHandler.effectsgainControl.getValue(), Database.teamdatabase, TournamentScheduler.collection);
		System.out.println("We tried to save them");
		frame.dispose();
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		frame.dispose();
		System.exit(1);

	}

	// The big launch function for the game.
	public static void main(String args[]) {

		game = new Application(); // Actually create an object game
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Doing some things with the size of the window
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame(game.TITLE); // Defining the window and making it visible.
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(new ImageIcon("res/logo.png").getImage());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start(); // Start the game thread.
	}

	// Literally only exists in the event that I need it in the future. In fact this
	// will probably be when I acutally implement a time system.
	private void tick() {
		if (State == STATE.ManagerMode) {

		}
		if (gameLoaded) {
			isMusic = Music.checkPlaying();

			// if(!isMusic && ButtonCollection.PlayPause.txt.equals("||"))
			// Music.next();
		}
		if (State == STATE.Exit) {
			running = false;
		}
		Timer.tick();
		if (State != STATE.SpectatorModeViewTeamB) {
			viewingTeam = null;
		}

	}

	// Only exists as a framework for when I decide to add hotkeys.
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {

		} else if (key == KeyEvent.VK_LEFT) {

		} else if (key == KeyEvent.VK_DOWN) {

		} else if (key == KeyEvent.VK_UP) {

		}
	}

	// Only exists as a framework for when I decide to add hotkeys.
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {

		} else if (key == KeyEvent.VK_LEFT) {

		} else if (key == KeyEvent.VK_DOWN) {

		} else if (key == KeyEvent.VK_UP) {

		}
	}

	// The main render method.

	public void run() {
		init();
		init2();// Initialize all the shit.
		long lastTime = System.nanoTime(); // Set the time in nanoseconds
		final double amountOfTicks = 60.0; // Set the number of ticks that we will run in a second.
		double ns = 1000000000 / amountOfTicks; // Some number that is basically nanoseconds between frames/ticks.
		double delta = 0; // Difference between system nanotime and when we should process frames and
							// ticks.
		int updates = 0; // Keep track of ticks since last console display message.
		int frames = 0; // Keep track of frames rendered since last console display message
		long notTimer = System.currentTimeMillis();
		long timer = System.currentTimeMillis(); // Millisecond timer for console output
		render();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // Calculate the delta
			lastTime = now; // Set the last nanotime we calculated with
			if (delta >= 1) { // If we should render a frame and all that jazz.

				updates++;

				tick();
				render();

				delta--;

				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) { // Every second
				timer += 1000;

				System.out.println(updates + " Ticks, FPS " + frames); // Output ticks per second and frames per second.

				updates = 0;
				frames = 0;

			}

			if ((timer - notTimer) > 10000) {
				Application.gameLoaded = true;
			}

		}

		stop(); // Stop when we are out of the loop and no longer running

	}
	public static void drawViewPlayers(int i) {
		Color color = g.getColor();
		Player p = Database.playerdatabase.get((PlayerViewer.page * 10) - 11 + i);

		g.setFont(bitoperatorfont13);
		try {
			drawString2(p.toStringN(), 700, 100);
			drawString2(p.toStringN2(), 700, 100);
			g.setColor(Color.WHITE);
		} catch (IndexOutOfBoundsException e) {

		}
		g.setColor(color);
	}

	public static void drawViewPlayers(Player i) {
		Color color = g.getColor();
		g.setFont(bitoperatorfont13);
		try {
			drawString2(i.toStringN(), 700, 100);
			drawString2(i.toStringN2(), 700, 100);

			g.setColor(Color.WHITE);
		} catch (IndexOutOfBoundsException e) {

		}
		g.setColor(color);
	}

	public static void drawViewTeams(int i) {
		Color color = g.getColor();
		if (Application.viewingTeam == null)
			Application.viewingTeam = Database.teamdatabase.get((TeamViewer.page * 10) - 11 + i);

		g.setFont(bitoperatorfont36);
		g.setColor(Color.white);
		try {
			drawString2(Application.viewingTeam.toStringN(), 700, 100);
			g.setColor(Color.white);
			drawString2(Application.viewingTeam.toStringN2(), 700, 150);

			for (int j = 0; j < Application.viewingTeam.roster.size(); j++) {
				drawString2(Application.viewingTeam.toStringN3(j), 700, 550 + (50 * j));
				g.setColor(Color.white);
			}
		} catch (IndexOutOfBoundsException e) {

		}
		g.setColor(color);
	}

	public static void drawViewTeams(Team i) {
		Color color = g.getColor();
		g.setFont(bitoperatorfont13);
		try {
			drawString2(i.toString(), 700, 100);
		} catch (IndexOutOfBoundsException e) {

		}
		g.setColor(color);
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
				z.draw(g, (Graphics2D) g);
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

		
		///////
		// windowSize = frame.getSize();
		// WIDTH = windowSize.width;
		// HEIGHT = windowSize.height;
		
		
		g.dispose(); // We are done drawing this frame.
		bs.show(); // Display the frame.

	}

	public static void drawCenteredText(String txt, int x1, int y1, int x2, int y2) {
		Color color = g.getColor();
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
		g.setColor(color);
	}

	public void drawButton(NormalButton button) {
		Color color = g.getColor();
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
		g.setColor(color);
//			g.drawString(txt, bounds.x + 5, bounds.y + 40);	
		}

	}

	public void drawButton(SpecialButton button) {
		Color color = g.getColor();
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
		g.setColor(color);
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
