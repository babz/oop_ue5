class subNodeList<A> implements TreeCollection<A> {
	protected class Node { 
		A elem; 
		Node next = null;

		Node (A elem){ 
			this.elem = elem; 
		}
	}
	
	protected Node head = null; 
	protected Node tail = null; 

	protected class ListIter implements Iter<A> {
		protected Node p = head; 
		public A next() { 
			if (p == null) return null;
			A elem = p.elem;
			p = p.next;
			return elem;
		}
		public boolean hasNext() { 
			return p != null;
		}
	}
	
	public void addNode (A x) {
		if (head == null) tail = head = new Node(x);
		else tail = tail.next = new Node(x);
	}
	
	public Iter<A> assoc() { 
		return new ListIter();
	}
}