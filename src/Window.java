import java.awt.Dimension;

import javax.swing.JFrame;

public class Window{
	public int width;
	public int height;
	public JFrame frame;
	public Game game;
	
	private Dimension dimension; 
	
	public Window(int width, int height, Game game) {
		this.width = width;
		this.height = height;
		this.game = game;
		
		this.dimension = new Dimension(this.width,this.height);
		
		this.frame = new JFrame("Window");
//		this.frame.getContentPane().add(game);
		this.frame.add(game);
		this.frame.setPreferredSize(dimension);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	
}