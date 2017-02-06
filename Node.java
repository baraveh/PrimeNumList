public class Node<T> {
		private T _value;
		private Node<T> _next;

		public Node(T v, Node<T> n) {
			_value = v;
			_next = n;
		}

		public T getValue() {
			return _value;
		}

		public Node<T> getNext() {
			return _next;
		}

		public void setValue(T v) {
			_value = v;
		}

		public void setNext(Node<T> n) {
			_next = n;
		}


}
