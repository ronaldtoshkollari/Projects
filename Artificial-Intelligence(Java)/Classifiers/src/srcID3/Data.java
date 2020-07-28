import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Data { 
	
	ID3 id3 = new ID3();
	
	public HashMap<String, ArrayList<String>> makeDataset(String path, int C) throws IOException{
		
		HashMap<String, ArrayList<String>> ham = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> spam = new HashMap<String, ArrayList<String>>();
							
		BufferedReader reader;
		String line;
		String[] tokens;
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		
		for(int i = 0; i < listOfFiles.length; i++){
			
			File file = listOfFiles[i];
			ArrayList<String> vector = new ArrayList<String>();
			
			try {
				reader = new BufferedReader(new InputStreamReader( new FileInputStream(file)));
				line = reader.readLine();
				while(line != null) {
		
					if(!line.trim().equals("")){
						tokens = id3.splitVector(line);
					
						for(String word: tokens){
							if(word == null || word.trim().equals("")){
								continue;
							}
							
							if(file.getName().contains("ham")) {
								if(!vector.contains(word)) {
									vector.add(word);
									ham.put(file.getName(), vector);
								}
							}else{//contains("spam")
								if(!vector.contains(word)) {
									vector.add(word);
									spam.put(file.getName(), vector);
								}
							}//end if
						}//end for
				
					}//end if 
					line = reader.readLine();
				}//end while	
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end try
			
			
		}//end for
		
		System.out.println("ham messages in the HashMap are " + ham.keySet().size() + "Data here!Line 78");//unnecessary
		System.out.println("spam messages in the HashMap are " + spam.keySet().size() + "Data here!Line 79");//unnecessary
		
		if(C == 1) return spam;
		else return ham;//C == 0
		
	}//end makeDataset */

}
