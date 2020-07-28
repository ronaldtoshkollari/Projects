import java.util.HashMap;

public class EmailVec {

	private HashMap<String, Integer> vec = new HashMap<String, Integer>();
	private String emailClass;
	
	public EmailVec(){}
	
	public String getEmailClass() {
		return emailClass;
	}
	public void setEmailClass(String emailClass) {
		this.emailClass = emailClass;
	}
	public HashMap<String, Integer> getVec() {
		return vec;
	}
	public void setVec(HashMap<String, Integer> vec) {
		this.vec = vec;
	}
	
}
