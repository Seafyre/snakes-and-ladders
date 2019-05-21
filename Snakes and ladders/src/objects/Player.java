package objects;

// Klasse beschreibt Spieler

public class Player {
	
	Player(int id, String name, String color)
	{
		this.init(id, name, color);
	}
	
	//initial methods
	public void init(int id, String name, String color)
	{
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	//getter methods
	public String getName() {
		return this.name;
	}
	
	//attributes
	private int id;
	private String name;
	private String color;
	
}

