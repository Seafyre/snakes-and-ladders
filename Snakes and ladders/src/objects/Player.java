package objects;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;

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
		this.position = 1;
		this.oldposition = 1;
	}
	
	//getter methods
	public String getName() 
	{
		return this.name;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public int getOldPosition()
	{
		return this.oldposition;
	}
	
	//setter methods
	private void setPosition(int val)
	{
		this.setOldPosition(this.position);
		this.position = val;
	}
	
	private void setOldPosition(int val)
	{
		this.oldposition = val;
	}
	
	public void move(int amount)
	{
		this.setPosition(this.getPosition() + amount);
		System.out.println("new position of " + this.name + " is: " + this.getPosition());
	}
	
	
	
	//attributes
	private int id;
	private String name;
	private String color;
	private int position;
	private int oldposition;
	
	BufferedImage myModel;
	JLabel modelLabel;
	
}

