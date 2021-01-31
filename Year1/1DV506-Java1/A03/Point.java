package lg222sv_assign3;

public class Point {
	//Fields
	private int X;
	private int Y;
	private double Dist;
	
	//Constructors 
	public Point() {
		X=0;
		Y=0;
	}
	public Point(int Xvalue, int Yvalue) {
		X=Xvalue;
		Y=Yvalue;
	}
	
	//Different Methods
	public String toString() {
		String Coor = "("+X+","+Y+")";
		return Coor;
	}
	
	public double distanceTo(Point n) {
		Dist = Math.sqrt(Math.pow((n.X)-X, 2)+Math.pow((n.Y)-Y, 2));
		return Dist;
	}
	
	public boolean isEqualTo(Point n) {
		if (X == n.X && Y ==n.Y) {
			return true;
		}
		else
			return false;
	}
	
	public void move(int Xvalue, int Yvalue) {
		X +=Xvalue;
		Y +=Yvalue;
	}
	
	public void moveToXY(int a,int b) {
		X = a;
		Y=b;
	}
}