package lg222sv_assign3;

public class Fraction {
	
	private int Numerator;
	private int Denominator;
	
	public Fraction (int num, int Den) {
		if (Den != 0) {
		Numerator = num;
		Denominator = Den;
		}
		else
			System.out.println("Please provide a Denominator different then 0 ");
	}
	
	public int getNumerator() {return Numerator;}
	public int getDenominator() {return Denominator;}
	
	public boolean isNegative() {
		if (Numerator<0 || Denominator<0) {
			return true;
		}
		else
			return false;
	}
	
	public String add(Fraction f2) {
		if (CheckDenZero()|| f2.CheckDenZero()) {
			return "Please Provide an integer different than 0 for the denominator";
		}
		else {
			return ((Numerator*f2.Denominator)+(f2.Numerator*Denominator))+
				"/"+(Denominator*f2.Denominator) ;
		}
	}
	
	public String subtract(Fraction f2) {
		if (CheckDenZero()|| f2.CheckDenZero()) {
			return "Please Provide an integer different than 0 for the denominator";
		}
		else {
			return ((Numerator*f2.Denominator)-(f2.Numerator*Denominator))+
				"/"+(Denominator*f2.Denominator) ;
		}
	}
	public String multiply(Fraction f2) {
		if (CheckDenZero()|| f2.CheckDenZero()) {
			return "Please Provide an integer different than 0 for the denominator";
		}
		else {
			return (Numerator*f2.Numerator)+"/"+(Denominator*f2.Denominator) ;
		}
	}
	
	public String divide(Fraction f2) {
		if (CheckDenZero()|| f2.CheckDenZero()) {
			return "Please Provide an integer different than 0 for the denominator";
		}
		else {
			return (Numerator*f2.Denominator)+"/"+(Denominator*f2.Numerator) ;
		}
	}
	
	public String toString() {
		String text =Numerator+"/"+Denominator;
		return text;
	}
	private boolean CheckDenZero() {
		if (Denominator==0) {return true;}
		else return false;
	}
	
	public boolean isEqualTo(Fraction f2) {
		if (Numerator==f2.Numerator && Denominator==f2.Denominator) {return true;}
		else return false;
	}
	
}
