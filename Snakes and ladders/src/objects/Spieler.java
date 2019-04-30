package objects;

// Klasse beschreibt Spieler

public class Spieler {
	private char zeichen;
	private String name;
	
// Konstruktor um Spieler anzulegen
// name Name des Spielers
// sign Zeichen des Spielers

public String getName() {
	return this.name;
}

public char getZeichen() {
	return this.zeichen;
}

//Spielerzeichen festlegen
public void setZeichen(char zeichen) {
	this.zeichen = zeichen;
}
}
