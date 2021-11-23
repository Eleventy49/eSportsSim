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
import Game.MusicHandler;

public class MainMenuExit extends NormalButton implements ButtonInterface, MouseListener {

	public MainMenuExit() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 800, 100, 50), "Exit", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.Exit, "MainMenuExit");
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Music.mouseClick();
		MusicHandler.exit();
	}

	@Override
	public void init() {
		Application.getGraphical().addMouseListener(this);
	}
}
