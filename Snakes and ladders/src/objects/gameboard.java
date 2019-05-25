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



public class gameboard {
	
	gameboard(int width, int heigth, int size)
	{
		this.init_board(width, heigth, size);
	}
	
	//initial methods
	private void init_board(int width, int heigth, int size)
	{
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
		this.size = size;
		this.board.setSize(this.width, this.heigth);
		//setting the layout to gridbagconstraints
		this.board.setLayout(new GridBagLayout());
		//making it visible
		this.board.setVisible(true);
		//make windows close when clicking on 'x' in top right corner
		this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//initialize fields
		this.init_fields();
	}
	
	private void init_fields()
	{
		this.c.anchor = GridBagConstraints.NORTH;
		this.c.gridy = 0;
		for(int i = 0; i < this.size; i++)
		{
			if(i%10 == 0) 
			{
				System.out.println("gridy: " + this.c.gridy);
				this.c.gridy += 10;
			}
			
			
			//-2/-4 due to the borders
			this.fields.add(new Field(this.calculateFieldId(i), 0, (this.width/10), (this.heigth/10)));
			if(i%10 == 0)
				((Field)this.fields.get(i)).setplayer(true);
			
			if(i == 10)
				((Field)this.fields.get(10)).setplayer(false);
			
			this.board.add(((Field) this.fields.get(i)).get_piclabel(), this.c);
		}
		//Updating frame to make initialized fields appear
		SwingUtilities.updateComponentTreeUI(this.board);
	}
	
	private int calculateFieldId(int i)
	{
		//int temp = size-i;
		return 100 - (i%10);
		
		/*if (temp % 10 == 0)
			return (this.size-i);
		else
			return i;*/
		
		//int subtract = size/10;
	}
	
	public void update_fields()
	{
		/*for(int i = 0; i < fields.size(); i++)
		{
			if(((Field)fields.get(i)).hasplayer())
				this.board.add()

		}*/
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
	//GridBagLayout
	GridBagConstraints c = new GridBagConstraints();
	//picked array list due to performance
	private List fields = new ArrayList();
	//heigth of board(in pixels)
	private int heigth;
	//width of board(in pixels)
	private int width;
	//size of board(in fields)
	private int size;
	
}
