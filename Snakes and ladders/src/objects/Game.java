package objects;

public class Game {

	Game(int boardwidth, int boardheigth, int boardsize)
	{
		this.init_game(boardwidth, boardheigth, boardsize);
	}
	
	gameboard board;
	
	public void init_game(int boardwidth, int boardheigth, int boardsize)
	{
		System.out.println("starting game");
		this.board = new gameboard(boardwidth, boardheigth, boardsize);
	}
	
	
	public int run()
	{
		this.board.update_fields();
		return 0;
	}
	
	/*public Board board;
	public Dice dice;
	public Playerlist pl;*/
}
