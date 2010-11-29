import java.util.NoSuchElementException;


public class LinkedList<Item> {
	public LinkedList(){
	}
	private Location start = null;
	
	public Iterator iterate() {
		return new Iterator(null, null);
	}

	public class Location {
		Location next;
		public final Item data;
		protected Location(Item data, Location next){
			assert data != null;
			this.data = data;
			this.next = next;
		}
	}
	
	public class Iterator implements ValueIter<Item, Item, Item> {
		private Location cur, prev;
		private Iterator(Location cur, Location prev) {
			this.cur = cur;
			this.prev = prev;
		}
		
		public Iterator copy() {
			return new Iterator(cur, prev);
		}
		
		@Override
		public Item assoc() {
			if (cur == null) {
				return null;
			}
			return cur.data;
		}
		//pre: a is not null
		@Override
		public boolean insert(Item v) {
			if (cur == null) {
				// start
				start = new Location(v, start);
			} else {
				 cur.next = new Location(v, cur.next);
			}
			return true;
		}
		
		@Override
		public boolean delete() {
			if (cur == null) {
				return false;
			} else if (prev == null) {
				// at start
				if (start == null) {
					return false;
				} else {
					start = start.next;
					cur = start;
				}
			} else {
				cur = cur.next;
				prev.next = cur;
			}
			return true;
		}

		@Override
		public Item next() {
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
				prev = cur; 
				cur = cur.next;
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
		public void set(Item v) {
			Location n = new Location(v, null);
			if (cur == null || prev == null){
				// at start
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
		public Item get() {
			if (cur == null) {
				// iterator at beginning, raise IllegalState?
				return null;
			}
			return cur.data;
		}
	}
}
