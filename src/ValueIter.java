/*
 * gibt direkten Zugriff auf einen Wert des Aggregats
 * (durch einen weiteren Typparameter bestimmten Typs)
 */
public interface ValueIter <A> extends AssocIter<A>{
	/*
	 * setzt den Wert im Aggregat auf den Parameter von set (
	 * unabhängig vom Zustand des Iterators)
	 */
	//pre: a is not null
	public void set(A a);
	
	/*
	 * retourniert den Wert des Aggregats (unabhängig vom Zustand des Iterators)
	 */
	public A get();
}