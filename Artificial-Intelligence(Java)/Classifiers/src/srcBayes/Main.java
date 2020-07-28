import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String args[]) throws IOException{
		//Dictionary dic = new Dictionary();
		//dic.getDictionaryWords("C:/Users/roni3/Desktop/enron1/ham");
		
		//train
		Dictionary dic = new Dictionary();
		NaiveBayes nv = new NaiveBayes();
		ArrayList<WordFrequencyCategory> hamList = dic.getDictionaryWords("C:/Users/roni3/Desktop/enron1/ham");
		ArrayList<WordFrequencyCategory> spamList = dic.getDictionaryWords("C:/Users/roni3/Desktop/enron1/spam");
		
		
		//test
		ArrayList<Email> emailList = new ArrayList<>();
		File folder = new File("C:/Users/roni3/Desktop/enron/test2");
		File[] listOfFiles = folder.listFiles();
		BufferedReader reader;
		String line;
		
		
		for(int i = 0; i < folder.listFiles().length; i++){
			File file = listOfFiles[i];
			String text = "";
			reader = new BufferedReader(new InputStreamReader( new FileInputStream(file) ));
			line = reader.readLine();
			while(line != null){
				text += line;
				line = reader.readLine();
			}
			Email email = new Email(text);
			if(file.getName().contains("ham"))
				email.setCategory(0);
			else
				email.setCategory(1);
			emailList.add(email);
		}//end for
		
		int spam = 0;
		int ham = 0;
		for(Email e: emailList){
			int c = nv.guess(e, hamList, spamList);
			if(c == 0)
				ham++;
			else
				spam++;
					
		}
		System.out.println("Ham" + ham);
		System.out.println("Spam" + spam);
	}//end main
	
}//end Class Main
