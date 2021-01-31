package lg222sv_assign4.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;



public class StackProg implements Stack {
	private int StackSize;
	private ArrayList<Object> List;
	
	public StackProg() {
		List = new ArrayList<Object>();
		StackSize=0;
	}

	@Override
	public int size() {return StackSize;}

	@Override
	public boolean isEmpty() {
		if (StackSize==0) {
			return true;
		}else
			return false;
	}

	@Override
	public void push(Object element) {
		
			List.add(element);
			StackSize++;
		
	}

	@Override
	public Object pop() {
		try {
			Object o = List.get(StackSize-1);
			List.remove(StackSize-1);
			StackSize--;
			return o;
		}catch (Exception nosize) {nosize.printStackTrace();}
		
		return null;
		
		
	}

	@Override
	public Object peek(){
		try {
			Object a = List.get(StackSize-1);
			return a;
		}catch(Exception e) {e.printStackTrace();};
		return null;
	}

	@Override
	public Iterator<Object> iterator() {
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Object> help  = (ArrayList<Object>) List.clone();
		
		Collections.reverse(help);
		
		Iterator<Object> it = help.iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		
		return it;
	}

}
