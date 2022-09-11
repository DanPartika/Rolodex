package rolodex;

public abstract class Entry extends Rolodex{
	protected Entry prev;
	protected Entry next;
	
	public Entry(Entry prev, Entry next) {
		super();
		this.prev = prev;
		this.next = next;
	}
	
	public abstract Boolean isSeparator();
	
	public abstract int size();
	
	public abstract String getName();
	
}
