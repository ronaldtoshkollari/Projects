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
	//public method to get cost
	public double getCost(Contracts a){
		double totalCost;
		if(getFreeData() - a.getDataUsed() >= 0 ){
			totalCost = getSubFee();
		}else{
			totalCost = (a.getDataUsed() - getFreeData()) * getDataCost() + getSubFee();
		}
		return totalCost - (totalCost * 0.30);
	}
	//method to get Free data Balance
	public void getFreeDataBalance(Contracts d){
		double balance = getFreeData() - d.getDataUsed();
		System.out.println("Free Data balance: " + balance);
	}
}