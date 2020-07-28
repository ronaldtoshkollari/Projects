public abstract  class MobileTelephony extends Services {
	//Declaring Variables
	private double freeTalkTimeMobile;
	private double freeTalkTimeHome;
	private int freeSms;
	private double mobileTalkTimeCost;
	private double homeTalkTimeCost;
	private double smsCost;
	
	
	//Constructor of MobileTelephony
	public MobileTelephony(String serviceName,double subFee,double freeTalkTimeMobile,double freeTalkTimeHome,int freeSms,double mobileTalkTimeCost,double homeTalkTimeCost,double smsCost){
		super(serviceName,subFee);
		this.freeTalkTimeMobile = freeTalkTimeMobile;
		this.freeTalkTimeHome = freeTalkTimeHome;
		this.freeSms = freeSms;
		this.mobileTalkTimeCost = mobileTalkTimeCost;
		this.homeTalkTimeCost = homeTalkTimeCost;
		this.smsCost = smsCost;
	}
	public MobileTelephony(){
		
	}
	//public method to set freeTalkTimeMobile
	public void setFreeTalkTimeMobile(double freeTalkTimeMobile){
		this.freeTalkTimeMobile = freeTalkTimeMobile;
	}
	//public method to get freeTalkTimeMobile
	public double getFreeTalkTimeMobile(){
		return freeTalkTimeMobile;
	}
	//public method to set freeTalkTimeHome
	public void setFreeTalkTimeHome(double freeTalkTimeHome){
		this.freeTalkTimeHome = freeTalkTimeHome;
	}
	//public method to get freeTalkTimeHome
	public double getFreeTalkTimeHome(){
		return freeTalkTimeHome;
	}
	//public method to set freeSms
	public void setFreeSms(int freeSms){
		this.freeSms = freeSms;
	}
	//public method to get freeSms
	public int getFreeSms(){
		return freeSms;
	}
	//public method to set talkTimeCost Mobile
	public void setMobileTalkTimeCost(double mobileTalkTimeCost){
		this.mobileTalkTimeCost = mobileTalkTimeCost;
	}
	//public method to get talkTimeCost Mobile
	public double getMobileTalkTimeCost(){
		return mobileTalkTimeCost;
	}
	//public method to set talkTimeCost Home
	public void setHomeTalkTimeCost(double homeTalkTimeCost){
		this.homeTalkTimeCost = homeTalkTimeCost;
	}
	//public method to get talkTimeCost Home
	public double getHomeTalkTimeCost(){
		return homeTalkTimeCost;
	}
	//public methos to set smsCost
	public void setSmsCost(double smsCost){
		this.smsCost = smsCost;
	}
	//public method to get smsCost
	public double getSmsCost(){
		return smsCost;
	}
	//print method
	public void print(){
		super.print();
		System.out.println("Free talk time for Mobiles: " + getFreeTalkTimeMobile());
		System.out.println("Free talk time for home phones: " + getFreeTalkTimeHome());
		System.out.println("Free SMS: " + getFreeSms());
		System.out.println("Cost for Mobile phones: " + getMobileTalkTimeCost());
		System.out.println("Cost for Home phones: " + getHomeTalkTimeCost());
		System.out.println("SMS cost: " + getSmsCost());
	}
	
	//abstract methods 
	public abstract double getCost(Contracts a);
	public abstract String getType();
	public abstract double getDiscount();
	public abstract void getStatistics(Contracts a);
}