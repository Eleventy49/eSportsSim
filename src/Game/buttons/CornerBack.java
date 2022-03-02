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

public class CornerBack extends SpecialButton implements ButtonInterface, MouseListener {

	public CornerBack() {
		super(new Rectangle(1, 56, 100, 50), "Back", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Menu, "CornerBackButton");
	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		Application.State = Application.prevState.get(Application.prevState.size() - 1);
		Application.prevState.remove(Application.prevState.size() - 1);
		Music.mouseClick();
	}

}
