package MySocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.EventQueue;
import view.MainFrame;
import view.GamePanel;

/**
 * ¿Í»§¶Ë
 */
public class Client {
    private static ExchangeThread clientExchangeThread;
    private static int PORT = 12345;
    private static GamePanel gap;
    private static Socket socket;
    static BufferedReader bufferedReader;
    static BufferedWriter bufferedWriter;
    public static void Init(String a){
        try {
            socket = new Socket("127.0.0.1",PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("Client"+a+" is connect!");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            if(bufferedReader.readLine()=="ready");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void runthread(){
    	clientExchangeThread=new ExchangeThread(socket,gap);
    }
    public static ExchangeThread getExchangeThread(){
        return clientExchangeThread;
    }
    public static void getpanel(GamePanel gap1){
    	gap=gap1;
    }
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				MainFrame frame = new MainFrame();
    				frame.setVisible(true);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
}


