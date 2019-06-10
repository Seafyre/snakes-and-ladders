package objects;

import java.rmi.RemoteException;
import java.sql.Time;

import javax.swing.SwingUtilities;

public class Game implements GameInterface {

	Game(int boardwidth, int boardheigth, int boardsize, boolean client)
	{
		this.init_game(boardwidth, boardheigth, boardsize, client);
	}
	
	public void init_game(int boardwidth, int boardheigth, int boardsize, boolean client)
	{
		
		this.player1 = new Player(1, "Nick", "Green");
		this.player2 = new Player(2, "Sebastian", "Blue");
		//letting player1 start
		this.activeplayer = this.player1;
		this.roundfinished = false;
		
		System.out.println("starting game");
		if(client)
			this.dice = new Dice(200, 200);
		this.board = new Gameboard(boardwidth, boardheigth, boardsize, this.activeplayer, client);
		
	}
	
	//check if the acitveplayer is standing on an ufo source field, if so move him up
	private void checkUfo(Player activeplayer)
	{
		if(!activeplayer.hasWon())
		{
			if(board.getField(activeplayer.getPosition()).getUfoSrcDest() == 2)
			{
				System.out.println("Player " + Integer.toString(activeplayer.getId()) + " hit a ufo");
				activeplayer.move(20);
			}
		}
	}
	
	//check if the acitveplayer is standing on an wormhole source field, if so move him down
	private void checkWormhole(Player activeplayer)
	{
		if(!activeplayer.hasWon())
		{
			if(board.getField(activeplayer.getPosition()).getWormSrcDest() == 2)
			{
				System.out.println("Player " + Integer.toString(activeplayer.getId()) + " hit a wormhole");
				activeplayer.move(-20);
			}
		}
	}
	
	private void movePlayer(Player activeplayer, int randomVal)
	{
		if(activeplayer.getPosition() + randomVal < this.board.getSize())
		{
			for(int i = 0; i < randomVal; i++)
			{
				activeplayer.move(1);
				this.board.update_fields(activeplayer);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		
		while(true)
		{
			if(!this.dice.getRoll() && !activeplayer.hasWon())
			{
				//getting value of rolled dice
				int randomVal = this.dice.getVal();
				
				//move player randomVal fields
				this.movePlayer(activeplayer, randomVal);
				
				//check if player stands on an ufo/wormhole, if so move him again
				this.checkUfo(activeplayer);
				this.checkWormhole(activeplayer);
				
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

	@Override
	public int turn() throws RemoteException
	{
		return this.whosturn;
	}
	
	@Override
	public Player moveOnlinePlayer(Player player, int val) throws RemoteException {
		// TODO Auto-generated method stub
		
		
		//player.move(val);
		
		System.out.println("Player position is: " + Integer.toString(this.board.getField(player.getPosition()).getUfoSrcDest()));
		
		//error here
		if(!player.hasWon())
		{
			if(board.getField(player.getPosition() + val).getUfoSrcDest() == 2)
			{
				System.out.println("Player " + Integer.toString(player.getId()) + " hit a ufo");
				player.move(val + 20);
			}
			else if(board.getField(player.getPosition() + val).getWormSrcDest() == 2)
			{
				System.out.println("Player " + Integer.toString(player.getId()) + " hit a wormhole");
				player.move(val - 20);
			}
			else
			{
				player.move(val);
			}
		}
		
		
		this.activeplayer = player;
		
		this.roundfinished = true;
		return player;
		/*this.activeplayer = player;
		this.movePlayer(activeplayer, val);
		return this.activeplayer;*/
	}
	
	@Override
	public boolean roundFinished() throws RemoteException
	{
		return this.roundfinished;
	}
	
	@Override
	public Player getOtherPlayer() throws RemoteException
	{
		this.roundfinished = false;
		if(this.activeplayer.getId() == 1)
			this.whosturn = 2;
		else
			this.whosturn = 1;
		return this.activeplayer;
	}
	
	//getter methods
	Gameboard getBoard()
	{
		return this.board;
	}
	
	private Gameboard board;
	private Dice dice;
	Player player1;
	Player player2;
	Player activeplayer;
	private int whosturn;
	private boolean roundfinished;
	
	
	
	/*public Board board;
	public Dice dice;
	public Playerlist pl;*/
}
