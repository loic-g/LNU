package lg222sv_assign4.newsagency;

import java.util.ArrayList;

public class NewsAgency {

	private ArrayList <String> Name; //Store the name of all the NewsPapers
	private static ArrayList<NewsPaper> News ; //Store the all the Objects from class NewsPaper
	public NewsAgency() {
		Name = new ArrayList<String>();
		News = new ArrayList<NewsPaper>();
	}
	/* AS soon as a newspaper register itself at the news agency, all its news are sent to all the registered NewsPaper
	 * 
	 * Inside the NewsMain Class there is a method that add all the news from other newspaper that the news agency send them.
	 * 
	 */
	// TO register every Newspaper
	public void registerNP(NewsPaper np) {
		Name.add(np.getName());
		News.add(np);
		SendNewsToAllNP(np.getSentNews());
	}
	//to send news to all the other newspaper
	public static void SendNewsToAllNP(ArrayList<String> nn) {
		if (News.size()>1) {
			for (int i=0;i<News.size()-1;i++) {
				News.get(i).setReceiveNews(nn);
			}
		}
	}
	
	
	
}
