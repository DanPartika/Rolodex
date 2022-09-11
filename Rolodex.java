package rolodex;

import java.util.ArrayList;

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;
	char[] letters = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


	// Constructor

	Rolodex() {
	    index = new Entry[26];
		//set the Separator letters
		for (int i = 0; i < letters.length; i++) {
			index[i] = new Separator(null, null, letters[i]);
		}
		//link each separator
		for (int i = 0; i < index.length; i++) { 
			if (i == 0) {  //specific condition for first and last
	    		index[i].next = index[1];
	    		index[i].prev = index[25];
	    	} else if (i == 25) {  // need to link last separator 
				index[i].next = index[0]; // to first
				index[i].prev = index[24];	
			} else {
				index[i].next = index[i+1]; //otherwise letter B links to A(prev) and C(next)
				index[i].prev = index[i-1];
			}
		}
	}

	public Boolean contains(String name) {
	    if (name == null) {
			throw new IllegalArgumentException("contains: No name was inputted");
		}
		 
		return false;
	}
	
	public int size() {
		    // TODO
		return 0;
	}

	public ArrayList<String> lookup(String name) {
		    // TODO
		return null;
	}


	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}




	public void addCard(String name, String cell) {
			    // TODO

	}

	public void removeCard(String name, String cell) {
			    // TODO


	}
	
	public void removeAllCards(String name) {
		    // TODO

	}

	// Cursor operations

	public void initializeCursor() {
		    // TODO

	}

	public void nextSeparator() {
		    // TODO


	}

	public void nextEntry() {
		    // TODO

	}

	public String currentEntryToString() {
			    // TODO
		return null;
	}


	public static void main(String[] args) {

		Rolodex r = new Rolodex();


		System.out.println(r);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//		r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		System.out.println(r);




	}

}
