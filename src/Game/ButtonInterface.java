package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public interface ButtonInterface {	
	void draw(Graphics g, Graphics2D g2d);
	void mouseLeftClicked(MouseEvent e);
	void mouseMiddleClicked(MouseEvent e);
	void mouseRightClicked(MouseEvent e);
	void mouseMoved(MouseEvent e);
	void init();
}
