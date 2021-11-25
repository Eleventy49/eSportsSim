package Game;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
//This collection of code exists to get the name of the player and hand it off to the "Player" class, where the player will be created.
public class AddTeam{  
	//First I have to create a place to store all the letters the user typed. Strings are just collections of letters.
	static String text = "";
	static String abb = "";
	public static boolean abbSelected = false;
	public static boolean textSelected = false;
	static Rectangle a = new Rectangle(200,300,500,50);
	static Rectangle b = new Rectangle(200,400,500,50);
	//This 
	public static void Team(Graphics g)  {		//Declaring a "Method", which is a collection of code that can be run over and over again from somewhere else. (This just draws the text)
		Graphics2D g2d = (Graphics2D)g;
		g.setColor(Color.WHITE);
		FontMetrics fm   = g.getFontMetrics(Application.bitoperatorfont36);
		Rectangle2D rect = fm.getStringBounds(text, g);

		int textHeight = (int)(rect.getHeight()); 
		int textWidth  = (int)(rect.getWidth());
		int panelHeight= (int) a.getHeight();
		int panelWidth = (int) a.getWidth();

		// Center text horizontally and vertically
		int x = ((panelWidth  - textWidth)  / 2) + a.x;
		int y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + a.y;

		g.drawString(text, x, y);  
		g2d.draw(a);	
		
		
		g.setColor(Color.WHITE);
	
		fm   = g.getFontMetrics(Application.bitoperatorfont36);
		 rect = fm.getStringBounds(abb, g);

		textHeight = (int)(rect.getHeight()); 
		textWidth  = (int)(rect.getWidth());
		panelHeight= (int) b.getHeight();
		panelWidth = (int) b.getWidth();

		// Center text horizontally and vertically
		x = ((panelWidth  - textWidth)  / 2) + b.x;
		y = ((panelHeight - textHeight) / 2  + fm.getAscent()) + b.y;

		g.drawString(abb, x, y);  
		g2d.draw(b);
	}												
	
	public static void keyPressed(KeyEvent e) {							//Declaring another method that will be run every time a key is pressed
		int key = e.getKeyCode();										//Translate from whatever "KeyEvent" is to something humans can understand.
		
		
		if(textSelected) {
			if((key != KeyEvent.VK_ENTER && key != KeyEvent.VK_BACK_SPACE) && text.length() < 20)
			{
				text += e.getKeyChar();	
			}	
			if(key == KeyEvent.VK_BACK_SPACE)								
			{
				if(text.length() != 0)										
				{
				text = text.substring(0, text.length() -1 );				
				}
			}
			if(key == KeyEvent.VK_SHIFT)									
			{																
				text = text.substring(0, text.length() -1 );				
			}																
	
		}
		
		
		if(abbSelected) {
			if((key != KeyEvent.VK_ENTER && key != KeyEvent.VK_BACK_SPACE) && text.length() < 20)
			{
				abb += e.getKeyChar();	
			}	
			if(key == KeyEvent.VK_BACK_SPACE)								
			{
				if(abb.length() != 0)										
				{
				abb = abb.substring(0, abb.length() -1 );				
				}
			}
			if(key == KeyEvent.VK_SHIFT)									
			{																
				abb = abb.substring(0, abb.length() -1 );				
			}																
			
		}
		if(key == KeyEvent.VK_ENTER)									
		{	if(!abb.isEmpty() && !text.isEmpty())
		{
			
			Team t = new Team(text, abb);								
			abb = "";
			text = "";
		}
		}
	}
}