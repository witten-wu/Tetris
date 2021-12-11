package view;

import javax.swing.JPanel;
import MySocket.ExchangeThread;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;




/**
 * 单人模式下的界面
 *
 */
public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExchangeThread exchangeThread;
    public GameWindow W1;
    public GameWindow W2;
	public int a,b;
	public int wyd=0;

	/**
	 * Create the panel.
	 */
	public GamePanel(int x,int y) {
		setLayout(null);  
        a=x;
        b=y;
        
        W1=new GameWindow(this,a,1);
        W2=new GameWindow(this,b,2);
        W1.setBounds(0,0,480,610);
        this.add(W1);
        W2.setBounds(470,0,310,610);
        this.add(W2);
        W1.requestFocus();
        //W2.requestFocus();
	}

	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		ImageIcon icon=new ImageIcon("Sucai/background2.jpg");
	    Image background=icon.getImage().getScaledInstance(800,650,Image.SCALE_DEFAULT);
	    icon.setImage(background);
	    background=icon.getImage();
	    g.drawImage(background, 0, 0,null); 
	}
	
}
