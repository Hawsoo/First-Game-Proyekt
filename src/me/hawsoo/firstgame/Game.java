package me.hawsoo.firstgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 * This class manages the graphics canvas,
 * as well as the main loop for the game.
 * @author Hawsoo
 *
 */
public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private static final int FPS = 60;
	private static final Dimension DIMENSIONS = new Dimension(640, 360);
	
	private int scale = 3;
	private BufferStrategy bStrat;
	private boolean running = false;
	
	/**
	 * Creates the game.
	 */
	public Game()
	{
		// Setup canvas
		setupDimensions(scale);
		
		// Setup properties
		setIgnoreRepaint(true);
		
		createBufferStrategy(2);
		bStrat = getBufferStrategy();
	}
	
	public void setupDimensions(int scale)
	{
		this.scale = scale;
		
		// Set the preferred size
		setPreferredSize(new Dimension(DIMENSIONS.width * scale, DIMENSIONS.height * scale));
		setMaximumSize(new Dimension(DIMENSIONS.width * scale, DIMENSIONS.height * scale));
		setMinimumSize(new Dimension(DIMENSIONS.width * scale, DIMENSIONS.height * scale));
	}
	
	/**
	 * Starts the thread if not already.
	 */
	public synchronized void start()
	{
		if (!running)
		{
			running = true;
			new Thread(this).start();
		}
	}
	
	/**
	 * Stops the thread if not already.
	 */
	public synchronized void stop()
	{
		if (running)
		{
			running = false;
		}
	}
	
	/**
	 * Updates the game.
	 */
	@Override
	public void run()
	{
		while (running)
		{
			// Update
			
			// Render
			render();
			
			// Wait
			try
			{
				Thread.sleep(1000l / FPS);
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Renders the game.
	 */
	public void render()
	{
		// Clear buffer
		Graphics2D g = (Graphics2D)bStrat.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, DIMENSIONS.width, DIMENSIONS.height);
		
		// Clear graphics and flip buffer
		g.dispose();
		bStrat.show();
	}
}
