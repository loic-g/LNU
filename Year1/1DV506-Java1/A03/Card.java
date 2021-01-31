package lg222sv_assign3;


public class Card {
	public enum suite {
		HEART(1), DIAMOND(2), SPADES(3), CLUBS(4);
		
		private int intValueSuite;
		
		private suite (int intvalsui) {
			this.intValueSuite=intvalsui;
		}
		private int getSuiteLevel () {
			return this.intValueSuite;
		}
		public static String getSuiteName(int n) {
			for (suite test : suite.values()) {
				if (n == test.getSuiteLevel() ) {
					return test.name();
				}
			}
			return null;
		}
	}
	public enum rank {
		TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),
		NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13),ACE(1);
		
		private int intValueRank;
		
		private rank (int intvalra) {
			this.intValueRank=intvalra;
		}
		private int getRankLevel () {
			return this.intValueRank;
		}
		public static String getRankName(int n) {
			for (rank test : rank.values()) {
				if (n == test.getRankLevel() ) {
					return test.name();
				}
			}
			return null;
		}
	}
	
}
