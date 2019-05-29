package objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;

public class Field 
{
	
	Field(int id, int link, int width, int height)
	{
		this.init(id, link, width, height);
	}
	
	//initial methods
	public void init(int id, int link, int width, int height)
	{
		//setting the id - 100, so we start from the bottom left corner
		this.id = id;
		this.link = link;
		this.width = width;
		this.height = height;
		
		this.ufo = null;
		this.ufosrcdest = 0;
		
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
		
		 //adding an overlaylayout, so we can display the playermodel over the tile's background image
		 LayoutManager overlay = new OverlayLayout(picLabel);
		 picLabel.setLayout(overlay);

		 //adding the displayed numbers to the field
		 this.initFieldNumber();
		 
		 //set background
		// create a line border with the specified color and width
        //Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
 
        // set the border of this component
        //this.picLabel.setBorder(border);
	}
	
	private void initFieldNumber()
	{
		JLabel num = new JLabel(Integer.toString(this.id));
        num.setFont(num.getFont().deriveFont(28f));
        num.setForeground(Color.CYAN);
        num.setAlignmentX(0.5f);
        num.setAlignmentY(0.5f);
        picLabel.add(num);
	}
	
	//setter methods
	public void setplayer(int amount)
	{
		this.hasPlayers = amount;
		System.out.println("Field " + Integer.toString(this.id) + " has " + Integer.toString(this.hasPlayers()) + " Players");
		
		if(this.hasPlayers >= 1)
		{
			if(!this.hasPlayerModel)
			{
				this.hasPlayerModel = true;
				try {
					this.playerModel = ImageIO.read(new File("images/triangle.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.playerLabel = new JLabel(new ImageIcon(playerModel.getScaledInstance(this.width-20, this.height-20, Image.SCALE_FAST)));
				
				//setting alignments to 0.5f each, so the playermodel is displayed centered within the field
				this.playerLabel.setAlignmentX(0.5f);
				this.playerLabel.setAlignmentY(0.5f);
				
				
				picLabel.add(playerLabel);
			}
			else
				return;
		}
		else
		{
			System.out.println("removed on " + Integer.toString(this.id));
			picLabel.remove(playerLabel);
			this.hasPlayerModel = false;
		}
	}
	
	//setufo field srcdest = 0 -> src, srcdest = 1 -> dest
	public void setUfo(Ufo ufo, int srcdest)
	{
		this.ufo = ufo;
		this.ufosrcdest = srcdest;
		
		if(!this.hasUfoModel  && this.ufosrcdest == 1)
		{
			this.hasUfoModel = true;
			try {
				this.ufoModel = ImageIO.read(new File("images/ufo.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ufoLabel = new JLabel(new ImageIcon(ufoModel.getScaledInstance(this.width-20, this.height, Image.SCALE_FAST)));
			
			//setting alignments to 0.5f each, so the playermodel is displayed centered within the field
			this.ufoLabel.setAlignmentX(0.5f);
			this.ufoLabel.setAlignmentY(0.5f);
			
			
			picLabel.add(ufoLabel);
		}
		
		if(!this.hasUfoModel  && this.ufosrcdest == 2)
		{
			this.hasUfoModel = true;
			try {
				this.ufoModel = ImageIO.read(new File("images/ufo.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ufoLabel = new JLabel(new ImageIcon(ufoModel.getScaledInstance(this.width-20, this.height*3, Image.SCALE_FAST)));
			
			//setting alignments to 0.5f each, so the playermodel is displayed centered within the field
			this.ufoLabel.setAlignmentX(0.5f);
			this.ufoLabel.setAlignmentY(0.5f);
			
			
			picLabel.add(ufoLabel);
		}
		
		if(!this.hasUfoModel  && this.ufosrcdest == 3)
		{
			this.hasUfoModel = true;
			try {
				this.ufoModel = ImageIO.read(new File("images/ufo.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.ufoLabel = new JLabel(new ImageIcon(ufoModel.getScaledInstance(this.width-20, this.height*3, Image.SCALE_FAST)));
			
			//setting alignments to 0.5f each, so the playermodel is displayed centered within the field
			this.ufoLabel.setAlignmentX(0.5f);
			this.ufoLabel.setAlignmentY(0.5f);
			
			
			picLabel.add(ufoLabel);
		}
	}
	
	//getter methods
	//return piclabel, to initialize fields in gameboard
	public JLabel get_piclabel()
	{
		return this.picLabel;
	}
	
	public JLabel getUfoLabel()
	{
		return this.ufoLabel;
	}
	
	//return playerlabel
	public JLabel getPlayerLabel()
	{
		return this.playerLabel;
	}
	
	//return id of field
	public int getid()
	{
		return this.id;
	}
	
	//return linkstatus of field(if its connected via ladder/snake))
	public int getlink()
	{
		return this.link;
	}
	
	//check if there's a player standing on the field
	public int hasPlayers()
	{
		return this.hasPlayers;
	}
	
	//check if the field is the source or dest of a ufo
	public int getUfoSrcDest()
	{
		return this.ufosrcdest;
	}
	
	//attributes
	private int id;
	private int link;
	private int width;
	private int height;
	int hasPlayers;
	boolean hasPlayerModel;
	boolean hasUfoModel;
	//background image of each seperate tile
	BufferedImage myPicture;
	JLabel picLabel;
	//add(picLabel);
	BufferedImage playerModel;
	JLabel playerLabel;
	
	//ufo pics
	BufferedImage ufoModel;
	JLabel ufoLabel;
	
	Ufo ufo;
	//srcdest = 1 -> src, srcdest = 2 -> dest
	int ufosrcdest;
	

}
