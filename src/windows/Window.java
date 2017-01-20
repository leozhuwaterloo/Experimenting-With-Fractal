package windows;

import javax.swing.JFrame;

public class Window extends JFrame{
	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	public Window(String name){
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setName(name);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		DrawingPanel paint = new DrawingPanel();
		ControlPanel control = new ControlPanel(paint);

		this.add(paint);
		this.add(control);
		
		this.repaint();
		this.revalidate();
	}

}
