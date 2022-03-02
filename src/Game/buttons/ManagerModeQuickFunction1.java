package Game.buttons;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.Bracket32;

import Game.ButtonInterface;
import Game.Database;
import Game.Application;
import Game.SimTournament;
import Game.Application.STATE;

public class ManagerModeQuickFunction1 extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModeQuickFunction1() {
		super(new Rectangle(1097, Bottom, 327, 50), "Quick Function 1", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode },
				Application.STATE.SpectatorQuickFunctionDefault, "SpectatorQuickFunction1");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.isInGame = true;

		Music.mouseClick();
		Bracket32.clearTournament();
		Application.prevState.add(Application.State);
		if (Application.State == Application.STATE.TournamentsWorldTournament
				|| Application.State == Application.STATE.Tournaments) {

			SimTournament.World(Database.teamdatabase);

		}
		Application.State = gameStateOnClick;

	}

}
