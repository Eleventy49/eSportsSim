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
import java.awt.image.ImageObserver;
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
	public static Font bitoperatorfont36;
	public static Font bitoperatorfont13;
	static Graphics g;
	static BufferedImage background = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage player;
	private GraphicsEnvironment graphicsenvironment;
	public static int frameCounter = 0;
	static float alpha = 1;
	static boolean CountingFrames = false;

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
	public static Graphical graphical = new Graphical();

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
	public static Player trackingPlayer;

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
				graphicsenvironment.registerFont(bitoperatorfont36);
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
		graphical.render();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // Calculate the delta
			lastTime = now; // Set the last nanotime we calculated with
			if (delta >= 1) { // If we should render a frame and all that jazz.

				updates++;

				tick();
				graphical.render();

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

	public static ImageObserver getImageObserver() {
		return game;
	}


}
