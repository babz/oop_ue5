
public class Test{
	
	
	static void err(String s) {
		System.out.print("ERROR: ");
		System.out.println(s);
	}
	
	static void info(String s) {
		System.out.print("INFO: ");
		System.out.println(s);
	}
	
	static void printIter(String label, Iter<?> it) {
		System.out.print(label);
		System.out.print(": ");
		while (it.hasNext()) {
			System.out.print(String.format("%s, ", it.next()));
		}
		System.out.println();
	}
	
	static int runCount = 0, successCount = 0;
	static boolean checkIter(Iter<?> it, Object... expectedValues) {
		StringBuilder buf = new StringBuilder();
		runCount++;
		int i = 0;
		while (it.hasNext() && i < expectedValues.length) {
			Object ex = expectedValues[i];
			Object ac = it.next();
			buf.append(""+ac).append(", ");
			if (! (ex == ac || ex.equals(ac))){
				err(String.format("Expected %s doesn't match %s found at position %d", ex, ac, i));
				info(buf.toString());
				printIter("failed", it);
				return false;		
			}
			i++;
		}
		if (it.hasNext() || i != expectedValues.length) {
			err(String.format("Lengths don't match, Iterator hasNext() == %b; expectedValues.length == %d; i == %d", it.hasNext(), expectedValues.length, i));
			return false;
		}
		successCount++;
		return true;
	}
	
	static void assert_(boolean cond) {
		if ( ! cond) {
			err("Assertion failed!");
			System.exit(1);
		}
	}

	public static void main(String args[]){
		
		testLList();

		/* 1:
		 * Erzeugen Sie einen Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ MyInt sind, 
		 * und �berpr�fen Sie die Funktionalit�t. Das hei�t, f�gen Sie Kanten auf mehreren Ebenen ein 
		 * (also auch Kanten, die nicht von der Wurzel ausgehen), entfernen Sie Kanten, und so weiter. 
		 * �berpr�fen Sie die Korrektheit durch Ausgabe der �ber Iteratoren ermittelten Labels.
		 */
		Tree<MyInt> tree;
		MyInt i1 = new MyInt(1);
		MyInt i2 = new MyInt(2);
		MyInt i3 = new MyInt(3);
		MyInt i4 = new MyInt(4);
		MyInt i5 = new MyInt(5);
		MyInt i6 = new MyInt(6);
		
		tree = new Tree<MyInt>();
		//iterators for insertion
		RecursiveIter<MyInt> i = tree.assoc();
		RecursiveIter<MyInt> temp;
		
		i.insert(i4);
		i.insert(i1);
		i.insert(i2);

		//run through tree via the assoc iterator
		info("Testcase #" + runCount + ", testing sorted insert ...");
		temp = tree.assoc();
		assert_(checkIter(temp, i1, i2, i4));
		info("... success");
		info("-----------------------------------------------------------\n");
		
		temp = tree.assoc();
		temp.next(); temp.next();
		// one level down
		temp = temp.assoc();
		temp.insert(i5);
		temp.insert(i3);
		
		info("Testcase #" + runCount + ", checking lower branch ...");
		assert_(checkIter(tree.allLabels(), i1, i2, i3, i5, null, i4));
		info("... success");
		info("-----------------------------------------------------------\n");
		
		temp = tree.assoc();
		temp.next(); temp.next();
		temp.delete();
		info("Testcase #" + runCount + ", check after delete ...");
		assert_(checkIter(tree.allLabels(), i1, i4));
		info("... success");
		info("-----------------------------------------------------------\n");

		//add some more 
		info("Testcase #" + runCount + ", adding some subbranches...");
		temp = tree.assoc();
		temp.insert(i3);
		temp.next(); temp.next();
		temp = temp.assoc();
		temp.insert(i5);
		temp.insert(i1);
		
		temp = tree.assoc();
		temp.insert(i2); 
		temp.next(); temp.next();
		temp = temp.assoc();
		temp.insert(i6);
		temp.insert(i5); temp.next();
		temp = temp.assoc();
		temp.insert(i4);
		temp.insert(i1);
		 
		assert_(checkIter(tree.allLabels(), i1, i2, i5, i1, i4, null, i6, null, i3, i1, i5, null, i4));
		info("... success");
		info("-----------------------------------------------------------\n");
		
		/* 2:
		 * Erzeugen Sie einen Baum als Instanz von ValueTree, in dem Labels auf Kanten vom Typ ADescriptor 
		 * und Werte der Knoten vom Typ BDescriptor sind, und machen Sie entsprechende �berpr�fungen wie in 
		 * Punkt 1. Geben Sie auch die Ergebnisse der Aufrufe von as in Labels auf Kanten und bs in Werten 
		 * von Knoten aus. 
		 */
		
		ADescriptor a1 = new ADescriptor("L0f0 isst Schokolade");
		ADescriptor a2 = new ADescriptor("Alle Kinder moegen Schokolade");
		ADescriptor a3 = new ADescriptor("Am besten ist aber Kinder Schokolade");
		ADescriptor a4 = new ADescriptor("und Milka");
		ADescriptor a5 = new ADescriptor("des Rest isst nix");
		
		BDescriptor b1 = new BDescriptor("Bald ist Weihnachten");
		BDescriptor b2 = new BDescriptor("Da wirds kalt");
		BDescriptor b3 = new BDescriptor("aber nur mit Schlagobers");
		BDescriptor b4 = new BDescriptor("aber jetzt");

		
		ValueTree<ADescriptor, BDescriptor> tree2 = new ValueTree<ADescriptor, BDescriptor>();
		RecursiveValueIter<ADescriptor, BDescriptor> move = tree2.assoc();
		
		move.insert(a1);
		move.insert(a2);
		move.insert(a3);
		move.next();
		move.set(b1);
		move.next();
		move.set(b2);
		
		// second level
		move = move.assoc();
		move.insert(a4);
		move.insert(a5);
		move.next();
		move.set(b3);
		move.next();
		move.set(b4);
		move = tree2.assoc();
		
		info("<<<<<<<<<<<<<<<<<<<<<  2.ValueTree  >>>>>>>>>>>>>>>>>>>>>" + "\n" + "\n");
		//run through current branch & return a's & b's (wide search)
		info("Testcase #" + runCount + ", testing assoc Iterator ...");
		assert_(checkIter(tree2.allLabels(), a2, a3, a5, a4, null, a1));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
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
		Tree<Descriptor> tree4;
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
	
	static void testLList() {
		LinkedList<String> l = new LinkedList<String>();
		checkIter(l.iterate());
		ValueIter<String, String, String> temp = l.iterate();
		
		temp.insert("abc"); temp.next();
		checkIter(l.iterate(), "abc");
		
		temp.insert("def"); temp.next();
		checkIter(l.iterate(), "abc", "def");
		
		temp = l.iterate(); temp.next();
		assert_(temp.delete());
		checkIter(l.iterate(), "def");
	}
}