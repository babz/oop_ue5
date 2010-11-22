public class Test{
	static void err(String s) {
		System.out.print("ERROR: ");
		System.out.println(s);
	}
	
	static void info(String s) {
		System.out.print("INFO: ");
		System.out.println(s);
	}
	
	
	static int runCount = 0, successCount = 0;
	static boolean checkIter(Iter<? extends Object> it, Object... expectedValues) {
		runCount++;
		int i = 0;
		while (it.hasNext() && i < expectedValues.length) {
			Object ex = expectedValues[i];
			Object ac = it.next();
			if ( ! ex.equals(ac)){
				err(String.format("Expected %s doesn't match %s found at position %d", ex, ac, i));
				return false;
			}
			i++;
		}
		if (it.hasNext() || i == expectedValues.length) {
			err(String.format("Lengths don't match, Iterator hasNext() == %b; expectedValues.length == %d; i == %d", it.hasNext(), expectedValues.length, i));
			return false;
		}
		successCount++;
		return true;
	}

	public static void Main(String args[]){

		/* 1:
		 * Erzeugen Sie einen Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ MyInt sind, 
		 * und ï¿½berprï¿½fen Sie die Funktionalitï¿½t. Das heiï¿½t, fï¿½gen Sie Kanten auf mehreren Ebenen ein 
		 * (also auch Kanten, die nicht von der Wurzel ausgehen), entfernen Sie Kanten, und so weiter. 
		 * ï¿½berprï¿½fen Sie die Korrektheit durch Ausgabe der ï¿½ber Iteratoren ermittelten Labels.
		 */
		Tree intTree;
		MyInt myint1 = new MyInt(6);
		MyInt myint2 = new MyInt(3);
		MyInt myint3 = new MyInt(4);
		MyInt myint4 = new MyInt(8);
		MyInt myint5 = new MyInt(9);
		
		intTree = new Tree(myInt1);
		
		
//		checkIter(tree.allLabels(), "bla", "bli", "blu");
		

			
		/* 2:
		 * Erzeugen Sie einen Baum als Instanz von ValueTree, in dem Labels auf Kanten vom Typ ADescriptor 
		 * und Werte der Knoten vom Typ BDescriptor sind, und machen Sie entsprechende ï¿½berprï¿½fungen wie in 
		 * Punkt 1. Geben Sie auch die Ergebnisse der Aufrufe von as in Labels auf Kanten und bs in Werten 
		 * von Knoten aus. 
		 */
		
		ADescriptor ades1 = new ADescriptor("Herwig isst Schokolade");
		ADescriptor ades2 = new ADescriptor("Alle Kinder mögen Schokolade");
		ADescriptor ades3 = new ADescriptor("Am besten ist aber Kinder Schokolade");
		ADescriptor ades4 = new ADescriptor("und Milka");
		ADescriptor ades5 = new ADescriptor("des Rest isst nix");
		
		BDescriptor bdes1 = new BDescriptor("Bald ist Weihnachten");
		BDescriptor bdes2 = new BDescriptor("Da wirds kalt");
		BDescriptor bdes3 = new BDescriptor("aber nur mit Schlagobers");
		BDescriptor bdes4 = new BDescriptor("aber jetzt");
		
		int aint1 = 1;
		int aint2 = 2;
		int aint3 = 3;
		int aint4 = 1;
		int aint5 = 0;
		
		boolean bbool1 = true;
		boolean bbool2 = false;
		boolean bbool3 = true;
		boolean bbool4 = true;
		
		/* 3:
		 * Falls ValueTree mit entsprechenden Typparameterersetzungen ein Untertyp von Tree ist, 
		 * betrachten Sie den in Punkt 2 erzeugten Baum als Instanz von Tree, das heiï¿½t, weisen Sie 
		 * den Baum an eine Variable vom deklarierten Typ Tree (mit entsprechenden Typparameterersetzungen) 
		 * zu, und fï¿½hren Sie die folgenden Tests auf dieser Variablen durch: Fï¿½gen Sie noch einige Kanten 
		 * ein, entfernen Sie einige Kanten, lesen Sie die Labels der Kanten ï¿½ber Iteratoren aus, und geben 
		 * Sie auch die Ergebnisse der Aufrufe von as in den Labels aus. Falls ValueTree kein Untertyp von 
		 * Tree ist, geben Sie an Stelle dieser Testergebnisse eine Begrï¿½ndung dafï¿½r aus, warum zwischen 
		 * diesen Typen keine Untertypbeziehung bestehen kann.
		 */
		
		
		
		
		/* 4:
		 * Erzeugen Sie einen weiteren Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ 
		 * Descriptor sind. Lesen Sie alle Labels auf Kanten und Werte von Knoten vom in Punkt 2 erzeugten 
		 * (und mï¿½glicherweise in Punkt 3 verï¿½nderten) Baum aus, und fï¿½gen Sie diese (sowohl Instanzen von 
		 * ADescriptor als auch von BDescriptor wenn ungleich null) als Labels von Kanten in den neuen Baum 
		 * ein. Lesen Sie die Labels der Kanten ï¿½ber Iteratoren aus.
		 */
		
		ADescriptor ades6 = new ADescriptor("solalala");
		ADescriptor ades7 = new ADescriptor("jaja");
		ADescriptor ades8 = new ADescriptor("kein b");
		
		BDescriptor bdes5 = new BDescriptor("mit b");
		BDescriptor bdes6 = new BDescriptor("geht nicht");
		
		int aint6 = 3;
		int aint7 = 2;
		int aint8 = 0;
		
		boolean bbool5 = true;
		boolean bbool6 = false;
		
	}
}