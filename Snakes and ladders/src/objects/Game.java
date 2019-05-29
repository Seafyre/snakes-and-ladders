package objects;

import java.sql.Time;

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
	
	
	public void run()
	{
		Player activeplayer = this.player1;
		while(true)
		{
			if(!dice.getRoll() && !activeplayer.hasWon())
			{
				int randomVal = this.dice.getVal();
				if(activeplayer.getPosition() + randomVal < this.board.getSize())
					activeplayer.move(randomVal);
				else
				{
					activeplayer.move(this.board.getSize());
					activeplayer.setWon();
				}
				
				this.board.update_fields(activeplayer);
				
				if(board.getField(activeplayer.getPosition()).getUfoSrcDest() == 2)
				{
					System.out.println("on ufo dest field");
					activeplayer.move(20);
					this.board.update_fields(activeplayer);
				}
				
				
				if(!activeplayer.hasWon())
				{
					if(activeplayer == this.player1)
						activeplayer = this.player2;
					else
						activeplayer = this.player1;
				}
			}
			else if(activeplayer.hasWon())
			{
				System.out.println("Player: " + activeplayer.getName() + " has won!");
				System.exit(0);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
