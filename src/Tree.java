import java.util.NoSuchElementException;

/*
 * Eine Instanz der Klasse Tree stellt einen baumförmigen gerichteten Graphen dar, 
 * in dem von jedem Knoten beliebig viele, mit einem Label versehene Kanten ausgehen. 
 * Die Label auf Kanten sind sortierbar; das heißt, sie unterstützen eine Methode compareTo 
 * (wie auf Seite 103 des Skriptums). Knoten des Graphen werden nach außen nicht direkt, 
 * sondern nur durch Iteratoren über die von den Knoten ausgehenden Kanten dargestellt. 
 * Je zwei Iteratoren haben unterschiedliche Identitäten, auch wenn sie über die vom selben 
 * Knoten ausgehenden Kanten iterieren.
 */
public class Tree<Label extends Comparable<Label>> {
	private class Children extends LinkedList<Child>{}
	private class Child {
		public Child(Label label) {
			this.label = label;
			this.subChildren = new Children();
		}
		private Label label;
		private Children subChildren;
	}
	
	private Child root = new Child(null);
	
	class DeepIterator implements Iter<Label> {
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
	
	class WideIterator implements AssocIter<Label, WideIterator> {
		Children here;
		Children.Iterator pos;
		public WideIterator(Children c) {
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
		public WideIterator assoc() {
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
			return pos.insert(new Child(a));
		}

		@Override
		public boolean delete() {
			return pos.delete();
		}
	}

	/*
	 * retourniert einen Iterator des Typs AssocIter (mit geeigneten Typparameterersetzungen), 
	 * der über die Label aller von der Wurzel des Baums ausgehenden Kanten in aufsteigender 
	 * Reihenfolge (entsprechend compareTo) iteriert. Die Methode insert des Iterators fügt nur 
	 * dann eine neue Kante mit einem neuen Label hinzu, wenn es vom selben Knoten aus noch keine 
	 * Kante mit einem gleichen Label (entsprechend compareTo) gibt. Die über den Iterator zugänglichen 
	 * assoziierten Objekte entsprechen Iteratoren des Typs AssocIter, welche (so wie hier für den 
	 * Wurzelknoten beschrieben) über die Label der Kanten iterieren, die von dem Knoten ausgehen, 
	 * der über die Kante erreichbar ist, dessen Label zuletzt von next zurückgegeben wurde. 
	 * Jeder Aufruf von assoc (sowohl in Instanzen von Tree als auch in Iteratoren) erzeugt einen 
	 * neuen Iterator.
	 */
	public WideIterator assoc(){
		return new WideIterator(root.subChildren);
	}
	
	/*
	 * erzeugt einen neuen Iterator des Typs Iter, der eine Tiefensuche über dem Baum macht und 
	 * die Label der dabei besuchten Kanten sowie für jeden besuchten Blatt-Knoten (also jeden Knoten 
	 * ohne ausgehende Kanten) null zurückgibt. Tiefensuche bedeutet, dass für jede ausgehende Kante 
	 * der gesamte darunter hängende Teilbaum abgearbeitet wird, bevor die nächsten Kante an die Reihe 
	 * kommt. Die von einem Knoten ausgehenden Kanten werden dabei in der Reihenfolge bearteitet, 
	 * wie sie von durch assoc erzeugten Iteratoren zurückgegeben werden. Die Methode allLabels ist 
	 * beim Testen hilfreich.
	 */
	public DeepIterator allLabels(){
		return new DeepIterator();
	}
}