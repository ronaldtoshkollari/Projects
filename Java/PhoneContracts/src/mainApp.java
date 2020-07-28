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
		//Declaring 2 services for each services
		ProgrammContract programm1 = new ProgrammContract("Programm Contract",12.0,500.0,500.0,1000,1.2,0.7,0.3);
		ProgrammContract programm2 = new ProgrammContract("Programm Contract",7.0,150.0,150.0,300,1.2,0.7,0.3);
		CardContract card1 = new CardContract("Card Contract",15.0,1500.0,1500.0,2000,1.0,0.5,0.2,30.0);
		CardContract card2 = new CardContract("Card Contract",10.0,1200.0,1200.0,1500,1.0,0.5,0.2,20);
		MobileInternet internet1 = new MobileInternet("Mobile Internet",20.0,5000.0,2.3);
		MobileInternet internet2 = new MobileInternet("Mobile Internet",10.0,3000.0,2.3);
		//Creating a list with the available services
		ServicesList myServices = new ServicesList();
		//adding available services to the list 
		myServices.addProgrammContract(programm1);
		myServices.addProgrammContract(programm2);
		myServices.addCardContract(card1);
		myServices.addCardContract(card2);
		myServices.addMobileInternet(internet1);
		myServices.addMobileInternet(internet2);
		//Declaring 2 contracts for each services and then adding it to the list with contracts
		ContractsList myContracts = new ContractsList();
		Contracts contract0 = new Contracts(myContracts.getSize(),"Programm1","SM","6911111111","1/2/3","Cash",85.9,20.2,12,0.0);
		myContracts.addContract(contract0);
		Contracts contract1 = new Contracts(myContracts.getSize(),"Programm2","YP","6922222222","2/3/4","Card",60.3,32.5,200,0.0);
		myContracts.addContract(contract1);
		Contracts contract2 = new Contracts(myContracts.getSize(),"Card1","RI","6933333333","3/4/5","Cash",400.3,120.3,50,0.0);
		myContracts.addContract(contract2);
		Contracts contract3 = new Contracts(myContracts.getSize(),"Card2","RN","6933333331","3/4/4","Cash",300.3,200.3,240,0.0);
		myContracts.addContract(contract3);
		Contracts contract4 = new Contracts(myContracts.getSize(),"Mobile Internet1","IE","6912121212","5/3/2","Card",0.0,0.0,0.0,1390.2);
		myContracts.addContract(contract4);
		Contracts contract5 = new Contracts(myContracts.getSize(),"Mobile Internet2","SS","69323232323","4/3/4","Card",0.0,0.0,0.0,3150.2);
		myContracts.addContract(contract5);
		//Menu of the user
		
		while(!done) { 
			System.out.println("\n1. Show Available Services");
			System.out.println("2. Create new Contract");
			System.out.println("3. Show available contracts of services");
			System.out.println("4. Update Statistics");
			System.out.println("5. Total monthly cost for each Service and monthly available balance for Contract Card");
			System.out.println("6. Free talk time,sms for Mobile Phone and free data for Mobile Internet");
			System.out.println("0. Exit");
			System.out.print("> ");
			answer = in.nextLine();
			if (answer.equals("1")) {
				System.out.println("1)Programm Contracts");
				System.out.println("2)Card Contracts");
				System.out.println("3)Mobile Internet");
			}
			else if (answer.equals("2")) {
				System.out.println("Please choose a Service");
				System.out.println("0)Programm1\n1)Programm2\n2)Card1\n3)Card2\n4)Internet1\n5)Internet2");
				System.out.print(">");
				choosenService = in.nextLine();
				Contracts contract6 = new Contracts();
				myContracts.newContract(choosenService,contract6);
				myContracts.addContract(contract6);
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
			}else if (answer.equals ("5")){
				System.out.println("Enter the code that your contract has to show your monthly cost ");
				myContracts.listCodes();
				System.out.print(">");
				choosenCode = in.nextInt();
				double totalCost = 0;
				if(choosenCode < myContracts.getSize()){
					Contracts choosenContract = myContracts.getContract(choosenCode);
				    if(choosenContract.getServiceName().equals ("Programm1")){
					    totalCost = programm1.getCost(choosenContract);	
				    }else if (choosenContract.getServiceName().equals ("Card1")){
					    totalCost = card1.getCost(choosenContract);
				    }else if (choosenContract.getServiceName().equals ("Mobile Internet1")){
					    totalCost = internet2.getCost(choosenContract);
				    }else if (choosenContract.getServiceName().equals ("Programm2")){
					    totalCost = programm2.getCost(choosenContract);
				    }else if (choosenContract.getServiceName().equals ("Card2")){
					    totalCost = card2.getCost(choosenContract);
				    }else if (choosenContract.getServiceName().equals ("Mobile Internet2")){
					    totalCost = internet2.getCost(choosenContract);
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
					if(choosenContract.getServiceName().equals ("Programm1")){
						programm1.getFreeMobileTimeBalance(choosenContract);
						programm1.getFreeHomeTimeBalance(choosenContract);
						programm1.getFreeSmsBalance(choosenContract);
					}else if (choosenContract.getServiceName().equals ("Card1")){
						card1.getFreeMobileTimeBalance(choosenContract);
						card1.getFreeHomeTimeBalance(choosenContract);
						card1.getFreeSmsBalance(choosenContract);
					}else if (choosenContract.getServiceName().equals ("Mobile Internet1")){
						internet1.getFreeDataBalance(choosenContract);
					}else if (choosenContract.getServiceName().equals ("Programm2")){
						programm2.getFreeMobileTimeBalance(choosenContract);
						programm2.getFreeHomeTimeBalance(choosenContract);
						programm2.getFreeSmsBalance(choosenContract);
					}else if (choosenContract.getServiceName().equals ("Card2")){
						card2.getFreeMobileTimeBalance(choosenContract);
						card2.getFreeHomeTimeBalance(choosenContract);
						card2.getFreeSmsBalance(choosenContract);
					}else if (choosenContract.getServiceName().equals ("Mobile Internet2")){
						internet2.getFreeDataBalance(choosenContract);
					}
				}else{
					System.out.println("Wrong code.");
				}
			}else if(answer.equals("0")) done = true;
		}
	}
}
