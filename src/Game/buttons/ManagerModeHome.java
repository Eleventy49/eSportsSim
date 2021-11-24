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

public class ManagerModeHome extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModeHome() {
		super(new Rectangle(0, Bottom, 105, 50), "Home", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode }, Application.STATE.ManagerMode,
				"SpectatorHome");

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
