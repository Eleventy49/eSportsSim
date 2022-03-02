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
import Game.Timer;

public class DecreaseGameSpeed extends SpecialButton implements ButtonInterface, MouseListener {

	public DecreaseGameSpeed() {
		super(new Rectangle(100, 6, 50, 50), "<<", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu, Application.STATE.Menu }, Application.STATE.Menu,
				"DEFAULT");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (Timer.running) {
			Timer.decrease();
		}

		Music.mouseClick();

	}

	@Override
	public void mouseMiddleClicked(MouseEvent e) {
		if (Timer.running) {
			for (int i = 0; i < 10; i++)
				Timer.decrease();
		}

		Music.mouseClick();
		Application.WarningQuery = true;
		Application.WarningMessage = "Decreased speed by 10";

	}

	@Override
	public void mouseRightClicked(MouseEvent e) {
		if (Timer.running) {
			Timer.speed = 1;
		}

		Music.mouseClick();
		Application.WarningQuery = true;
		Application.WarningMessage = "Set speed to 1";
	}

}
