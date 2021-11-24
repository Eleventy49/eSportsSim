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

public class ManagerModePlayers extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModePlayers() {
		super(new Rectangle(106, Bottom, 170, 50), "Players", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode }, Application.STATE.SpectatorModePlayers,
				"SpectatorPlayers");
	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		Music.mouseClick();
		Application.isInGame = true;
		Application.State = gameStateOnClick;
	}

}
