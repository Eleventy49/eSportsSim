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

public class MainMenuOptions extends NormalButton implements ButtonInterface, MouseListener {

	public MainMenuOptions() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 500, 100, 50), "Options", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Options, "MainMenuOptions");

	}

	@Override
	public void init() {
		Application.getGraphical().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State);

		Music.mouseClick();
		Application.isInGame = false;
		Application.State = gameStateOnClick;
	}

}
