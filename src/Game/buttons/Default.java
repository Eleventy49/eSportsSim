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

public class Default extends NormalButton implements ButtonInterface, MouseListener {

	public Default() {
		super(new Rectangle(1, 1, 100, 50), "TEXT", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Menu, "DEFAULT");
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
		Application.State = gameStateOnClick;
		Music.mouseClick();
	}

}
