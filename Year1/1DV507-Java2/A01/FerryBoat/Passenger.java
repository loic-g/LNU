package lg222sv_assign1.FerryBoat;

public class Passenger {
	private int AmountOfPassenger;
	
	public Passenger() {
		AmountOfPassenger=0;
	}
	public Passenger(int n) {
		if (n>0) {
			AmountOfPassenger=n;
		}
	}
	
	public int getAPassenger() {return AmountOfPassenger;}
	public void setAmountPassenger(int passenger) {
		if (passenger>0) {
			AmountOfPassenger=passenger;
		}
	}
}