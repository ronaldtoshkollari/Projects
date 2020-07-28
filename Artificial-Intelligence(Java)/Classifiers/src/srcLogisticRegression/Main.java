import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Dictionary dict = new Dictionary();
		HashSet<String> vocab = dict.getDictionaryWords("C:/Users/roni3/Desktop/enron/spam");
		HashSet<String> vocabHam = dict.getDictionaryWords("C:/Users/roni3/Desktop/enron/ham");
		vocab.addAll(vocabHam);
		System.out.println(vocab.size());
		vocabHam = null;
		ArrayList<String> classes = new ArrayList<String>();
		classes.add("ham");
		classes.add("spam");
		System.out.println("Creating LR");
		LogisticRegression lr = new LogisticRegression(vocab, classes);
		System.out.println("Training LR");
		lr.train();
		File folder = new File("C:/Users/roni3/Desktop/enron/test2");
		BufferedReader buffer = null;
		int totalHam = 0;
		int totalSpam = 0;
		
		for(File test: folder.listFiles()){
			
			buffer = new BufferedReader(new FileReader(test));
			String result = lr.test(buffer);
			
			if(result.equals("ham")){
				totalHam++;
			}else{
				totalSpam++;
			}
			
		}//end for
		
		System.out.println("Ham " + totalHam);
		System.out.println("Spam " + totalSpam);

	}

}
