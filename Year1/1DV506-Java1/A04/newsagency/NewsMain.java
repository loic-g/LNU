package lg222sv_assign4.newsagency;

import java.util.ArrayList;

public class NewsMain {

	public static void main(String[] args) {
		NewsPaper BBC = new NewsPaper("BBC");
		NewsPaper RussiaToday = new NewsPaper("RussiaToday");
		NewsPaper TheBuletin = new NewsPaper("TheBuletin");
		
		BBC.setsendNews("First news BBC ");
		BBC.setsendNews("Second news BBC");
		
		RussiaToday.setsendNews("First news RT ");
		RussiaToday.setsendNews("Second news RT");
		RussiaToday.setsendNews("Third news RT ");
		RussiaToday.setsendNews("Fourth news RT");
		
		TheBuletin.setsendNews("First news TB");
		TheBuletin.setsendNews("Second news TB");
		TheBuletin.setsendNews("THird news TB ");
		NewsAgency NA = new NewsAgency();
		NA.registerNP(BBC);
		NA.registerNP(TheBuletin);
		NA.registerNP(RussiaToday);
		
		ArrayList<ArrayList<String>> help =BBC.getReceivedNews();
		for (int i=0;i<help.size();i++) {
			System.out.println(help.get(i));
		}

	}

}
