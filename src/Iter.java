import java.util.NoSuchElementException;

public interface Iter<Node>{
	
	public Node next() throws NoSuchElementException;
	
	public boolean hasNext();
	
}