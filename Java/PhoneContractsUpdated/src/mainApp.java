/*Stelios Kokkokyris , 2o examino , 3160063
  Ronald Toshkollari , 2o examino , 3160244
  Vasilis Mayraganis , 2o examino , 3160091*/
import java.util.*;



public class mainApp {
	
	public static void main(String args[] ){
		Scanner in = new Scanner(System.in);
		boolean done = false;
		String answer,code,service,name,phone,date,payment,choosenService;
		int choosenCode;
		StoreFileWRL test = new StoreFileWRL();
		
		
		
		//Declaring 2 services for each services
		ProgrammContract programm1 = new ProgrammContract("Programm Contract 0",12.0,500.0,500.0,1000,1.2,0.7,0.3);
		ProgrammContract programm2 = new ProgrammContract("Programm Contract 1",7.0,150.0,150.0,300,1.2,0.7,0.3);
		CardContract card1 = new CardContract("Card Contract 0",15.0,1500.0,1500.0,2000,1.0,0.5,0.2,30.0);
		CardContract card2 = new CardContract("Card Contract 1",10.0,1200.0,1200.0,1500,1.0,0.5,0.2,20);
		MobileInternet internet1 = new MobileInternet("Mobile Internet 0",20.0,5000.0,2.3);
		MobileInternet internet2 = new MobileInternet("Mobile Internet 1",10.0,3000.0,2.3);
		//Creating a list with the available services
		ServicesList myServices = new ServicesList();
		//adding available services to the list 
		//myServices.addProgrammContract(programm1);
		//myServices.addProgrammContract(programm2);
		//myServices.addCardContract(card1);
		//myServices.addCardContract(card2);
		//myServices.addMobileInternet(internet1);
		//myServices.addMobileInternet(internet2);
		//adding the services to the ArrayList of StoreFileWRL
		test.add(programm1);
		test.add(programm2);
		test.add(card1);
		test.add(card2);
		test.add(internet1);
		test.add(internet2);
		//then adding them to the ArrayList of myServices
		for(int i = 0;i<test.sizeService();i++){
			Services ser = null;
			if(test.getService(i).getType().equals("ProgrammContract")){
				ser = test.getService(i);
				myServices.addProgrammContract(ser);
			}else if(test.getService(i).getType().equals("CardContract")){
				ser = test.getService(i);
				myServices.addCardContract(ser);
			}else if(test.getService(i).getType().equals("MobileInternet")){
				ser = test.getService(i);
				myServices.addMobileInternet(ser);
			}
		}
		//Declaring 2 contracts for each services and then adding it to the list of StoreFileWRL
		ContractsList myContracts = new ContractsList();
		Contracts contract0 = new Contracts(myContracts.getSize(),"Programm Contract 0","K","6911111111","1/2/3","Cash",85.9,20.2,12,0.0);
		//myContracts.addContract(contract0);
		test.add(contract0);
		Contracts contract1 = new Contracts(myContracts.getSize(),"Programm Contract 1","A","6922222222","2/3/4","Card",60.3,32.5,200,0.0);
		//myContracts.addContract(contract1);
		test.add(contract1);
		Contracts contract2 = new Contracts(myContracts.getSize(),"Card Contract 0","I","6933333333","3/4/5","Cash",400.3,120.3,50,0.0);
		//myContracts.addContract(contract2);
		test.add(contract2);
		Contracts contract3 = new Contracts(myContracts.getSize(),"Card Contract 1","N","6933333331","3/4/4","Cash",300.3,200.3,240,0.0);
		//myContracts.addContract(contract3);
		test.add(contract3);
		Contracts contract4 = new Contracts(myContracts.getSize(),"Mobile Internet 0","E","6912121212","5/3/2","Card",0.0,0.0,0.0,1390.2);
		//myContracts.addContract(contract4);
		test.add(contract4);
		Contracts contract5 = new Contracts(myContracts.getSize(),"Mobile Internet 1","S","69323232323","4/3/4","Card",0.0,0.0,0.0,3150.2);
		//myContracts.addContract(contract5);
		test.add(contract5);
		
		
		//then adding them to myContracts 
		for(int i = 0;i<test.sizeContract();i++){
			Contracts con = null;
			con = test.getContract(i);
			con.setCode(myContracts.getSize());
			myContracts.addContract(con);
			
		}
		//creating two files with createFile method
		test.createFile("service_list.txt");
		test.createContractFile("contract_list.txt");
		//loading the files that i created before 
		test.loadServiceFile("service_list.txt");
		test.loadContractFile("contract_list.txt");
		
		
		
		//Menu of the user
		
		while(!done) { 
			System.out.println("\n1. Show Available Services");
			System.out.println("2. Create new Contract");
			System.out.println("3. Show available contracts of services");
			System.out.println("4. Update Statistics");
			System.out.println("5. Total monthly cost for each Service and monthly available balance for Contract Card");
			System.out.println("6. Free talk time,sms for Mobile Phone and free data for Mobile Internet");
			System.out.print("> ");
			answer = in.nextLine();
			if (answer.equals("1")) {
				System.out.println("1)Programm Contracts");
				System.out.println("2)Card Contracts");
				System.out.println("3)Mobile Internet");
			}
			else if (answer.equals("2")) {
				System.out.println("Please choose a Service:");
				for(int i = 0;i<myServices.getSize();i++){
					System.out.println(i + ") " + myServices.getService(i).getServiceName());
				}
				System.out.print(">");
				choosenService = in.nextLine();
				Contracts contract6 = new Contracts();
				myContracts.newContract(choosenService,contract6,myServices);
				myContracts.addContract(contract6);
				test.add(contract6);
				test.createContractFile("contract_list.txt");
			}else if (answer.equals("3")) {
				System.out.println("Please Choose a Service.\n1)Programm Contracts\n2)Card Contracts\n3)Mobile Internet");
				System.out.print(">");
				choosenService = in.nextLine();
				myServices.listServices(choosenService);
				
			}else if (answer.equals ("4")) {
				System.out.println("Enter the code of your contract to update your statistics ");
				myContracts.listCodes();
				System.out.print(">");
				choosenCode = in.nextInt();
				myContracts.updateContract(choosenCode);
				test.createContractFile("contract_list.txt");
			}else if (answer.equals ("5")){
				System.out.println("Enter the code that your contract has to show your monthly cost ");
				myContracts.listCodes();
				System.out.print(">");
				choosenCode = in.nextInt();
				double totalCost = 0;
				if(choosenCode < myContracts.getSize()){
					Contracts choosenContract = myContracts.getContract(choosenCode);
					for(int i = 0;i<myServices.getSize();i++){
						if(choosenContract.getServiceName().equalsIgnoreCase(myServices.getService(i).getServiceName())){
							Services ser = myServices.getService(i);
							totalCost = ser.getCost(choosenContract);
							break;
						}
					}
				}else{
					System.out.println("Wrong code.");
				}
				System.out.println("Your total cost is: " + totalCost);
			}else if(answer.equals("6")){
				System.out.println("Enter the code that your contract has to show your monthly statistics ");
				myContracts.listCodes();
				System.out.print(">");
				choosenCode = in.nextInt();
				if(choosenCode < myContracts.getSize()){
					Contracts choosenContract = myContracts.getContract(choosenCode);
					Services tempService = null;
					for(int i = 0;i < myServices.getSize();i++){
						if(choosenContract.getServiceName().equalsIgnoreCase(myServices.getService(i).getServiceName())){
							tempService = myServices.getService(i);
							break;
						}
					}
					tempService.getStatistics(choosenContract);
				}else{
					System.out.println("Wrong code.");
				}
			}else{
				done = true;
			}
			answer = in.nextLine();
		}
	}
}
