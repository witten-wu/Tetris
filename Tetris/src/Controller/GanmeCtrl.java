package Controller;



import java.util.TimerTask;

import javax.swing.JOptionPane;
import view.GamePanel;
import view.GameWindow;

public class GanmeCtrl extends TimerTask{
	
	public static final int LEFTMOVE = 2;
	public static final int RIGHTMOVE =3;
	public static final int DOWNMOVE  =4;
	
	private GamePanel gw = null;
	private GameWindow gP =null;
	private boolean [][]curShape =new boolean[4][4]; 
	private int [] A={0,6,1,5,2,4,3,3,4,2,5,1,6,0};
	private int row =0;
	public int start=0;
	private int col = 3;
	private int next = 0;
	private int cur  = 0;
	private int label;
	public GanmeCtrl(GamePanel G,GameWindow gamePl,int a,int b){
		super();
		this.gw=G;
		this.start=a;
		this.label=b;
		this.gP = gamePl;
		gP.j=start;
		next = A[start];
		nextShape();
	}
	@Override
	public void run() {
		moveShape(DOWNMOVE);
			
	}
	public void nextShape(){
		cur = next;
		start=(start+1)%14;
		next = A[start];
		row=0;
		col=3;
		changeNextBlock();
		getShape();
		changeBlock();
		boolean bl = changeMap();
		if(bl==true){
			if(label==1)
			{
				gw.wyd=1;
				cancel();
				gP.timer.cancel();
				JOptionPane.showMessageDialog(null, "ƒ„ ‰¡À£°"); 
				gP.state = GameWindow.STOP;
			}
			if(label==2)
			{					
				cancel();
				gP.timer.cancel();
				if(gw.wyd!=1)
					JOptionPane.showMessageDialog(null, "ƒ„”Æ¡À£°"); 
				gP.state = GameWindow.STOP;
			}
		}
		
	}
	public void changeShape(){
		if(col == -1||col ==8){
			return ;
		}
		if(cur ==0){
			if(col == 7){
				return ;
			}
			if(gP.myMap[row][col] == true||gP.myMap[row][col+1] == true ||gP.myMap[row+1][col]==true){
				return ;
			}
			for(int i =1;i<4;i++){
				for(int j =1;j<4;j++){
					if(gP.myMap[row+i][col+j] == true){
						return ;
					}
				}
			}
			for(int i =0;i<4;i++){
				boolean temp = curShape[1][i];
				curShape[1][i] = curShape[i][1];
				curShape[i][1] = temp;
			}
			changeBlock();
		}
		else if(cur == 3){
			return ;
		}
		else{
			boolean [][]temp = new boolean [3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					temp[j][2-i] = curShape[i][j];
					if(gP.myMap[row+i][col+j] == true){
						return ;
					}
				}
			}
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
		           curShape[i][j] = temp[i][j];
				}
			}
			changeBlock();
			
		
		}
	}
	public void getShape(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
                curShape[i][j] = false;
			}
		}
		if(cur ==0){
			for(int i=0;i<4;i++){
				curShape[i][1] = true;
			}
		    return ;
		}
		if(cur==1){
			curShape[0][0] = true;
			for(int i=0;i<3;i++){
				curShape[1][i] = true;
			}
			return;
		}
		if(cur==2){
			curShape[0][2] = true;
			for(int i=0;i<3;i++){
				curShape[1][i] = true;
			}
			return;
		}
		if(cur==3){
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
	                curShape[i][j] = true;
				}
			}
			return ;
		}
		if(cur==4){
			curShape[0][1] = true;
			curShape[0][2] = true;
			curShape[1][0] = true;
			curShape[1][1] = true;
			return ;
		}
		if(cur==5){
			curShape[0][1] = true;
			for(int i=0;i<3;i++){
				curShape[1][i] = true;
			}
			return;
		}
		if(cur==6){
			curShape[0][0] = true;
			curShape[0][1] = true;
			curShape[1][1] = true;
			curShape[1][2] = true;
			return ;
		}
	}	

	public void moveShape(int flag){
		switch(flag){
			case(LEFTMOVE):{
				if(col == 0){
					for(int i =0;i<4;i++){
						if(curShape[i][0] == true)
							return ;
					}
					col = -1;
				}
				else if(col >0){
					for(int i=0;i<4;i++){
						for(int j=0;j<4;j++){
							if(curShape[i][j] == true){
								if(gP.myMap[row+i][col+j-1] == true)
									return ;
							}
						}
					}
					col-=1;
				}
				else
					return ;
				changeBlock();
				break;
			}
		
			case(RIGHTMOVE):{
				if(col == 6){
					for(int i=0;i<4;i++){
						if(curShape[i][3] == true){
							return ;
						}
						if(curShape[i][2] == true && gP.myMap[row+i][col+3] == true)
							return ;
					}
					col++;
					
				}
				else if(col == 7){
					for(int i =0;i<4;i++){
						if(curShape[i][2] == true){
							return ;
						}
						if(curShape[i][1]==true&&gP.myMap[row+i][col+2] == true){
							return ;
						}
					}
					col++;
				}
				else if(col>7){
					return ;
				}
				else{
					for(int i=3;i>0;i--){
						for(int j=0;j<4;j++){
							if(curShape[j][i] == true &&gP.myMap[row+j][col+i+1] == true){
								return ;
							}
						}
					}
					col++;
				}
				changeBlock();
				break;
			}
			case(DOWNMOVE):{
				boolean bl =changeMap();
				if(bl == false){
					row++;
				}
				else{
					nextShape();
					gP.j++;
					Score();
				}
				changeBlock();
				
			}
			break;
			
		}
	}
	public boolean changeMap(){
		for(int i=3;i>=0;i--){
			for(int j=0;j<4;j++){
				
				if(curShape[i][j] == true){
					if(row+i+1 == 20){
						for(i=0;i<4;i++){
							for(j=0;j<4;j++){
								if(curShape[i][j] == true){
									gP.myMap[row+i][col+j] = true;
								}
							}
						}
						
						return true;
						
					}
					if(gP.myMap[row+i+1][col+j] == true){
						for(i=0;i<4;i++){
							for(j=0;j<4;j++){
								if(curShape[i][j] == true){
									gP.myMap[row+i][col+j] = true;
								}
							}
						}

						return true;
					}
				}
				
			}
		}
		return false;
		
    }
	@SuppressWarnings("unchecked")
	public void changeBlock(){
		gP.block.clear();
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(curShape[i][j] == true ){
					gP.block.add(row+i);
					gP.block.add(col+j);
				}
			}
		}
		gP.repaint();
	}
	public void Score(){
		int i,j;
		for( i=0;i<20;i++){
			for( j=0;j<10;j++){
				if(gP.myMap[i][j] == false){
					break;
				}
			}
			if(j==10){
				for(int x=i;x>0;x--){
					for(j=0;j<10;j++){
						gP.myMap[x][j] = gP.myMap[x-1][j];
					}
				}
				for(j=0;j<10;j++){
					gP.myMap[0][j]=false;
				}
				gP.score+=100;
				if(label==1){
				if(gw.exchangeThread!=null){
					gw.exchangeThread.sendMessage(Integer.toString(gP.score));}}
				gP.lb2.setText(""+gP.score);
			}
		}
	}
	public void changeNextBlock(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
                gP.nextBlock[i][j] = false;
			}
		}
		if(next ==0){
			for(int i=0;i<4;i++){
				gP.nextBlock[i][1] = true;
			}
		    return ;
		}
		if(next==1){
			gP.nextBlock[0][0] = true;
			for(int i=0;i<3;i++){
				gP.nextBlock[1][i] = true;
			}
			return;
		}
		if(next==2){
			gP.nextBlock[0][2] = true;
			for(int i=0;i<3;i++){
				gP.nextBlock[1][i] = true;
			}
			return;
		}
		if(next==3){
			for(int i=0;i<2;i++){
				for(int j=0;j<2;j++){
	                gP.nextBlock[i][j] = true;
				}
			}
			return ;
		}
		if(next==4){
			gP.nextBlock[0][1] = true;
			gP.nextBlock[0][2] = true;
			gP.nextBlock[1][0] = true;
			gP.nextBlock[1][1] = true;
			return ;
		}
		if(next==5){
			gP.nextBlock[0][1] = true;
			for(int i=0;i<3;i++){
				gP.nextBlock[1][i] = true;
			}
			return;
		}
		if(next==6){
			gP.nextBlock[0][0] = true;
			gP.nextBlock[0][1] = true;
			gP.nextBlock[1][1] = true;
			gP.nextBlock[1][2] = true;
			return ;
		}
	}	

}
