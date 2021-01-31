package lg222sv_assign4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Histogram {
	private int CountInteger;
	private int CountOther;
	private String Path;
	private String Content;
	private String[] Stars = {"","","","","","","","","",""};
	
	public Histogram(String path) {
		CountInteger=0;
		CountOther=0;
		Path=path;
		Content="";
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
							Stars[i-1]+="*";//Put it into string array
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
	public String printHistogram() {
		//Put all the text into StringBuilder to make it easier to print
		StringBuilder Histo = new StringBuilder();
		Histo.append("Reading integers from file: "+Path);
		Histo.append("\nNumbers of integers in the interval [1,100]: "+CountInteger);
		Histo.append("\nOthers: "+CountOther+"\nHistogram");
		Histo.append("\n\t1  - 10  | "+Stars[0]);
		for (int i=2;i<=9;i++) {
			String hp = "\n\t"+(((i-1)*10)+1)+" - "+i*10+"  | "+Stars[i-1];
			Histo.append(hp);}
		Histo.append("\n\t91 - 100 | "+Stars[9]);
		String Histog = Histo.toString();
		return Histog;
	}
	public static void main(String[]args) throws IOException {
		String Path = "C:\\Users\\Loic PC\\Documents\\java_courses\\1DV506\\src\\lg222sv_assign4\\JavaHistogram.txt";
		Histogram H1 = new Histogram(Path);
		H1.readFile(Path);
		System.out.println(H1.printHistogram());
	}
}

	
	

 