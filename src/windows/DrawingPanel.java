package windows;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{

	private int stage = 0;

	private int width = Window.WIDTH-160-5;
	private int height = Window.HEIGHT;

	private int type = 0;
	public DrawingPanel(){
		this.setBounds(0,0,Window.WIDTH-160-5,Window.HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(type==0)
			recursiveDrawSquare(g,400,Window.HEIGHT/2, width-800,Window.HEIGHT/2, stage);
		else if (type==1)
			recursiveDrawFeather(g,400,Window.HEIGHT/2, width-800,Window.HEIGHT/2, stage);
		else if (type==2)
			recursiveDrawCloud(g,400,Window.HEIGHT/2, width-800,Window.HEIGHT/2, stage);
	}

	public void setType(int type){
		if(type==3)
			type=0;
		this.type=type;
		this.stage = 0;
		this.repaint();
	}
	
	public int getType(){
		return this.type;
	}

	//cloud like
	private void recursiveDraw(Graphics g, double startX, double startY, double endX, double endY, int stage){
		double midX = (startX + endX)/2.0;
		double midY = (startY + endY)/2.0;

		double midX1 = (startX + midX)/2.0;
		double midY1 = (startY + midY)/2.0;

		double midX2 = (endX + midX)/2.0;
		double midY2 = (endY + midY)/2.0;

		double xDistance = Math.abs(startX - midX1);
		double yDistance = Math.abs(startY - midY1);
		double zDistance = Math.hypot(xDistance, yDistance);
		double angle = Math.asin(yDistance/zDistance);

		if(startX<=midX1 && startY >= midY1){
			angle = Math.PI - angle;
		} else if(startX<=midX1 && startY <= midY1){
			angle = Math.PI + angle;
		} else if(startX>=midX1 && startY <= midY1){
			angle = Math.PI*2.0 - angle;
		}


		double needDistanceX1 = zDistance * Math.cos(angle+Math.PI/2);
		double needDistanceY1 = zDistance * Math.sin(angle+Math.PI/2);

		double targetX1 = midX1+ needDistanceX1;
		double targetY1 =  midY1 + needDistanceY1;

		double needDistanceX2 = zDistance * Math.cos(angle-Math.PI/2);
		double needDistanceY2 = zDistance * Math.sin(angle-Math.PI/2);

		double targetX2 = midX2 + needDistanceX2;
		double targetY2 =  midY2 + needDistanceY2;

		//g.drawLine((int)targetX1, (int)targetY1, (int)startX, (int)startY);
		g.drawLine((int)targetX1, (int)targetY1, (int)midX, (int)midY);
		//g.drawLine((int)targetX1, (int)targetY1, (int)endX, (int)endY);

		//g.drawLine((int)targetX2, (int)targetY2, (int)startX, (int)startY);
		g.drawLine((int)targetX2, (int)targetY2, (int)midX, (int)midY);
		//g.drawLine((int)targetX2, (int)targetY2, (int)endX, (int)endY);

		if(stage== 0){
			return;
		}

		//recursiveDrawSquare(g, targetX1, targetY1, startX, startY, stage-1);
		recursiveDrawSquare(g, targetX1, targetY1, midX, midY, stage-1);
		//recursiveDrawSquare(g, targetX1, targetY1, endX, endY, stage-1);

		//recursiveDrawSquare(g, targetX2, targetY2, startX, startY, stage-1);
		recursiveDrawSquare(g, targetX2, targetY2, midX, midY, stage-1);
		//recursiveDrawSquare(g, targetX2, targetY2, endX, endY, stage-1);
	}

	//cloud like
	private void recursiveDrawCloud(Graphics g, double startX, double startY, double endX, double endY, int stage){
		double midX = (startX + endX)/2.0;
		double midY = (startY + endY)/2.0;

		double midX1 = (startX + midX)/2.0;
		double midY1 = (startY + midY)/2.0;

		double midX2 = (endX + midX)/2.0;
		double midY2 = (endY + midY)/2.0;

		double xDistance = Math.abs(startX - midX1);
		double yDistance = Math.abs(startY - midY1);
		double zDistance = Math.hypot(xDistance, yDistance);
		double angle = Math.asin(yDistance/zDistance);

		if(startX<=midX1 && startY >= midY1){
			angle = Math.PI - angle;
		} else if(startX<=midX1 && startY <= midY1){
			angle = Math.PI + angle;
		} else if(startX>=midX1 && startY <= midY1){
			angle = Math.PI*2.0 - angle;
		}


		double needDistanceX1 = zDistance * Math.cos(angle+Math.PI/2);
		double needDistanceY1 = zDistance * Math.sin(angle+Math.PI/2);

		double targetX1 = midX1+ needDistanceX1;
		double targetY1 =  midY1 + needDistanceY1;

		double needDistanceX2 = zDistance * Math.cos(angle-Math.PI/2);
		double needDistanceY2 = zDistance * Math.sin(angle-Math.PI/2);

		double targetX2 = midX2 + needDistanceX2;
		double targetY2 =  midY2 + needDistanceY2;

		g.drawLine((int)targetX1, (int)targetY1, (int)startX, (int)startY);
		//g.drawLine((int)targetX1, (int)targetY1, (int)midX, (int)midY);
		g.drawLine((int)targetX1, (int)targetY1, (int)endX, (int)endY);

		g.drawLine((int)targetX2, (int)targetY2, (int)startX, (int)startY);
		//g.drawLine((int)targetX2, (int)targetY2, (int)midX, (int)midY);
		g.drawLine((int)targetX2, (int)targetY2, (int)endX, (int)endY);

		if(stage== 0){
			return;
		}

		recursiveDrawCloud(g, targetX1, targetY1, startX, startY, stage-1);
		//recursiveDrawCloud(g, targetX1, targetY1, midX, midY, stage-1);
		recursiveDrawCloud(g, targetX1, targetY1, endX, endY, stage-1);

		recursiveDrawCloud(g, targetX2, targetY2, startX, startY, stage-1);
		//recursiveDrawCloud(g, targetX2, targetY2, midX, midY, stage-1);
		recursiveDrawCloud(g, targetX2, targetY2, endX, endY, stage-1);
	}

	//square base	
	private void recursiveDrawSquare(Graphics g, double startX, double startY, double endX, double endY, int stage){
		double midX = (startX + endX)/2.0;
		double midY = (startY + endY)/2.0;

		double xDistance = Math.abs(startX - midX);
		double yDistance = Math.abs(startY - midY);
		double zDistance = Math.hypot(xDistance, yDistance);
		double angle = Math.asin(yDistance/zDistance);

		if(startX<=midX && startY >= midY){
			angle = Math.PI - angle;
		} else if(startX<=midX && startY <= midY){
			angle = Math.PI + angle;
		} else if(startX>=midX && startY <= midY){
			angle = Math.PI*2.0 - angle;
		}


		double needDistanceX1 = zDistance * Math.cos(angle+Math.PI/2);
		double needDistanceY1 = zDistance * Math.sin(angle+Math.PI/2);

		double targetX1 = midX+ needDistanceX1;
		double targetY1 =  midY + needDistanceY1;

		double needDistanceX2 = zDistance * Math.cos(angle-Math.PI/2);
		double needDistanceY2 = zDistance * Math.sin(angle-Math.PI/2);

		double targetX2 = midX+ needDistanceX2;
		double targetY2 =  midY + needDistanceY2;

		g.drawLine((int)targetX1, (int)targetY1, (int)startX, (int)startY);
		g.drawLine((int)targetX1, (int)targetY1, (int)midX, (int)midY);
		g.drawLine((int)targetX1, (int)targetY1, (int)endX, (int)endY);

		g.drawLine((int)targetX2, (int)targetY2, (int)startX, (int)startY);
		g.drawLine((int)targetX2, (int)targetY2, (int)midX, (int)midY);
		g.drawLine((int)targetX2, (int)targetY2, (int)endX, (int)endY);

		if(stage== 0){
			return;
		}

		recursiveDrawSquare(g, targetX1, targetY1, startX, startY, stage-1);
		recursiveDrawSquare(g, targetX1, targetY1, midX, midY, stage-1);
		recursiveDrawSquare(g, targetX1, targetY1, endX, endY, stage-1);

		recursiveDrawSquare(g, targetX2, targetY2, startX, startY, stage-1);
		recursiveDrawSquare(g, targetX2, targetY2, midX, midY, stage-1);
		recursiveDrawSquare(g, targetX2, targetY2, endX, endY, stage-1);

	}

	//feather like
	private void recursiveDrawFeather(Graphics g, double startX, double startY, double endX, double endY, int stage){
		double midX = (startX + endX)/2.0;
		double midY = (startY + endY)/2.0;

		double midX1 = (startX + midX)/2.0;
		double midY1 = (startY + midY)/2.0;

		double xDistance = Math.abs(startX - midX1);
		double yDistance = Math.abs(startY - midY1);
		double zDistance = Math.hypot(xDistance, yDistance);
		double angle = Math.asin(yDistance/zDistance);

		if(startX<=midX1 && startY >= midY1){
			angle = Math.PI - angle;
		} else if(startX<=midX1 && startY <= midY1){
			angle = Math.PI + angle;
		} else if(startX>=midX1 && startY <= midY1){
			angle = Math.PI*2.0 - angle;
		}

		angle +=Math.PI/2;

		double needDistanceX = zDistance * Math.cos(angle);
		double needDistanceY = zDistance * Math.sin(angle);

		double targetX = midX1 + needDistanceX;
		double targetY =  midY1 + needDistanceY;

		g.drawLine((int)targetX, (int)targetY, (int)startX, (int)startY);
		g.drawLine((int)targetX, (int)targetY, (int)midX, (int)midY);
		g.drawLine((int)targetX, (int)targetY, (int)endX, (int)endY);

		if(stage== 0){
			return;
		}

		recursiveDrawFeather(g, targetX, targetY, startX, startY, stage-1);
		recursiveDrawFeather(g, targetX, targetY, midX, midY, stage-1);
		recursiveDrawFeather(g, targetX, targetY, endX, endY, stage-1);
	}

	public void setStage(int stage){
		if(stage<0){
			stage=0;
		}
		this.stage = stage;
		this.repaint();
	}

	public int getState(){
		return this.stage;
	}

}
