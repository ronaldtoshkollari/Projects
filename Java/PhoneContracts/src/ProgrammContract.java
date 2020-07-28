public class ProgrammContract extends MobileTelephony {
	
	//Constructor of ProgrammContract class
	public ProgrammContract(String serviceName,double subFee,double freeTalkTimeMobile,double freeTalkTimeHome,int freeSms,double mobileTalkTimeCost,double homeTalkTimeCost,double smsCost){
		super(serviceName,subFee,freeTalkTimeMobile,freeTalkTimeHome,freeSms,mobileTalkTimeCost,homeTalkTimeCost,smsCost);
	}
	//public method to get cost
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
		return totalCost - (totalCost*0.2);
	}
	//public method to get free mobile talk time balance
	public void getFreeMobileTimeBalance(Contracts m){
		double balance = getFreeTalkTimeMobile() - m.getTalkTimeMobileUsed();
		System.out.println("Free Mobile Time balance: " + balance);
	}
	//public method to get free home talk time balance
	public void getFreeHomeTimeBalance(Contracts h){
		double balance = getFreeTalkTimeHome() - h.getTalkTimeHomeUsed();
		System.out.println("Free Home Time balance: " + balance);
	}
	//public method to get free sms balance
	public void getFreeSmsBalance(Contracts s){
		double balance = getFreeSms() - s.getSmsUsed();
		System.out.println("Free Sms balance: " + balance);
	}
}