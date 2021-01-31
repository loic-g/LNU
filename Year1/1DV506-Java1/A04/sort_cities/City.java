package lg222sv_assign4.sort_cities;

public class City implements Comparable<City>{
	
	private int ZipCode;
	private String City;
	
	
	public City (int zip, String cit) {
		if (zip<0) {
			System.out.println("Please give a positive ZipCode");
		}else {
			ZipCode = zip;
			City = cit;
		}
	}
	 
	@Override
	public String toString() {
		return this.getZipCode()+" "+this.getCity();
	}
	
	@Override
	public int compareTo(City o) {
		int compare = ((City)o).getZipCode();
		return this.ZipCode-compare;
	}
	
	public int getZipCode() {return ZipCode;}
	public String getCity() {return City;}
}
