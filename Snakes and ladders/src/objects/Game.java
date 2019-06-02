package objects;

import java.sql.Time;

import javax.swing.SwingUtilities;

public class Game {

	Game(int boardwidth, int boardheigth, int boardsize)
	{
		this.init_game(boardwidth, boardheigth, boardsize);
	}
	
	public void init_game(int boardwidth, int boardheigth, int boardsize)
	{
		System.out.println("starting game");
		this.dice = new Dice(200, 200);
		this.board = new Gameboard(boardwidth, boardheigth, boardsize);
		this.player1 = new Player(1, "Nick", "Green");
		this.player2 = new Player(2, "Sebastian", "Blue");
	}
	
	private void checkUfo(Player activeplayer)
	{
		if(!activeplayer.hasWon())
		{
			if(board.getField(activeplayer.getPosition()).getUfoSrcDest() == 2)
			{
				this.dice.setDisabled(true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				activeplayer.move(20);
				this.board.update_fields(activeplayer);
				this.dice.setDisabled(false);
			}
		}
	}
	
	private void movePlayer(Player activeplayer, int randomVal)
	{
		if(activeplayer.getPosition() + randomVal < this.board.getSize())
		{
			activeplayer.move(randomVal);
		}
		else
		{
			activeplayer.move(this.board.getSize());
			activeplayer.setWon();
		}
	}
	
	private Player nextPlayer(Player activeplayer)
	{
		if(!activeplayer.hasWon())
		{
			if(activeplayer == this.player1)
				return this.player2;
			else
				return this.player1;
		}
		else
			return activeplayer;
	}
	
	private void afterRoundDelay(int time)
	{

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		//letting player1 start
		Player activeplayer = this.player1;
		
		while(true)
		{
			if(!this.dice.getRoll() && !activeplayer.hasWon())
			{
				//getting value of rolled dice
				int randomVal = this.dice.getVal();
				
				//move player randomVal fields
				this.movePlayer(activeplayer, randomVal);
				
				//update GUI
				this.board.update_fields(activeplayer);
				
				//check if player stand under Ufo, if so move him again
				this.checkUfo(activeplayer);
				
				//starting nextround by changing current player
				activeplayer = this.nextPlayer(activeplayer);
			}
			else if(activeplayer.hasWon())
			{
				System.out.println("Player: " + activeplayer.getName() + " has won!");
				System.exit(0);
			}
			
			//short delay time after each round to prevent bugs in GUI
			this.afterRoundDelay(10);
		}
		//this.board.update_fields();
		//return 0;
	}
	
	private Gameboard board;
	private Dice dice;
	Player player1;
	Player player2;
	
	/*public Board board;
	public Dice dice;
	public Playerlist pl;*/
}
