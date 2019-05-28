package objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;



public class Gameboard {
	
	Gameboard(int width, int heigth, int size)
	{
		this.init_board(width, heigth, size);
	}
	
	//initial methods
	private void init_board(int width, int heigth, int size)
	{
		this.swap = false;
		this.setup_board(width, heigth, size);
		//this.set_background_image();
	}
	
	private void setup_board(int width, int heigth, int size)
	{
		//creating new jframe
		this.board = new JFrame("Snakes and Ladders");
		//setting its size
		this.heigth = heigth;
		this.width = width;
		//size = amount of fields
		this.size = size;
		this.board.setSize(this.width, this.heigth);
		//setting the layout to gridbagconstraints
		this.board.setLayout(new GridBagLayout());
		//make windows appear at center of screen
		this.board.setLocationRelativeTo(null);
		//making it visible
		this.board.setVisible(true);
		//make windows close when clicking on 'x' in top right corner
		this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//initialize fields
		this.init_fields();
	}
	
	private void init_fields()
	{
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 0;
		for(int i = 0; i < this.size; i++)
		{
			if(i%10 == 0) 
			{
				c.gridy += 10;
			}
			
			
			//-2/-4 due to the borders
			this.fields.add(new Field(this.calculateFieldId(i), 0, (this.width/10), (this.heigth/10)));
			if(((Field)this.fields.get(i)).getid() == 1)
				((Field)this.fields.get(i)).setplayer(2);
			
			/*if(this.calculateFieldId(i) == 0)
				((Field)this.fields.get(this.calculateFieldId(i))).setplayer(true);*/
			/*if(i%10 == 0)
				((Field)this.fields.get(i)).setplayer(true);*/
			
			this.board.add(((Field) this.fields.get(i)).get_piclabel(), c);
		}
		//Updating frame to make initialized fields appear
		SwingUtilities.updateComponentTreeUI(this.board);
	}
	
	//calculates the id of each field, so the fieldorder is right
	private int calculateFieldId(int i)
	{
		
		int temp = 0;
		//order change after each row(10 fields)
		if (i > 0 && i%10 == 0)
			this.swap = !this.swap;
		
		
		if (this.swap == false)
			return this.size-i;
		else
		{
			temp = i%10;
			return this.size-(i-temp + 9-temp);
		}
		
		/*if (temp % 10 == 0)
			return (this.size-i);
		else
			return i;*/
		
		//int subtract = size/10;
	}
	
	public void update_fields(Player player)
	{
		for(int i = 0; i < this.size; i++)
		{
			if(((Field)this.fields.get(i)).getid() == player.getPosition())
			{
				if(((Field)this.fields.get(i)).hasPlayers() == 0)
					((Field)this.fields.get(i)).setplayer(1);
				else
					((Field)this.fields.get(i)).setplayer(((Field)this.fields.get(i)).hasPlayers()+1);
			}
			if(((Field)this.fields.get(i)).getid() == player.getOldPosition())
			{
				if(((Field)this.fields.get(i)).hasPlayers() == 0)
					((Field)this.fields.get(i)).setplayer(0);
				else
					((Field)this.fields.get(i)).setplayer(((Field)this.fields.get(i)).hasPlayers()-1);
			}
		}
		SwingUtilities.updateComponentTreeUI(this.board);
	}
	
	//setter methods
	/*private void set_background_image()
	{
		try {
            this.board.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/temporary_board_background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//sizes the frame so that all its contents are at or above their preferred sizes
        this.board.pack();
	}*/
	
	
	
	//attributes
	//board jframe
	private JFrame board;
	//picked array list due to performance
	private List fields = new ArrayList();
	//heigth of board(in pixels)
	private int heigth;
	//width of board(in pixels)
	private int width;
	//size of board(in fields)
	private int size;
	//Field swap variable to determine wether we need to change order of row
	private boolean swap;
	
	
}
