package game.mechanics;

import game.window.Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class GameListener implements MouseListener{

	private int y;
	private int x;
	private int size;
	
	public static boolean finished = false;
	
	private Main gInterface;
	
	private int points[][];
	
	public void setGameInterface(Main g){
		
		gInterface = g;
		
	}
	
	public void setY(int y){
		
		this.y = y;
		
	}
	
	public void setX(int x){
		
		this.x = x;
		
	}
	
	public void setSize(int size){
		
		this.size = size;
		
	}
	
	public void load(){
		
		points = new int[9][4];
		
		points[0][0] = x;
		points[0][1] = x+size;
		points[0][2] = y;
		points[0][3] = y+size;
		
		points[1][0] = x+size+1;
		points[1][1] = x+(size*2);
		points[1][2] = y;
		points[1][3] = y+size;
		
		points[2][0] = x+(size*2)+1;
		points[2][1] = x+(size*3);
		points[2][2] = y;
		points[2][3] = y+size;
		
		points[3][0] = x;
		points[3][1] = x+size;
		points[3][2] = y+size+1;
		points[3][3] = y+(size*2);
		
		points[4][0] = x+size+1;
		points[4][1] = x+(size*2);
		points[4][2] = y+size+1;
		points[4][3] = y+(size*2);
		
		points[5][0] = x+(size*2)+1;
		points[5][1] = x+(size*3);
		points[5][2] = y+size+1;
		points[5][3] = y+(size*2);
		
		points[6][0] = x;
		points[6][1] = x+size;
		points[6][2] = y+(size*2)+1;
		points[6][3] = y+(size*3);

		points[7][0] = x+size+1;
		points[7][1] = x+(size*2);
		points[7][2] = y+(size*2)+1;
		points[7][3] = y+(size*3);

		points[8][0] = x+(size*2)+1;
		points[8][1] = x+(size*3);
		points[8][2] = y+(size*2)+1;
		points[8][3] = y+(size*3);
		
	}
	
	private void getSquare(int x, int y){
		
		if(finished){
			return;
		}
		
		int p = 1;
		
		for(int point[] : points){
			
			if(x > point[0] && x < point[1] && y > point[2] && y < point[3]){
				
				int data[] = Game.convertPosToLineAndColumn(p);
				
				System.out.println("Owner : "+Game.getCaseOwner(data[0], data[1]));
				System.out.println("Column : "+data[1]);
				System.out.println("Line : "+data[0]);
				
				System.out.println("Clicked case : "+p);
				boolean response = gInterface.isChoosed(p);
					
				if(response == false){
					
					System.err.println("Case already completed");
					
				}else{	
					
					data = Game.convertPosToLineAndColumn(p);
					
					System.err.println("line : "+data[0]+" column : "+data[1]);
					
					Game.setCase(data[0], data[1], true);
							
					data = GameIA.getResponse();
					
					if(data != null){
						
						Game.setCase(data[0], data[1], false);
						
						System.out.println("IA : ");
						System.out.println("Line : "+data[0]);
						System.out.println("Column : "+data[1]);
						
						System.out.println("Computer pos : "+Game.convertLineAndColumnToPos(data[0], data[1]));
						
						gInterface.play(p, Game.convertLineAndColumnToPos(data[0], data[1]));
						
						Game.showGame();
						
						if(GameIA.hasPlayerWin()){
							
							finished = true;
							Main.showMessage(Main.PLAYER_WIN);
							return;
							
						}
						
					}else{
						
						if(Game.getFreeRemainingCase() == 0){
						
							finished = true;
							gInterface.play(p, -1);
							if(GameIA.hasPlayerWin()){
								Main.showMessage(Main.PLAYER_WIN);
							}else{
								Main.showMessage(Main.STANDARD);
							}
							return;
						}
						
					}
					
					if(Game.getFreeRemainingCase() == 0){
						finished = true;
						Main.showMessage(Main.STANDARD);
					}
					
				}
				
			}
			
			p++;
			
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		getSquare(e.getX(), e.getY());
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
