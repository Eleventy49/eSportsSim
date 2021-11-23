package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


//Basically in order to get keys we have to extend keyadapter, so we come here to get the key event and then pass it on to the game structure to deal with it.
public class KeyInput extends KeyAdapter {

	Application game;

	public KeyInput(Application game) {
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
		if(Application.State == Application.STATE.SpectatorModeAddPlayer)
			AddPlayer.keyPressed(e);
		if(Application.State == Application.STATE.SpectatorModeAddTeam)
			AddTeam.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
