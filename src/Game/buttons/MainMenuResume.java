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

public class MainMenuResume extends ScalingButton implements ButtonInterface, MouseListener {

	public MainMenuResume() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 300, 100, 50), "Resume", Color.red, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, null, "MainMenuResume");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}
	
	@Override
	public void scaleFunction() {
		bounds = new Rectangle(((Application.WIDTH / 2) - 50), 300, 100, 50);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(Application.gameLoaded) {
		Application.prevState.add(Application.State);
		if(Application.SpectatorMode)
		Application.State = Application.State.SpectatorMode;
		else
			Application.State=Application.State.ManagerMode;
		Music.mouseClick();
		Application.isInGame = true;
		}
	}

}
