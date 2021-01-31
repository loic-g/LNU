
package lg222sv_assign3;

public class Arrays {
	//Ex 1 
	private static int sum(int[]n) {
		int length=n.length, sum=0;
		for (int i=0;i<length;i++) {
			sum+=n[i];
		}
		return sum;
	}
	//Ex 2
	private static String toString(int[]n) {
		int length=n.length;
		String NiceLooking="[ ";
		for (int i =0;i<length;i++) {
			NiceLooking+=n[i]+" ";
			
		}
		NiceLooking+="]";
		return NiceLooking;
		
	}
	//Ex 3 
	private static int[] addN(int[]a, int b) {
		int length =a.length;
		int[] AddNum = new int[length];
		for (int i=0;i<length;i++) {
			AddNum[i]=a[i]+b;
		}
		
		return AddNum;
	}
	//Ex 4
	private static int[] reverse(int[]a) {
		int length =a.length;
		int[]Reverse = new int[length];		
		for (int i=0;i<length; i++) {
			Reverse[i]=a[(length-1)-i];
		}
		return Reverse;
	}
	//Ex 5
	private static boolean hasN(int[] a,int b) {
		int length =a.length;
		boolean HASN=true;
		for(int i=0;i<length;i++) {
			if (a[i]==b) {
				HASN=true;
				break;
			}
			else {
				HASN=false;
			}
		}
		
		return HASN;
	}
	//Ex 6
	private static void replaceAll(int[]arr, int old, int nw) {
		for (int i=0;i<arr.length-1;i++) {
			if (arr[i]==old) {
				arr[i]=nw;
				
			}
		}
	}
	//Ex 7
	private static int[] sort(int[] a) {
		
		int[] Sort=a.clone();
		
		for (int i=0;i<Sort.length;i++) {
				int LowIndex=i;
				
				for(int i2=i+1;i2<Sort.length;i2++) {
					if (Sort[i2]<Sort[i]) {
						LowIndex = i2;
					}
				}
				if (i!=LowIndex) {
					int temp = Sort[LowIndex];
					Sort[LowIndex]=Sort[i];
					Sort[i]=temp;
				}
			}
		return Sort;
	}
	
	//Ex 8 (VG)
	private static boolean hasSubsequence(int[] arr, int[] sub) {
		
		
		for (int i =0;i<arr.length-sub.length+1;i++) {
			int subcount=0;
			int count=0;
			for (int i2=i;i2<(sub.length)+i;i2++) {
				if (arr[i2]==sub[subcount]) {
					count++;
				}
				subcount++;		
			}
			if (count==sub.length) {
				return true;
			}
			
		}
		return false;
	}
	//Ex 9 (VG)
	private static boolean isLarger(int[]a1,int[]a2) {
		boolean response =true;
		if ((a1.length<a2.length)||(a1.length==a2.length)) {
			for (int i=0;i<a1.length;i++) {
				if (a1[i]==a2[i]) {
					continue;
				}
				else if(a1[i]>a2[i]) {
					
					response= true;
					return response;
				}
				else
					response= false;
					return response;
			}
			response = false;
			return response;
		}
		else if(a1.length>a2.length) {
			for (int i=0;i<a2.length;i++) {
				if (a1[i]==a2[i]) {
					continue;
				}
				else if(a1[i]>a2[i]) {
					response= true;
					return response;
				}
				else
					response= false;
					return response;
			}
			response= true;
			return response;
		}
		return response;
	}
	public static void main(String[] args) {
		int[] arr = {10,5,7,96,45,-96,425};
		int[] sub= {4,7,10};
		int[] a1 = {1,2,3,4};
		int[] a2 = {1,2,3};
		int n=5;
		int c=4;
		System.out.println("Exercise 1:"+"\nThe sum of the array is equal to: "+sum(arr));
		System.out.println("\nExercise 2:"+"\nNice Looking Array = "+toString(arr));
		System.out.println("\nExercise 3:"+"\nAdded n to every number of the Array: "+toString(addN(arr,n)));
		System.out.println("\nExercise 4:"+"\nReverse the order of the Array: "+toString(reverse(arr)));
		System.out.println("\nExercise 5:"+"\nIs the number 'n' in the Array: "+hasN(arr,n));
		replaceAll(arr,n,c);
		System.out.println("\nExercise 6:"+"\nThis is the changed Array: "+toString(arr));
		System.out.println("\nExercise 7:"+"\nThis is the sorted Array: "+toString(sort(arr)));
		System.out.println("\nExercise 8{VG}:"+"\nthe Array sub is a subset of arr: "+hasSubsequence(arr,sub));
		System.out.println("\nExercise 9{VG}:"+"\nIs the Array a1 larger than a2?: "+isLarger(a1,a2));
		
	}
}
