package lg222sv_assign3;

import java.util.ArrayList;
import java.util.Random;
import lg222sv_assign3.Card.rank;
import lg222sv_assign3.Card.suite;

public class Deck {
	private ArrayList<String> DeckOfCard =new ArrayList<String>();
	private int HowManyCards;
	private ArrayList<String> DealtCard = new ArrayList<String>();
	
	public Deck() {
		HowManyCards=52;
	}
	
	public void CreateDeckOfCard() {
		HowManyCards=52;
		
		for (int i=1;i<=4;i++) {
			for (int k=1;k<=13;k++) {
				String helps = suite.getSuiteName(i);
				String helpr = rank.getRankName(k);
				String Cardd = helpr + " "+helps;
				DeckOfCard.add(Cardd);
			}
		}
	}
	
	public String toString() {
		String Message="";
		for (int i=0;i<DeckOfCard.size();i++) {
			Message += DeckOfCard.get(i)+ " ";
		}
		return Message;
	}
	
	public String DealACard() {
		
		Random rd = new Random();
		int Random = rd.nextInt(HowManyCards);
		
		String Print = DeckOfCard.get(Random);
		
		DeckOfCard.remove(Random);
		DealtCard.add(Print);
		
		HowManyCards--;
		return Print;
	 
	}
	
	public void SuffleDeck() {
		if (HowManyCards==52) {
			Random rd2 =new Random();
		
			for (int i=0; i<52; i++) {
				int randomPosition = rd2.nextInt(DeckOfCard.size());
				String temp = DeckOfCard.get(i);
		    
				DeckOfCard.add(i,DeckOfCard.get(randomPosition));
				DeckOfCard.add(randomPosition, temp);
			}
		}
		else
			System.out.println("There is less than 52 cards so I can't shuffle ");
	}
	
	public int getAmountOfCard() {return HowManyCards;}
	
	public ArrayList<String> getDealtCard() {
		System.out.println("This is the dealt card : ");
		return DealtCard;}
}


