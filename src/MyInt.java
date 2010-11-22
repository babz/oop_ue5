public class MyInt implements Comparable<MyInt>{
	
	private Integer i;
	public MyInt(int i){
		this.i = i;
	}
	
	//vergleicht die Zahlen
	public int compareTo(MyInt other){
		return i.compareTo(other.i);
	}
	
	public String toString(){
		return i.toString();
	}
}