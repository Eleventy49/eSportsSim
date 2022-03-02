package Game.buttons;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.ButtonInterface;
import Game.Application;
import Game.Application.STATE;

public class ManagerModeTeams extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModeTeams() {
		super(new Rectangle(846, Bottom, 250, 50), "Teams", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode }, Application.STATE.SpectatorModeTeams,
				"SpectatorScoutTeams");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
		
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State);
		Music.mouseClick();
		Application.isInGame = true;
		Application.State = gameStateOnClick;
	}

}
