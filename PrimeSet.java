package Prime;

/**
 * this class represents lists of prime numbers. Requires Node class.
 * 
 * @author Bar Raveh
 *
 */
public class PrimeSet {

	// Instance variables
	private Node<Integer> _head;
	private int _numOfElements;

	// Constructor
	public PrimeSet() {
		_head = null;
		_numOfElements = 0;
	}

	/**
	 * Checks whether a PrimeSet is empty. Time = O(1) Memory= O(1)
	 * 
	 * @return true if PrimeSet is empty, else false.
	 */
	public boolean isEmpty() {
		return _head == null;
	}

	/**
	 * Checks whether a number is a part of the PrimeSet. Time = O(n) Memory =
	 * O(1)
	 * 
	 * @param num
	 *            Number to check.
	 * @return True if it is a member of the PrimeSet, else false.
	 */
	public boolean isMember(int num) {
		if (num % 2 == 0)
			return false;
		else {
			Node<Integer> temp = _head;
			while (temp != null) {
				// Goes over the PrimeSet and checks whether num is equal to any
				// value. Time = O(n).
				if (temp.getValue() == num)
					return true;
				temp = temp.getNext();
				if (temp.getValue() > num)
					// Since the PrimeSet is sorted if the value is bigger than num
					// then num won't be found in the rest of the PrimeSet.
					return false;
			}
			return false;
		}

	}

	/**
	 * Checks whether a PrimeSet is equal to another. Time = O(n) Memory = O(1)
	 * 
	 * @param other
	 *            The PrimeSet to check.
	 * @return True if other equals to PrimeSet, else false.
	 */
	public boolean equals(PrimeSet other) {
		if (this.subSet(other) && this._numOfElements == other.numOfElements())
			return true;
		return false;
	}

	/**
	 * Gets the number of elements in a PrimeSet. Time = O(1) Memory = O(1)
	 * 
	 * @return The number of elements
	 */
	public int numOfElements() {
		return _numOfElements;
	}

	/**
	 * Checks whether a PrimeSet is a subset of another. Time = O(n) Memory
	 * = O(1)
	 * 
	 * @param other
	 *            The subset to check.
	 * @return True if other is subset of PrimeSet, else false.
	 */
	public boolean subSet(PrimeSet other) {
		Node<Integer> i = _head;
		Node<Integer> j = other.getHead();
		while (j != null) {
			if (i == null)
				// Has reached the end of object PrimeSet without going over all
				// of other, then other is not a subset.
				return false;
			if (i.getValue() == j.getValue()) {
				// Values are equal, so far other is subset. Moves on to
				// next elements.
				i = i.getNext();
				j = j.getNext();
			} else if (i.getValue() < j.getValue())
				// i value is smaller than j but that does not mean other is not
				// subSet (example PrimeSet={1,2,3}, other = {1,3}). Moves
				// on to next i till i is bigger or equal.
				i = i.getNext();
			else
				// i is bigger than j meaning that there are j values that are
				// smaller than this i and bigger than previous i, so not all j
				// values are in PrimeSet. Other is not a subSet.
				return false;
		}
		return true;
	}

	/**
	 * Adds a number (only if it is prime) to the list. Time = O(n) Memory = O(1).
	 * 
	 * @param x
	 *            The integer to add.
	 */
	public void addToPrimeSet(int x) {
		if (isPrime(x)) {
			Node<Integer> temp = _head;
			boolean added = false;
			if (this.isEmpty() || x < _head.getValue()) {
				// PrimeSet is empty or x is smallest than the entire PrimeSet
				// so we add the node as head.
				_head = new Node<Integer>(x, temp);
				added = true;
				_numOfElements++;
			} 
			else if (x == _head.getValue())
				// In case head equals x
				added = true;
			else {
				Node<Integer> curr = _head.getNext();
				while (!added) {
					if (curr == null || curr.getValue() > x) {
						// Temp has the last value in PrimeSet that is smaller than x
						// (or it is the last element in PrimeSet and there are novalues that are bigger than x).
						Node<Integer> add = new Node<Integer>(x, curr);
						temp.setNext(add);
						added = true;
						_numOfElements++;
						// Sets temp's next as x and x's next as curr so that x is between temp and curr.
					} 
					else if (curr.getValue() == x) {
						// X is already in PrimeSet.
						added = true;
					} 
					else {
						// Curr value is smaller than x, moves on to next node.
						temp = curr;
						curr = curr.getNext();
					}
				}
			}

		}
	}

	/**
	 * Removes an integer from the PrimeSet. Time = O(n) Memory = O(1)
	 * 
	 * @param x
	 *            The integer to remove.
	 */
	public void removeFromPrimeSet(int x) {
		if (isPrime(x) && !this.isEmpty()) {
			// Only begins if x is prime and the Set isn't empty,
			// otherwise x
			// wouldn't be in the PrimeSet to begin with
			if (_head.getValue() == x) {
				// If x is head we change head to be the next
				_head = _head.getNext();
				_numOfElements--;
			} 
			else {
				Node<Integer> behind = _head;
				while (behind.getNext() != null) {
					if (behind.getNext().getValue() <= x) {
						// Since the Set is sorted there is no point checking after the value is bigger than x.
						if (behind.getNext().getValue() == x) {
							// Checks if next pointer is to x is so it points it
							// to next next (so nothing points at x) and ends = O(n) time
							behind.setNext(behind.getNext().getNext());
							_numOfElements--;
							return;
						} 
						else
							behind = behind.getNext();
					} 
					else
						return;

				}
			}
		}
	}

	/**
	 * Strings all elements of the PrimeSet. Time = O(n) Memory = O(1)
	 * 
	 * @return String of all elements for example: {a1,a2,a3}
	 */
	public String toString() {
		String toString = ("{");
		// Creates the start of the string.
		Node<Integer> temp = _head;
		if (temp != null) {
			toString = (toString + temp.getValue());
			temp = temp.getNext();
		}
		while (temp != null) {
			// Adds all elements of the PrimeSet to the string = O(n) time
			toString = (toString + "," + temp.getValue());
			temp = temp.getNext();
		}
		return (toString + "}");
		// Closes the string and returns it.
	}

	/**
	 * Creates the intersection PrimeSet of two PrimeSets. Time = O(n) Memory = O(n)
	 * 
	 * @param other
	 *            The PrimeSet to intersect with
	 * @return A PrimeSet of the intersection of the PrimeSet and other
	 */
	public PrimeSet intersection(PrimeSet other) {
		Node<Integer> i = _head;
		Node<Integer> j = other.getHead();
		PrimeSet intersection = new PrimeSet();
		// Creates the intersection PrimeSet = O(n) space
		while (i != null && j != null) {
			// Adds to intersection all elements of object PrimeSet that are also members of other = O(n) time
			// Since both PrimeSets are sorted we go over both of them and search for equal values.
			if (i.getValue() < j.getValue())
				// i is smaller so we go to next i and compare with j again
				i = i.getNext();
			else if (i.getValue() > j.getValue())
				// j is smaller so we go to next j and compare with i again
				j = j.getNext();
			else {
				// j and i are equal. we add the value and go to next i and next j.
				intersection.addToPrimeSet(i.getValue());
				i = i.getNext();
				j = j.getNext();
			}

		}
		return intersection;
	}

	/**
	 * Creates the union PrimeSet of two PrimeSets. Time = O(n) Memory = O(n)
	 * 
	 * @param other
	 *            The PrimeSet to unite with.
	 * @return A PrimeSet of the union of the PrimeSet and other.
	 */
	public PrimeSet union(PrimeSet other) {
		PrimeSet union = new PrimeSet();
		// Creates the PrimeSet to be returned = O(n) memory.
		Node<Integer> temp = _head;
		while (temp != null) {
			// Adds all elements of object PrimeSet = O(n) time.
			union.addToPrimeSet(temp.getValue());
			temp = temp.getNext();
		}
		Node<Integer> temp1 = other.getHead();
		while (temp1 != null) {
			// Adds all elements of other that aren't already in the PrimeSet =
			// O(n)
			// time.
			union.addToPrimeSet(temp1.getValue());
			temp1 = temp1.getNext();
		}
		return union;
		// Union Set now contains all elements of PrimeSet + all elements
		// of other that aren't in PrimeSet.
	}

	/**
	 * Creates the difference PrimeSet of two PrimeSets. Time = O(n) Memory =
	 * O(n)
	 * 
	 * @param other
	 *            The PrimeSet to check the difference with.
	 * @return A PrimeSet of all elements that are only in PrimeSet.
	 */
	public PrimeSet difference(PrimeSet other) {
		// Creates an identical PrimeSet to object PrimeSet and removes all
		// elements that intersect with other.
		PrimeSet toRemove = this.intersection(other);
		PrimeSet difference = new PrimeSet();
		Node<Integer> temp = _head;
		while (temp != null) {
			// Adds all elements in PrimeSet.
			difference.addToPrimeSet(temp.getValue());
			temp = temp.getNext();
		}
		temp = toRemove.getHead();
		while (temp != null) {
			// Removes all elements that intersect with other.
			difference.removeFromPrimeSet(temp.getValue());
			temp = temp.getNext();
		}

		return difference;
	}

	private Node<Integer> getHead(){
		return _head;
	}

	private static boolean isPrime(int x){
		
		if(x==1||x==0)
			return false;
		for(int i = (int) Math.sqrt((double) x); i > 1; i= i-2){
			if (x%i == 0)
				return false;
		}
		return true;
	}
}
