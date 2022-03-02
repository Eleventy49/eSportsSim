package Game.buttons;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.logging.FileHandler;

import Game.ButtonInterface;
import Game.Database;
import Game.Application;
import Game.Application.STATE;
import Game.Filehandler;
import Game.MusicHandler;
import Game.TournamentScheduler;

public class MainMenuSave extends NormalButton implements ButtonInterface, MouseListener {
	public MainMenuSave() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 500, 100, 50), "Save Game", Color.red, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Menu, "MainMenuSaveGame");
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.isInGame = false;
		Application.WarningQuery = true;
		Application.WarningMessage = "Saved the Game";
		try {
			Filehandler.Save(Database.playerdatabase, MusicHandler.songgainControl.getValue(),
					MusicHandler.effectsgainControl.getValue(), Database.teamdatabase, TournamentScheduler.collection);
		} catch (NullPointerException ebb) {
			Application.WarningQuery = true;
			Application.WarningMessage = "There is no game to save";
		}

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
		;
	}

}
