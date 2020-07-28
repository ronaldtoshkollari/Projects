import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	
	int numOfSpam;
	double posSpam;
	int numOfHam;
	double posHam;
	int totalNumOfMsg;
	String bestAttr;
	String attr;
	int C = 0;
	Node parent;
	static int rootPntr = -1;
	
	public Node() {}
	
	public Node(HashMap<String, ArrayList<String>> ham, HashMap<String, ArrayList<String>> spam) {
		this.numOfSpam = spam.keySet().size();
		this.numOfHam = ham.keySet().size();
		this.totalNumOfMsg = this.numOfHam+this.numOfSpam;
		this.posSpam = (double)this.numOfSpam / (this.numOfSpam+this.numOfHam);
		this.posHam = (double)this.numOfHam / (this.numOfHam+this.numOfSpam);
		attr = null;
		bestAttr = null;
		findParent();
		findC();
		this.rootPntr++;
	}
	
	public Node(int numOfHam, int numOfSpam, String bestAttr) {
		this.numOfSpam = numOfSpam;
		this.numOfHam = numOfHam;
		this.totalNumOfMsg = this.numOfHam+this.numOfSpam;
		this.posSpam = (double)this.numOfSpam / (this.numOfSpam+this.numOfHam);
		this.posHam = (double)this.numOfHam / (this.numOfHam+this.numOfSpam);
		this.bestAttr = bestAttr;
		findParent();
		findC();
		this.rootPntr++;
	}
	
	public void findAttr() {
		if(rootPntr >= 0) attr = parent.bestAttr;
		else attr = null;
	}
	
	public void findParent() {
		if(rootPntr >= 0) parent = ID3.tree.get(rootPntr);
		else parent = null;
	}
	
	public void findC() {
		if(numOfHam >= numOfSpam) C = 0;
		else C = 1;
	}
} 
