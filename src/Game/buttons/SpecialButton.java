package Game.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import Game.Application;

public class SpecialButton extends NormalButton {

	public SpecialButton(Rectangle r, String t, Color co, Color c02, Application.STATE[] statestoEXCLUDE,
			Application.STATE gs, String n) {
		super(r, t, co, c02, statestoEXCLUDE, gs, n);
	}

	public void init() {

	}

	public void draw() {
		if(!Application.getGame().gameStateIsPartOf(Application.getGame().State, prereq))
		Application.getGame().drawButton(this);
	}
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if (bounds.contains(x, y) && (!Application.getGame().gameStateIsPartOf(Application.State, prereq))) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseLeftClicked(e);
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				mouseRightClicked(e);
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				mouseMiddleClicked(e);
			}
		}
	}
}
