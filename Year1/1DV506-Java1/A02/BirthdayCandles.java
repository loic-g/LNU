package lg222sv_assign2;

public class BirthdayCandles {

	public static void main(String[] args) {
		int box = 24, amount =0,boxcount=0;
		
		for (int i=1;i<101;i++) {
			if (amount<i) {
				int help = i-amount;
				int help2 = (help/24);
				if ((help%24)==0) {
					boxcount= boxcount+help2;
					amount = amount+(help2*box);
					amount = amount-i;
					System.out.println("Before birthday "+i+", buy "+help2+"box(es)");	
				}
				else {
					help2++;
					boxcount= boxcount+help2;
					amount = amount+(help2*box);
					amount = amount-i;
					System.out.println("Before birthday "+i+", buy "+help2+"box(es)");
				}
				
			}
			else {
				amount = amount-i;
			}
		}
		System.out.println("\n\nTotal number of boxes: "+boxcount+", Remaining candles: "+amount);
	}

}
