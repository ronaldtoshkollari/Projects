import java.util.Objects;

/*Ayth h klash anaparista to kathe pouli to opoio exei kaapoia xaraktiristika 
 * 1)h thesh x,y sto pinaka board
 * 2)to xrwma toy to opoio tha symbolizetai me 1 gia lefko kai 2 gia mavro
 */
public class Checker {
	
	private int x,y,player;
	
	public Checker(){
		
	}//default constructor
	
	public Checker(int x, int y, int player){
		this.x = x;
		this.y = y;
		this.player = player;
	}//constructor 1
	
	public Checker(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Checker(int player){
		this.player = player;
	}//end checker
	
	
	//getters methods
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getPlayer(){
		return player;
	}
	//end of getters
	
	
	//override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(obj instanceof Checker){
			Checker ch = (Checker) obj;
			if(ch.x == this.x && ch.y == this.y && ch.player == this.player)
				return true;
		}
		return false;
	}//end of equals
	
	public int hashCode(){
		int hash = 7;
		hash = 31 * Objects.hashCode(this.x);
		hash = 31 * Objects.hashCode(this.y);
		hash = 31 * Objects.hashCode(this.player);
		return hash;
	}
	
}//end of Checkers

