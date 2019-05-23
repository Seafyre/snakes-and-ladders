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
	
	gameboard(int width, int heigth)
	{
		this.init_board(width, heigth);
	}
	
	//initial methods
	private void init_board(int width, int heigth)
	{
		this.setup_board(width, heigth);
		//this.set_background_image();
	}
	
	private void setup_board(int width, int heigth)
	{
		//creating new jframe
		this.board = new JFrame("Snakes and Ladders");
		//setting its size
		this.heigth = heigth;
		this.width = width;
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
		for(int i = 0; i < 100; i++)
		{
			if(i%10 == 0) 
			{
				System.out.println("gridy: " + this.c.gridy);
				this.c.gridy += 10;
			}
			
			//-2/-4 due to the borders
			this.fields.add(new Field(i, 0, (this.width/10)-2, (this.heigth/10)-4));
			this.board.add(((Field) this.fields.get(i)).get_piclabel(), this.c);
		}
		//Updating frame to make initialized fields appear
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
	//GridBagLayout
	GridBagConstraints c = new GridBagConstraints();
	//picked array list due to performance
	private List fields = new ArrayList();
	//heigth of board
	private int heigth;
	//width of board
	private int width;
}
