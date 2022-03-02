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

public class CornerMenu extends SpecialButton implements ButtonInterface, MouseListener {

	public CornerMenu() {
		super(new Rectangle(1, 6, 100, 50), "Menu", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Menu, "CornerMenuButton");
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

		Application.isInGame = false;
		Music.mouseClick();
		Application.State = gameStateOnClick;
	}

}
