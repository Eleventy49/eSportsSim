package Game.buttons;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import Game.Application;
import Game.MusicHandler;

public class OptionsEffectVolume extends Slider{
	
	public OptionsEffectVolume()
	{
		super(new Rectangle(200, 375, 200, 50), "Effects Volume", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.Options}, 200  );
	}

	public void init() {
		Application.getGame().addMouseListener(this);
		Application.getGame().addMouseMotionListener(this);
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if(outside.contains(arg0.getX(),arg0.getY()))
			coordx = arg0.getX();
		
		MusicHandler.setVolumeEffect((float)((coordx - 200) / 2.5) - 80);
	}
	
	public void mouseDragged(MouseEvent arg0) {
		if(outside.contains(arg0.getX(),arg0.getY()))
		coordx = arg0.getX();
		
		MusicHandler.setVolumeEffect((float)((coordx - 200) / 2.5) - 80);
		
	}
	
}
