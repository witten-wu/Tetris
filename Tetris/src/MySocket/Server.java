package MySocket;

import java.awt.BorderLayout;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * ��������
 */

public class Server{
	private static BridgeThread serverExchangeThread;
    private static int PORT = 12345;
    private static Socket s;
    private static Socket s2;
    static BufferedWriter bufferedWriter1;
    static BufferedWriter bufferedWriter2;
    static BufferedReader bufferedReader1;
    static BufferedReader bufferedReader2;
    
    public static void Init(JTextArea JT){
    	try{
    		 @SuppressWarnings("resource")
			 ServerSocket ss = new ServerSocket(PORT);
             s = ss.accept();
             bufferedReader1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
             bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
             JT.append(bufferedReader1.readLine()+"\n");
             JT.append("�ȴ�Client2����......\n");
             s2 = ss.accept();
             bufferedReader2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
             bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(s2.getOutputStream()));
             JT.append(bufferedReader2.readLine()+"\n");
             JT.append("Ready now!");
             ready(bufferedWriter1);
             ready(bufferedWriter2);
             serverExchangeThread=new BridgeThread(s,s2);
             serverExchangeThread=new BridgeThread(s2,s);
    	}catch (IOException e) {
			e.printStackTrace();
		  }
    }
    public static BridgeThread getExchangeThread(){
        return serverExchangeThread;
    }
    
    public static void ready(BufferedWriter bufferedWriter){
		 try {
			 bufferedWriter.write("ready");
			 bufferedWriter.newLine();
	         bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 JFrame J=new JFrame();
         JTextArea jtaLog = new JTextArea();
         JScrollPane scrollPane = new JScrollPane(jtaLog);
         J.add(scrollPane, BorderLayout.CENTER);
         jtaLog.append("�˿ں�"+PORT+",������������\n");
         jtaLog.append("�ȴ�Client1����......\n");
         J.setTitle("Server");
 		 J.setSize(440,410);
 		 J.setLocationRelativeTo(null);
 		 J.setVisible(true);
 		 J.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		 J.setResizable(false);
		 Server.Init(jtaLog);
	}
}