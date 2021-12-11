package MySocket;


import java.io.*;
import java.net.Socket;

import Controller.GanmeCtrl;
import view.GamePanel;


/**
 * 进程通信线程
 */

public class ExchangeThread implements Runnable {
    private Socket socket;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    DataInputStream is;
    DataOutputStream os;
    private GamePanel panel;

    
    public static boolean isNum(String str){
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
    
    public ExchangeThread(Socket socket1,GamePanel G) {
        this.socket = socket1;
        this.panel=G;
        try {
        	is = new DataInputStream(socket.getInputStream());
        	os = new DataOutputStream(socket.getOutputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(this).start();
    }
    
    public void run() {
        try {
            while(true) {
                // 这里负责读
                String mess = bufferedReader.readLine();
                if(isNum(mess)){
                	int score=Integer.parseInt(mess);
                	panel.W1.lb5.setText(""+score);
                }
                switch (mess){
                    case "u":
                    	panel.W2.gamectrl.changeShape();
                        break;
                    case "d":
                    	panel.W2.gamectrl.moveShape(GanmeCtrl.DOWNMOVE);
                        break;
                    case "l":
                    	panel.W2.gamectrl.moveShape(GanmeCtrl.LEFTMOVE);
                        break;
                    case "r":
                    	panel.W2.gamectrl.moveShape(GanmeCtrl.RIGHTMOVE);
                        break;     
                    default:
                    	break;
                }
            }
        } catch (Exception e) {
        	try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } 
    }

    public void sendMessage(String str){
        // 这里负责写
        try {
        	bufferedWriter.write(str);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}