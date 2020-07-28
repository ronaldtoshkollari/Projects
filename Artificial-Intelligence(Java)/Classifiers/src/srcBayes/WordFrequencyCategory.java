
public class WordFrequencyCategory {
	
	private String word;
	private int frequency;
	private String category;
	
	//constructors
	public WordFrequencyCategory(){
		
	}
	
	public WordFrequencyCategory(String word, int frequency, String category){
		
		this.word = word;
		this.frequency = frequency;
		this.category = category;
		
	}
	
	
	
	//setters getters
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
	
	

}//end of WordFrequencyCategory
