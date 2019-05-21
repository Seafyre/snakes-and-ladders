package objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;



public class gameboard {
	gameboard()
	{
		this.init_board();
	}
	
	private void init_board()
	{
		this.setup_board();
		this.set_background_image();
	}
	
	private void setup_board()
	{
		//creating new jframe
		this.board = new JFrame("Snakes and Ladders");
		//setting its size
		this.board.setSize(800, 600);
		//making it visible
		this.board.setVisible(true);
		//make windows close when clicking on 'x' in top right corner
		this.board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void set_background_image()
	{
		try {
            this.board.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("images/temporary_board_background.jpg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
		//sizes the frame so that all its contents are at or above their preferred sizes
        this.board.pack();
	}
	
	private JFrame board;
	
	
	/*public List s; 
	public Square square;*/
	//public boolean win
}
