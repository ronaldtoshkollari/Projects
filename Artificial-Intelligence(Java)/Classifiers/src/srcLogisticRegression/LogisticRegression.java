import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class LogisticRegression {
	
	
	HashSet<String> vocabulary;
	public static int nHam, nSpam, n;
	ArrayList<String> classes;
	double[] weights;
	double lambda = 0.0075;
	double eta = 0.001;
	int iretations = 20;
	ArrayList<EmailVec> data;
	
	public LogisticRegression(HashSet<String> vocabulary, ArrayList<String> classes) throws IOException{
		
		this.vocabulary = vocabulary;
        this.classes = classes;
        weights = new double[vocabulary.size() + 1];
        data = new ArrayList<>();
        BufferedReader reader = null;
        File folder = new File("C:/Users/roni3/Desktop/enron2/ham");
        
        int i = 0;
        
        //creating vector for each ham email
        for(File ham: folder.listFiles()){
        	nHam++;
        	n++;
        	EmailVec email = new EmailVec();
        	for(String s: vocabulary){
        		//System.out.println(s);
        		email.getVec().put(s, 0);
        	}//end for
        	
        	reader = new BufferedReader(new FileReader(ham));
        	String line = reader.readLine();
        	while (line != null) {
                String words[] = line.split(" ");
                for (String s : words) {
                    if (vocabulary.contains(s)) {
                        email.getVec().put(s, email.getVec().get(s) + 1);
                    }//end if
                }//end for
                line = reader.readLine();
            }//end while
        	
        	data.add(email);
        	data.get(i).setEmailClass("ham");
        	i++;
        }//end for
        
        folder = new File("C:/Users/roni3/Desktop/enron2/spam");
        //creating vector for each spam email
        for(File spam: folder.listFiles()){
        	nSpam++;
        	n++;
        	EmailVec email = new EmailVec();
        	for(String s: vocabulary){
        		email.getVec().put(s, 0);
        	}//end for
        	
        	reader = new BufferedReader(new FileReader(spam));
        	String line = reader.readLine();
        	while (line != null) {
                String words[] = line.split(" ");
                for (String s : words) {
                    if (vocabulary.contains(s)) {
                        email.getVec().put(s, email.getVec().get(s) + 1);
                    }//end if
                }//end for
                line = reader.readLine();
            }//end while
        	
        	data.add(email);
        	data.get(i).setEmailClass("spam");
        	i++;
        }//end for
        
        
		
	}//end of constr1
	
	public void train(){
		
		int iterations = 15;
		do{
			
			for(int i = 0; i < n; i++){
				
				EmailVec instance = data.get(i);
				String className = instance.getEmailClass();
				//Estimate weights
                double prob = estimate(instance, className );
                int j = 0;
                System.out.println(data.get(i));
                for(String word: vocabulary){
                	int delta = (className.equals("ham")) ? 1 : 0;
                	weights[j] += (eta * (double) instance.getVec().get(word) * ((double) delta - prob)) - (eta * lambda * weights[j] * weights[j]);
                	j++;
                }//end for
				
			}//end for
			
		}while(--iterations == 0);
		
	}//end of train
	
	public double estimate(EmailVec instance, String className){
		
		double parameterValue = weights[0];
		int i = 1;
        for (String key : vocabulary) {
            parameterValue += (weights[i++] * (double) instance.getVec().get(key));
        }//end for
        
        double expVal = Math.exp(parameterValue);
        
        double returnValue = (expVal / ((double) 1 + expVal));
        System.out.println(className);
        if (className.equals("ham")) {
            return returnValue;
        } else {
            return 1 - returnValue;
        }

	}//end estimate()
	
	public String test(BufferedReader reader) throws IOException{
		
		if (reader == null) {
            return null;
        }
		EmailVec email = new EmailVec();
		for (String s : vocabulary) {
			email.getVec().put(s, 0);
        }
		
		String line = reader.readLine();
		while (line != null) {
            String words[] = line.split(" ");
            for (String s : words) {
                if (vocabulary.contains(s)) {
                    email.getVec().put(s, email.getVec().get(s) + 1);
                }//end if
            }//end for
            line = reader.readLine();
        }//end while
		
		double prob[] = new double[classes.size()];
		prob[0] = estimate(email, "ham");
		System.out.println(prob[0]);
		prob[1] = estimate(email, "spam");
		System.out.println(prob[1]);
		System.out.println("-------------");

        if(prob[0] >= prob[1]){
        	return "ham";
        }else{
        	return "spam";
        }
		
	}//end test
	
	
	

}//end of LogisticRegression

