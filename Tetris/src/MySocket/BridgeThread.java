package MySocket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class BridgeThread implements Runnable {
	public Socket s1;
	public Socket s2;	
    BufferedReader bufferedReader1;
    BufferedWriter bufferedWriter1;
    BufferedReader bufferedReader2;
    BufferedWriter bufferedWriter2;
	public BridgeThread(Socket socket1,Socket socket2) {
        this.s1 = socket1;
        this.s2 = socket2;
        try {
        	bufferedReader1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(s1.getOutputStream()));
            bufferedReader2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
            bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(s2.getOutputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        new Thread(this).start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            while(true) {
            	String mess=bufferedReader1.readLine();
            	sendMessage(mess,bufferedWriter2);
            }
        } catch (Exception e) {
        	try {
				s1.close();
	        	s2.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	  public void sendMessage(String str,BufferedWriter bufferedWriter){
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
