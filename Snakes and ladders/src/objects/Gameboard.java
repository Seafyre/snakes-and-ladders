package objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;



public class Gameboard implements Serializable {
	
	
	private static final long serialVersionUID = 3608126758176641235L;
	
	Gameboard(int width, int heigth, int size, Player activeplayer, boolean server)
	{
		this.init_board(width, heigth, size, activeplayer, server);
	}
	
	//initial methods
	private void init_board(int width, int heigth, int size, Player activeplayer, boolean server)
	{
		this.swap = false;
		this.setup_board(width, heigth, size, activeplayer, server);
		//this.set_background_image();
	}
	
	private void setup_board(int width, int heigth, int size, Player activeplayer, boolean client)
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
		this.board.setVisible(client);
		//make windows close when clicking on 'x' in top right corner
		this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//initialize fields
		this.init_fields(activeplayer);
	}
	
	//init field layout
	private void init_fields(Player activeplayer)
	{
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.gridy = 0;
		
		//initializing basic fields
		for(int i = 0; i < this.size; i++)
		{
			if(i%10 == 0) 
			{
				c.gridy += 10;
			}
			
			
			//-2/-4 due to the borders
			this.fields.add(new Field(this.calculateFieldId(i), 0, (this.width/10), (this.heigth/10)));
			if(((Field)this.fields.get(i)).getid() == 1)
				((Field)this.fields.get(i)).setplayer(2, activeplayer);
			
			/*if(this.calculateFieldId(i) == 0)
				((Field)this.fields.get(this.calculateFieldId(i))).setplayer(true);*/
			/*if(i%10 == 0)
				((Field)this.fields.get(i)).setplayer(true);*/
			
			this.board.add(((Field) this.fields.get(i)).get_piclabel(), c);
		}
		
		//initializing ufos
		this.initUfos();
		
		//initializing Wormholes
		this.initWormholes();
		
		//Updating frame to make initialized fields appear
		this.board.revalidate();
		this.board.repaint();
	}
	
	
	
	//init ufos
	//1 = src, 2 = dest, everything else -> just overlay
	private void initUfos()
	{
		this.initUfo(33, 3);
		this.initUfo(46, 3);
		this.initUfo(89, 3);
	}
	
	private void initWormholes()
	{
		this.initWormhole(37, 3);
		this.initWormhole(65, 3);
		this.initWormhole(78, 3);
	}
	
	//initialize single ufo
	private void initUfo(int fieldid, int size)
	{
		for(int i = 0; i < this.size; i++)
		{
			if(((Field)this.fields.get(i)).getid() == fieldid)
			{
				for(int k = 0; k < size; k++)
				{		
					if(k == size-1)
					{
						((Field)this.fields.get(i+(k*10))).setUfo(new Ufo(3), 2);
					}
					else if(k != 2)
						((Field)this.fields.get(i+(k*10))).setUfo(new Ufo(3), k);
					else
						((Field)this.fields.get(i+(k*10))).setUfo(new Ufo(3), size+1);
					
					/*((Field)this.fields.get(i)).setUfo(new Ufo(3), 1);
					((Field)this.fields.get(i+10)).setUfo(new Ufo(3), 3);
					((Field)this.fields.get(i+20)).setUfo(new Ufo(3), 2);*/
				}
			}
		}
	}
	
	//initialize single wormhole
	private void initWormhole(int fieldid, int size)
	{
		for(int i = 0; i < this.size; i++)
		{
			if(((Field)this.fields.get(i)).getid() == fieldid)
			{
				for(int k = 0; k < size; k++)
				{	
					//setting the src(2) to size-1, so the src is set to the last tile reached by wormhole/ufo
					if(k == 0)
					{
						((Field)this.fields.get(i+(k*10))).setWormhole(new Wormhole(3), 2);
					}
					//otherwise just pass in k
					else if(k == size-1)
					{
						((Field)this.fields.get(i+(k*10))).setWormhole(new Wormhole(3), 0);
					}
					else if(k != 2)
						((Field)this.fields.get(i+(k*10))).setWormhole(new Wormhole(3), k);
					//if ks is equal to 2, we can't just pass it, since 2 indicates the src, so we just pass in size+1
					else
						((Field)this.fields.get(i+(k*10))).setWormhole(new Wormhole(3), size+1);
					
					/*((Field)this.fields.get(i)).setUfo(new Ufo(3), 1);
					((Field)this.fields.get(i+10)).setUfo(new Ufo(3), 3);
					((Field)this.fields.get(i+20)).setUfo(new Ufo(3), 2);*/
				}
			}
		}
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
	
	//update fields to display new playerposition after each round
	public void update_fields(Player player)
	{
		if(!player.hasWon())
		{
			for(int i = 0; i < this.size; i++)
			{
				if(((Field)this.fields.get(i)).getid() == player.getPosition())
				{
					((Field)this.fields.get(i)).setplayer(((Field)this.fields.get(i)).hasPlayers()+1, player);	
				}
				if(((Field)this.fields.get(i)).getid() == player.getOldPosition())
				{
					((Field)this.fields.get(i)).setplayer(((Field)this.fields.get(i)).hasPlayers()-1, player);
				}
			}
		}
		else
		{
			for(int i = 0; i < this.size; i++)
			{
				if(((Field)this.fields.get(i)).getid() == this.size)
				{
					if(((Field)this.fields.get(i)).hasPlayers() == 0)
						((Field)this.fields.get(i)).setplayer(1, player);
				}
				if(((Field)this.fields.get(i)).getid() == player.getOldPosition())
				{
					if(((Field)this.fields.get(i)).hasPlayers() == 0)
						((Field)this.fields.get(i)).setplayer(0, player);
					else
						((Field)this.fields.get(i)).setplayer(((Field)this.fields.get(i)).hasPlayers()-1, player);
				}
			}
		}
			
		//refresh GUI components
		this.board.revalidate();
		this.board.repaint();
	}
	
	//getter methods
	//return amount of fields on the board
	public int getSize()
	{
		return this.size;
	}
	
	public Field getField(int val)
	{
		for(int i = 0; i < this.size; i++)
		{
			if(((Field)this.fields.get(i)).getid() == val)
				return (Field)this.fields.get(i);
			else
				continue;
		}
		
		return null;
			
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
	//List of ufos on the map
	private List ufos = new ArrayList();
	
	//heigth of board(in pixels)
	private int heigth;
	//width of board(in pixels)
	private int width;
	//size of board(in fields)
	private int size;
	//Field swap variable to determine wether we need to change order of row
	private boolean swap;
	
	
}