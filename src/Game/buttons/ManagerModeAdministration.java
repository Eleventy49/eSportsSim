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

public class ManagerModeAdministration extends NormalButton implements ButtonInterface, MouseListener {
	final static int Bottom = Application.HEIGHT - 41;

	public ManagerModeAdministration() {
		super(new Rectangle(538, Bottom, 307, 50), "Administration", Color.red, Color.GRAY,
				new Application.STATE[] { Application.STATE.ManagerMode }, Application.STATE.ManagerMode,
				"SpectatorAdministration");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}

}
