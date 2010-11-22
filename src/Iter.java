import java.util.NoSuchElementException;

public interface Iter<A>{
	
	public A next() throws NoSuchElementException;
	
	public boolean hasNext();
	
}