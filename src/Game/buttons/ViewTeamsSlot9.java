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

public class ViewTeamsSlot9 extends NormalButton implements ButtonInterface, MouseListener {
	public ViewTeamsSlot9() {
		super(new Rectangle(195, 500, 350, 50), "", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.SpectatorModeViewTeam },
				Application.STATE.SpectatorModeViewTeamB, "TeamsViewTeam");

	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State);
		Application.ourversionofi = 9;
		Application.State = gameStateOnClick;
		Music.mouseClick();
	}

}
