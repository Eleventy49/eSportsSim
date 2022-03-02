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

public class PreviousSong extends SpecialButton implements ButtonInterface, MouseListener {

	public PreviousSong() {
		super(new Rectangle(Application.WIDTH - 150, 6, 50, 50), "<|", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Exit }, Application.STATE.Menu, "PreviousSong");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
		
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		Music.prev();
		Music.mouseClick();

	}
}
