package game.mechanics;

import game.window.Main;

import javax.swing.SwingUtilities;

public class GameIA {

	private static int getComputerCasesInColumn(int column){
		
		int nb = 0;
		
		nb = (Game.getCaseOwner(1, column) == Game.COMPUTER)? nb+1 : nb;
		nb = (Game.getCaseOwner(2, column) == Game.COMPUTER)? nb+1 : nb;
		nb = (Game.getCaseOwner(3, column) == Game.COMPUTER)? nb+1 : nb;
		
		return nb;
		
	}
	
	private static int getComputerCasesInLine(int line){
		
		int nb = 0;
		
		nb = (Game.getCaseOwner(line, 1) == Game.COMPUTER)? nb+1 : nb;
		nb = (Game.getCaseOwner(line, 2) == Game.COMPUTER)? nb+1 : nb;
		nb = (Game.getCaseOwner(line, 3) == Game.COMPUTER)? nb+1 : nb;
		
		return nb;
		
	}
	
	private static int getComputerCasesInDiagonale(int diagonale){
		
		int nb = 0;
		
		if(diagonale == 1 || diagonale == 3){
			
			nb = (Game.getCaseOwner(1, 1) == Game.COMPUTER)? nb+1 : nb;
			nb = (Game.getCaseOwner(3, 3) == Game.COMPUTER)? nb+1 : nb;
			
		}else if(diagonale == 2 || diagonale == 3){
			
			nb = (Game.getCaseOwner(1, 3) == Game.COMPUTER)? nb+1 : nb;
			nb = (Game.getCaseOwner(3, 1) == Game.COMPUTER)? nb+1 : nb;
			
		}
		
		nb = (Game.getCaseOwner(2, 2) == Game.COMPUTER)? nb+1 : nb;
		
		return nb;
		
	}
	
	private static int getPlayerCasesInDiagonale(int diago){
		
		int nb = 0;
		
		if(diago == 1 || diago == 3){
			
			nb = (Game.getCaseOwner(1, 1) == Game.PLAYER)? nb+1 : nb;
			nb = (Game.getCaseOwner(3, 3) == Game.PLAYER)? nb+1 : nb;
			
		}else if(diago == 2 || diago == 3){
			
			nb = (Game.getCaseOwner(1, 3) == Game.PLAYER)? nb+1 : nb;
			nb = (Game.getCaseOwner(3, 1) == Game.PLAYER)? nb+1 : nb;
			
		}
		
		nb = (Game.getCaseOwner(2, 2) == Game.PLAYER)? nb+1 : nb;
		
		return nb;
		
	}
	
	private static int getPlayerCasesInColumn(int column){
			
		int nb = 0;
		
		nb = (Game.getCaseOwner(1, column) == Game.PLAYER)? nb+1 : nb;
		nb = (Game.getCaseOwner(2, column) == Game.PLAYER)? nb+1 : nb;
		nb = (Game.getCaseOwner(3, column) == Game.PLAYER)? nb+1 : nb;
		
		return nb;
		
	}
	
	private static int getPlayerCasesInLine(int line){
		
		int nb = 0;
		
		nb = (Game.getCaseOwner(line, 1) == Game.PLAYER)? nb+1 : nb;
		nb = (Game.getCaseOwner(line, 2) == Game.PLAYER)? nb+1 : nb;
		nb = (Game.getCaseOwner(line, 3) == Game.PLAYER)? nb+1 : nb;
		
		return nb;
		
	}
	
	public static boolean hasPlayerWin(){
		
		for(int i = 1; i <= 3; i++){
			
			if(getPlayerCasesInColumn(i) == 3 || getPlayerCasesInLine(i) == 3 || getPlayerCasesInDiagonale(i) == 3){
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public static int[] getResponse(){
		
		System.out.println("--RESPONSE--");
		
		int[] rep = new int[2];
		
		//TODO COMPUTER CASES CHECK
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
				
				if(Game.getCaseOwner(i, j) == Game.COMPUTER){
					
					int diagonale = Game.getDiagonale(i, j);
					final int message;
					
					if(getComputerCasesInColumn(j) == 2 && Game.getFreeSpaceInColumn(j) != -1){
						
						rep[0] = Game.getFreeSpaceInColumn(j);
						rep[1] = j;
						if(!hasPlayerWin()){
							message = Main.COMPUTER_WIN;
						}else{
							message = Main.PLAYER_WIN;	
						}
						
						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								GameListener.finished = true;
								Main.showMessage(message);
								
							}
						});
						
						return rep;
						
					}else if(getComputerCasesInLine(i) == 2 && Game.getFreeSpaceInLine(i) != -1){
						
						rep[0] = i;
						rep[1] = Game.getFreeSpaceInLine(i);
						
						if(!hasPlayerWin()){
							message = Main.COMPUTER_WIN;
						}else{
							message = Main.PLAYER_WIN;	
						}
						
						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								GameListener.finished = true;
								Main.showMessage(message);
								
							}
						});
						
						return rep;
						
					}else if(getComputerCasesInDiagonale(diagonale) == 2 && Game.getFreeSpaceInDiagonale(diagonale) != null){
						
						System.err.println("Analyse, diagonale : "+diagonale);
						
						rep = Game.getFreeSpaceInDiagonale(diagonale);
						
						if(!hasPlayerWin()){
							message = Main.COMPUTER_WIN;
						}else{
							message = Main.PLAYER_WIN;			
						}
						
						SwingUtilities.invokeLater(new Runnable() {
							
							public void run() {
								GameListener.finished = true;
								Main.showMessage(message);
								
							}
						});
						
						return rep;
						
					}
					
				}
			
			}
			
		}
		
		//TODO PLAYER CASES CHECK
		
		System.out.println("PLAYER CHECK");
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
				
				if(Game.getCaseOwner(i, j) == Game.PLAYER){
					
					int diago = Game.getDiagonale(i, j);
					
					if(getPlayerCasesInDiagonale(diago) == 2 && Game.getFreeSpaceInDiagonale(diago) != null){
						
						System.err.println("1");
						rep = Game.getFreeSpaceInDiagonale(Game.getDiagonale(i, j));
						return rep;
						
					}else if(getPlayerCasesInColumn(j) == 2 && Game.getFreeSpaceInColumn(j) != -1){
						
						System.err.println("2");
						rep[0] = Game.getFreeSpaceInColumn(j);
						rep[1] = j;
						return rep;
						
					}else if(getPlayerCasesInLine(i) == 2 && Game.getFreeSpaceInLine(i) != -1){
						
						System.err.println("3, line : "+i);
						rep[0] = i;
						rep[1] = Game.getFreeSpaceInLine(i);
						return rep;
						
					}
					
				}
				
			}
			
		}
		
		System.out.println("PLAYER CHECK AFTER");
		
		for(int i = 1; i <= 3; i++){
		
			if(getComputerCasesInDiagonale(i) > 1 && Game.getFreeSpaceInDiagonale(i) != null){
				
				System.out.println("1");
				return Game.getFreeSpaceInDiagonale(i);
				
			}else if(Game.getFreeSpaceAmountInColumn(i) > 1 && getComputerCasesInColumn(i) == 1){
				
				System.out.println("2");
				rep[0] = Game.getFreeSpaceInColumn(i);
				rep[1] = i;
				return rep;
				
			}else if(Game.getFreeSpaceAmountInLine(i) > 1 && getComputerCasesInLine(i) == 1){
				
				rep[0] = i;
				rep[1] = Game.getFreeSpaceInLine(i);
				return rep;
				
			}
			
		}
		
		System.out.println("random choice");
		
		if(!Game.isFree(2, 2)){
		
			return Game.getRandomSpace();
		
		}
		
		rep[0] = 2;
		rep[1] = 2;
		return rep;
		
	}
	
}