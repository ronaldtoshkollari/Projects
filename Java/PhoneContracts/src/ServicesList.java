import java.util.*;

public class ServicesList {
	//declaring services ArrayList that contains Services objects
	private ArrayList <Services> services = new ArrayList<Services> ();
	
	//public method to add ProgrammContract
	public void addProgrammContract(ProgrammContract a){
		services.add(a);
	}
	//public method to add CardContract
	public void addCardContract(CardContract b){
		services.add(b);
	}
	//public method to add Mobile internet
	public void addMobileInternet(MobileInternet m){
		services.add(m);
	}
	//public method to show available services
	public void listServices (String answer) {
		for(int i = 0; i < services.size();i++){
			if(answer.equals ("1")){
				if(services.get(i).getServiceName().equals ("Programm Contract")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else if (answer.equals ("2")){
				if(services.get(i).getServiceName().equals ("Card Contract")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else if (answer.equals ("3")){
				if(services.get(i).getServiceName().equals ("Mobile Internet")){
					services.get(i).print();
					System.out.println("--------------------");
				}
			}else{
				System.out.println("Wrong Input");
				break;
			}
			
		}
	}
	//public method to get ArrayList Size
	public int getSize(){
		return services.size();
	}
	
}