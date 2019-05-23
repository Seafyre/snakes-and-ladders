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

public class Field {
	
	Field(int id, int link, int width, int height)
	{
		this.init(id, link, width, height);
	}
	
	//initial methods
	public void init(int id, int link, int width, int height)
	{
		this.id = id;
		this.link = link;
		
		//init background picture
		this.init_bg_picture(width, height);
	}
	
	private void init_bg_picture(int width, int height)
	{
		 try {
			this.myPicture = ImageIO.read(new File("images/tile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(width, height, Image.SCALE_FAST)));
		 
		 //set background
		// create a line border with the specified color and width
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
 
        // set the border of this component
        this.picLabel.setBorder(border);
	}
	
	
	//getter methods
	//return piclabel, to initialize fields in gameboard
	public JLabel get_piclabel()
	{
		return this.picLabel;
	}
	
	public int getid()
	{
		return this.id;
	}
	
	public int getlink()
	{
		return this.link;
	}
	
	
	//attributes
	private int id;
	private int link;
	//background image of each seperate tile
	BufferedImage myPicture;
	JLabel picLabel;
	//add(picLabel);

}
