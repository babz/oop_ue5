import java.util.NoSuchElementException;

/*
 * ähnelt Tree, jedoch ist jeder Knoten mit einem Wert (als Instanz eines durch einen Typparameter 
 * bestimmten Typs) versehen. Die Methoden von ValueTree entsprechen denen von Tree, wobei aber assoc 
 * (sowohl in ValueTree als auch in den Iteratoren) je eine Instanz von ValueIter zurückgibt. Wird 
 * kein Wert für einen Knoten über set im Iterator gesetzt, so ist der Wert null. Wenn möglich soll 
 * ValueTree mit geeigneten Typparameterersetzungen Untertyp von Tree sein.
 */

public class ValueTree<Label extends Comparable<? super Label>, Value> extends Tree<Label> {
	protected class Children extends LinkedList<Child> {}
	protected class Child {
		private Label label;
		private Value value;
		private Children subChildren;
		public Child(Label label, Value value) {
			this.label = label;
			this.value = value;
			subChildren = new Children();
		}
	}
	private Child root = new Child(null, null);

	public RecursiveValueIter<Label, Value> assoc() {
		return new WideIterator(root.subChildren);
	}

	private class WideIterator implements RecursiveValueIter<Label, Value> {
		Children here;
		Children.Iterator pos;
		private WideIterator (Children c) {
			here = c;
			pos = c.iterate();
		}
		@Override
		public Label next() throws NoSuchElementException {
			return pos.next().label;
		}

		@Override
		public boolean hasNext() {
			return pos.hasNext();
		}

		@Override
		public RecursiveValueIter<Label, Value> assoc() {
			Child c = pos.get();
			if(c == null) {
				return null;
			}
			return new WideIterator(c.subChildren);
		}

		@Override
		public boolean insert(Label a) {
			Children.Iterator pos = here.iterate();
			Children.Iterator ahead = pos.copy();
			while (ahead.hasNext()) {
				Child next = ahead.next();
				if (a.compareTo(next.label) > 0) {
					pos.next();
				} else {
					break;
				}
			}
			return pos.insert(new Child(a, null));
		}

		@Override
		public boolean delete() {
			return pos.delete();
		}
		@Override
		public void set(Value a) {
			Child c = pos.get();
			if (c != null) {
				c.value = a;
			}
		}
		@Override
		public Value get() {
			Child c = pos.get();
			if (c != null) {
				return c.value;
			}
			return null;
		}
	}

	class DeepIterator extends Tree<Label>.DeepIterator {
		private class Stack extends LinkedList<Children.Iterator>{}
		private Stack stack = new Stack();
		private Stack.Iterator sp = stack.iterate();
		public DeepIterator() {
			sp.insert(root.subChildren.iterate());
		}

		@Override
		public boolean hasNext() {
			// first entry (top) of stack
			Stack.Iterator tos = sp.copy();
			Children.Iterator here = tos.next();

			// if we're not on base level, we have at least null to return next
			if (tos.hasNext()) return true;

			// also if we have at least one more node horizontally
			if (here.hasNext()) return true;

			// item has no children
			return false;
		}

		@Override
		public Label next() throws NoSuchElementException {
			Stack.Iterator tos = sp.copy();
			Children.Iterator here = tos.next();
			if (here.hasNext()) {
				Child item = here.next();
				Children.Iterator sub = item.subChildren.iterate();
				if (sub.hasNext()) {
					sp.insert(sub);
				}
				return item.label;
			} else {
				if ( ! tos.hasNext()) {
					throw new NoSuchElementException("Tree iterator depleted");
				}
				tos.delete();
				return null;
			}
		}
	}
	public DeepIterator allLabels(){
		return new DeepIterator();
	}
}