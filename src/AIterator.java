import java.util.NoSuchElementException;


public abstract class AIterator<Location, Node, Assoc, Value> implements ValueIter<Node, Assoc, Value> {
	abstract protected Node getNode(Location here);
	abstract protected Assoc getAssoc(Location here);
	abstract protected Value getValue(Location here);
	abstract protected Location getStart();
	abstract protected void setStart(Location start);
	abstract protected Location getNext(Location here);
	abstract protected void setNext(Location here, Location next);
	abstract protected Location createValue(Value v, Location next);
	abstract protected Location createNode(Node n, Location next);
	
	private Location cur, prev;
	protected AIterator(Location cur, Location prev) {
		this.cur = cur;
		this.prev = prev;
	}
	@Override
	public Assoc assoc() {
		if (cur == null) {
			if (getStart() != null){
				return getAssoc(getStart());
			}
			return null;
		}
		return getAssoc(cur);
	}
	//pre: a is not null
	@Override
	public boolean insert(Node v) {
		Location l;
		if (cur == null) {
			// start
			l = createNode(v, getStart());
			setStart(l);
		} else {
			 l = createNode(v, getNext(cur));
			 setNext(cur, l);
		}
//		cur = l;
		return true;
	}
	
	@Override
	public boolean delete() {
		if (cur == null){
			return false;
		} else if (prev == null){
			// at start
			Location start = getStart();
			if (start == null) {
				return false;
			} else {
				setStart(getNext(start));
			}
		} else {
			setNext(prev, getNext(cur));
		}
		return true;
	}

	@Override
	public Node next() {
		if (cur == null) {
			if (getStart() == null) {
				throw new NoSuchElementException();
			}
			cur = getStart();
		} else {
			if (getNext(cur) == null) {
				// end of list
				throw new NoSuchElementException();
			}
			prev = cur; 
			cur = getNext(cur);
		}
		return getNode(cur);
	}

	@Override
	public boolean hasNext() {
		if (cur == null) {
			return getStart() != null;
		}
		return getNext(cur) != null;
	}

	//pre: a is not null
	@Override
	public void set(Value v) {
		Location n = createValue(v, null);
		if (cur == null || prev == null){
			// at start
			if (getStart() != null) {
				setNext(n, getNext(getStart()));
			}
			setStart(n);
			cur = n;
			return;
		}
		setNext(n, getNext(cur));
		setNext(prev, n);
		cur = n;
	}

	@Override
	public Value get() {
		if (cur == null) {
			// iterator at beginning, raise IllegalState?
			return null;
		}
		return getValue(cur);
	}
}
