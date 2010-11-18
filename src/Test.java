public class Test{

	public static void Main(String args[]){

		/* 1:
		 * Erzeugen Sie einen Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ MyInt sind, 
		 * und überprüfen Sie die Funktionalität. Das heißt, fügen Sie Kanten auf mehreren Ebenen ein 
		 * (also auch Kanten, die nicht von der Wurzel ausgehen), entfernen Sie Kanten, und so weiter. 
		 * Überprüfen Sie die Korrektheit durch Ausgabe der über Iteratoren ermittelten Labels.
		 */
		Tree tree;
		
		/* 2:
		 * Erzeugen Sie einen Baum als Instanz von ValueTree, in dem Labels auf Kanten vom Typ ADescriptor 
		 * und Werte der Knoten vom Typ BDescriptor sind, und machen Sie entsprechende Überprüfungen wie in 
		 * Punkt 1. Geben Sie auch die Ergebnisse der Aufrufe von as in Labels auf Kanten und bs in Werten 
		 * von Knoten aus. 
		 */
		
		
		/* 3:
		 * Falls ValueTree mit entsprechenden Typparameterersetzungen ein Untertyp von Tree ist, 
		 * betrachten Sie den in Punkt 2 erzeugten Baum als Instanz von Tree, das heißt, weisen Sie 
		 * den Baum an eine Variable vom deklarierten Typ Tree (mit entsprechenden Typparameterersetzungen) 
		 * zu, und führen Sie die folgenden Tests auf dieser Variablen durch: Fügen Sie noch einige Kanten 
		 * ein, entfernen Sie einige Kanten, lesen Sie die Labels der Kanten über Iteratoren aus, und geben 
		 * Sie auch die Ergebnisse der Aufrufe von as in den Labels aus. Falls ValueTree kein Untertyp von 
		 * Tree ist, geben Sie an Stelle dieser Testergebnisse eine Begründung dafür aus, warum zwischen 
		 * diesen Typen keine Untertypbeziehung bestehen kann.
		 */
		
		/* 4:
		 * Erzeugen Sie einen weiteren Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ 
		 * Descriptor sind. Lesen Sie alle Labels auf Kanten und Werte von Knoten vom in Punkt 2 erzeugten 
		 * (und möglicherweise in Punkt 3 veränderten) Baum aus, und fügen Sie diese (sowohl Instanzen von 
		 * ADescriptor als auch von BDescriptor wenn ungleich null) als Labels von Kanten in den neuen Baum 
		 * ein. Lesen Sie die Labels der Kanten über Iteratoren aus.
		 */

	}
}