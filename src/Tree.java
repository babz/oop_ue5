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
public class Tree<Label extends Comparable> {
	protected class Children extends LinkedList<Child>{}
	protected class Child implements Comparable<Child> {
		public Child(Label label) {
			this.label = label;
			this.assoc = new Children();
		}
		private Label label;
		private Children assoc;
		@Override
		public int compareTo(Child other) {
			return label.compareTo(other.label);
		}
	}
	
	private Child root = new Child(null);
	
	class DeepIterator implements Iter<Label> {
		private class Stack extends LinkedList<Children.Iterator>{}
		private Stack stack = new Stack();
		private Stack.Iterator sp = stack.iterate();
		public DeepIterator() {
			sp.insert(root.assoc.iterate());
		}

		@Override
		public boolean hasNext() {
			Children.Iterator here = sp.get();
			return here.hasNext() || sp.hasNext() || here.get().assoc.iterate().hasNext();
		}
		@Override
		public Label next() throws NoSuchElementException {
			Children.Iterator here = sp.get();
			Children.Iterator down = here.get().assoc.iterate();
			if (down.hasNext()) {
				sp.insert(down);
				return down.next().label;
			} else if (here.hasNext()) {
				return here.next().label;
			} else if (sp.delete()) {
				return null;
			} else {
				throw new NoSuchElementException();
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
			if(c == null) return null;
			return new WideIterator(c.assoc);
		}

		@Override
		public boolean insert(Label a) {
			Children.Iterator pos = here.iterate();
			while (pos.hasNext() && pos.next().label.compareTo(a) < 0) {/*loopy loop*/}
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
		return new WideIterator(root.assoc);
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