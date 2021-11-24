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
import Game.AddTeam;
import Game.Application;
import Game.Application.STATE;

public class AddTeamName extends NormalButton implements ButtonInterface, MouseListener {

	public AddTeamName() {
		super(new Rectangle(200, 300, 500, 50), "", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.SpectatorModeAddTeam },
				Application.STATE.SpectatorModeAddTeam, "AddTeamName");
	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Name");
		AddTeam.textSelected = true;
		AddTeam.abbSelected = false;
		Application.isInGame = true;
		Music.mouseClick();
		Application.State = gameStateOnClick;
	}
}
