import java.util.Arrays;
import java.util.HashSet;

public class Board {
	
	private int user;
	private int ai;
	private int scoreBlack;
	private int scoreWhite;
	private int winner;
	private int board[][] = new int[][]
		{{0,0,0,0,0,0,0,0},
		 {0,0,0,0,0,0,0,0},
		 {0,0,0,0,0,0,0,0},
		 {0,0,0,1,2,0,0,0},
		 {0,0,0,2,1,0,0,0},
		 {0,0,0,0,0,0,0,0},
		 {0,0,0,0,0,0,0,0},
		 {0,0,0,0,0,0,0,0}}; 
		 
		
		 
	//kataskeyasths gia clone toy pinaka
    public Board(Board b){
    	Board board = new Board();
    	board.setScoreBlack(b.getScoreBlack());
    	board.setScoreWhite(b.getScoreWhite());
    	
    	
    	board.setUser(b.getUser());
    	board.setAi(b.getAi());
    	board.setBoard(b.getBoard());
    	
    	/*for(int i = 0; i < 8; i++){
    		for(int j = 0; j < 8; j++){
    			b.board[i][j] = this.board[i][j];
    		}
    	}*/
    }//end Board()
    
    //aplos kataskeyasths pou arxikoipoiei ta score
    public Board(){
    	scoreBlack = 0;
    	scoreWhite = 0;
    }
    		
	//methodos gia na paroume to skor twn asprwn
	public int getScoreWhite(){
		int white = 0;
		int[][] b = getBoard();
		
		for(int i = 0; i < 8;i++){
			for(int j = 0; j < 8; j++){
				if(b[i][j] == 1)
					white++;
			}
		}
		
		return white;
	}
	
	//set methodos toy board
	public void setBoard(int[][] board){
		this.board = board;
	}
	
	// setters kai getters
	public int[][] getBoard(){
		return board;
	}
	
	public int getUser(){
		return user;
	}
	
	//epistrefei ton arithmo pou antiprosopeyoun ta poulia toy ypologisth
	public int getAi(){
		return ai;
	}
	
	//epistrefei ton arithmo pou antiprosopeyoun ta poulia toy paixth
	public void setUser(int user){
		this.user = user;
	}
	
	public void setAi(int ai){
		this.ai = ai;
	}
	
	
	public void setScoreBlack(int scoreBlack){
		this.scoreBlack = scoreBlack;
	}
	
	public void setScoreWhite(int scoreWhite){
		this.scoreWhite = scoreWhite;
	}
	
	//to skor gia ta mayra poulia
	public int getScoreBlack(){
		int black = 0;
		int[][] b = getBoard();
		
		for(int i = 0; i < 8;i++){
			for(int j = 0; j < 8; j++){
				if(b[i][j] == 2)
					black++;
			}
		}
		
		return black;
		
	}
	
	//methods pou dinei poios paixths nikhse 1 gia aspra 2 gia maura 0 gia isopalia
	public int getWinner(){
		if(availableMovesBlack().isEmpty() && availableMovesWhite().isEmpty()){
			int black = 0;
			int white = 0;
			int[][] b = getBoard();
			
			for(int i = 0; i < 8;i++){
				for(int j = 0; j < 8; j++){
					if(b[i][j] == 1) //aspro
						white++;
					else if(b[i][j] == 2) //mavro
						black++;
				}
			}
			
			if(black > white)
				return 2;
			else
				return 1;
		}//end if
		
		return 0;
	}//end of getWinner
	
	//methodos pou epistrefei an exoume nikhth
	public boolean isWinner(){
		if(getWinner() == 1 || getWinner() == 2)
			return true;
		return false;
	}
	
	
	//methodos gia na paroume tis diathesimes kinhseis gia ta mayra poulia
	public HashSet<Checker> availableMovesBlack(){
		
		HashSet<Checker> movesB = new HashSet<>(); //pairnoume HashSet gia na apofygoume diplotypa
		
		
		//System.out.println("prin thn for");
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8;j++){ //gia kathe thesh ston pinaka
				
									   //an broume kanh thesi
				if(board[i][j] == 0){
					
					int a = i;			//krata thn thesh
					int b = j;
					
					//elenxos dexia ths kenhs theshs
					if(j+1 <=7){
						if(board[i][j+1] == 1){ //an dexia sou exeis aspro 
							//System.out.println("Koitaw dexia");
							j++; //proxwra
							while(j+1 <= 7 && board[i][j] == 1) j++; //synexise na proxwras oso exeis aspra poulia
							if(board[i][j] == 2){ //an vreis diko sou pouli
								
								movesB.add(new Checker(a, b, 2)); //tote einai epitrepth kinhsh
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos aristera ths kenhs theshs
					if(j-1 >= 0){
						if(board[i][j-1] == 1){
							j--;
							while(j-1 >= 0 && board[i][j] == 1) j--;
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos katw ths kenhs theshs
					if(i+1 <=7){
						if(board[i+1][j] == 1){
							i++;
							while(i+1 <= 7 && board[i][j] == 1) i++;
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos panw ths kenhs theshs
					if(i-1 >= 0){
						if(board[i-1][j] == 1){
							i--;
							while(i-1 >= 0 && board[i][j] == 1) i--;
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos gia panw dexia
					if(j+1 <= 7 && i - 1 >= 0){
						if(board[i-1][j+1] == 1){
							j++;
							i--;
							while(i-1 >= 0 && j+1 <= 7 && board[i][j] == 1){
								j++;
								i--;
							}//end while
							
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					//elenxos gia panw aristera
					if(j-1 >= 0 && i - 1 >= 0){
						if(board[i-1][j-1] == 1){
							j--;
							i--;
							while(i-1 >= 0 && j-1 > 0 && board[i][j] == 1){
								j--;
								i--;
							}//end while
							
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					//elenxos gia katw aristera
					if(j-1 >= 0 && i+1 <= 7){
						if(board[i+1][j-1] == 1){
							j--;
							i++;
							while(i+1 <= 7 && j-1 >= 0 && board[i][j] == 1){
								j--;
								i++;
							}//end while
							
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					
					//elenxos gia katw dexia
					if(j+1 <= 7 && i+1 <= 7){
						if(board[i+1][j+1] == 1){
							j++;
							i++;
							while(i+1 <= 7 && j+1 <= 7 && board[i][j] == 1){
								j++;
								i++;
							}//end while
							
							if(board[i][j] == 2){
								
								movesB.add(new Checker(a, b, 2));
								
							}
								
						}
					}//end if
					i = a;
					j = b;

				}//end if				
			}//end for j
		}//end for i
		
		return movesB;
		
	}//end of availableMoves
	
	//gia ta aspra
	public HashSet<Checker> availableMovesWhite(){
		
		HashSet<Checker> movesW = new HashSet<>();
		
		
		//System.out.println("prin thn for");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8;j++){
				
				//an broume kanh thesi
				if(board[i][j] == 0){
					
					int a = i;
					int b = j;
					
					//elenxos dexia ths kenhs theshs
					if(j+1 <=7){
						if(board[i][j+1] == 2){
							//System.out.println("Koitaw dexia");
							j++;
							while(j+1 <= 7 && board[i][j] == 2) j++;
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos aristera ths kenhs theshs
					if(j-1 >= 0){
						if(board[i][j-1] == 2){
							j--;
							while(j-1 >= 0 && board[i][j] == 2) j--;
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos katw ths kenhs theshs
					if(i+1 <=7){
						if(board[i+1][j] == 2){
							i++;
							while(i+1 <= 7 && board[i][j] == 2) i++;
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos panw ths kenhs theshs
					if(i-1 >= 0){
						if(board[i-1][j] == 2){
							i--;
							while(i-1 >= 0 && board[i][j] == 2) i--;
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
						}//end if
					}//end if
					i = a;
					j = b;
					
					//elenxos gia panw dexia
					if(j+1 <= 7 && i - 1 >= 0){
						if(board[i-1][j+1] == 2){
							j++;
							i--;
							while(i-1 >= 0 && j+1 <= 7 && board[i][j] == 2){
								j++;
								i--;
							}//end while
							
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					//elenxos gia panw aristera
					if(j-1 >= 0 && i - 1 >= 0){
						if(board[i-1][j-1] == 2){
							j--;
							i--;
							while(i-1 >= 0 && j-1 > 0 && board[i][j] == 2){
								j--;
								i--;
							}//end while
							
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					//elenxos gia katw aristera
					if(j-1 >= 0 && i+1 <= 7){
						if(board[i+1][j-1] == 2){
							j--;
							i++;
							while(i+1 <= 7 && j-1 >= 0 && board[i][j] == 2){
								j--;
								i++;
							}//end while
							
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
								
						}
					}//end if
					i = a;
					j = b;
					
					
					//elenxos gia katw dexia
					if(j+1 <= 7 && i+1 <= 7){
						if(board[i+1][j+1] == 2){
							j++;
							i++;
							while(i+1 <= 7 && j+1 <= 7 && board[i][j] == 2){
								j++;
								i++;
							}//end while
							
							if(board[i][j] == 1){
								
								movesW.add(new Checker(a, b, 1));
								
							}
								
						}
					}//end if
					i = a;
					j = b;

				}//end if				
			}//end for j
		}//end for i
		
		return movesW;
		
	}//end of availableMoves

	//kanei thn kinhsh symfwna me tous kanones tou paixnidiou
	public void makeMove(Checker c){
		
		
		int player,enemy;
		
		
		if(c.getPlayer() == 1){	//an h kinhsh ginetai apo ta aspra
			player = 1;
			enemy = 2;
		}else{					//an h kinhsh ginetai apo ta mayra
			player = 2;
			enemy = 1;
		}//end if-else
		
		//pare thn thesh kai kane thn kinhsh
		int i = c.getX();
		int j = c.getY();
		board[i][j] = player;
		//krata thn thesh
		int a = i;
		int b = j;
		
		//dexia toy pinaka
		if(j+1 <= 7){
			if(board[i][j+1] == enemy){ //to dipla einai antipaloy
				
				j++;
				while(j+1 <= 7 && board[i][j] == enemy) j++; //proxwra dexia oso exoume checker antipalou
				if(board[i][j] == player){ //an breis dika mas checker
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					//kanta ola ta dexia mexri ekei pou htan to checker to 2 dika mas checkers 
					j++;
					while(j+1 <= 7 && board[i][j] == enemy){
						board[i][j] = player;
						j++;
					}//end while
				}//end if	
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		
		//aristera toy pinaka
		if(j-1 >= 0){
			if(board[i][j-1] == enemy){ //to dipla einai antipaloy
				
				j--;
				while(j-1 >= 0 && board[i][j] == enemy) j--; //proxwra dexia oso exoume checker antipalou
				if(board[i][j] == player){ //an breis dika mas checker
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					//kanta ola ta dexia mexri ekei pou htan to checker to 2 dika mas checkers 
					j--;
					while(j-1 >= 0 && board[i][j] == enemy){
						board[i][j] = player;
						j--;
					}//end while
				}//end if	
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		//panw tou pinaka
		if(i-1 >= 0){
			if(board[i-1][j] == enemy){ //to dipla einai antipaloy
				
				i--;
				while(i-1 >= 0 && board[i][j] == enemy) i--; //proxwra dexia oso exoume checker antipalou
				if(board[i][j] == player){ //an breis dika mas checker
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					//kanta ola ta dexia mexri ekei pou htan to checker to 2 dika mas checkers 
					i--;
					while(i-1 >= 0 && board[i][j] == enemy){
						board[i][j] = player;
						i--;
					}//end while
				}//end if	
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		
		//katw toy pinaka
		if(i+1 <= 7){
			if(board[i+1][j] == enemy){ //to dipla einai antipaloy
				
				i++;
				while(i+1 <= 7 && board[i][j] == enemy) i++; //proxwra dexia oso exoume checker antipalou
				if(board[i][j] == player){ //an breis dika mas checker
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					//kanta ola ta dexia mexri ekei pou htan to checker to 2 dika mas checkers 
					i++;
					while(i+1 <= 7 && board[i][j] == enemy){
						board[i][j] = player;
						i++;
					}//end while
				}//end if	
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		//dexia panw tou pinaka
		if(i-1 >= 0 && j+1 <= 7){
			if(board[i-1][j+1] == enemy){
				
				i--;
				j++;
				while(i-1 >= 0 && j+1 <= 7 && board[i][j] == enemy){
					i--;
					j++;
				}//end while
				
				if(board[i][j] == player){
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					i--;
					j++;
					while(i-1 >= 0 && j+1 <= 7 && board[i][j] == enemy){
						board[i][j] = player;
						i--;
						j++;
					}//end while
				}//end if
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		
		
		//aristera panw
		if(i-1 >= 0 && j-1 >= 0){
			if(board[i-1][j-1] == enemy){
				
				i--;
				j--;
				while(i-1 >= 0 && j-1 >= 0 && board[i][j] == enemy){
					i--;
					j--;
				}//end while
				
				if(board[i][j] == player){
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					i--;
					j--;
					while(i-1 >= 0 && j-1 >= 0 && board[i][j] == enemy){
						board[i][j] = player;
						i--;
						j--;
					}//end while
				}//end if
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		//aristera katw
		if(i+1 <= 7 && j-1 >= 0){
			if(board[i+1][j-1] == enemy){
				
				i++;
				j--;
				while(i+1 <= 7 && j-1 >= 0 && board[i][j] == enemy){
					i++;
					j--;
				}//end while
				
				if(board[i][j] == player){
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					i++;
					j--;
					while(i+1 <= 7 && j-1 >= 0 && board[i][j] == enemy){
						board[i][j] = player;
						i++;
						j--;
					}//end while
				}//end if
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
		
		//dexia katw
		if(i+1 <= 7 && j+1 <= 7){
			if(board[i+1][j+1] == enemy){
				
				i++;
				j++;
				while(i+1 <= 7 && j+1 <= 7 && board[i][j] == enemy){
					i++;
					j++;
				}//end while
				
				if(board[i][j] == player){
					
					//epestrepse sthn arxh
					i = a;
					j = b;
					
					i++;
					j++;
					while(i+1 <= 7 && j+1 <= 7 && board[i][j] == enemy){
						board[i][j] = player;
						i++;
						j++;
					}//end while
				}//end if
			}//end if
		}//end if
		
		//epestrepse sthn arxh
		i = a;
		j = b;
	}//end of makeMove

	
	//methodos gia na parw thn diathesimh kinhsh toy paixth
	public boolean isValidMove(Checker c){
		boolean sameX = false;
		boolean sameY = false;
		boolean samePlayer = false;
		
		//gia ta aspra
		if(c.getPlayer() == 1){
			HashSet<Checker> white = availableMovesWhite();
			for(Checker ch: white){
				if(ch.getX() == c.getX() && ch.getY() == c.getY()){
					 sameX = true;
					 sameY = true;
				}
				if(ch.getPlayer() == c.getPlayer()) samePlayer = true;
			}
		}
		
		//gia ta mavra
		if(c.getPlayer() == 2){
			HashSet<Checker> black = availableMovesBlack();
			for(Checker ch: black){
				if(ch.getX() == c.getX() && ch.getY() == c.getY() && ch.getPlayer() == c.getPlayer()){
					 sameX = true;
					 sameY = true;
					 samePlayer = true;
				}
			}
		}
		
		return (sameX && sameY && samePlayer);
		
		
		
	}//end isValidMove
	
	
	public void displayBoard(Board b)
	{  
        System.out.print("\n  ");
        for(int i = 0; i < 8; ++i) System.out.print(i + " ");
        System.out.println();
        for(int i = 0; i < 8; ++i)
		{
            System.out.print(i + " ");
            for(int j = 0; j < 8; ++j) System.out.print(b.board[i][j] + " ");
            System.out.println();
        }
        System.out.println(); 
    }//end of displayBoard
	
	


}//end of board
