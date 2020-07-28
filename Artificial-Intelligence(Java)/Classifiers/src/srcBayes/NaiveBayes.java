import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NaiveBayes {
	
	
	
	
	public int guess(Email e, ArrayList<WordFrequencyCategory> hamList, ArrayList<WordFrequencyCategory> spamList) throws IOException{
		String text = e.getText();
		String[] tokens = text.split(" ");
		
		int totalWords = 0;
		totalWords = hamList.size() + spamList.size();
		double ham = new File("C:/Users/roni3/Desktop/enron2/ham").listFiles().length;
		double spam = new File("C:/Users/roni3/Desktop/enron2/spam").listFiles().length;
		double pHam = ham/(ham+spam);
		double pSpam = spam/(ham+spam);
		double propTextHam = 1;
		double propTextSpam = 1;
		
		
		//calculate prop of ham
		for(String s: tokens){
			for(WordFrequencyCategory w: hamList){
				
				if(w.getWord().equals(s)){
					propTextHam *= (double)(w.getFrequency()+1) / (hamList.size()+totalWords);
					//System.out.println("In if");
				}
					
				
				
			}//end for
			
			for(WordFrequencyCategory w: spamList){
				
				if(w.getWord().equals(s)){
					propTextSpam *= (double)(w.getFrequency()+1) / (spamList.size()+totalWords);
					//System.out.println("In if");
				}
					
				
				
			}//end for
			
		}//end for
		
		pHam *= propTextHam;
		pSpam *= propTextSpam;
		//System.out.println("Ham:" + pHam);
		//System.out.println("Spam:" + pSpam);
		if(pHam > pSpam){
			//System.out.println("Pham is bigger then pspam");
			return 1;
		}else{
			//System.out.println("Pspam is bigger");
			return 0;
		}
		
		
	}

}//end of NaiveBeyes
