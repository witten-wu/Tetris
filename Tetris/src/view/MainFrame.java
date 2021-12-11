package view;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *主界面
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 */
    private static String bgmUrl="Sucai\\bgm.wav";
    private static AudioClip bgmAudioClip;
    public JLabel lb1;
    
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bgmPlay();
		
		//设置标题
		this.setTitle("Client");
		//设置窗体大小
		this.setSize(440,410);
		//居中
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//关闭事件
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置默认Panel
		this.setContentPane(new LauncherJPanel(this));
		lb1 = new JLabel();
		lb1.setBounds(30,-70,300,300);
		lb1.setFont(new Font("宋体",2,40));
		lb1.setForeground(Color.GRAY);
        lb1.setText("俄罗斯方块");
        this.add(lb1);
		
		this.setResizable(false);
	}
	 @SuppressWarnings("deprecation")
	public static void bgmPlay(){
	    	if(bgmAudioClip==null){
	    		try {
	                URL cb;
	                File file = new File(bgmUrl);
	                cb = file.toURL();
	                bgmAudioClip = Applet.newAudioClip(cb);
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }
	    	}
	    	try{
	    		bgmAudioClip.loop();
	    	}catch(Exception exception){
	    		exception.printStackTrace();
	    	}
	    }
}
