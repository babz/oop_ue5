public interface AssocIter <A> extends Iter<A>{
	
	/* assoc liefert ein Objekt zur�ck, das mit dem zuletzt von 
	 * next zur�ckgegebenen Element assoziiert ist. Das Ergebnis
	 *  ist null wenn noch kein Aufruf von next erfolgt ist oder 
	 *  der letzte Aufruf von next ebenso null geliefert hat.
	 */
	public Iter<A> assoc();
	
	/* insert kann ein neues Element (an nicht n�her definierter 
	 * Stelle und ohne Angabe assoziierter Objekte) in das Aggregat 
	 * einf�gen, �ber das iteriert wird. Das Ergebnis eines Aufrufs 
	 * ist true wenn das Element tats�chlich eingef�gt wurde, sonst false. 
	 */
	public void insert(A a);
	
	/*delete entfernt das zuletzt von next zur�ckgegebe Element aus dem Aggregat. 
	 * Das Aggregat bleibt unver�ndert, wenn zuvor kein Element von next 
	 * zur�ckgegeben wurde oder das Element schon entfernt ist. 
	 * Das Ergebnis eines Aufrufs ist true wenn tats�chlich ein Element entfernt 
	 * wurde, sonst false. 
	 */
	public void delete(A a);
}