package lg222sv_assign4.newsagency;

import java.util.ArrayList;

public class NewsPaper {
	private String name; //Store the name of the Newspaper
	private ArrayList<String> sentnews = new ArrayList<String>(); //Store the news created by the newspaper
	private ArrayList<ArrayList<String>> ReceivedNews = new ArrayList<ArrayList<String>>(); //Store all the news from all the other NewsPaper
	
	public NewsPaper(String na) {
		name =na;
	}
	//TO set the news that need to be sent to the news agency
	public void setsendNews(String ne) {
		sentnews.add(ne);
	}
	public void setsendNews(ArrayList<String> ne) {
		sentnews.addAll(ne);
	}
	//set the news that we receive from the news agency
	public void setReceiveNews(ArrayList<String> recnew) {
		ReceivedNews.add(recnew);
	}
	public ArrayList<String> getSentNews(){return sentnews;}
	public ArrayList<ArrayList<String>> getReceivedNews() {return ReceivedNews;}
	public String getName() {return name;}
}
