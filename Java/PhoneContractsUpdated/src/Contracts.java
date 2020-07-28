import java.util.*;
public class Contracts {
	//Declaring variables
	private int code;
	private String serviceName;
	private String name;
	private String phoneNumber;
	private String date;
	private String payment;
	private double talkTimeMobileUsed;
	private double talkTimeHomeUsed;
	private int smsUsed;
	private double dataUsed;
	private Scanner in = new Scanner(System.in);
	
	
	//Constructor of Contracts class
	public Contracts(int code,String serviceName, String name, String phoneNumber,String date, String payment,double talkTimeMobileUsed,double talkTimeHomeUsed,double smsUsed,double dataUsed){
		this.code = code;
		this.serviceName = serviceName;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.date = date;
		this.payment = payment;
		this.talkTimeMobileUsed = talkTimeMobileUsed;
		this.talkTimeHomeUsed = talkTimeHomeUsed;
		this.dataUsed = dataUsed;
	}
	//Default Construcotr
	public Contracts(){
		
	}
	//public method to set code
	public void setCode(int code){
		this.code = code;
	}
	//public method to get code
	public int getCode(){
		return code;
	}
	//public method to set serviceName
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	//public method to get serviceName
	public String getServiceName(){
		return serviceName;
	}
	//public method to set name
	public void setName(String name){
		this.name = name;
	}
	//public method to get name
	public String getName(){
		return name;
	}
	//public method to set phoneNumber
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	//public method to get phoneNumber
	public String getPhoneNumber(){
		return phoneNumber;
	}
	//public method to set date
	public void setDate(String date){
		this.date = date;
	}
	//public method to get date
	public String getDate(){
		return date;
	}
	//public method to set payment
	public void setPayment(String payment){
		this.payment = payment;
	}
	//public method to get payment
	public String getPayment(){
		return payment;
	}
	//public method to set talkTimeMobileUsed
	public void setTalkTimeMobileUsed(double talkTimeMobileUsed){
		this.talkTimeMobileUsed = talkTimeMobileUsed;
	}
	//public method to get talkTimeMobileUsed
	public double getTalkTimeMobileUsed(){
		return talkTimeMobileUsed;
	}
	//public method to set talkTimeHomeUsed
	public void setTalkTimeHomeUsed(double talkTimeHomeUsed){
		this.talkTimeHomeUsed = talkTimeHomeUsed;
	}
	//public method to get talkTimeHomeUsed
	public double getTalkTimeHomeUsed(){
		return talkTimeHomeUsed;
	}
	//public method to set smsUsed
	public void setSmsUsed(int smsUsed){
		this.smsUsed = smsUsed;
	}
	//public method to get smsUsed
	public int getSmsUsed(){
		return smsUsed;
	}
	//public method to set dataUsed
	public void setDataUsed(double dataUsed){
		this.dataUsed = dataUsed;
	}
	//public method to get dataUsed
	public double getDataUsed(){
		return dataUsed;
	}
	//public method that asks user to fill infos
	public void setInfo(){
		String n,p,d,m;
		System.out.println("Please enter your name: ");
		n = in.nextLine();
		setName(n);
		System.out.println("Please enter your phone Number: ");
		p = in.nextLine();
		setPhoneNumber(p);
		System.out.println("Please enter date: ");
		d = in.nextLine();
		setDate(d);
		System.out.println("Please enter your payment way(cash or card): ");
		m = in.nextLine();
		setPayment(m);
		setTalkTimeMobileUsed(0.0);
		setTalkTimeHomeUsed(0.0);
		setSmsUsed(0);
		setDataUsed(0.0);
	}
	//public method to updateStats of a certain type of service
	public void updateStats() {
		double mobile,home,data;
		int sms;
		if(getServiceName().startsWith("Programm Contract")){
			System.out.println("Enter monthly mobile talk time used: ");
			mobile = in.nextDouble();
			setTalkTimeMobileUsed(mobile);
			System.out.println("Enter monthly home talk time used: ");
			home = in.nextDouble();
			setTalkTimeHomeUsed(home);
			System.out.println("Enter monthly sms sent:" );
			sms = in.nextInt();
			setSmsUsed(sms);
		}else if(getServiceName().startsWith("Card Contract")){
			System.out.println("Enter monthly mobile talk time used: ");
			mobile = in.nextDouble();
			setTalkTimeHomeUsed(mobile);
			System.out.println("Enter monthly home talk time used: ");
			home = in.nextDouble();
			setTalkTimeHomeUsed(home);
			System.out.println("Enter monthly sms sent:" );
			sms = in.nextInt();
			setSmsUsed(sms);
		}else if (getServiceName().startsWith("Mobile Internet")){
			System.out.println("Enter monthly mobile data used: ");
			data = in.nextDouble();
			setDataUsed(data);
		}
		
	}
	//public method to get discount
	public double getDiscount(Services a){
		if(a.getType().equals("ProgrammContract")){
			return a.getDiscount();
		}else if(a.getType().equals("CardContract")){
			return a.getDiscount();
		}else if(a.getType().equals("MobileInternet")){
			return a.getDiscount();
		}else{
			return 0;
		}
	}
	//public method to get the "Type" of the service that has the contract
	public String getType(Contracts a){
		if(a.getServiceName().startsWith("Programm Contract")){
			return "ProgrammContract";
		}else if(a.getServiceName().startsWith("Card Contract")){
			return "CardContract";
		}else if(a.getServiceName().startsWith("Mobile Internet")){
			return "MobileIntenet";
		}else{
			return "NO SERVICE TYPE FOUND";
		}
	}
	//public method to get discount
	public double getDiscount(String type){
		if(type.equalsIgnoreCase("ProgrammContract")){
			return 0.2;
		}else if(type.equalsIgnoreCase("CardContract")){
			return 0.25;
		}else if(type.equalsIgnoreCase("MobileInternet")){
			return 0.3;
		}else{
			return 0;
		}
	}
	
}