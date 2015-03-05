package me.hawsoo.firstgame.engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * This contains the data
 * of an image and renders
 * with scaling.
 * @author Hawsoo
 *
 */
public class Sprite
{
	private BufferedImage image;

	/**
	 * Creates the sprite.
	 * @param image - the image
	 */
	public Sprite(BufferedImage image)
	{
		super();
		this.image = image;
	}
	
	/**
	 * Render the image with scaling.
	 * The offset is calculated <i>with</i>
	 * the scale affecting it.
	 * @param x - the x offset
	 * @param y - the y offset
	 * @param scale - the scale for image
	 */
	public void render(Graphics2D g, int x, int y, float scale)
	{
		g.drawImage(image, (int)(x * scale), (int)(y * scale), (int)(image.getWidth() * scale), (int)(image.getHeight() * scale), null);
	}
}
