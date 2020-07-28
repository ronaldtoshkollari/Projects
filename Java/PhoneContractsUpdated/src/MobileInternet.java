public class MobileInternet extends Services {
	//Declaring Variables
	private double freeData;
	private double dataCost;
	
	//Constructor of MobileInternet class
	public MobileInternet(String serviceName,double subFee,double freeData,double dataCost){
		super(serviceName,subFee);
		this.freeData = freeData;
		this.dataCost = dataCost;
	}
	//default constructor
	public MobileInternet(){
		
	}
	//public method to set freeData
	public void setFreeData(double freeData){
		this.freeData = freeData;
	}
	//public method to get freeData
	public double getFreeData(){
		return freeData;
	}
	//public method to set dataCost
	public void setDataCost(double dataCost){
		this.dataCost = dataCost;
	}
	//public method to get dataCost
	public double getDataCost(){
		return dataCost;
	}
	//Print method
	public void print(){
		super.print();
		System.out.println("Free Mobile Data: " + getFreeData());
		System.out.println("Data Cost " + getDataCost());
	}
	//public method to get Cost
	public double getCost(Contracts a){
		double totalCost;
		if(getFreeData() - a.getDataUsed() >= 0 ){
			totalCost = getSubFee();
		}else{
			totalCost = (a.getDataUsed() - getFreeData()) * getDataCost() + getSubFee();
		}
		return totalCost - (totalCost * getDiscount());
	}
	//method to get Free data Balance
	public void getStatistics(Contracts a){
		double balance = getFreeData() - a.getDataUsed();
		System.out.println("Free Data balance: " + balance);
	}
	//public method to get Discount of MobileInternet
	public double getDiscount(){
		return 0.3;
	}
	//public method which returns a String that the "type" of class
	public String getType(){
		return "MobileInternet";
	}
}