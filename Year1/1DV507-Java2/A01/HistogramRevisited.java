package lg222sv_assign1;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;



public class HistogramRevisited {

	private int CountInteger;
	private int CountOther;
	private String Path;
	private String Content;
	private int[] Stars;
	
	public HistogramRevisited(String path) {
		CountInteger=0;
		CountOther=0;
		Path=path;
		Content="";
		Stars = new int[10];
	}
	public String getAbsolutePath() {return Path;}
	public int getIntegerInterval() {return CountInteger;}
	public int getIntegerOther() {return CountOther;}
	public String getContent() {return Content;}
	
	public void readFile(String path) throws IOException {
		StringBuilder text = new StringBuilder();
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				int help = sc.nextInt();
				if (help>=1 && help<=100) {
					CountInteger++;
					for (int i=1;i<=10;i++) {
						if (help>=((i-1)*10)+1 && help<=i*10) {//Check how many stars per range 
							Stars[i-1]+=1;//Put it into string array
						}
					}
				}else
					CountOther++;
				text.append(help+"\n");
			}sc.close();
		}catch (IOException ioe) {ioe.printStackTrace();}
		catch (Exception e) {e.printStackTrace();}
		Content=text.toString();
	}
	
	//Create a different method just for printing the histogram
	
	public void printHistogram() {	
		List charts = new ArrayList();
		int[] yaxis = {10,20,30,40,50,60,70,80,90,100};
		
		CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Score Histogram").xAxisTitle("Different range of Integer").yAxisTitle("Number of Integer").build();
		chart.addSeries("Histogram Revisited",yaxis,Stars);
		
		charts.add(chart);
		
	    
	    PieChart piechart = new PieChartBuilder().width(800).height(600).title("Pie Chart Histogram").build();	    
	    for (int i=1;i<=10;i++) {
	    	String help = (((i-1)*10)+1)+" - "+i*10;
	    	piechart.addSeries(help, Stars[i-1]);
	    }
	    charts.add(piechart);
	    
	    new SwingWrapper (charts).displayChartMatrix();
	}
	
	public static void main(String[]args) throws IOException {
		String Path = "C:\\Users\\Loic PC\\Documents\\java_courses\\1DV506\\src\\lg222sv_assign4\\JavaHistogram.txt";
		HistogramRevisited H1 = new HistogramRevisited(Path);
		H1.readFile(Path);
		H1.printHistogram();
		//System.out.println(H1.printHistogram());
	}

}
