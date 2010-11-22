public abstract class Descriptor{
	
	private String s;
	
	public Descriptor(String s){
	}
	
	public boolean compareTo(Descriptor d){
		return false;
	}
	
	public String toString(){
		return s;
	}
}