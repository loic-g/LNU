package lg222sv_assign1.FerryBoat;

public class Lorry extends Vehicle {
	
	private boolean AlreadyEmbarkOnce;
	
	public Lorry() {
		
	}
	public Lorry(int ampassenger) throws Exception {
		if (ampassenger>0 && ampassenger<=2) {
			setAmountPassenger(ampassenger);
		}
		else
			throw new Exception("Please provide a number of passenger between 1 and 2"); 
	}
	
	
	public boolean getAlreadyEmbOnceLorry() {return AlreadyEmbarkOnce;}
	public void setEmbOnceLorry(boolean b) {AlreadyEmbarkOnce=b;}
	
	public void setAmountPassLorry(int passenger) throws Exception {
		if (passenger>0 && passenger<=2) {
			setAmountPassenger(passenger);
		}
		else 
			throw new Exception("Please provide a number of passenger between 1 and 2");
	}
}
