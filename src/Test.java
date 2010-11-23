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

		/* 1:
		 * Erzeugen Sie einen Baum als Instanz von Tree, in dem Labels auf Kanten vom Typ MyInt sind, 
		 * und �berpr�fen Sie die Funktionalit�t. Das hei�t, f�gen Sie Kanten auf mehreren Ebenen ein 
		 * (also auch Kanten, die nicht von der Wurzel ausgehen), entfernen Sie Kanten, und so weiter. 
		 * �berpr�fen Sie die Korrektheit durch Ausgabe der �ber Iteratoren ermittelten Labels.
		 */
		Tree<MyInt> intTree;
		MyInt myint1 = new MyInt(1);
		MyInt myint2 = new MyInt(2);
		MyInt myint3 = new MyInt(3);
		MyInt myint4 = new MyInt(4);
		MyInt myint5 = new MyInt(5);
		
		intTree = new Tree<MyInt>();
		//iterators for insertion
		Tree<MyInt>.WideIterator i = intTree.assoc();
		Tree<MyInt>.WideIterator i2;
		//test Iterator
		Tree<MyInt>.WideIterator temp;
		
		i.insert(myint1);
		i.insert(myint2);
		i.next();
		i.next();
		i.insert(myint4);//wieder auf einer  Ebene h�her
		
		info("<<<<<<<<<<<<<<<<<<<<<  1. MyInt Tree  >>>>>>>>>>>>>>>>>>>>>" + "\n" + "\n");
		
		//run through tree via the assoc iterator
		info("Testcase #" + runCount + ", testing assoc Iterator ...");
		temp = intTree.assoc();
		assert_(checkIter(temp, myint1, myint2, myint4));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
		i2 = i.assoc();
		i2.insert(myint3);//mit assoc eine Ebene tiefer
		i2.delete();
		i2.insert(myint5);
		//go down to i2 and check if myint5 is there
		info("Testcase #" + runCount + ", checking lower branch with assoc after delete() before next() ....");
		temp = intTree.assoc();
		temp.next();
		temp.next();
		temp = temp.assoc();
		assert_(checkIter(temp, myint3, myint5));
		info("... success");
		info("-----------------------------------------------------------" + "\n");

		i2.next();
		i2.delete();
		info("Testcase #" + runCount + ", checking lower branch with assoc after delete() after next() ....");
		temp = intTree.assoc();
		temp.next();
		temp.next();
		temp = temp.assoc();
		assert_(checkIter(temp, myint5));
		info("... success");
		info("-----------------------------------------------------------" + "\n");

		
		//myint4 & myint2 are deleted. subtree should be deleted as well
		info("Testcase #" + runCount + ", deleting a node also deletes his subtree...");
		i.delete();
		i.delete();
		Tree<MyInt>.DeepIterator deepTemp;
		deepTemp = intTree.allLabels();
		
		assert_(checkIter(temp, myint1));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		

		//check iteration with allLabel'S Iterator
		info("Testcase #" + runCount + ", testing allLabel's Iterator ...");
		deepTemp = intTree.allLabels();
		assert_(checkIter(temp, myint1, myint2, myint5, null, myint4));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
		//add some more 
		info("Testcase #" + runCount + ", adding some subbranches...");
		temp = i.assoc();
		temp.insert(myint3);
		temp.insert(myint4);
		temp = temp.assoc();
		temp.insert(myint5);
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		//checking correctness
		info("Testcase #" + runCount + ", checking correctness with allLabels Iterator ...");
		deepTemp = intTree.allLabels();
		assert_(checkIter(temp, myint1, myint2, myint5, null, myint4, myint3, myint4, myint5));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
		//remove them again
		info("Testcase #" + runCount + ", removing previously inserted...");
		temp = i.assoc();
		temp.delete();
		temp.delete();
		info("... success");
		info("-----------------------------------------------------------" + "\n");
		//checking correctness
		info("Testcase #" + runCount + ", checking correctness with allLabels Iterator ...");
		deepTemp = intTree.allLabels();
		assert_(checkIter(temp, myint1, myint2, myint5, null, myint4));
		info("... success");
		info("-----------------------------------------------------------" + "\n");
			
		
		
		
		/* 2:
		 * Erzeugen Sie einen Baum als Instanz von ValueTree, in dem Labels auf Kanten vom Typ ADescriptor 
		 * und Werte der Knoten vom Typ BDescriptor sind, und machen Sie entsprechende �berpr�fungen wie in 
		 * Punkt 1. Geben Sie auch die Ergebnisse der Aufrufe von as in Labels auf Kanten und bs in Werten 
		 * von Knoten aus. 
		 */
		
		ADescriptor ades1 = new ADescriptor("Herwig isst Schokolade");
		ADescriptor ades2 = new ADescriptor("Alle Kinder moegen Schokolade");
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
		/*
		ValueTree<Descriptor> tree2;
		ValueTree<Descriptor>.iterator i1 = tree2.assoc;
		ValueTree<Descriptor>.iterator i2;
		ValueTree<Descriptor>.iterator i3;
		*/
		
		info("<<<<<<<<<<<<<<<<<<<<<  2.ValueTree  >>>>>>>>>>>>>>>>>>>>>" + "\n" + "\n");
		
		
		
		//run through current branch & return a's & b's (wide search)
		info("Testcase #" + runCount + ", testing assoc Iterator ...");

		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
		
		//run through complete tree & return a's & b's (depth search)
		info("Testcase #" + runCount + ", testing assoc Iterator ...");

		info("... success");
		info("-----------------------------------------------------------" + "\n");
		
		
		
		//
		info("Testcase #" + runCount + ", testing assoc Iterator ...");

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
}