package lg222sv_assign4.sort_cities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SortCities {
	
	public static void main(String[] args) {
		String path = "C:\\Users\\Loic PC\\Documents\\java_courses\\1DV506\\src\\lg222sv_assign4\\sort_cities\\SomeCities.txt";
		int count =0;
		ArrayList<City> cities = new ArrayList<City>();
		try {
			Scanner sc = new Scanner(new File(path));
			while(sc.hasNext()) {
				int help = sc.nextInt();
				String city = sc.nextLine();
				city = city.replaceAll("[; ]","");
				cities.add(new City(help,city));
				Collections.sort(cities);
				count++;
			}
			System.out.println("Reading cities from file: "+path);
			System.out.println("Number of cities found: "+ count+"\n");
			
			for (int i=0;i<cities.size();i++) {
				System.out.println(cities.get(i));
			}
			
		sc.close();
		}catch (Exception e) {e.printStackTrace();}	
	}
}
