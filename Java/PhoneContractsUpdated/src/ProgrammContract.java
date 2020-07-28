public class ProgrammContract extends MobileTelephony {
	
	//Constructor of ProgrammContract class
	public ProgrammContract(String serviceName,double subFee,double freeTalkTimeMobile,double freeTalkTimeHome,int freeSms,double mobileTalkTimeCost,double homeTalkTimeCost,double smsCost){
		super(serviceName,subFee,freeTalkTimeMobile,freeTalkTimeHome,freeSms,mobileTalkTimeCost,homeTalkTimeCost,smsCost);
	}
	//default Constructor
	public ProgrammContract(){
		
	}
	//method that returns cost
	public double getCost(Contracts a){
		double totalCost;
		if(getFreeTalkTimeMobile() - a.getTalkTimeMobileUsed() >= 0 ){
			totalCost = getSubFee();
	    }else{
		     totalCost = (a.getTalkTimeMobileUsed() - getFreeTalkTimeMobile()) * getMobileTalkTimeCost() + getSubFee();
	    }
		if(getFreeTalkTimeHome() - a.getTalkTimeHomeUsed() < 0) {
			totalCost =  totalCost + (a.getTalkTimeHomeUsed() - getFreeTalkTimeHome()) * getHomeTalkTimeCost();
		}
		if(getFreeSms() - a.getSmsUsed() < 0){
			totalCost = totalCost + (a.getSmsUsed() - getFreeSms()) * getSmsCost();
		} 
		return totalCost - (totalCost * getDiscount());
	}
	//public method to get the statistics 
	public void getStatistics(Contracts a){
		double balance = getFreeTalkTimeMobile() - a.getTalkTimeMobileUsed();
		System.out.println("Free Mobile Time balance: " + balance);
		balance = getFreeTalkTimeHome() - a.getTalkTimeHomeUsed();
		System.out.println("Free Home Time balance: " + balance);
		balance = getFreeSms() - a.getSmsUsed();
		System.out.println("Free Sms balance: " + balance);
		
	}
	//public method to get discount of ProgrammContract
	public double getDiscount(){
		return 0.2;
	}
	//public method which returns a String that the "type" of class
	public String getType(){
		return "ProgrammContract";
	}
}