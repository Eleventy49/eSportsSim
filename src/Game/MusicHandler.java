
package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import Game.buttons.PlayPause;
import Game.WarningText;


public class MusicHandler {

	static int index = 0;
	static ArrayList<String> songnames = new ArrayList<String>();
	boolean isPlaying = true;
	static Clip sound;
	static Clip clip;
	static FloatControl gainControl;
	public static FloatControl songgainControl;
	public static FloatControl effectsgainControl;
	static BooleanControl muteControl;
	static int timer = 0;

	public Clip getSound()
	{
		return sound;
	}
	public static void exit()
	{
		sound.stop();
		sound.flush();
		sound.drain();
		sound.close();
	}
	private void playSong(String file) {
		// Try to play the sound passed in the string
		if(sound != null) {
		getSound().flush();
		getSound().drain();
	//	sound.close();
		}
		timer = 0;
		try {
			sound = AudioSystem.getClip();
	
			InputStream inRequest =this.getClass().getResourceAsStream(file);
			AudioInputStream soundStream = AudioSystem.getAudioInputStream(inRequest);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			sound = (Clip)AudioSystem.getLine(info);
			getSound().open(soundStream);
			getSound().start();
			
			LineListener listener = new LineListener()
					{
						public void update(LineEvent event) {
							System.out.println(timer + "Timer, for some reason");
							if(event.getType() == Type.STOP) {
							timer++;
							
							}
						
						
				
					}
					};
					
			
			if(sound.isOpen()) {
			songgainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
			songgainControl.setValue(OptionsMenu.songVolume);
			muteControl = (BooleanControl) sound.getControl(BooleanControl.Type.MUTE);}
			nowPlaying(index);
			//sound.addLineListener(listener);
			// sound.start();
	
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		} catch (IllegalArgumentException e4 ) {
			e4.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			
			e.printStackTrace();
		}
		
	}
	private void nowPlaying(int index2) {
		Application.getGame().setFrameCounter(0);
		switch(index2) {
		case 0:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - This Finally Thought Everyone";
			Application.getGame().resetAlpha();break;
		case 1:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Crabs"; 
			Application.getGame().resetAlpha();break;
		case 2:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - The Best Number is 74";
			Application.getGame().resetAlpha();break;
		case 3:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Invasion of Pumpkins"; 
			Application.getGame().resetAlpha();break;
		case 4:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - A Collection of Melodies";
			Application.getGame().resetAlpha();break;
		case 5:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Welcome to Winter";
			Application.getGame().resetAlpha();break;
		case 6:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Welcome to Spring"; 
			Application.getGame().resetAlpha();break;
		case 7:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - For the House";
			Application.getGame().resetAlpha();break;
		case 8:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Welcome to Summer";
			Application.getGame().resetAlpha();break;
		case 9:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - Welcome to Autumn";
			Application.getGame().resetAlpha();break;
		case 10:
			Application.WarningQuery = true;
			Application.WarningMessage = "Now playing: Eleventy 49 - In Collaboration"; 
			Application.getGame().resetAlpha();break;
			
			
		
		}
		
	}
	private void playSound(String file) {
		// Try to play the sound passed in the string

	
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResource(file)));
			clip.start();
			effectsgainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			effectsgainControl.setValue(OptionsMenu.effectVolume);
		//	System.out.println(gainControl.getMinimum());
			muteControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
			// sound.start();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (LineUnavailableException e3) {
			e3.printStackTrace();
		}
		
	}
	
	static boolean checkPlaying() {
		return sound.isRunning();
		
	}

	public void init() {
	
		songnames.add("/song1.wav"); // 0
		songnames.add("/song2.wav");
		songnames.add("/song3.wav");
		songnames.add("/song4.wav");
		songnames.add("/song5.wav");
		songnames.add("/song7.wav");
		songnames.add("/song8.wav");
		songnames.add("/song9.wav");
		songnames.add("/song10.wav");
		songnames.add("/song11.wav"); // 9


	}

	// Play the default sound
	public void defaultSong() {
	playSong("/song1.wav");
		isPlaying = true;
	
	}

	public void mouseClick() {
		playSound("/click.wav");
	}

	public void quickFunction() {
		playSound("/save.wav");
	}

	public void error() {
		playSound("/error.wav");
	}

	public void next() {
		if (index < 9) {
			muteControl.setValue(true);
			getSound().stop();
			getSound().flush();
			getSound().drain();
			index++;
			playSong(songnames.get(index));
		} else {
			muteControl.setValue(true);
			getSound().stop();
			getSound().flush();
			getSound().drain();
			index = 0;
			playSong(songnames.get(index));
		}
	}
	

	public void prev() {
		if (index > 0) {
			muteControl.setValue(true);
			getSound().stop();
			getSound().flush();
			getSound().drain();
			index--;
			playSong(songnames.get(index));

		} else {
			muteControl.setValue(true);
			getSound().stop();
			getSound().flush();
			getSound().drain();
			index = 9;
			playSong(songnames.get(index));

		}
	}

	public void playpause() {
		if (isPlaying) {

			muteControl.setValue(true);
			getSound().stop();
			
			isPlaying = false;
			
		} else {

			muteControl.setValue(false);
			getSound().start();
			isPlaying = true;
			
			
		}
	}
	public static void setVolumeSong(float passing) {
		// TODO Auto-generated method stub
		songgainControl.setValue(passing);
	}
	public static void setVolumeEffect(float passing) {
		// TODO Auto-generated method stub
		effectsgainControl.setValue(passing);
	}
}
