package lg222sv_assign4.stack;

public class StackMain {

	public static void main(String[] args) {
		StackProg s1 = new StackProg();
		System.out.println("Is the Stack Empty? "+s1.isEmpty());
		s1.push("Plate 1");
		s1.push("Plate 2");
		s1.push("Plate 3");
		s1.push("Plate 4");
		s1.push("Plate 5");
		s1.push("Plate 6");
		s1.push("Plate 7");
		s1.push("Plate 8");
		
		System.out.println("Pop this elememt: "+s1.pop());
		System.out.println("Peek this element: "+s1.peek());
		s1.iterator();
		System.out.println("\nThe Stack contains "+s1.size()+" objects!");

	}

}
