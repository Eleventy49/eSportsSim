package Game.buttons;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import Game.Application;

public class SpecialButton extends NormalButton {

	public SpecialButton(Rectangle r, String t, Color co, Color c02, Application.STATE[] statestoEXCLUDE,
			Application.STATE gs, String n) {
		super(r, t, co, c02, statestoEXCLUDE, gs, n);
	}

	public void init() {

	}

	public void draw(Graphics g, Graphics2D g2d) {
		Application.getGraphical().drawButton(this);
	}
}
