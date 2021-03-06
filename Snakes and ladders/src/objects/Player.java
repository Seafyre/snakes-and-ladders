package objects;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

// This class describes the player itself

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5967802960863871052L;

	Player(int id, String name, String color) {
		this.init(id, name, color);
	}
	
	// Initial methods
	public void init(int id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.position = 1;
		this.oldposition = 1;
		this.won = false;
		this.setModel(id);
	}
	
	private void setModel(int id) {
		if(id == 1)
			this.modelpath = "images/player_m.png";
		else
			this.modelpath = "images/player_f.png";	
	}
	
	
	// Getter methods
	public String getName()  {
		return this.name;
	}
	
	public String getModelPath() {
		return this.modelpath;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public int getOldPosition() {
		return this.oldposition;
	}
	
	public boolean hasWon() {
		return this.won;
	}
	
	// Setter methods
	// Set the new position of the player
	private void setPosition(int val) {
		this.setOldPosition(this.position);
		this.position = val;
	}
	
	// Set the old position of the player
	private void setOldPosition(int val) {
		this.oldposition = val;
	}
	
	// Move the player in relation to it's current position
	public void move(int amount) {
		this.setPosition(this.getPosition() + amount);
		//System.out.println("new position of " + this.name + " is: " + this.getPosition());
	}
	
	// Set won flag on player
	public void setWon() {
		this.won = true;
	}
	
	// Attributes
	private int id;
	private String name;
	private String color;
	private int position;
	private int oldposition;
	
	private boolean won;
	
	String modelpath;
	
}