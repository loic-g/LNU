package lg222sv_assign3;

public class PlayCardMain {

	public static void main(String[] args) {
		Deck d1 = new Deck();
		d1.CreateDeckOfCard();
		d1.SuffleDeck();
		System.out.println("\n"+d1.toString());
		System.out.println(d1.DealACard());
		d1.getAmountOfCard();
		System.out.println(d1.DealACard()); 
		System.out.println(d1.DealACard());
		System.out.println(d1.DealACard());
		System.out.println(d1.DealACard());
		
		System.out.println("\n"+d1.getDealtCard());
		System.out.println("There is "+d1.getAmountOfCard()+" left in the Deck");
	}
}
