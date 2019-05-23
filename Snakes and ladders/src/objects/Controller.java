package objects;

public class Controller {

	Controller(int boardwidth, int boardheigth)
	{
		this.init_controller(boardwidth, boardheigth);
	}
	
	gameboard board;
	
	public void init_controller(int boardwidth, int boardheigth)
	{
		System.out.println("starting game");
		this.board = new gameboard(boardwidth, boardheigth);
	}
	
	
	public int run()
	{
		
		return 0;
	}
	
	/*public Board board;
	public Dice dice;
	public Playerlist pl;*/
}
