package Game.buttons;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Game.ButtonInterface;
import Game.Application;
import Game.Application.STATE;

public class MainMenuPlayButton extends ScalingButton implements ButtonInterface, MouseListener {

	public MainMenuPlayButton() {
		super(new Rectangle(((Application.WIDTH / 2) - 50), 200, 100, 50), "New Game", Color.white, Color.gray,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.GameSelect, "New Game");

	}

	@Override
	public void scaleFunction() {
		bounds = new Rectangle(((Application.WIDTH / 2) - 50), 200, 100, 50);
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}

	public void mouseLeftClicked(MouseEvent e) {

		Application.prevState.add(Application.State);

		Music.mouseClick();
		Application.isInGame = false;
		Application.State = gameStateOnClick;
		// super.mouseLeftClicked();
	}

}
