package lg222sv_assign1.intCollection;

public class CollectionMain {

	public static void main(String[] args) {
		//ArrayIntList
		System.out.println("\nThis part is the main for ArrayIntList: ");
		ArrayIntList a1 = new ArrayIntList();
		
		a1.add(1);
		a1.add(2);
		a1.add(3);
		a1.add(4);
		a1.add(5);
		a1.add(6);
		/*
		a1.add(7);
		a1.add(8);
		a1.add(9);
		a1.add(10);
		a1.add(11);
		a1.add(12);
		a1.add(13);
		a1.add(14);
		a1.add(15);
		a1.add(16);
		a1.add(17);
		*/
		//System.out.println("\nThe integer at index 5 is: "+a1.get(11));
	//	System.out.println("The index of 4 is: "+a1.indexOf(9));
		System.out.println(a1.toString());
		a1.remove(2);
		System.out.println("WATCHCHHHH HEEERRREEE\n"+a1.toString());
		a1.addAt(64,4);
		System.out.println(a1.toString());
		
		
		//ArrayIntStack 
		System.out.println("\nThis part is the main for ArrayIntStack: \n");
		ArrayIntStack s1 = new ArrayIntStack();
		s1.push(1);
		s1.push(2);
		s1.push(3);
		s1.push(4);
		System.out.println(s1.toString());
		System.out.println("This element is now popped: "+s1.pop());
		System.out.println("The element at the top of the stack is: "+s1.peek());
		
		System.out.println(s1.toString());
	}

}
