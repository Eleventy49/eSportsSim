package Game;

import java.util.ArrayList;
import java.util.Collection;

import Game.buttons.*;


public class ButtonCollection {

	ArrayList<Object> collection = new ArrayList<Object>();

	static SLIDERTEST SLIDERTEST = new SLIDERTEST();

	
	
	
	public ButtonCollection()
	{
		collection.add(SLIDERTEST);
	}
	
	public ArrayList<Object> getCollection() {
		return collection;
	}
}
