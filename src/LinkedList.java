import java.util.NoSuchElementException;


public class LinkedList<T> {
	private Item start = null;
	
	public ValueIter<T, ?> iterate() {
		return new Iterator(null, null);
	}

	private class Item {
		Item next = null;
		public final T data;
		Item(T data){
			assert data != null;
			this.data = data;
		}
	}
	private class Iterator implements ValueIter<T, Object> {
		private Item cur, prev;
		Iterator(Item cur, Item prev) {
			this.cur = cur;
			this.prev = prev;
		}
		@Override
		public Object assoc() {
			return null;
		}

		//pre: a is not null
		@Override
		public boolean insert(T a) {
			Item n = new Item(a);
			n.next = start;
			if (cur == null || prev == null){
				start = n;
			} else {
				prev.next = n;
			}
			cur = n;
			return true;
		}

		@Override
		public boolean delete() {
			if (cur == null){
				return false;
			} else if (prev == null){
				// at start
				if (start == null) {
					return false;
				} else {
					start = start.next;
				}
			} else {
				prev.next = cur.next;
			}
			return true;
		}

		@Override
		public T next() {
			if (cur == null) {
				if (start == null) {
					throw new NoSuchElementException();
				}
				cur = start;
			} else {
				if (cur.next == null) {
					// end of list
					throw new NoSuchElementException();
				}
				cur = cur.next;
				prev = cur;
			}
			return cur.data;
		}

		@Override
		public boolean hasNext() {
			if (cur == null) {
				return start != null;
			}
			return cur.next != null;
		}

		//pre: a is not null
		@Override
		public void set(T a) {
			Item n = new Item(a);
			if (cur == null || prev == null){
				if (start != null) {
					n.next = start.next;
				}
				start = n;
				cur = n;
				return;
			}
			n.next = cur.next;
			prev.next = n;
			cur = n;
		}

		@Override
		public T get() {
			if (cur == null) {
				// iterator at beginning, raise IllegalState?
				return null;
			}
			return cur.data;
		}
		
	}
	
}
