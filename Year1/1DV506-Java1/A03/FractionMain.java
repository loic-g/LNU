package lg222sv_assign3;

public class FractionMain {

	public static void main(String[] args) {
		int Num = 2;
		int Den = 1;
		
		
		Fraction f1 = new Fraction(Num, Den);
		Fraction f2 = new Fraction(-2,-1);
		
		System.out.println("Ex 2: \nThis is the numerator: "+f1.getNumerator());
		System.out.println("This is the denominator: "+f1.getDenominator());
		System.out.println("\nEx 3: \nIs the fractional number negative? "+f1.isNegative());
		System.out.println("\nEx 4: \nAdding two fractions : "+f1.add(f2));
		System.out.println("Subtracting two fucntions: "+f1.subtract(f2));
		System.out.println("Multiplying two fucntions: "+f1.multiply(f2));
		System.out.println("Dividing two functions: "+f1.divide(f2));
		System.out.println("\nEx 5: \nAre the two functions equals? "+f1.isEqualTo(f2));
		System.out.println("\nEx 6: \nThis is a fraction "+f1.toString());
	}

}
