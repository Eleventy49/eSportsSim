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

public class ManagerModeQuickFunction2 extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModeQuickFunction2() {
		super(new Rectangle(1425, Bottom, 335, 50), "Quick Function 2", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode },
				Application.STATE.ManagerQuickFunctionDefault, "SpectatorQuickFunction2");

	}

	@Override
	public void init() {
		Application.getGraphical().addMouseListener(this);

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
