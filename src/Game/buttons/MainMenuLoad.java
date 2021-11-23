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
import Game.Filehandler;
import Game.Application;
import Game.Application.STATE;
import Game.Timer;

public class MainMenuLoad extends NormalButton implements ButtonInterface, MouseListener {
	public MainMenuLoad() {
		super(new Rectangle((Application.WIDTH / 2) - 50, 300, 100, 50), "Load Game", Color.red, Color.GRAY,
				new Application.STATE[] { Application.STATE.Menu }, Application.STATE.LoadGame, "MainMenuLoadGame");
	}

	@Override
	public void init() {
		Application.getGraphical().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State);
		Application.isInGame = false;
		Filehandler.Load();
		Timer.enable();
		Application.isInGame = true;
		Application.State = Application.STATE.SpectatorMode;
	}

}
