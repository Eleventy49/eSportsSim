package Game.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

import Game.Application;

import Game.MusicHandler;
import Game.Application.STATE;
import Game.Graphical;

public class NormalButton implements MouseListener {
	public Rectangle bounds; // The phyisical button
	public String txt; // What the button displays as text
	Color cDefault; // The default color when the button is NOT highlighted
	Color cHovered; // The color of the button when the color is highlighted
	public Application.STATE[] prereq; // The gamestate that allows this button to show up [SPECIAL CASES EXIST FOR
										// "CORNERMENUBUTTON"]
	public Application.STATE gameStateOnClick; // The gamestate that the game will be set to when the button is pushed
	public boolean hovered = false; // Whether or not this button is being hovered over by the mouse.
	public String name; // The name of the button. I don't think this will have any use but it exists
						// just in case.
	static boolean isMouse = true;
	MusicHandler Music = new MusicHandler();

	// The constructor. These are all invoked in ButtonCollection
	public NormalButton(Rectangle b, String t, Color co, Color co2, Application.STATE[] states, Application.STATE gs,
			String n) {
		bounds = b;
		txt = t;
		cDefault = co;
		cHovered = co2;
		prereq = states;
		gameStateOnClick = gs;
		name = n;

	}

	public NormalButton(Rectangle b, String t, Color co, Color co2, ArrayList<Application.STATE> states,
			Application.STATE gs, String n) {
		Object[] temp = states.toArray();

		Application.STATE[] temp2 = new Application.STATE[temp.length];
		for (int i = 0; i < temp.length; i++) {
			temp2[i] = (STATE) temp[i];
		}
		bounds = b;
		txt = t;
		cDefault = co;
		cHovered = co2;
		prereq = temp2;
		gameStateOnClick = gs;
		name = n;

	}

	public void draw(Graphics g, Graphics2D g2d) {
		Application.getGraphical().drawButton(this);
	}

	public void init() {

	}

	public void mouseLeftClicked(MouseEvent e) {
		Application.prevState.add(Application.State);
		Music.mouseClick();
		Application.State = gameStateOnClick;
	}

	public void mouseRightClicked(MouseEvent e) {

	}

	public void mouseMiddleClicked(MouseEvent e) {

	}

	public Color getHoveredColor() {
		return cHovered;
	}

	public Color getDefaultcolor() {
		return cDefault;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if (bounds.contains(x, y)) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				mouseLeftClicked(e);
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				mouseRightClicked(e);
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				mouseMiddleClicked(e);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		hovered = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		hovered = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	public void mouseMoved(MouseEvent arg0) {

	}

}
