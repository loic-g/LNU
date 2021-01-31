package lg222sv_assign1.intCollection;

public class ArrayIntList extends AbstractIntCollection implements IntList {

	public ArrayIntList() {

	}

	@Override
	public void add(int n) {
		/* Add integer n to the end of the list. */
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
	public void addAt(int n, int index) throws IndexOutOfBoundsException {
		/*
		 * Inserts integer n at position index. Shifts the element currently at that
		 * position (if any) and any subsequent elements to the right.
		 */
		if (index >size-1) {
			throw new IndexOutOfBoundsException("Index out of Bound");
		} else {
			if (checkIndex(size, values.length - 1)==false) {
				resize();
			}
			
			
			for (int i = size; i > index - 1; i--) {
				values[i + 1] = values[i];
			}
			values[index] = n;
			size++;
		}
	}

	@Override
	public void remove(int index) throws IndexOutOfBoundsException {
		/* Remove integer at position index. */
		if (index > size - 1) {
			throw new IndexOutOfBoundsException("This is index is out of bound for the array");
		} else {
			for (int i = index; i < size - 1; i++) {
				values[i] = values[i + 1];
			}
			size--;
		}
	}

	@Override
	public int get(int index) throws IndexOutOfBoundsException {
		/* Get integer at position index. */
		if (index > size - 1) {
			throw new IndexOutOfBoundsException("This index is out of bound for the array");
		} else
			return values[index];
	}

	@Override
	public int indexOf(int n) {
		/* Find position of integer n, otherwise return -1 */
		for (int i = 0; i < size - 1; i++) {
			if (n == values[i]) {
				return i;
			}
		}
		return -1;
	}
}
