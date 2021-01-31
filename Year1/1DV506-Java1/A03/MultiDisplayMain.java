package lg222sv_assign3;

public class MultiDisplayMain {

	public static void main(String[] args) {
		
		MultiDisplay md = new MultiDisplay();
		
		md.setDisplayMessage("Hello World!");
		md.setDisplayCount(3);
		md.display();
	
		
		md.display("Goodbye cruel World!", 5);
		
		System.out.println("Current Message: "+md.getDisplayMessage());
	}

}
