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
	}
	
	private void init_player_model(int width, int height)
	{
		 try {
			this.myModel = ImageIO.read(new File("images/triangle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.modelLabel = new JLabel(new ImageIcon(myModel.getScaledInstance(width, height, Image.SCALE_FAST)));
		 
		 //set background
		// create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
 
        // set the border of this component
        //this.modelLabel.setBorder(border);
	}
	
	//getter methods
	private JLabel getModelLabel()
	{
		return this.modelLabel;
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	//attributes
	private int id;
	private String name;
	private String color;
	private int position;
	
	BufferedImage myModel;
	JLabel modelLabel;
	
}

