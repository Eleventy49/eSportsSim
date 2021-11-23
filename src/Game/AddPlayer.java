package Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Game.buttons.NormalButton;
//This collection of code exists to get the name of the player and hand it off to the "Player" class, where the player will be created.
public class AddPlayer{  
									//First I have to create a place to store all the letters the user typed. Strings are just collections of letters.
		public static String text = "";
		
	//This 
	public static void AddPlayer(Graphics g)  {		//Declaring a "Method", which is a collection of code that can be run over and over again from somewhere else. (This just draws the text)
		
		g.setColor(Color.WHITE);
		g.drawRect(195, 195, 170, 50);
		g.drawString(text, 200, 233);				//Display the text in the Canvas. "text" is just the collection of letters, and it is placed at position (200,200)
	}												//End container for the Method.
	
	public static void keyPressed(KeyEvent e) {							//Declaring another method that will be run every time a key is pressed
		int key = e.getKeyCode();										//Translate from whatever "KeyEvent" is to something humans can understand.
		
		if((key != KeyEvent.VK_ENTER && key != KeyEvent.VK_BACK_SPACE) && text.length() < 35)
		{
			text += e.getKeyChar();										//Add whatever letter you just typed into the collection of letters on line 7
		}	
		if(key == KeyEvent.VK_BACK_SPACE)								//This is saying "If the key is backspace, then do the things in the curly brackets.
		{
			if(text.length() != 0)										//This is saying "If there is SOMETHING in the collection of letters, do the things in curly brackets.
			{
			text = text.substring(0, text.length() -1 );				//"Take one character out of the collection". And no, there really isn't an "easier" way to do this.
			}
		}
		if(key == KeyEvent.VK_SHIFT)									//"If the key is Shift"
		{																//"Take one character out of the collection". I have to do this because of how I wrote the code between lines 18 and 21.
			text = text.substring(0, text.length() -1 );				//Basically, since shift isn't enter or backspace, it adds SHIFT (The machine codey version at least) to the collection
		}																//All this is doing is making sure that I dont have a name like "[SHIFT_KEY]Dylan", but instead what you would expect.
		if(key == KeyEvent.VK_ENTER)									//"If the key is enter"
		{
			
			Player p = new Player(text);								//Create the new player, and give him the same name as our collection of letters.
			Application.WarningQuery = true;
			Application.WarningMessage = "Added player";
			text = new Name().user;
		}
	}
}
