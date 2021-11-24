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

public class MainMenuCredits extends NormalButton implements ButtonInterface, MouseListener {

	public MainMenuCredits() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 600, 100, 50), "Credits", Color.red, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Credits, "MainMenuCredits");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State);
		Application.State = gameStateOnClick;
		Music.mouseClick();
		Application.isInGame = false;
	}

}
