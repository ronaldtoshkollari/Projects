import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Dictionary {
	
	public HashSet<String> getDictionaryWords(String path) throws IOException{
		
		Map<String, Integer> words = new HashMap<>();
		BufferedReader reader;
		String line;
		String[] tokens;
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			File file = listOfFiles[i];
			//System.out.println(i);
			try {
				reader = new BufferedReader(new InputStreamReader( new FileInputStream(file) ));
				line = reader.readLine();
				
				if(!line.trim().equals("")){
					tokens = line.split(" ");
					
					for(String word: tokens){
						if(word == null || word.trim().equals("") || word.length() <= 3){
							continue;
						}
						String pro = word.toLowerCase();
						pro = pro.replaceAll("[^A-Za-z]", "");
						
						
						if(words.containsKey(pro)){
							words.put(pro, words.get(pro)+1);
						}else{
							words.put(pro, 1);
						}
					}
					
					
				}//end if 
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}//end for
		words.remove("");
		words.remove("subject");
		HashSet<String> wordsList = new HashSet<String>();
		//5000 most common words
		for(int i = 0; i < 5000; i++){
			String  word = mostCommon(words);
			wordsList.add(word);
			words.remove(word);
		}//end for
		
		
		return wordsList;
		
	}//end getDictionaryWords
	
	
	public String mostCommon(Map<String, Integer> words){
		int mostUsed = 0;
		String theWord = null;
		for(String word: words.keySet()){
			Integer value = words.get(word);
			if(value > mostUsed){
				mostUsed = value;
				theWord = word;
			}//end if
		}//end for
		
		return theWord;
	}//end mostCommon
	

}
