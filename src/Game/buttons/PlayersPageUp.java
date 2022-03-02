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
import Game.PlayerViewer;
import Game.Application.STATE;

public class PlayersPageUp extends NormalButton implements ButtonInterface ,MouseListener{

	public PlayersPageUp()
	{
		super( new Rectangle(700, 700, 50, 50), "+", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.SpectatorModeViewPlayer}, Application.STATE.SpectatorModeViewPlayer, "PlayersViewPlayer");
	
	}
	@Override
	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}
	
	@Override
	public void mouseLeftClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Application.prevState.add(Application.State); 
		PlayerViewer.increasePage();
		Music.mouseClick();
		Application.isInGame = true;
	}
		
}
