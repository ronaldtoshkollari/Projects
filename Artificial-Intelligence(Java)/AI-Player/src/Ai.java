import java.util.HashSet;

public class Ai {
	
	
	private int xOfBestMove;
	private int yOfBestMove;
	private int maxDepth;
	private int x,y;
	
	
	//o ypologisths kanei kinhsh xrishmopoiontas ton katw pinaka 
	//dinoume kapoia varh stis theseis 
	public double evaluatePosition(Board b, int x, int y){
		
		
		final double[][] valueOfTiles = new double[][]{
			{16.16,3.03,0.99,0.43,0.43,0.99,3.03,16.16},
			{4.12,1.81,0.8,0.27,0.27,0.8,1.81,4.12},
			{1.33,0.04,0.51,0.07,0.07,0.51,0.04,1.33},
			{0.63,0.18,0.04,0.01,0.01,0.04,0.18,0.63},
			{0.63,0.18,0.04,0.01,0.01,0.04,0.18,0.63},
			{1.33,0.04,0.51,0.07,0.07,0.51,0.04,1.33},
			{4.12,1.81,0.8,0.27,0.27,0.8,1.81,4.12},
			{16.16,3.03,0.99,0.43,0.43,0.99,3.03,16.16}};
			
			
		//vlepei ti kinhseis borei na kanei o ypologisths
		HashSet<Checker> move;
		if(b.getAi() == 2){
			move = b.availableMovesBlack();
		}else{
			move = b.availableMovesWhite();
		}
			
		if(b.getWinner() == b.getAi() && move.isEmpty()){
			return 1000.0; //an kerdisei
		}else{
			return valueOfTiles[x][y]; //to baris ths sygkekrimenhs theshs
		} 
		
		
	}//end evaluate Position

	//setters kai getters
	public int getBestX(){
		return xOfBestMove;
	}
	
	public int getBestY(){
		return yOfBestMove;
	}
	
	public void setMaxDepth(int maxDepth){
		this.maxDepth = maxDepth;
	}
	
	public int getMaxDepth(){
		return maxDepth;
	}
	
	//o minimax me a,b pruning
	public double minimax(boolean isMax, Board b, double alpha, double beta, int depth){
		if(depth++ == maxDepth || b.isWinner() ){ //an eftase to megisto vathos h an to paixnidi teleiwse
			return evaluatePosition(b, x, y); //gyrna ayth thn timh
		}
		
		if(isMax){ //an einai o maximizer
			return getMax(false, b, alpha, beta, depth); //pare to max apo twn diathesimwn kinhsewn gia to vathos depth
		}else{
			return getMin(true, b, alpha, beta, depth); //pare to min apo twn diathesimwn kinhsewn gia to vathos depth
		}
			
	}//end minimax
	
	public double getMax(boolean isMax, Board b, double alpha, double beta, int depth){
		
		//vlepei ti poulia exei o ypologisths
		HashSet<Checker> moves;
		if(b.getAi() == 2){
			moves = b.availableMovesBlack();
		}else{
			moves = b.availableMovesWhite();
		}
		
		//gia kathe diathesimh kinhsh
		//ylopoihsh dentroy me anadromh
		for(Checker ch: moves){
			
			//krata thn thesh
			x = ch.getX();
			y = ch.getY();
			
			
			//kane ena copy toy pinaka
			Board copy = new Board(b);
			//kane kapoia kinhsh apo diathesimes
			copy.makeMove(ch);
			//kalese thn minmax gia ta nea dedomena, dhladh ton board copy
			double score = minimax(false, copy, alpha, beta, depth);
			
			//an ayth einai h kalyterh kinhsh krata thn thesh ths
			if(score > alpha){
				alpha = score;
				xOfBestMove = ch.getX();
				yOfBestMove = ch.getY();
			}
			
			//prionisma toy dentrou
			if(alpha >= beta)
				break;
		}//end for
		
		b.makeMove(new Checker(xOfBestMove, yOfBestMove, b.getAi())); //kane thn kinhsh ston arxiko pinaka
		//System.out.println("Cpu made this move: (" + xOfBestMove + " ," + yOfBestMove + ")");
		return alpha;
	}//end getMax
	
	public double getMin(boolean isMax, Board b, double alpha, double beta, int depth){
		
		//pairnei tis diathesimes kinhseis toy xrhsth
		HashSet<Checker> moves;
		if(b.getUser() == 2)
			moves = b.availableMovesBlack();
		else
			moves = b.availableMovesWhite();
		
		//kanei kathe kinhsh toy xrhsth
		for(Checker ch: moves){
			
			//krataei thn thesh toys
			x = ch.getX();
			y = ch.getY();
			
			//antigrafh toy pinaka 
			Board copy = new Board(b);
			//kanei thn kinhsh
			copy.makeMove(ch);
			//kalei thn minmax
			double score = minimax(true, copy, alpha, beta, depth);
			
			
			if(score < beta){
				beta = score;
			}
			
			//prionisma
			if(alpha >= beta)
				break;
		}//end for
		
		//kanei thn kinhsh
		b.makeMove(new Checker(xOfBestMove, yOfBestMove, b.getAi()));
		//System.out.println("Cpu made this move: (" + xOfBestMove + " ," + yOfBestMove + ")");
		return beta;
		
	}//end getMin
	
	

}
