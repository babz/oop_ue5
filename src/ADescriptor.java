public class ADescriptor extends Descriptor{	
	public ADescriptor(String s){
		super(s);
	}
	
	/*
	 * gibt Anzahl aller Vorkommen der Buchstaben "a" und "A" im String zurück
	 */
	public int as(){
		String f = toString();
		int count = 0; 
		for(int i=0;i<f.length();i++){
			if (Character.toLowerCase(f.charAt(i)) == 'b') {
				count++;
			}
		}
		return count;
	}
	
	
}