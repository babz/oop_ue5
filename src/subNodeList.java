class subNodeList<A> implements TreeCollection<A> {
	protected class Node { // list nodes
		A elem; // element in this node
		Node next = null; // next node in the list

		Node (A elem){ 
			this.elem = elem; 
		}
	}
	
	protected Node head = null; // first node of list
	protected Node tail = null; // last list node

	protected class ListIter implements Iter<A> {
		protected Node p = head; // iterator position
		public A next() { // get next list element
			if (p == null) return null;
			A elem = p.elem;
			p = p.next;
			return elem;
		}
		public boolean hasNext() { // more elements?
			return p != null;
		}
	}
	
	public void addNode (A x) { // add element to list
		if (head == null) tail = head = new Node(x);
		else tail = tail.next = new Node(x);
	}
	
	public Iter<A> assoc() { // new list iter.
		return new ListIter();
	}
}