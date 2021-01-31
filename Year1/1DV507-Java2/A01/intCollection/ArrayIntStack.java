package lg222sv_assign1.intCollection;

public class ArrayIntStack extends AbstractIntCollection implements IntStack{

	@Override
	public void push(int n) {
		/* Add integer at top of stack. */
		if (size == 0) {
			values[0] = n;
			size++;
		} else {
			if (checkIndex(size - 1, values.length - 1) == false) {
				resize();
			}
			values[size] = n;
			size++;
		}
	}

	@Override
	public int pop() throws IndexOutOfBoundsException {
		/* Returns and removes integer at top of stack */
		int help=values[size-1];
		values[size-1]=0;
		
		size--;
		return help;
	}

	@Override
	public int peek() throws IndexOutOfBoundsException {
		/* Returns without removing integer at top of stack */
		
		return values[size-1];
	}

}
