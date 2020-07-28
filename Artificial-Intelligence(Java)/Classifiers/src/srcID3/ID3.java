import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.lang.Math;

public class ID3 {
	
	static LinkedList<Node> tree = new LinkedList<Node>();
	String[] tokens;
	String bestAttr;
	int bestAttrPntr;
	HashMap<String, ArrayList<String>> ham;
	HashMap<String, ArrayList<String>> spam;
	int numH;
	int numS;
	double entropy;
	
	public ID3() {}
	
	public ID3(double entropy) {
		this.entropy = entropy;
	}
	
	public Node ID3Algorithm(HashMap<String, ArrayList<String>> ham, HashMap<String, ArrayList<String>> spam, String vector, int defC) {
		System.out.println("vector : " + vector);/////unnecessary
		tokens = splitVector(vector);
		this.ham = (HashMap<String, ArrayList<String>>) ham.clone();
		this.spam = (HashMap<String, ArrayList<String>>) spam.clone();
		this.numH = ham.keySet().size();
		this.numS = spam.keySet().size();
		
		System.out.println(ham.isEmpty() + " ham || " + spam.isEmpty() + " spam");/////unnecessary
		if((this.ham.isEmpty() && this.spam.isEmpty())) return tree.get(Node.rootPntr);
		else if(this.ham.isEmpty() && !this.spam.isEmpty()) return tree.get(Node.rootPntr); 
		else if(!this.ham.isEmpty() && this.spam.isEmpty()) return tree.get(Node.rootPntr);
		else if(tokens.length == 0) return tree.get(Node.rootPntr);
		else {
			if(tokens.length == 1) {
				bestAttr = tokens[0];
				//System.out.println("bestAttr : " + bestAttr);/////unnecessary
				updateMessages();
				this.numH = this.ham.keySet().size();
				System.out.println("line 35 " + this.numH);/////unnecessary
				this.numS = this.spam.keySet().size();
				System.out.println("line 37 " + this.numS);/////unnecessary
				tree.add(new Node(this.numH, this.numS, bestAttr));
			}
			else {
				bestAttribute(this.ham, this.spam);
				System.out.println("bestAttr : " + bestAttr);/////unnecessary
				updateMessages();
				this.numH = this.ham.keySet().size();
				System.out.println("line 44 " + this.numH);/////unnecessary
				this.numS = this.spam.keySet().size();
				System.out.println("line 46 " + this.numS);/////unnecessary
				tree.add(new Node(this.numH, this.numS, bestAttr));
			}
			int m = numH >= numS ? 0 : 1;
			Node subtree = ID3Algorithm(this.ham, this.spam, updateVector(), m);
			return tree.get(Node.rootPntr);
		}
	}//end ID3Algorithm 
	
	//updates the message by removing the best attribute
	public String updateVector() {
		String v = "";
		for(String word : tokens) 
			if(!word.equals(bestAttr)) v = v.concat(word + " ");
		System.out.println("updateVector here!!New vector has the following words : ");////////////unnecessary
		String[] r = v.split(" ");//////////////////////////////////////////////////////////////////unnecessary
		for(String word : r) System.out.print(word + " ");/////////////////////////////////////////unnecessary
		return v;
	}//end updateVector
	
	public void updateMessages() {

		HashMap<String, ArrayList<String>> newHam = (HashMap<String, ArrayList<String>>) this.ham.clone(); 
		ham.clear();
		for (HashMap.Entry<String, ArrayList<String>> entry : newHam.entrySet()) {
			if(entry.getValue().contains(bestAttr)) ham.put(entry.getKey(), entry.getValue());
		}//end for
		System.out.println("Ham messages : " + ham.keySet().size() + " for << " + bestAttr + " >>......updateMessages here!Line 80");//////unnecessary
		
		HashMap<String, ArrayList<String>> newSpam = (HashMap<String, ArrayList<String>>) this.spam.clone();
		spam.clear();
		for (HashMap.Entry<String, ArrayList<String>> entry : newSpam.entrySet()) {
			if(entry.getValue().contains(bestAttr)) spam.put(entry.getKey(), entry.getValue());
		}//end for
		System.out.println("Spam messages : " + spam.keySet().size() + " for << " + bestAttr + " >>......updateMessages here!Line 87");/////unnecessary
		
		newHam.clear();
		newSpam.clear();
	}//end updateMessages 
	
	public String[] splitVector(String vector){
		String[] t = vector.split("[\\s@&.?$+-]+");//split special characters and spaces
		int i = 0;
		for(String word : t){
			String w = t[i].toLowerCase();
			word = w.replaceAll("[^A-Za-z]", "");
			t[i] = word;
			i++;
		}//end for
		return t;
	}//end splitVector
	
	public String bestAttribute(HashMap<String, ArrayList<String>> ham, HashMap<String, ArrayList<String>> spam) {
		int numOfHam = this.numH;
		int numOfSpam = this.numS;
		double[] igArray = calcIGs(ham, spam, numOfHam, numOfSpam, numOfHam+numOfSpam);
		
		int p = 0;
		double maxIg = -1.0;
		for(double ig : igArray){
			if(ig >= maxIg) {
				maxIg = ig;
				bestAttrPntr = p;
			}
			p++;
		}//end for
		bestAttr = tokens[bestAttrPntr];
		return bestAttr;
	}//end bestAttribute
	
	public double[] calcIGs(HashMap<String, ArrayList<String>> ham, HashMap<String, ArrayList<String>> spam, int numOfHam, int numOfSpam, int totalMSG) {
		double[] igArray = new double[tokens.length];
		
		int n = 0;
		double ig = 0.0;
		for(String word : tokens) {
			if(word == null || word.trim().equals("")){
				continue;
			}//end if
			
			int h = 0;
			for (HashMap.Entry<String, ArrayList<String>> entry : ham.entrySet()) {
				if(entry.getValue().contains(word)) h++;
			}//end for
			//System.out.println("Ham messages : " + newHam.keySet().size() + " for << " + word + " >>.....calcIGs here!Line 141");//unnecessary
			
			int s = 0;
			for (HashMap.Entry<String, ArrayList<String>> entry : spam.entrySet()) {
				if(entry.getValue().contains(word)) s++;
			}//end for
			//System.out.println("Spam messages : " + newSpam.keySet().size() + " for << " + word + " >>......calcIGs here!Line 149");//unnecessary
			
			int nHam = numOfHam - h;
			System.out.println("nHam : " + nHam);//unnecessary
			System.out.println("Ham : " + h);//unnecessary
			int nSpam = numOfSpam - s;
			System.out.println("nSpam : " + nSpam);//unnecessary
			System.out.println("Spam : " + s);//unnecessary
			double posSpamX0 = (nSpam+1) / (double)(nSpam+nHam+2);//division with zero
			System.out.println("posSpamX0 : " + posSpamX0);//unnecessary
			double posHamX0 = (nHam+1) / (double)(nHam+nSpam+2);//division with zero
			System.out.println("posHamX0 : " + posHamX0);//unnecessary
			double posX0 = -posSpamX0*Math.log(posSpamX0)-posHamX0*Math.log(posHamX0);//fix posSpamX0 and posHamX0
			System.out.println("posX0 : " + posX0);//unnecessary
			double posSpamX1 = (s+1) / (double)(s+h+2);//division with zero
			System.out.println("posSpamX1 : " + posSpamX1);//unnecessary
			double posHamX1 = (h+1) / (double)(h+s+2);//devision with zero
			System.out.println("posHamX1 : " + posHamX1);//unnecessary
			double posX1 = -posSpamX1*Math.log(posSpamX1)-posHamX1*Math.log(posHamX1);//fix posSpamX1 and posHamX1
			System.out.println("posX1 : " + posX1);//unnecessary
			double sumOfPos = ((h+s+1)/(double)(h+s+nHam+nSpam+2))*posX1 + ((nHam+nSpam+1)/(double)(nHam+nSpam+h+s+2))*posX0;//prosimo!!!!!!!!!!!!!!!!!!!!
			System.out.println("sumOfPos : " + sumOfPos);//unnecessary
			System.out.println("entropy : " + entropy);//unnecessary
			igArray[n] = entropy - sumOfPos;//prosimo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			System.out.println("Ig : " + igArray[n] + " for << " + word + " >>....calcIGs here!Line171");//unnecessary
			n++;
		}//end for
		return igArray;
	}//end calIGs
	
}//end ID3
