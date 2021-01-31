package lg222sv_assign2;

import java.util.*;

public class SalaryRevision {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);
		int count=0, average=0;
		
		System.out.print("Provide salaries (and terminate input with 'X'): ");
		while (sc.hasNextInt()) {
		    int salary = sc.nextInt();
		    list.add(salary);
		    average+=salary;
		    count++;	
		}
		sc.close();
		Collections.sort(list);
		int median, help=count/2;
		
		if ((count%2)==0) {
			median= (((list.get(help-1))+(list.get(help)))/2);
		}
		else {
			median=(list.get(help));
		}
		int gap = list.get((list.size())-1)-list.get(0);
		average=average/count;
		System.out.println("Median: "+ median);
		System.out.println("Average: "+ average);
		System.out.println("Gap: "+gap);
	}

}
