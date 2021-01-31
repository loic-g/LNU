package lg222sv_assign3;

public class RandomWalkMain {

	public static void main(String[] args) {
		RandomWalk RW = new RandomWalk(10,2);
		
		RW.takeStep();
		RW.takeStep();
		System.out.println(RW.getPosition());
		System.out.println("Is there more step available? "+RW.moreSteps());
		System.out.println(RW.toString());
		System.out.println("Is the postion in the boundries? "+RW.inBounds());
		
		RW.walk();
		System.out.println(RW.toString());
		
	}

}
