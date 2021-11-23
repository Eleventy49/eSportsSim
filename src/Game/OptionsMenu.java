
package Game;

import java.awt.Color;

public class OptionsMenu {
	static int mousex = 300;
	static int mousex2 = 300;
	static float songVolume = 0;
	static float effectVolume = 0;
	public static void drawCenteredCircle(int x, int y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  Application.g.fillOval(x,y,r,r);
		}
	
	public static void render()
	{
		Application.g.setColor(Color.white);
		Application.g.drawLine(200, 100, 400, 100);
		drawCenteredCircle(mousex,100,15);
		
		Application.g.drawLine(200, 400, 400, 400);
		drawCenteredCircle(mousex2,400,15);
		Application.g.drawString("Music Volume", 430, 115);
		Application.g.drawString("Console Output [Developer Intended]", 430, 215);
		Application.g.drawString("Quick Function Opens Tournament Bracket", 430, 315);
		Application.g.drawString("Effects Volume", 430, 415);
		songVolume = (float)((mousex - 200) / 2.5) - 80;
		effectVolume = (float)((mousex2 - 200) / 2.5) - 80;
		
		if(MusicHandler.sound != null)
		{
			MusicHandler.setVolumeSong(songVolume);
			MusicHandler.setVolumeEffect(effectVolume);
		}
		if(Application.ConsoleOutput)
		System.out.println(mousex + "\t" + songVolume);
		
	}

	public static void setVolumeSong(float volume) {
		songVolume = volume;
		mousex = (int)((volume + 80) * 2.5) + 200;
		MusicHandler.setVolumeSong(songVolume);
		
	}
	public static void setVolumeEffect(float volume) {
		effectVolume = volume;
		mousex2 = (int)((volume + 80) * 2.5) + 200;
		MusicHandler.setVolumeEffect(effectVolume);
	}
	
	
}
