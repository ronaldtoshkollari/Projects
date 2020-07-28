
public class Email {
	
	
	private int category;
	private String text;
	
	
	//constructors
	public Email(){
		
	}
	
	public Email(int category){
		this.category = category;
	}
	
	public Email(String text){
		this.text = text;
	}
	
	public Email(int category, String text){
		this.category = category;
		this.text = text;
	}
	
	//setters and getters
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	

}//end of email
