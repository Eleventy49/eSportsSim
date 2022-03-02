package Game.buttons;

import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.ButtonInterface;
import Game.Application;

public class PlayPause extends SpecialButton implements ButtonInterface, MouseListener {

	public PlayPause() {
		super(new Rectangle(Application.WIDTH - 100, 6, 50, 50), "||", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Exit }, Application.STATE.Menu, "DEFAULT");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
		;
	}

	public void mouseLeftClicked(MouseEvent e) {
		Music.playpause();
		Music.mouseClick();
	}
}
