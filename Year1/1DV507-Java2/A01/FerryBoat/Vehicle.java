package lg222sv_assign1.FerryBoat;


public abstract class Vehicle {
	protected int AmountofPassenger;
	
	public Vehicle() {
		
	}
	
	protected void setAmountPassenger(int n ) {
		AmountofPassenger=n;
	}
	
	public int getAmountPassenger() {return AmountofPassenger;}

	
}
