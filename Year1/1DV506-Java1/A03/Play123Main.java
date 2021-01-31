package lg222sv_assign3;

public class Play123Main {
	
	private int PlayedGame;
	private int GameWon;
	
	public Play123Main(){
		PlayedGame=0;
		GameWon=0;
	}
	
	public boolean play123() {
		
		boolean Win = true;
		Deck d1 = new Deck();
		d1.CreateDeckOfCard();
		while (Win==true) {
			for (int i=1;i<=3;i++) {
				String help =d1.DealACard();
				if (i==1) {
					if(help.contains("ACE")){
						Win=false;
						break;
					}
				}
				else if (i==2) {
					if(help.contains("TWO")){
						Win=false;
						break;
					}
				}
				else if (i==3) {
					if(help.contains("THREE")){
						Win=false;
						break;
					}
				}
				
				if (d1.getAmountOfCard()==0) {
					Win =false;
					GameWon++;
					break;
				}
			}
		}
		PlayedGame++;
		return Win;
	}
	
	public int getWonGame() {return GameWon;}
	public int getGamePlayed() {return PlayedGame;}

	public static void main(String[] args) {
		Play123Main Play = new Play123Main();
		
		for (int i=0;i<10000;i++) {
		Play.play123();
		}
		double GW = Play.getWonGame();
		double GP=Play.getGamePlayed();
		double calc = (GW/GP);
		System.out.println("This is the percentage of Won Games: "+calc*100+" %");
	}

}
