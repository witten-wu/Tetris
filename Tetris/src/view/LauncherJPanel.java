package view;

import javax.swing.JPanel;

import MySocket.Client;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * 启动界面，MainFrame启动后首先调用这个panel
 *
 */
public class LauncherJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFrame mainFrame;
	public GamePanel onlinePanel;
	public GamePanel onlinePanel2;
	/**
	 * Create the panel.
	 */
	public LauncherJPanel(MainFrame mainFrame1) {
		this.mainFrame=mainFrame1;
		setLayout(null);
		

		/*MyButton btnOffline = new MyButton("Sucai/null.png","单人模式",123,50);
		btnOffline.setFont(new Font("黑体", Font.BOLD, 16));
		btnOffline.setForeground(Color.GRAY);
		btnOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GamePanel offlinePanel=new GamePanel(0,2);
				mainFrame.setTitle("俄罗斯方块");
				mainFrame.setSize(800,650);
				mainFrame.setContentPane(offlinePanel);
			}
		});
		btnOffline.setBounds(30, 200, 123, 28);
		add(btnOffline);*/

		
		
		MyButton btnMode1 = new MyButton("Sucai/null.png","创建房间",123,50);
		btnMode1.setForeground(Color.GRAY);
		btnMode1.setFont(new Font("黑体", Font.BOLD, 16));
		btnMode1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showInputDialog("请输入房间号:");
				//Server.Init(Integer.parseInt(port));
				Client.Init("1");
				onlinePanel=new GamePanel(3,5);
				Client.getpanel(onlinePanel);
				Client.runthread();	
				onlinePanel.exchangeThread=Client.getExchangeThread();
				System.out.println("连接成功");
				mainFrame.setTitle("俄罗斯方块");
				mainFrame.setSize(800,650);
				mainFrame.setContentPane(onlinePanel);
			}
		});
		btnMode1.setBounds(30, 231, 123, 28);
		add(btnMode1);
		
		MyButton btnMode2 = new MyButton("Sucai/null.png","进入房间",123,50);
		btnMode2.setForeground(Color.GRAY);
		btnMode2.setFont(new Font("黑体", Font.BOLD, 16));
		btnMode2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog("请输入房间号:");
				//Client.Init(Integer.parseInt(port2));
				Client.Init("2");
				onlinePanel2=new GamePanel(5,3);
				Client.getpanel(onlinePanel2);
				Client.runthread();
				onlinePanel2.exchangeThread=Client.getExchangeThread();
				System.out.println("连接成功");
				mainFrame.setTitle("俄罗斯方块");
				mainFrame.setSize(800,650);
				mainFrame.setContentPane(onlinePanel2);
			}
		});
		btnMode2.setBounds(30, 269, 123, 33);
		add(btnMode2);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		ImageIcon icon=new ImageIcon("Sucai/background.jpg");
	    Image background=icon.getImage().getScaledInstance(520,430,Image.SCALE_DEFAULT);
	    icon.setImage(background);
	    background=icon.getImage();
	    g.drawImage(background, -20, -15,null); 
	}

}
