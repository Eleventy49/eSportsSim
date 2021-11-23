package Game.buttons;

import java.awt.Color;
import java.awt.Rectangle;

import Game.Application;

public class SLIDERTEST extends Slider{
	
	public SLIDERTEST()
	{
		super(new Rectangle(200, 450, 200, 100), "TEST", Color.white, Color.GRAY,new Application.STATE[]{Application.STATE.Options}, 200  );
	}

}
