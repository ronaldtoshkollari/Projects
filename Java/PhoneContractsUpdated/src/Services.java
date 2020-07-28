public abstract class Services {
	//Declaring Variables
	private String serviceName;
	private double subFee;
	
	//Constructor of Services
	public Services(String serviceName,double subFee){
		this.serviceName = serviceName;
		this.subFee = subFee;
	}
	public Services(){
		
	}
	//public method to set serviceName
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	//public method to get serviceName
	public String getServiceName(){
		return serviceName;
	}
	//public method to set subFee
	public void setSubFee(double subFee){
		this.subFee = subFee;
	}
	//public method to get subFee
	public double getSubFee(){
		return subFee;
	}
	//print method
	public void print(){
		System.out.println("Service: " + getServiceName());
		System.out.println("Subscription Fee: " + getSubFee());
	}
	//abstract method to calculate the cost
	abstract double getCost(Contracts a);
	public abstract String getType();
	public abstract double getDiscount();
	public abstract void getStatistics(Contracts a);
}