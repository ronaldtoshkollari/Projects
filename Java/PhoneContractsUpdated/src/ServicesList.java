import java.util.*;

public class ServicesList {
	//declaring services ArrayList that contains Services objects
	private ArrayList <Services> services = new ArrayList<Services> ();
	
	//public method to add ProgrammContract
	public void addProgrammContract(Services a){
		services.add(a);
	}
	//public method to add CardContract
	public void addCardContract(Services b){
		services.add(b);
	}
	//public method to add Mobile internet
	public void addMobileInternet(Services m){
		services.add(m);
	}
	//public method to show available services
	public void listServices (String answer) {
		for(int i = 0; i < services.size();i++){
			if(answer.equals ("1")){
				if(services.get(i).getServiceName().startsWith ("Programm Contract")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else if (answer.equals ("2")){
				if(services.get(i).getServiceName().startsWith ("Card Contract")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else if (answer.equals ("3")){
				if(services.get(i).getServiceName().startsWith ("Mobile Internet")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else{
				System.out.println("Wrong Input");
			}
		}
	}
	//public method to get ArrayList Size
	public int getSize(){
		return services.size();
	}
	public Services getService(int i){
		return services.get(i);
	}
	
}