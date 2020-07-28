import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub			
		Data d = new Data();
		
		HashMap<String, ArrayList<String>> spam = d.makeDataset("C:/Users/hp/Desktop/dataID3/train",1);
		//System.out.println("spam " + spam.size());
		HashMap<String, ArrayList<String>> ham = d.makeDataset("C:/Users/hp/Desktop/dataID3/train",0);
		//System.out.println("spam " + spam.size() + " ham " + ham.size());  
		
		double posHam = (ham.size() + 1) / (double)(ham.size() + spam.size() + 2);
		double posSpam = (spam.size() + 1) / (double)(spam.size() + ham.size() + 2);
		double entropy = -posSpam*Math.log(posSpam)-posHam*Math.log(posHam);
		
		ID3 id3 = new ID3(entropy);
		
		spam.remove("");
		ham.remove("");
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		BufferedReader reader;
		String line;
		String[] tok;
		
		File folder = new File("C:/Users/hp/Desktop/dataID3/test");
		File[] listOfFiles = folder.listFiles();
		String[] emails = new String[listOfFiles.length];
		
		for(int i = 0; i < listOfFiles.length; i++){
			File file = listOfFiles[i];
			String msg = "";
			
			try {
				reader = new BufferedReader(new InputStreamReader( new FileInputStream(file)));
				line = reader.readLine();
				while(line != null) {
		
					if(!line.trim().equals("")){
						tok = id3.splitVector(line);
						
						for(String word: tok){
							if(word == null || word.trim().equals("")){
								continue;
							}//end if
							
							msg = msg.concat(word + " ");
							
						}//end for
					}//end if
					line = reader.readLine();
				}//end while
				emails[i] = msg;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try
		}//end for
				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		for(int i = 0; i < emails.length; i++) {
			Node result;
			if(emails[i].isEmpty() || emails[i].trim().equals("") || spam.isEmpty() || ham.isEmpty()) {
				//System.out.println("line27 here!Main");
				result = new Node(ham, spam);
				ID3.tree.add(result);
			}
			else {
				//System.out.println("line32 here!Main");
				//emails[i].trim();
				result = new Node();
				if(ham.keySet().size() >= spam.keySet().size()) result = id3.ID3Algorithm(ham, spam, emails[i], 0);
				else result = id3.ID3Algorithm(ham, spam, emails[i], 1);
			}
		
			System.out.println("C => " + result.C);
			if(result.C == 1) System.out.println("It's a spam message.");
			else System.out.println("It's a ham message.");
		}
		//sc.close();
	}
}
