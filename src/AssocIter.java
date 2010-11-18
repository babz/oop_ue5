public interface AssocIter <A> extends Iter<A>{
	
	/* assoc liefert ein Objekt zurück, das mit dem zuletzt von 
	 * next zurückgegebenen Element assoziiert ist. Das Ergebnis
	 *  ist null wenn noch kein Aufruf von next erfolgt ist oder 
	 *  der letzte Aufruf von next ebenso null geliefert hat.
	 */
	public A assoc();
	

	/* insert kann ein neues Element (an nicht näher definierter 
	 * Stelle und ohne Angabe assoziierter Objekte) in das Aggregat 
	 * einfügen, über das iteriert wird. Das Ergebnis eines Aufrufs 
	 * ist true wenn das Element tatsächlich eingefügt wurde, sonst false. 
	 */
	public A insert();
	
	
	/*delete entfernt das zuletzt von next zurückgegebe Element aus dem Aggregat. 
	 * Das Aggregat bleibt unverändert, wenn zuvor kein Element von next 
	 * zurückgegeben wurde oder das Element schon entfernt ist. 
	 * Das Ergebnis eines Aufrufs ist true wenn tatsächlich ein Element entfernt 
	 * wurde, sonst false. 
	 */
	public void delete(A a);
}