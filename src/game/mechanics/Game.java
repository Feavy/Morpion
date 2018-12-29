package game.mechanics;

import game.window.Main;

import java.util.Random;

public class Game {

	public static int COMPUTER = 2;
	public static int PLAYER = 1;
	
	public static int[][] tbl = new int[4][4];
	
	// tbl[line][column];
	
	public static int[] convertPosToLineAndColumn(int pos){
		
		int rep[] = new int [2];
		
		rep[0] = getLine(pos);
		rep[1] = getColumn(pos);
		
		return rep;
		
	}
	
	public static int convertLineAndColumnToPos(int line, int column){
		
		int data[] = {};
		
		for(int i = 1; i <= 9; i++){
			
			data = convertPosToLineAndColumn(i);
			if(data[0] == line && data[1] == column){
				
				return i;
				
			}
			
		}
		
		return -1;
		
	}
	
	public static void initialize(){
		
		Main.msgShowed = false;
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
			
				tbl[i][j] = 0;
			
			}
			
		}
		
		System.out.println(tbl);
		
	}
	
	public static void showGame(){
		
		System.out.println("GAME");
		String g = "";
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
				
				g += tbl[i][j];
				
			}
			
			g += "/n";
			
		}
		
		System.out.println(g);
		
	}
	
	public static void setCase(int line, int column, boolean isPlayer){
		
		tbl[line][column] = (isPlayer) ? PLAYER : COMPUTER;
		
	}
	
	public static int getFreeRemainingCase(){
		
		int nb = 0;
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
				
				nb = (tbl[i][j] == 0)? nb+1 : nb;
				
			}
			
		}
		
		return nb;
		
	}
	
	public static int getFreeSpaceInLine(int line){
		
		for(int i = 1; i<= 3; i++){
			
			if(tbl[line][i] == 0){
				
				return i;
				
			}
		}
		
		System.err.println("There is no free space in line "+line);
		return -1;
		
	}
	
	public static int getFreeSpaceInColumn(int column){
		
		for(int i = 1; i<= 3; i++){
			
			if(tbl[i][column] == 0){
				
				return i;
				
			}
		}
		
		System.err.println("There is no free space in column "+column);
		return -1;
		
	}
	
	public static int[] getFreeSpaceInDiagonale(int diagonale){
		
		if(diagonale == 0){
			return null;
		}
		
		int[] rep = new int[2];
		
		if(isFree(2, 2)){
			
			rep[0] = 2;
			rep[1] = 2;
			return rep;
			
		}
		
		if(diagonale == 1 || diagonale == 3){
			
			for(int i = 1; i <= 3; i+=2){
				
				if(isFree(i, i)){
					
					rep[0] = i;
					rep[1] = i;
					return rep;
					
				}
				
			}
			
		}else{
		
			if(isFree(1, 3)){
				
				rep[0] = 1;
				rep[1] = 3;
				return rep;
				
			}else if(isFree(3, 1)){
				
				rep[0] = 3;
				rep[1] = 1;
				return rep;
				
			}
			
		}
		
		return null;
		
	}
	
	public static int getFreeSpaceAmountInColumn(int column){
		
		int amount = 0;
		
		for(int i = 1; i<= 3; i++){
			
			if(tbl[i][column] == 0){
				
				amount++;
				
			}
		}
		
		return amount;
		
	}
	
	public static int getFreeSpaceAmountInLine(int line){
		
		int amount = 0;
		
		for(int i = 1; i<= 3; i++){
			
			if(tbl[line][i] == 0){
				
				amount++;
				
			}
		}
		
		System.err.println("There is no free space in line "+line);
		return amount;
		
	}
	
	public static int[] getRandomSpace(){
		
		int[][] rand = new int[9][2];
		int rnb = 0;
		
		for(int i = 1; i <= 3; i++){
			
			for(int j = 1; j <= 3; j++){
				
				if(tbl[i][j] == 0){
					
					rand[rnb][0] = i;
					rand[rnb][1] = j;
					rnb++;
					
				}
				
			}
			
		}
		
		System.out.println("rnb : "+rnb);
		
		if(rnb == 0)	
			return rand[0];
		
		Random r = new Random();
		int number = r.nextInt(rnb);
		return rand[number];
		
	}
	
	public static boolean isFree(int line, int column){
		
		return (tbl[line][column] == 0)? true: false;
		
	}
	
	public static int[][] getGameTable(){
		
		return tbl;
		
	}
	
	public static int getCaseOwner(int line, int column){
		
		return tbl[line][column];
		
	}
	
	public static boolean isInDiagonale(int line, int column){
		
		if(line == 2 && column == 2){
			return true;
		}else if(column == 1 || column == 3){
			return true;
		}
		
		return false;
		
	}
	
	public static int getDiagonale(int line, int column){
		
		if(isInDiagonale(line, column)){
			if(line == 2){
				return 3;
			}
			if(line == column){
				return 1;
			}
			return 2;
		}
		return 0;
	}
	
	private static int getLine(int pos){
		
		if(pos < 4){
			
			return 1;
			
		}else if(pos > 6){
			
			return 3;
			
		}
		
		return 2;
		
	}
	
	private static int getColumn(int pos){
		
		String pattern1 = "^[1|4|7]$";
		String pattern2 = "^[2|5|8]$";
		
		if(pos % 3 == 0){
			
			return 3;
			
		}
		
		if(String.valueOf(pos).matches(pattern2)){
			
			return 2;
			
		}else if(String.valueOf(pos).matches(pattern1)){
			
			return 1;
			
		}
		
		return 0;
		
	}
	
}
