package rolodex;

import java.util.ArrayList;

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;
	char[] letters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	// Constructor

	Rolodex() {
		index = new Entry[26];
		// set the Separator letters
		for (int i = 0; i < letters.length; i++) {
			index[i] = new Separator(null, null, letters[i]);
		}
		// link each separator
		for (int i = 0; i < index.length; i++) {
			if (i == 0) { // specific condition for first and last
				index[i].next = index[1];
				index[i].prev = index[25];
			} else if (i == 25) { // need to link last separator
				index[i].next = index[0]; // to first
				index[i].prev = index[24];
			} else {
				index[i].next = index[i + 1]; // otherwise letter B links to A(prev) and C(next)
				index[i].prev = index[i - 1];
			}
		}
	}

	public Boolean contains(String name) {
		if (name == null) {
			throw new IllegalArgumentException("contains: No name was inputted");
		}

		int c = name.charAt(0) - 65;
		if (c > 25 || c < 0)
			return false;
		cursor = index[c];
		Entry traverse = cursor;
		while (name.compareToIgnoreCase(traverse.getName()) >= 0) {
			if (name.equals((traverse).getName())) {
				return true;
			} else {
				traverse = traverse.next;
			}
		}
		throw new IllegalArgumentException("lookup: name not found.");
	}

	public int size() {
		int count = 0;
		Entry traverse = index[0];
		while (traverse.next != index[0]) {
			if (!traverse.isSeparator())
				count++;
			traverse = traverse.next;
		}
		return count;
	}

	public ArrayList<String> lookup(String name) {
		if (name == null) {
			throw new IllegalArgumentException("lookup: no name to lookup");
		}
		char first = name.charAt(0);
		int j = 0;
		for (int i = 0; i < letters.length; i++) {
			if (first == letters[i]) {
				j = i;
			}
		}
		cursor = index[j];
		Boolean check = false;
		Entry traverse = cursor;
		ArrayList<String> numbers = new ArrayList<String>();
		while (traverse != null) {
			if (name.equals((traverse).getName())) {
				numbers.add(((Card) (traverse)).getCell());
				traverse = traverse.next;
				check = true;
			} else if (check) {
				traverse = null;
			} else {
				traverse = traverse.next;
			}
		}

		if (numbers.size() == 0) {
			throw new IllegalArgumentException("lookup: name not found");
		}
		return numbers;
	}

	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next != index[0]) {
			b.append(current.toString() + "\n");
			current = current.next;
		}
		b.append(current.toString() + "\n");
		return b.toString();
	}

	private Entry nextSep(String name) {
		int c = name.charAt(0) -65;
		Entry nextSep;
		if (c == 25) {
			nextSep = index[0];
		} else {
			nextSep = index[c+1];
		}
		return nextSep;
	}

	public void addCard(String name, String cell) {
		if (name == null) {
			throw new IllegalArgumentException("addCard: name non existant(empty input)");
		} else if(cell == null){
			throw new IllegalArgumentException("addCard: cell non existant(empty input)");
		} else if (contains(name) && lookup(name).contains(cell)) {
			throw new IllegalArgumentException("addCard: duplicate entry");
		}
		int c = name.charAt(0) -65;
		if (c>25 || c<0) {
			throw new IllegalArgumentException("addCard: Invalid name entry");
		}
		Entry traverse = index[c];
		while (traverse != nextSep(name)) {
			if (traverse.next.isSeparator() && name.compareToIgnoreCase(traverse.getName()) >= 0) {
				Card newCard = new Card(traverse, traverse.next, name, cell);
				Entry newEntry = traverse.next;
				traverse.next = newCard;
				newEntry.prev = newCard;
				break;
			} else if (name.compareTo(traverse.getName()) >=0 && name.compareTo(traverse.next.getName()) <= 0) {
				Card newCard = new Card(traverse, traverse.next,name,cell);
				Entry newEntry = traverse.next;
				traverse.next = newCard;
				newEntry.prev = newCard;
				break;
			} else {
				traverse = traverse.next;
			}
		}

		
	}

	public void removeCard(String name, String cell) {
		if (name == null) {
			throw new IllegalArgumentException("removeCard: name non existant");
		}
		int c = name.charAt(0) -65;
		cursor = index[c];

		Entry traverse = cursor;
		while (name.compareToIgnoreCase(traverse.getName()) >= 0){
			if(name.equals((traverse).getName()) && cell.equals(((Card)(traverse)).getCell())){
				traverse.prev.next = traverse.next;
				traverse.next.prev = traverse.prev;
				return;
			} else if (name.compareToIgnoreCase((traverse).getName()) >= 0){
				traverse = traverse.next;
			} 
		}
	}

	public void removeAllCards(String name) {
		if(!contains(name)) {
			throw new IllegalArgumentException("removeAllCards: name does not exist");
		}
		ArrayList<String> nameList = lookup(name);
		
		for(int i = 0; i<nameList.size();i++) {
			removeCard(name,nameList.get(i));
		}
	}

	// Cursor operations
	public void initializeCursor() {
		cursor = index[0];

	}

	public void nextSeparator() {
		cursor = cursor.next;
		while(!cursor.isSeparator()) {
			cursor = cursor.next;
		}
	}

	public void nextEntry() {
		cursor = cursor.next;
	}

	public String currentEntryToString() {
		return cursor.toString();
	}

	public static void main(String[] args) {

		Rolodex r = new Rolodex();

		System.out.println(r);

	}

}
