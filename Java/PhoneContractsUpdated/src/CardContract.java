public class CardContract extends MobileTelephony {
	//Declaring Variables
	private double monthlyTotal;
	
	//Constructor of CardContract class
	public CardContract(String serviceName,double subFee,double freeTalkTimeMobile,double freeTalkTimeHome,int freeSms,double mobileTalkTimeCost,double homeTalkTimeCost,double smsCost,double monthlyTotal){
		super(serviceName,subFee,freeTalkTimeMobile,freeTalkTimeHome,freeSms,mobileTalkTimeCost,homeTalkTimeCost,smsCost);
		this.monthlyTotal = monthlyTotal;
	}
	//default contructor
	public CardContract(){
		
	}
	//public method to set monthlyTotal
	public void setMonthlyTotal(double monthlyTotal){
		this.monthlyTotal = monthlyTotal;
	}
	//public method to get monthlyTotal
	public double getMonthlyTotal(){
		return monthlyTotal;
	}
	//Print method
	public void print(){
		super.print();
		System.out.println("Monthly Total: " + getMonthlyTotal());
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
		totalCost = (getMonthlyTotal() - totalCost);
		totalCost = totalCost - (totalCost * getDiscount());
		return totalCost;
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
	//public method to get discount of CardContract
	public double getDiscount(){
		return 0.25;
	}
	//public method which returns a String that the "type" of class
	public String getType(){
		return "CardContract";
	}
}