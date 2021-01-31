package lg222sv_assign1.FerryBoat;

public class Car extends Vehicle {
	
	private boolean AlreadyEmbarkOnce;

	public Car() {
		
	}

	public Car(int amountpass) throws Exception {
		if (amountpass > 0 && amountpass <= 4)
			
			setAmountPassenger(amountpass);
		else
			throw new Exception("Please provide a positive integer greater than 0");
	}

	//public int getAPassCar() {return AmountOfPassenger;}
	public boolean getAlreadyEmbOnceCar() {return AlreadyEmbarkOnce;}
	public void setEmbOnceCar(boolean b) {AlreadyEmbarkOnce=b;}

	public void setAmountPassengerCar(int n) throws Exception {
		if (n > 0 && n <= 4)
			setAmountPassenger(n);
		else
			throw new Exception("Please provide a positive integer greater than 0");
	}
}
