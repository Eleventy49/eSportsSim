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
import Game.MusicHandler;

public class MainMenuTutorial extends NormalButton implements ButtonInterface, MouseListener {
	public MainMenuTutorial() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 700, 100, 50), "Tutorial", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Tutorial, "MainMenuTutorial");
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Music.mouseClick();
		Application.prevState.add(Application.State);

		Application.isInGame = false;

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		;
	}

}
