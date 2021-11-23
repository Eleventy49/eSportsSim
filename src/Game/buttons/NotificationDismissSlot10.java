
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
import Game.Graphical;
import Game.Application;
import Game.Application.STATE;
import Game.NotificationHandler;

public class NotificationDismissSlot10 extends NormalButton implements ButtonInterface, MouseListener {
	public NotificationDismissSlot10() {
		super(new Rectangle(125, (12 * 50) - 40, 50, 50), "", Color.white, Color.GRAY,
				new Application.STATE[] { Application.STATE.SpectatorMode }, Application.STATE.SpectatorMode,
				"TeamsViewTeam");

	}

	@Override
	public void init() {
		Application.getGraphical().addMouseListener(this);
		;
	}

	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		NotificationHandler.delete(9);
		Music.mouseClick();
	}

	public void draw(Graphics g, Graphics2D g2d) {
		if (NotificationHandler.coll.size() >= 10 && Application.State == Application.STATE.SpectatorMode) {
			super.draw(Application.getGraphical().getGraphics(), (Graphics2D) Application.getGraphical().getGraphics());
		}
	}
}
