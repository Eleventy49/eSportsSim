package Game;

import java.awt.image.BufferedImage;


//I don't even think I use this class, but it gets sprites from the spite sheet. Refer to RealTutsGML tutorial.
public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
		return img;
	}
}
