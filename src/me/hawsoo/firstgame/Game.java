package me.hawsoo.firstgame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * This class manages the graphics canvas,
 * as well as the main loop for the game.
 * @author Hawsoo
 *
 */
public class Game extends Canvas implements Runnable
{
	public static final String NAME = "First Game -- PreAlpha (By Hawsoo)";
	private static final long serialVersionUID = 1L;
	
	private static final Dimension INITIAL_DIM = new Dimension(640, 360);
	private static final int FPS = 60;
	
	private BufferStrategy bStrat;
	private boolean running = false;
	
	private int width;
	private int height;
	private int prevW = INITIAL_DIM.width;
	private int prevH = INITIAL_DIM.height;
	private float aspect = 16f / 9;
	
	/**
	 * Creates the game.
	 */
	public Game()
	{
//		setPreferredSize(INITIAL_DIM);
		
		// Setup properties
		setIgnoreRepaint(true);
		setVisible(true);
	}
	
	/**
	 * Sets up the buffering.
	 */
	public void setupBuffering()
	{
		// Setup buffering
		createBufferStrategy(2);
		bStrat = getBufferStrategy();
	}
	
	/**
	 * Updates the dimensions
	 * according to aspect ratio.
	 */
	private void updateRealDimensions()
	{
		// Find preference anchored at width
		int preferH = (int) (getWidth() / aspect);
		
		// Find bias
		if (getHeight() == preferH)
		{
			// No bias
			width = getWidth();
			height = getHeight();
		}
		else if (height < preferH)
		{
			// Horizontal bias
			width = (int) (height * aspect);
			height = getHeight();
		}
		else
		{
			// Vertical bias
			width = getWidth();
			height = preferH;
		}
		
		// Reset flags
		prevW = getWidth();
		prevH = getHeight();
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
		// Calculate width and height
		if (getWidth() != prevW || getHeight() != prevH) updateRealDimensions();
		
		System.out.println(getWidth() + "x" + getHeight());
		
		// Clear buffer
		Graphics2D g = (Graphics2D)bStrat.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.red);
		g.fillRect((getWidth() / 2) - (width / 2), (getHeight() / 2) - (height / 2), width, height);
		
		// Clear graphics and flip buffer
		g.dispose();
		bStrat.show();
	}
	
	/**
	 * The driver method if it is
	 * an executable.
	 * @param argv
	 */
	public static void main(String[] argv)
	{
		// Create a JFrame to contain the canvas
		JFrame frame = new JFrame(NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(
				new WindowListener()
				{
					@Override
					public void windowActivated(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosed(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowClosing(WindowEvent e)
					{
						// LATER save the game
						// Shut down application
						System.exit(0);
						
					}

					@Override
					public void windowDeactivated(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowDeiconified(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowIconified(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void windowOpened(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}
				}
				);
		
		frame.addWindowFocusListener(
				new WindowFocusListener()
				{
					@Override
					public void windowGainedFocus(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void windowLostFocus(WindowEvent e)
					{
						// TODO Auto-generated method stub
						
					}
				}
				);
		
		Game game = new Game();
		
		frame.add(game, BorderLayout.CENTER);
		frame.getContentPane().setPreferredSize(INITIAL_DIM);
		frame.getContentPane().setBackground(Color.black);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// Setup buffering
		game.setupBuffering();
		
		// Start thread
		game.start();
	}
}
