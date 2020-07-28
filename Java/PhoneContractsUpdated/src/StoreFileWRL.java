import java.io.*;
import java.util.*;

public class StoreFileWRL {

	private ArrayList <Services> StoreServices = new ArrayList<Services>();
	private ArrayList <Contracts> StoreContracts = new ArrayList<Contracts>();
	private ArrayList <String> tempInfo = new ArrayList<String>();
	private ArrayList <String> tempInfo2 = new ArrayList<String>();

	public void loadServiceFile(String data){

		File f = null;
		BufferedReader reader = null;
		Services service = null;
		String line;

		try{
			f = new File(data);
		}
		catch (NullPointerException e){
			System.err.println ("File not found.");
		}

		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e ){
			System.err.println("Error opening file!");
		}
		
		try {
			line = reader.readLine(); //go to next line 
			while(line != null){
				if(line.trim().equalsIgnoreCase("SERVICE_LIST")){ 
					line = reader.readLine();
					if(line.trim().equals("{")){
						line = reader.readLine();
						while(true){        
							if(line.trim().equalsIgnoreCase("SERVICE")){
								line = reader.readLine();
								if(line.trim().equals("{")){
									while(line.trim().equals("}") == false){
										line = reader.readLine();          //add all infos between the {} that are taken from txt file
										if(line.trim().equals("}") == false) tempInfo.add(line.trim());
									}
									for(int i = 0;i < tempInfo.size();i++){  //searching for TYPE 
										if(tempInfo.get(i).toUpperCase().startsWith("TYPE")){
											//checking the type to make a certain object
											if(tempInfo.get(i).substring(4).trim().equalsIgnoreCase("ProgrammContract")){
												service = new ProgrammContract();
												tempInfo.remove(i); //when i get the type of object i remove the String of type because i dont need it any more
												//Taking the infos from arraylist and i set them to object that i created 
												for(int j = 0;j<tempInfo.size();j++){
													if(tempInfo.get(j).toUpperCase().startsWith("SERVICE_NAME")){
														String temp = tempInfo.get(j).substring(12).trim();
														service.setServiceName(temp);
													}else if(tempInfo.get(j).toUpperCase().startsWith("MONTHLY_PRICE")){
														String temp = tempInfo.get(j).substring(13).trim();
														service.setSubFee(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_MOBILE_MINUTES")){
														String temp = tempInfo.get(j).substring(19).trim();
														((MobileTelephony) service).setFreeTalkTimeMobile(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_FIXED_MINUTES")){
														String temp = tempInfo.get(j).substring(18).trim();
														((MobileTelephony) service).setFreeTalkTimeHome(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_SMS")){
														String temp = tempInfo.get(j).substring(8).trim();
														((MobileTelephony) service).setFreeSms(Integer.parseInt(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("MOBILE_COST")){
														String temp = tempInfo.get(j).substring(11).trim();
														((MobileTelephony) service).setMobileTalkTimeCost(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FIXED_COST")){
														String temp = tempInfo.get(j).substring(10).trim();
														((MobileTelephony) service).setHomeTalkTimeCost(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("SMS_COST")){
														String temp = tempInfo.get(j).substring(8).trim();
														((MobileTelephony) service).setSmsCost(Double.parseDouble(temp));
													}
												}
												//adding the object to arraylist with services
												StoreServices.add(service);
												tempInfo.clear();
												//breaking the for loop because i have found the type
												break;
											}else if(tempInfo.get(i).substring(4).trim().equalsIgnoreCase("CardContract")){
												service = new CardContract();
												tempInfo.remove(i);
												for(int j = 0;j<tempInfo.size();j++){
													if(tempInfo.get(j).toUpperCase().startsWith("SERVICE_NAME")){
														String temp = tempInfo.get(j).substring(12).trim();
														service.setServiceName(temp);
													}else if(tempInfo.get(j).toUpperCase().startsWith("MONTHLY_PRICE")){
														String temp = tempInfo.get(j).substring(13).trim();
														service.setSubFee(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_MOBILE_MINUTES")){
														String temp = tempInfo.get(j).substring(19).trim();
														((MobileTelephony) service).setFreeTalkTimeMobile(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_FIXED_MINUTES")){
														String temp = tempInfo.get(j).substring(18).trim();
														((MobileTelephony) service).setFreeTalkTimeHome(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_SMS")){
														String temp = tempInfo.get(j).substring(8).trim();
														((MobileTelephony) service).setFreeSms(Integer.parseInt(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("MOBILE_COST")){
														String temp = tempInfo.get(j).substring(11).trim();
														((MobileTelephony) service).setMobileTalkTimeCost(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FIXED_COST")){
														String temp = tempInfo.get(j).substring(10).trim();
														((MobileTelephony) service).setHomeTalkTimeCost(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("SMS_COST")){
														String temp = tempInfo.get(j).substring(8).trim();
														((MobileTelephony) service).setSmsCost(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("MONTHLY_TOTAL")){
														String temp = tempInfo.get(j).substring(13).trim();
														((CardContract) service).setMonthlyTotal(Double.parseDouble(temp));
													}
												}
												StoreServices.add(service);
												tempInfo.clear();
												break;
											}else if(tempInfo.get(i).substring(4).trim().equalsIgnoreCase("MobileInternet")){
												service = new MobileInternet();
												tempInfo.remove(i);
												for(int j = 0;j<tempInfo.size();j++){
													if(tempInfo.get(j).toUpperCase().startsWith("SERVICE_NAME")){
														String temp = tempInfo.get(j).substring(12).trim();
														service.setServiceName(temp);
													}else if(tempInfo.get(j).toUpperCase().startsWith("MONTHLY_PRICE")){
														String temp = tempInfo.get(j).substring(13).trim();
														service.setSubFee(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("FREE_DATA")){
														String temp = tempInfo.get(j).substring(9).trim();
														((MobileInternet) service).setFreeData(Double.parseDouble(temp));
													}else if(tempInfo.get(j).toUpperCase().startsWith("DATA_COST")){
														String temp = tempInfo.get(j).substring(9).trim();
														((MobileInternet) service).setDataCost(Double.parseDouble(temp));
													}
												}
												StoreServices.add(service);
												tempInfo.clear();
												break;
											}
										}
									}
								}
							}
							line = reader.readLine();
							if(line == null) break;
						}
					}
				}
				line = reader.readLine();
			}
		}catch (IOException e) {
            System.out.println("Error reading line ");
		}
		
		try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
		
	}
	
	public void loadContractFile(String data){
		
		File f = null;
		BufferedReader reader = null;
		Contracts contract = null;
		String line;
		Services tempService = null;
		
		try{
			f = new File(data);
		}
		catch (NullPointerException e){
			System.err.println ("File not found.");
		}

		try{
			reader = new BufferedReader(new FileReader(f));
		}
		catch (FileNotFoundException e ){
			System.err.println("Error opening file!");
		}
	
		try {
			line = reader.readLine(); //go to next line
			while(line != null){
				if(line.trim().equalsIgnoreCase("CONTRACT_LIST")){
					line = reader.readLine();
					if(line.trim().equals("{")){
						line = reader.readLine();
						while(true){ 
							if(line.trim().equalsIgnoreCase("CONTRACT")){
								line = reader.readLine();
								if(line.trim().equals("{")){
									line = reader.readLine();
									int n = 0;
									while(n != 2){
										if(line.trim().equals("{") || line.trim().equals("}")){
											n++;
										}else{
											tempInfo2.add(line.trim());
										}
										line = reader.readLine();
									}
									boolean flag = false;
									for(int i = 0; i < tempInfo2.size();i++){  //searching for TYPE
										if(tempInfo2.get(i).toUpperCase().startsWith("TYPE")){
											//checking the type to make a certain object
											for(int j = 0; j < sizeService(); j++){
												if(tempInfo2.get(i).substring(4).trim().equalsIgnoreCase(getService(j).getType().trim())){
													flag = true;
													tempService = StoreServices.get(j);
												}
											}
											if(flag = false){
												System.out.println("Error");
												tempInfo2.clear();
												break;
											}else{
												contract = new Contracts();
												tempInfo2.remove(i);
												for(int j = 0; j < tempInfo2.size();j++){
													if(tempInfo2.get(j).toUpperCase().startsWith("CONTRACT_NUMBER")){
														String temp = tempInfo2.get(j).substring(15).trim();
														contract.setCode(Integer.parseInt(temp));
													}else if(tempInfo2.get(j).toUpperCase().startsWith("SERVICE_NAME")){
														String temp = tempInfo2.get(j).substring(12).trim();
														contract.setServiceName(temp);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("CUSTOMER")){
														String temp = tempInfo2.get(j).substring(8).trim();
														contract.setName(temp);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("PHONE_NUMBER")){
														String temp = tempInfo2.get(j).substring(12).trim();
														contract.setPhoneNumber(temp);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("ACTIVATION_DATE")){
														String temp = tempInfo2.get(j).substring(15).trim();
														contract.setDate(temp);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("DISCOUNT")){
														contract.getDiscount(tempService);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("PAYMENT")){
														String temp = tempInfo2.get(j).substring(7).trim();
														contract.setPayment(temp);
													}else if(tempInfo2.get(j).toUpperCase().startsWith("MONTHLY USAGE")){
														String temp = tempInfo2.get(j + 1).substring(6).trim();
														contract.setTalkTimeMobileUsed(Double.parseDouble(temp));
														String temp2 = tempInfo2.get(j + 2).substring(5).trim();
														contract.setTalkTimeHomeUsed(Double.parseDouble(temp2));
														String temp3 = tempInfo2.get(j + 3).substring(3).trim();
														contract.setSmsUsed(Integer.parseInt(temp3));
														String temp4 = tempInfo2.get(j + 4).substring(4).trim();
														contract.setDataUsed(Double.parseDouble(temp4));
													}
												}
												//adding the object to arraylist with contract
												StoreContracts.add(contract);
												tempInfo2.clear();
												//breaking the for loop because i have found the type
												break;
											}
										}
									}
								}
							}
							line = reader.readLine();
							if(line == null) break;
						}
					}
				}
				line = reader.readLine();
			}
		}catch (IOException e) {
            System.out.println("Error reading line ");
		}
		
		try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error closing file.");
        }
	
	}
	//public method to create a file that has services infos
	public void createFile(String path) {

		File f = null;
		BufferedWriter writer = null;
		int numInstancesProgrammContract = 0;
		int numInstancesCardContract = 0;
		int numInstancesMobileInternet = 0;

		try	{
			f = new File(path);
		}
		catch (NullPointerException e) {
			System.err.println ("Error creating file.");
		}

		try	{
			writer = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(f)));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error opening file for writing!");
		}
		try{
			writer.write("SERVICES_LIST" + "\n" + 
						 "{" + "\n" );
		}catch (IOException e) {
				System.err.println("Write error!");
		}
		for (Services service:StoreServices) {
			try	{
				if(service instanceof ProgrammContract){
					numInstancesProgrammContract++;
					writer.write("\t" +  "SERVICE  " + "\n" + "\t" + "{" + "\n" + "\t" + "\t" +
								 "SERVICE_NAME  " + service.getServiceName() + "\n" + "\t" + "\t" +
								 "TYPE  " + ((ProgrammContract)service).getType() + "\n" + "\t" + "\t" +
								 "MONTHLY_PRICE  " + service.getSubFee() + "\n" + "\t" + "\t" +
								 "FREE_MOBILE_MINUTES  " + ((MobileTelephony)service).getFreeTalkTimeMobile() + "\n" + "\t" + "\t" +
								 "FREE_FIXED_MINUTES  " + ((MobileTelephony)service).getFreeTalkTimeHome() + "\n" + "\t" + "\t" +
								 "FREE_SMS  " + ((MobileTelephony)service).getFreeSms() + "\n" + "\t" + "\t" +
								 "MOBILE_COST  " + ((MobileTelephony)service).getMobileTalkTimeCost() + "\n" + "\t" + "\t" +
								 "FIXED_COST  " + ((MobileTelephony)service).getHomeTalkTimeCost() + "\n" + "\t" + "\t" +
								 "SMS_COST  " + ((MobileTelephony)service).getSmsCost() + "\n" + "\t" + "}" + "\n");
				}else if(service instanceof CardContract){
					numInstancesCardContract++;
					writer.write("\t" +"SERVICE  " + "\n" + "\t" + "{" + "\n" + "\t" + "\t" +
								 "SERVICE_NAME  " + service.getServiceName() + "\n" + "\t" + "\t" +
								 "TYPE  " + ((CardContract)service).getType() + "\n" + "\t" + "\t" +
								 "MONTHLY_PRICE  " + service.getSubFee() + "\n" + "\t" + "\t" +
								 "FREE_MOBILE_MINUTES  " + ((MobileTelephony)service).getFreeTalkTimeMobile() + "\n" + "\t" + "\t" +
								 "FREE_FIXED_MINUTES  " + ((MobileTelephony)service).getFreeTalkTimeHome() + "\n" + "\t" + "\t" +
								 "FREE_SMS  " + ((MobileTelephony)service).getFreeSms() + "\n" + "\t" + "\t" +
								 "MOBILE_COST  " + ((MobileTelephony)service).getMobileTalkTimeCost() + "\n" + "\t" + "\t" +
								 "FIXED_COST  " + ((MobileTelephony)service).getHomeTalkTimeCost() + "\n" + "\t" + "\t" +
								 "SMS_COST  " + ((MobileTelephony)service).getSmsCost() + "\n" + "\t" + "\t" +
								 "MONTHLY_TOTAL " + ((CardContract)service).getMonthlyTotal() + "\n" + "\t" + "}" + "\n");
				}else if(service instanceof MobileInternet){
					numInstancesMobileInternet++;
					writer.write("\t" +"SERVICE  " + "\n" + "\t" + "{" + "\n" + "\t" + "\t" +
								 "SERVICE_NAME  " + service.getServiceName() + "\n" + "\t" + "\t" +
								 "TYPE  " + ((MobileInternet)service).getType() + "\n" + "\t" + "\t" +
								 "MONTHLY_PRICE  " + service.getSubFee() + "\n" + "\t" + "\t" +
								 "FREE_DATA  " + ((MobileInternet)service).getFreeData() + "\n" + "\t" + "\t" +
								 "DATA_COST  " + ((MobileInternet)service).getDataCost() + "\n" + "\t" + "}" + "\n");
				}
			}
			catch (IOException e) {
				System.err.println("Write error!");
			}
		}
		try {
			writer.write("}" + "\n");
			writer.write("Number of Programm Contract  " + numInstancesProgrammContract + '\n' );
			writer.write("Number of Card Contract " + numInstancesCardContract + '\n');
			writer.write("Number of Mobile Internet " + numInstancesMobileInternet + '\n');
			writer.close();
			
		}
		catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}
	//public method to create a file that has contracts infos
	public void createContractFile(String path) {

		File f = null;
		BufferedWriter writer = null;
		int numInstancesContracts = 0;;

		try	{
			f = new File(path);
		}
		catch (NullPointerException e) {
			System.err.println ("Error creating file.");
		}

		try	{
			writer = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(f)));
		}
		catch (FileNotFoundException e) {
			System.err.println("Error opening file for writing!");
		}
		try{
			writer.write("CONTRACTS_LIST" + "\n" + 
						 "{" + "\n" );
		}catch (IOException e) {
				System.err.println("Write error!");
		}
		for (Contracts contract:StoreContracts) {
			try	{
				numInstancesContracts++;
				writer.write("\t" + "CONTRACT" + "\n" + "\t" + "{" + "\n" + "\t" + "\t" +
							"CONTRACT_NUMBER  " + contract.getCode() + "\n" + "\t" + "\t" +
							"SERVICE_NAME  " + contract.getServiceName() + "\n" + "\t" + "\t" +
							"TYPE  " + contract.getType(contract) + "\n" + "\t" + "\t" +
							"CUSTOMER  " + contract.getName() + "\n" + "\t" + "\t" +
							"PHONE_NUMBER  " + contract.getPhoneNumber() + "\n" + "\t" + "\t" +
							"ACTIVATION_DATE  " + contract.getDate() + "\n" + "\t" + "\t" +
							"DISCOUNT  " + contract.getDiscount(contract.getType(contract).trim()) + "\n" + "\t" + "\t" +
							"PAYMENT  " + contract.getPayment() + "\n" + "\t" + "\t" +
							"MONTHLY USAGE  " + "\n" + "\t" + "\t" + "{" + "\n" + "\t" + "\t" + "\t" +
							"MOBILE  " + contract.getTalkTimeMobileUsed() + "\n" + "\t" + "\t" + "\t" +
							"FIXED  " + contract.getTalkTimeHomeUsed() + "\n" + "\t" + "\t" + "\t" +
							"SMS  " + contract.getSmsUsed() + "\n" + "\t" + "\t" + "\t" +
							"DATA  " + contract.getDataUsed() + "\n" + "\t" + "\t" + "}" + "\n" + "\t" + "}" + "\n");
			}
			catch (IOException e) {
				System.err.println("Write error!");
			}
		}
		try {
			writer.write("}" + "\n");
			writer.write("Number of Programm Contract  " + numInstancesContracts + '\n' );
			writer.close();

		}
		catch (IOException e) {
			System.err.println("Error closing file.");
		}
	}
	//public method to get a service from StoreServices ArrayList
	public Services getService(int i){
		return StoreServices.get(i);
	}
	//public method to get a contract from StoreContracts ArrayList
	public Contracts getContract(int i){
		return StoreContracts.get(i);
	}
	//public method to get the size StoreServices ArrayList
	public int sizeService(){
		return StoreServices.size();
	}
	//public method to get the size StoreContracts ArrayList
	public int sizeContract(){
		return StoreContracts.size();
	}
	//public method to add ProgrammContract StoreServices ArrayList
	public void add(ProgrammContract p){
		StoreServices.add(p);
	}
	//public method to add CardContract StoreServices ArrayList
	public void add(CardContract c){
		StoreServices.add(c);
	}
	//public method to add MobileInternet StoreServices ArrayList
	public void add(MobileInternet m){
		StoreServices.add(m);
	}
	//public method to add Contract StoreContracts ArrayList
	public void add(Contracts con){
		StoreContracts.add(con);
	}
	
}