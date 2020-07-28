import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Dictionary {
	
	public ArrayList<WordFrequencyCategory> getDictionaryWords(String path) throws IOException{
		
		Map<String, Integer> words = new HashMap<>();
		BufferedReader reader;
		String line;
		String[] tokens;
		ArrayList<WordFrequencyCategory> wordList = new ArrayList<>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String category = "";
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
						}//end if
						
						if((file.getName()).contains("spam")) {
							category = "spam";
						}else {  //else ham
							category = "ham";
						}//end if
						
					}//end for
					
					
				}//end if 
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}//end for
		words.remove("");
		
		
		
		for(int i = 0; i < 5000; i++){
			WordFrequencyCategory wfc = new WordFrequencyCategory();
			wfc.setCategory(category);
			String w = mostCommon(words);
			//System.out.println(w);
			if(w == null)
				break;
			wfc.setWord( w );
			wfc.setFrequency( words.get(w) );
			wordList.add(wfc);
			words.remove(w);
		}
		return wordList;
		
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
