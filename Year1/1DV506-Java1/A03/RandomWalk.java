package lg222sv_assign3;

import java.util.Random;

public class RandomWalk {
	
	private int MaxNbStep;
	private int NbStep;
	private int Edge;
	private int PosX;
	private int PosY;
	
	public RandomWalk(int max, int edge) {
		if (max>0 && edge>0) {
		MaxNbStep=max;
		Edge=edge;
		PosX=0;
		PosY=0;
		NbStep=0;
		}
		else {
			System.out.println("Please provide positive integers!");
			System.exit(-1);
		}
	}
	
	public String toString() {
		String Info = "The person has taken "+NbStep+" steps so far and is at the position: ("+PosX+","+PosY+")";
		return Info;
	}
	
	public void takeStep() {
		Random rd = new Random();
		int help = rd.nextInt(4);
		
		//down
		if (help==0) {
			PosY--;
			NbStep++;
		}
		//Up
		else if (help==1) {
			PosY++;
			NbStep++;
		}
		//Right
		else if (help==2) {
			PosX++;
			NbStep++;
		}
		//left
		else if (help==3) {
			PosX--;
			NbStep++;
		}
	}
	
	public boolean moreSteps() {
		if (NbStep<MaxNbStep) {
			return true;
		}
		else 
			return false;
	}
	public boolean inBounds() {
		if (PosX<=Edge && PosX>=-Edge) {
			if (PosY<=Edge && PosY>=-Edge) {
				return true;
			}
			else
				return false;
		}
		else 
			return false;
	}
	
	public void walk() {
		NbStep=0;
		for (int i=0;i<MaxNbStep;i++) {
			if (PosX<=Edge && PosX>=-Edge) {
				if (PosY<=Edge && PosY>=-Edge) {
					Random rd = new Random();
					int help = rd.nextInt(4);
					//down
					if (help==0) {
						PosY--;
					}
					//Up
					else if (help==1) {
						PosY++;
					}
					//Right
					else if (help==2) {
						PosX++;
					}
					//left
					else if (help==3) {
						PosX--;
					}
				}
				else {break;}
			}
			else {break;}
			NbStep++;
		}
	}
	public String getPosition() {
		return "This is the position (X,Y): ("+PosX+","+PosY+")";
	}
	
	
}
