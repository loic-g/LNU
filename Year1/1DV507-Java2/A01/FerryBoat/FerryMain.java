package lg222sv_assign1.FerryBoat;

public class FerryMain {

	public static void main(String[] args) throws Exception {
		Car Mercedes = new Car(3);
		Bus Volvo = new Bus(19);
		Lorry Truck = new Lorry(2);
		FerryProg Ferry = new FerryProg();
		Bus MEMEM = new Bus(15);
		Bicycle bike = new Bicycle();
		Bicycle bike2 = new Bicycle();
	
		
		Ferry.embark(Mercedes);
		Ferry.embark(Volvo);
		Ferry.embark(Truck);
		Ferry.embark(MEMEM);
		Ferry.embark(bike);
		Ferry.embark(bike2);

		System.out.println("There are "+ Ferry.countPassengers()+" passengers on board");
		System.out.println("There are "+Ferry.countVehicleSpace()+" used vehicle space");
		System.out.println("The Ferry has made "+Ferry.countMoney()+" dollars so far");
		//Ferry.disembark();
		System.out.println(Ferry.toString());
		;
		//Ferry.embark(bike);
		//Ferry.embark(bike2);
		//System.out.println(Ferry.toString());
		Ferry.disembark();
		Ferry.embark(Mercedes);
		
		System.out.println(Ferry.toString());
		
		
	}

}
