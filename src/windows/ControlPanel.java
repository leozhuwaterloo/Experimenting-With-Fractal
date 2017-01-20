package windows;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
	
	
	public ControlPanel(DrawingPanel paint){
		this.setBounds(Window.WIDTH-160,0,150,Window.HEIGHT);
		this.setLayout(null);
		int startY = 10;
		
		JButton next = new JButton("Next");
		next.setBounds(0,startY,150,30);
		startY+=30+5;
		JButton back = new JButton("Back");
		back.setBounds(0,startY,150,30);
		startY+=30+5;
		JLabel stageLabel = new JLabel("Stage: "+paint.getState());
		stageLabel.setBounds(0,startY,150,30);
		startY+=30+5;
		JButton nextExample= new JButton("Next Example");
		nextExample.setBounds(0,startY,150,30);
		startY+=30+5;
		JLabel exampleID= new JLabel("Example: "+paint.getType());
		exampleID.setBounds(0,startY,150,30);
		startY+=30+5;
		JButton exit = new JButton("Exit");
		exit.setBounds(0,startY,150,30);
		
		this.add(next);
		this.add(back);
		this.add(stageLabel);
		this.add(nextExample);
		this.add(exampleID);
		this.add(exit);
		
		next.addActionListener(e->{
			paint.setStage(paint.getState()+1);
			stageLabel.setText("Stage: "+paint.getState());
		});
		
		back.addActionListener(e->{
			paint.setStage(paint.getState()-1);
			stageLabel.setText("Stage: "+paint.getState());
		});
		
		exit.addActionListener(e->{
			System.exit(0);
		});
		
		nextExample.addActionListener(e->{
			paint.setType(paint.getType()+1);
			stageLabel.setText("Stage: "+paint.getState());
			exampleID.setText("Example: "+paint.getType());
		});
	}
}
