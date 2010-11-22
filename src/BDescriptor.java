public class BDescriptor extends Descriptor{
	
	public BDescriptor(String s){
		super(s);
	}
	 
	public boolean bs(){
		String f = toString();
		return f.indexOf('b') != -1 || f.indexOf('B') != -1;
	}
}