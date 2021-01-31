package lg222sv_assign1.FerryBoat;

public class Bus extends Vehicle{
	
	private boolean AlreadyEmbarkOnce;
	
	public Bus() {
		
	}
	public Bus(int ampassenger) throws Exception {
		if (ampassenger>0 && ampassenger<=20) {
			setAmountPassenger(ampassenger);
		}
		else
			throw new Exception("Please provide a number of passenger between 1 and 20"); 
	}
	
	
	public boolean getAlreadyEmbOnceBus() {return AlreadyEmbarkOnce;}
	public void setEmbOnceBus(boolean b) {AlreadyEmbarkOnce=b;}
	
	public void setAmountPassBus(int passenger) throws Exception {
		if (passenger>0 && passenger<=20) {
			setAmountPassenger(passenger);
		}
		else 
			throw new Exception("Please provide a number of passenger between 1 and 20");
	}
	
}
