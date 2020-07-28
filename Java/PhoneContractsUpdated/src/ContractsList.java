import java.util.*;

public class ContractsList {
	
	//declaring contract ArrayList that contains Contracts objects
    private ArrayList <Contracts> contract = new ArrayList <Contracts>();
	
	//public method to add contract to ArrayList
	public void addContract(Contracts a){
		contract.add(a);
	}
	//public method to get the size of ArrayList
	public int getSize(){
		return contract.size();
	}
	//public method to get Codes
	public void listCodes() {
		for(int i = 0;i<contract.size();i++){
			System.out.print("*");
			System.out.println(contract.get(i).getCode());
		}
	}
	//Method for a new Contract with default values 
	public void newContract(String answer,Contracts c,ServicesList myServices){
		c.setCode(contract.size());
		c.setServiceName(myServices.getService(Integer.parseInt(answer)).getServiceName());
		c.setInfo();
	}
	//public method to get a certain contract
	public void updateContract(int code){
		for(int i = 0;i < contract.size();i++){
			if(i == code){
				contract.get(i).updateStats();
				break;
			}else if(i > contract.size() - 1){
				System.out.println("Wrong Code");
				
			}
		}
	}
	//public method that returns a contract with a certain code or a default contract if user gave wrong code
	public Contracts getContract(int c){
		Contracts choosenContract = new Contracts();
		for(int i = 0; i < contract.size();i++){
			if(i == c){
				choosenContract = contract.get(i);
				break;
			}else if(i > contract.size() - 1){
				System.out.println("There is no contract with that code.");
			}
		}
		return choosenContract;
	}
 }	