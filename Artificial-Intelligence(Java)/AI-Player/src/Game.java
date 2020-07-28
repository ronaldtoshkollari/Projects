import java.util.HashSet;
import java.util.Scanner;


/*
 * O X DEIXNEI TIS THESEIS GIA TON KATHETO AKSONA
 * O Y DEIXNEI TIS THESEIS GIA TON ORIZODIO AKSONA
 * 1 EINAI TA ASPRA POULIA
 * 2 EINAI TA MAYRA POYLIA
 */
public class Game {
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		Board board = new Board();
		Ai cpu = new Ai();
		boolean playerTurn;
		HashSet<Checker> userMoves,cpuMoves;
		
		System.out.print("Give 1 if you want white or 2 for black: ");
		//epilogh paixth apo ton xrhsth 1 h 2(aspra h mavra)
		board.setUser(input.nextInt());
		while(board.getUser() != 1 && board.getUser() != 2){ //elenxos timwn
			System.out.print("Wrong value. :(........Please enter 1 or 2: ");
			board.setUser(input.nextInt());
		}//end while
		
		//epilogh dyskolias apo ton xrhsth
		//vathos dentroy
		System.out.print("Please choose difficulty: ");
		cpu.setMaxDepth(input.nextInt());
		while(cpu.getMaxDepth() < 1){ //elenxos timwn
			System.out.print("Wrong value. :(........Please enter: ");
			cpu.setMaxDepth(input.nextInt());
		}//end while
		
		
		//orismos diatheimwn kinhsewn analoga me thn epilogh toy xrhsth
		if(board.getUser() == 1){
			board.setAi(2);
			userMoves = board.availableMovesWhite();
			cpuMoves = board.availableMovesBlack();
			playerTurn = false;
		}else{
			board.setAi(1);
			userMoves = board.availableMovesBlack();
			cpuMoves = board.availableMovesWhite();
			playerTurn = true;
		}//end if
		
		System.out.println("The game starts....!!");
		board.displayBoard(board);
		
		Checker playerChecker = new Checker(board.getUser());
		
		
		//gia thn prwth kinhsh mono
		if(board.getUser() == 1){ // an o xrthsths eixe to 1 o ypologisths paizei prwtos
			
			//pairnei tis diathesimes kinhseis
			for(Checker ch: cpuMoves)
				System.out.println("Computer can do this move: (" + ch.getX() +  " ," + ch.getY() + ")" + "with score: " + cpu.evaluatePosition(board, ch.getX(), ch.getY()));
			
			
			System.out.println("Thinking.........");
			//kanei kinhsh xrhsimopoiontas minimax
			cpu.minimax(true, board, Double.MIN_VALUE, Double.MAX_VALUE, 0);
			//cpuChecker = new Checker(cpu.getBestX(), cpu.getBestY(), board.getAi());
			//board.makeMove(cpuChecker);
			
			
			
			//ektypwsh pinaka
			System.out.println("After Computer Move our board is...........");
			board.displayBoard(board);
			userMoves.clear();
			cpuMoves.clear();
			//diathesimes kinhseis meta thn kinhsh toy ypologisth
			if(board.getUser() == 1){
				userMoves = board.availableMovesWhite();
				cpuMoves = board.availableMovesBlack();
			}else{
				userMoves = board.availableMovesBlack();
				cpuMoves = board.availableMovesWhite();
			}
			
			//orizoume poios paizei sthn synexeia
			if(userMoves.isEmpty()) 
				playerTurn = false; //ean den exei kinish den einai h seira toy
			else
				playerTurn = true; // ean exei kinhsh einai h seira toy
			
			
		}else{ //paizei o xrhsths
			
			//toy deixoume tis kinhseis pou borei na kanei
			System.out.println("Please choose one of the moves below:");
			for(Checker ch: userMoves)
				System.out.println("You can do this move: (" + ch.getX() +  " ," + ch.getY() + ")");
			System.out.print("Steps to go horizontal:");
			int x = input.nextInt();
			System.out.print("Steps to go vertical:");
			int y = input.nextInt();
			playerChecker = new Checker(x, y, board.getUser());
			while(!board.isValidMove(playerChecker)){ //elenxos kinhshs
				System.out.println("You cant do that move......Try again.");
				System.out.print("Steps to go horizontal:");
				x = input.nextInt();
				System.out.print("Steps to go vertical:");
				y = input.nextInt();
				playerChecker = new Checker(x, y, board.getUser());
			}
			board.makeMove(playerChecker); //kanei thn kinhsh
			System.out.println("After player Move our board is...........");
			board.displayBoard(board);
			userMoves.clear();
			cpuMoves.clear();
			if(board.getUser() == 1){
				userMoves = board.availableMovesWhite();
				cpuMoves = board.availableMovesBlack();
			}else{
				userMoves = board.availableMovesBlack();
				cpuMoves = board.availableMovesWhite();
			}
			//orismos poianou seira einai
			if(cpuMoves.isEmpty())
				playerTurn = true;
			else
				playerTurn = false;
			
			
		}//end if
		
		while(!board.availableMovesBlack().isEmpty() || !board.availableMovesWhite().isEmpty()){ //oso kapoios apo tous duo exei kinhseis
			
			if(playerTurn){ //an einai h seira toy paixth
				
				System.out.println("Please choose one of the moves below:");
				int i = 0;
				for(Checker ch: userMoves){
					System.out.println("You can do this move: (" + ch.getX() +  " ," + ch.getY() + ")");
					i++;
				}
				System.out.println(i);
				System.out.print("Steps to go horizontal:");
				int x = input.nextInt();
				System.out.print("Steps to go vertical:");
				int y = input.nextInt();
				playerChecker = new Checker(x, y, board.getUser());
				while(!board.isValidMove(playerChecker)){
					System.out.println("You cant do that move......Try again.");
					System.out.print("Steps to go horizontal:");
					x = input.nextInt();
					System.out.print("Steps to go vertical:");
					y = input.nextInt();
					playerChecker = new Checker(x, y, board.getUser());
				}
				board.makeMove(playerChecker);
				System.out.println("After player Move our board is...........");
				board.displayBoard(board);
				
				userMoves.clear();
				cpuMoves.clear();
				if(board.getUser() == 1){
					userMoves = board.availableMovesWhite();
					cpuMoves = board.availableMovesBlack();
				}else{
					userMoves = board.availableMovesBlack();
					cpuMoves = board.availableMovesWhite();
				}
				
				if(cpuMoves.isEmpty())
					playerTurn = true;
				else
					playerTurn = false;
				
			}else{ //an paizei o ypologisths
				
				for(Checker ch: cpuMoves)
					System.out.println("Computer can do this move: (" + ch.getX() +  " ," + ch.getY() + ")" + "with score: " + cpu.evaluatePosition(board, ch.getX(), ch.getY()));
				System.out.println("Thinking.........");
				
				cpu.minimax(true, board, Double.MIN_VALUE, Double.MAX_VALUE, 0);
				
				
				//cpuChecker = new Checker(cpu.getBestX(), cpu.getBestY(), board.getAi());
				
				
				//board.makeMove(cpuChecker);
				System.out.println("After Computer Move our board is...........");
				board.displayBoard(board);
				userMoves.clear();
				cpuMoves.clear();
				if(board.getUser() == 1){
					userMoves = board.availableMovesWhite();
					cpuMoves = board.availableMovesBlack();
				}else{
					userMoves = board.availableMovesBlack();
					cpuMoves = board.availableMovesWhite();
				}
				
				if(userMoves.isEmpty()) 
					playerTurn = false; //ean den exei kinish den einai h seira toy
				else
					playerTurn = true; // ean exei kinhsh einai h seira toy
				
			}//end if
			
			
		}//end while
		
		//ektypwsh score
		System.out.println("Scores are: Black ->" +  board.getScoreBlack() + " , White ->" + board.getScoreWhite());
		
		
		
	}//end of main

}//end of Game class
