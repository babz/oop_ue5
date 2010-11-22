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
		 * und �berpr�fen Sie die Funktionalit�t. Das hei�t, f�gen Sie Kanten auf mehreren Ebenen ein 
		 * (also auch Kanten, die nicht von der Wurzel ausgehen), entfernen Sie Kanten, und so weiter. 
		 * �berpr�fen Sie die Korrektheit durch Ausgabe der �ber Iteratoren ermittelten Labels.
		 */
		Tree tree;
		
		checkIter(tree.allLabels(), "bla", "bli", "blu");
		
		/* 2:
		 * Erzeugen Sie einen Baum als Instanz von ValueTree, in dem Labels auf Kanten vom Typ ADescriptor 
		 * und Werte der Knoten vom Typ BDescriptor sind, und machen Sie entsprechende �berpr�fungen wie in 
		 * Punkt 1. Geben Sie auch die Ergebnisse der Aufrufe von as in Labels auf Kanten und bs in Werten 
		 * von Knoten aus. 
		 */
		
		
		/* 3:
		 * Falls ValueTree mit entsprechenden Typparameterersetzungen ein Untertyp von Tree ist, 
		 * betrachten Sie den in Punkt 2 erzeugten Baum als Instanz von Tree, das hei�t, weisen Sie 
		 * den Baum an eine Variable vom deklarierten Typ Tree (mit entsprechenden Typparameterersetzungen) 
		 * zu, und f�hren Sie die folgenden Tests auf dieser Variablen durch: F�gen Sie noch einige Kanten 
		 * ein, entfernen Sie einige Kanten, lesen Sie die Labels der Kanten �ber Iteratoren aus, und geben 
		 * Sie auch die Ergebnisse der Aufrufe von as in den Labels aus. Falls ValueTree kein Untertyp von 
		 * Tree ist, geben Sie an Stelle dieser Testergebnisse eine Begr�ndung daf�r aus, warum zwischen 
		 * diesen Typen keine Untertypbeziehung bestehen kann.
		 */
		
		/* 4:
		 * Erzeugen Sie einen weiteren Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ 
		 * Descriptor sind. Lesen Sie alle Labels auf Kanten und Werte von Knoten vom in Punkt 2 erzeugten 
		 * (und m�glicherweise in Punkt 3 ver�nderten) Baum aus, und f�gen Sie diese (sowohl Instanzen von 
		 * ADescriptor als auch von BDescriptor wenn ungleich null) als Labels von Kanten in den neuen Baum 
		 * ein. Lesen Sie die Labels der Kanten �ber Iteratoren aus.
		 */

	}
}