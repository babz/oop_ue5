
/*
 * gibt direkten Zugriff auf einen Wert des Aggregats
 * (durch einen weiteren Typparameter bestimmten Typs)
 */
public interface ValueIter extends AssocIter{
	
	/*
	 * setzt den Wert im Aggregat auf den Parameter von set (unabhängig vom Zustand des Iterators)
	 */
	public void set(){
		
	}
	
	/*
	 * retourniert den Wert des Aggregats (unabhängig vom Zustand des Iterators)
	 */
	public Typ get(){
		return null;
	}
}