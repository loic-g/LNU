package lg222sv_assign3;

public class MultiDisplay {
	
	private String Message;
	private int count;
	
	public MultiDisplay() {
		Message="";
		count=0;
	}
	
	public void setDisplayMessage(String DC) {
		Message = DC;
	}
	
	public String getDisplayMessage() {return Message;}
	
	public void setDisplayCount(int n) {
		if (n>=0) {
			count = n;
		}
		else
			System.err.println("Please provide a positive number for the count");
	}
	
	public int getDisplayCount() {
		return count;
	}
	
	public void display() {
		
		for (int i=0;i<count;i++) {
			System.out.println(Message);
		}
	}
	
	public void display(String msg, int c) {
		if (c>=0) {
			Message = msg;
			for (int i=0;i<c;i++) {
				System.out.println(msg);
			}
		}
		else
			System.err.println("Please provide a positive number for the count");
		
	}
}
