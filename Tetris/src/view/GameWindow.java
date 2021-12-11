package view;

import javax.swing.*;

import Controller.GanmeCtrl;
import view.GamePanel;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Timer;


public class GameWindow extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gw = null;
	public  boolean [][]myMap = new boolean[20][10];      // 10*20
	public boolean  [][]coMap = new boolean[20][10];
	@SuppressWarnings("rawtypes")
	public ArrayList block = new ArrayList();
	public boolean[][] nextBlock =new boolean[4][4];
	public static final int STOP = 0;
	public static final int RUNNING = 1;
	public Timer timer =null;
	public int state;
	public int score;
	public int k;
	public int j=0;
	public Color [] C={Color.WHITE,Color.RED,Color.CYAN,Color.PINK,Color.GREEN,Color.ORANGE,Color.BLUE,Color.BLUE,Color.ORANGE,Color.GREEN,Color.PINK,Color.CYAN,Color.RED,Color.WHITE};
	private JLabel lb1;
	private JLabel lb3;
	public JLabel lb2;
	public JLabel lb4;
	public JLabel lb5;
	public int label;
	public GanmeCtrl gamectrl = null;

	/**
	 * 只有窗体没有内容的调用这个构造函数
	 */
	public GameWindow(GamePanel G,int a,int b){
		this.gw=G;
		k=a;
		label=b;
		Init(k,label);
		this.listenter();
		this.setFocusable(true);
		
		
		lb2 = new JLabel();
	    lb2.setBounds(385,250,100, 100);
	    lb2.setFont(new Font("黑体",1,16));
		lb2.setForeground(Color.WHITE);
	    lb2.setText(""+score);
	    this.add(lb2);
	    
	    lb1 = new JLabel();
		lb1.setBounds(360,220,100, 100);
		lb1.setFont(new Font("黑体",1,16));
		lb1.setForeground(Color.WHITE);
        lb1.setText("您的得分：");
        this.add(lb1);

        lb3 = new JLabel();
        lb3.setBounds(360,80,100, 100);
		lb3.setFont(new Font("黑体",1,16));
		lb3.setForeground(Color.WHITE);
        lb3.setText("下个形状：");
        this.add(lb3);
        
        lb4 = new JLabel();
		lb4.setBounds(360,300,100, 100);
		lb4.setFont(new Font("黑体",1,16));
		lb4.setForeground(Color.WHITE);
        lb4.setText("对手得分：");
        this.add(lb4);
        
        lb5 = new JLabel();
	    lb5.setBounds(385,330,100, 100);
	    lb5.setFont(new Font("黑体",1,16));
		lb5.setForeground(Color.WHITE);
	    lb5.setText(""+score);
	    this.add(lb5);
	}
	public void Init(int a,int b){
		this.score =0;
		for(int i=0;i<20;i++){
			for(int j=0;j<10;j++ ){
				myMap[i][j] = false;
				coMap [i][j] = false;
			}
		}
		timer = new Timer();  
		gamectrl =new GanmeCtrl(gw,this,a,b);
        timer.schedule(gamectrl,0,500); //1000ms 
        state =RUNNING;
	}
	private void listenter(){
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(state == STOP){
					return ;
				}
				try{
		    		//bgmAudioClip.play();
		    	}catch(Exception exception){
		    		exception.printStackTrace();
		    	}
				switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					if(gw.exchangeThread!=null){
						gw.exchangeThread.sendMessage("u");}
					gamectrl.changeShape();		
					break;
				case KeyEvent.VK_DOWN :
					if(gw.exchangeThread!=null) 
						gw.exchangeThread.sendMessage("d");
					gamectrl.moveShape(GanmeCtrl.DOWNMOVE);
					break;
				
				case KeyEvent.VK_LEFT :
					if(gw.exchangeThread!=null)
						gw.exchangeThread.sendMessage("l");
					gamectrl.moveShape(GanmeCtrl.LEFTMOVE);
					break;
				
				case KeyEvent.VK_RIGHT :
					if(gw.exchangeThread!=null)
						gw.exchangeThread.sendMessage("r");
					gamectrl.moveShape(GanmeCtrl.RIGHTMOVE);
					break;	
				}
			}
				
			
		});
		this.setFocusable(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setStroke(new BasicStroke(2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.WHITE);
		RoundRectangle2D rect = new RoundRectangle2D.Double(10, 10,300 ,600, 10, 10);
		g2d.draw(rect);
		RoundRectangle2D rect2 = new RoundRectangle2D.Double(480, 10,300 ,600, 10, 10);
		g2d.draw(rect2);
		
		g2d.setStroke(new BasicStroke(0.01f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.WHITE);
		int i;
		for(i=1;i<20;i++){
			Line2D line = new Line2D.Double(10,10+i*30,310,10+i*30);
			g2d.draw(line);
			Line2D line2 = new Line2D.Double(10,10+i*30,310,10+i*30);
			g2d.draw(line2);
		}
		for(i=1;i<10;i++){
			Line2D line = new Line2D.Double(i*30+10,10,i*30+10,610);
			g2d.draw(line);
			Line2D line2 = new Line2D.Double(i*30+10,10,i*30+10,610);
			g2d.draw(line2);
		}
		g2d.setColor(Color.LIGHT_GRAY);
		for(i=0;i<20;i++){
			for(int j=0;j<10;j++){
				if(myMap[i][j] == true){
					//g2d.fillRect(10+j*30, 10+i*30, 30, 30);
					g2d.fillRoundRect(10+j*30+2, 10+i*30+2,27,27,8, 8);
					g2d.fillRoundRect(480+j*30+2, 10+i*30+2,27,27,8, 8);
				}
		   }
		}
		Object[] shape = block.toArray();
		g2d.setColor(C[j%14]);
		for(i=0;i<shape.length;i+=2){
			g2d.fillRoundRect(10+(int)shape[i+1]*30+2, 10+(int)shape[i]*30+2,27,27,8, 8);
			g2d.fillRoundRect(480+(int)shape[i+1]*30+2, 10+(int)shape[i]*30+2,27,27,8, 8);
		}
		g2d.setColor(C[(j+1)%14]);
		for( i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(nextBlock[i][j] == true){
					g2d.drawRect(365+20*j, 160+20*i, 20, 20);
					g2d.fillRoundRect(365+20*j+2, 160+20*i+2, 17, 17,2,2);
				}
			}
		}
	    
	}
}
