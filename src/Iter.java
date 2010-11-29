import java.util.NoSuchElementException;

public interface Iter<Item>{
	
	public Item next() throws NoSuchElementException;
	
	public boolean hasNext();
	
}