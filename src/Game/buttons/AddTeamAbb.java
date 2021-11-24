package Game.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import Game.ButtonInterface;
import Game.AddTeam;
import Game.Application;
import Game.Application.STATE;

public class AddTeamAbb extends NormalButton implements ButtonInterface, MouseListener {

	public AddTeamAbb() {
		super(new Rectangle(200, 400, 500, 50), "", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.SpectatorModeAddTeam },
				Application.STATE.SpectatorModeAddTeam, "AddTeamAbb");
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Abbreviation");
		AddTeam.abbSelected = true;
		AddTeam.textSelected = false;
		Application.isInGame = true;
		Music.mouseClick();
		Application.State = gameStateOnClick;
	}

	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
	}

}
