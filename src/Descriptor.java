public abstract class Descriptor implements Comparable<Descriptor> {
	
	private String s;
	
	public Descriptor(String s){
		this.s = s;
	}
	
	public int compareTo(Descriptor d){
		return s.compareTo(d.s);
	}
	
	public String toString(){
		return s;
	}
}