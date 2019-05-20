package objects;

public class Controller {

	Controller()
	{
		this.init_controller();
	}
	
	gameboard board;
	
	public void init_controller()
	{
		System.out.println("starting game");
		this.board = new gameboard();
	}
	
	
	public int run()
	{
		
		return 0;
	}
	
	/*public Board board;
	public Dice dice;
	public Playerlist pl;*/
}
