package Game;

import java.awt.event.MouseEvent;

public class BracketHoverHandler {

	public static void detectHover(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(mx >= 120 && my >= 1 && mx <= (120 + 130) && my < (1 + 35)) {
			Bracket32.setHoveredPlayer(0);
		
		}
		
		
		
		
		else Bracket32.setHoveredPlayer(-1);
		
	}
}
