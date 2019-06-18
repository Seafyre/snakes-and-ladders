package objects;

public class Ufo {

	Ufo(int size) {
		this.init(size);
	}
	
	private void init(int size) {
		this.setSize(size);
	}
	
	// Getter methods
	public int getSize() {
		return this.size;
	}
	
	// Setter methods
	private void setSize(int size) {
		this.size = size;
	}
	
	private int size;
	
}