import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

//canvas is a canvas to draw stuff on the screen and process input events.
public class Game extends Canvas implements Runnable{
	public static int WIDTH = 800;
	public static int HEIGHT = WIDTH*9/16;
	
	private boolean isRunning = false;
	private Thread thread;
	private JFrame frame;
	
	//constructor
	public Game() {
		Window win = new Window(WIDTH, HEIGHT,this);		
	}
	
	//synch to prevent overlaps of instructions over one thread.
	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this, "game"); //game is the name of the thread.
		thread.start();
			
	}
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

	@Override
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double numberOfTicks = 30.0;
		double ns = 1000000000 / numberOfTicks;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;	
			lastTime = now;
			
			if(delta>= 1) {
				update();
				render();
				frames++;
				delta--;
				if(System.currentTimeMillis() - time >= 1000) {
					System.out.println("fps: "+frames);
					time += 1000;
					frames = 0;
				}
			}
		}
		
	}
	
	
	
	private void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //creates a link between graphics and buffer strategy
		
		//graphics goes here:
		g.setColor(Color.BLACK);
		g.fillRect(0,0, getWidth(), getHeight());
		
		
		//release resources used by graphics
		g.dispose();
		bs.show();	
		
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
		
	}
}
