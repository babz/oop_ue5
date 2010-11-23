import java.util.NoSuchElementException;


public class LinkedList<T> {
	public LinkedList(){
	}
	private Item start = null;
	
	public Iterator iterate() {
		return new Iterator(null, null);
	}

	public class Item {
		Item next;
		public final T data;
		protected Item(T data, Item next){
			assert data != null;
			this.data = data;
			this.next = next;
		}
	}
	
	public class Iterator extends AIterator<Item, T, T, T> {
		private Iterator(Item cur, Item prev) {
			super(cur, prev);
		}

		@Override
		protected T getValue(Item here) {
			return here.data;
		}

		@Override
		protected Item getStart() {
			return start;
		}

		@Override
		protected void setStart(Item start) {
			LinkedList.this.start = start;
		}

		@Override
		protected Item getNext(Item here) {
			return here.next;
		}

		@Override
		protected void setNext(Item here, Item next) {
			here.next = next;
		}

		@Override
		protected T getAssoc(Item here) {
			return getValue(here);
		}

		@Override
		protected T getNode(Item here) {
			return here.data;
		}

		@Override
		protected Item createValue(T v, Item next) {
			return new Item(v, next);
		}

		@Override
		protected Item createNode(T n, Item next) {
			return new Item(n, next);
		}
		
	}
}
