package lg222sv_assign1.FerryBoat;

/*
 * This is where I have the biggest part of my code as it implements the Ferry Interface.
 * 
 * Objects from the different classes (Bus, Car, Bicycle, Lorry) are implemented and then are used inside this class.
 * The Vehicle class is the abstract super class and Car,Bus,Lorry and Bicycle are the subclasses. 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;

public class FerryProg implements Ferry {
	private final int MaxPassSpace;
	private final int MaxVehiSpace;
	private int CPassengers;
	private int CMoney;
	private int CVehiculeSpace;
	private int bicycleAmount;
	private ArrayList<Vehicle> List;
	private int HowManyCars;
	private int HowManyBuses;
	private int HowManyLorry;
	private int HowManyBicycle;
	

	public FerryProg() {
		
		MaxPassSpace = 200;
		MaxVehiSpace = 40;
		CPassengers = 0;
		CMoney = 0;
		CVehiculeSpace = 0;
		bicycleAmount = 0;
		HowManyBicycle=0;
		HowManyBuses=0;
		HowManyCars=0;
		HowManyLorry=0;
		List = new ArrayList<Vehicle>();
	}

	@Override
	public int countPassengers() {return CPassengers;}

	@Override
	public int countVehicleSpace() {return CVehiculeSpace;}

	@Override
	public int countMoney() {return CMoney;}

	@Override
	public Iterator<Vehicle> iterator() {
		Iterator<Vehicle> it = List.iterator();
		//Go through every vehicle and add 1 depending on which class the vehicle comes from

		 while(it.hasNext()) {
			 Vehicle test = it.next();
			if (test instanceof Car) {HowManyCars++;}
			else if (test instanceof Bus) {HowManyBuses++;}
			else  if (test instanceof Lorry) {HowManyLorry++;}
			else if (test instanceof Bicycle) {HowManyBicycle++;}
		 }
		return it;
	}

	@Override
	public void embark(Vehicle v) {
		if (v instanceof Car) {
			if (((Car) v).getAlreadyEmbOnceCar() == false) {
				if (hasSpaceFor(v)) {
					int help = v.getAmountPassenger();
					CPassengers += help;
					CMoney += 100 + help * 20;
					CVehiculeSpace++;
					((Car) v).setEmbOnceCar(true);
					List.add(v);
				} else
					System.out.println("The ferry is full, please take the next one");
			} else
				System.out.println("This Vehicle already embarked once");
		} else if (v instanceof Bus) {
			if (((Bus) v).getAlreadyEmbOnceBus() == false) {
				if (hasSpaceFor(v)) {
					int help = v.getAmountPassenger();
					CPassengers += help;
					CMoney += 200 + help * 15;
					CVehiculeSpace += 4;
					((Bus) v).setEmbOnceBus(true);
					List.add(v);
				} else
					System.out.println("The ferry is full, please take the next one");
			} else
				System.out.println("This Vehicle already embarked once");

		} else if (v instanceof Lorry) {
			if (((Lorry) v).getAlreadyEmbOnceLorry() == false) {
				if (hasSpaceFor(v)) {
					int help = v.getAmountPassenger();
					CPassengers += help;
					CMoney += 300 + help * 20;
					CVehiculeSpace += 8;
					List.add(v);
				} else
					System.out.println("The ferry is full, please take the next one");
			} else
				System.out.println("This Vehicle already embarked once");
		} else if (v instanceof Bicycle) {
			if (((Bicycle) v).getAlreadyEmbOnceBike() == false) {
				if (bicycleAmount % 5 == 0) {
					if (hasSpaceFor(v)) {
						int help = v.getAmountPassenger();
						CPassengers += help;
						CMoney += 40;
						bicycleAmount++;
						CVehiculeSpace++;
						List.add(v);
					} else
						System.out.println("The ferry is full, please take the next one");
				} else {
					CPassengers++;
					CMoney += 40;
					bicycleAmount++;
					List.add(v);
				}
			} else {
				System.out.println("This Vehicle already embarked once");
			}
		}
	}

	@Override
	public void embark(Passenger p) {
		if ((CPassengers + p.getAPassenger()) <= MaxPassSpace) {
			CPassengers += p.getAPassenger();
		} else
			System.out.println("There is not enough space on the Ferry, Please take teh next one");
	}

	@Override
	public void disembark() {
		CPassengers = 0;
		CVehiculeSpace = 0;
		HowManyCars=0;
		HowManyBuses=0;
		HowManyLorry=0;
		HowManyBicycle=0;
		bicycleAmount=0;
		List.clear();
	}

	@Override
	public boolean hasSpaceFor(Vehicle v) {
		if (v instanceof Car) {
			if ((CVehiculeSpace + 1) <= MaxVehiSpace) {
				if ((CPassengers + v.getAmountPassenger()) <= MaxPassSpace)
					return true;
				else
					return false;
			} 
		} else if (v instanceof Bus) {
			if ((CVehiculeSpace + 4) <= MaxVehiSpace) {
				if ((CPassengers + v.getAmountPassenger()) <= MaxPassSpace)
					return true;
				else
					return false;
			}
		} else if (v instanceof Lorry) {
			if ((CVehiculeSpace + 8) <= MaxVehiSpace) {
				if ((CPassengers + v.getAmountPassenger()) <= MaxPassSpace)
					return true;
				else
					return false;
			}
		} else if (v instanceof Bicycle) {
			if ((CVehiculeSpace + 1) <= MaxVehiSpace) {
				if ((CPassengers + v.getAmountPassenger()) <= MaxPassSpace)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	@Override
	public boolean hasRoomFor(Passenger p) {
		if ((CPassengers + p.getAPassenger()) <= MaxPassSpace)
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		iterator();
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n  Number of passengers on board: " + CPassengers);
		sb.append("\n  Used vehicle space:  " + CVehiculeSpace);
		sb.append("\n  Earned Money: " + CMoney);
		sb.append("\n  Vehicles Currently on board:");
		sb.append("\n  \tCars: " + HowManyCars);
		sb.append("\n  \tBuses: " + HowManyBuses);
		sb.append("\n  \tLorry: " + HowManyLorry);
		sb.append("\n  \tBicycle: " + HowManyBicycle);
		
		return sb.toString();
	}

}
