package objects;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 

public class Client {
	private Client(int boardwidth, int boardheigth, int boardsize, boolean client) 
	{
		this.init(boardwidth, boardheigth, boardsize, client);
	}  
	
	private void init(int boardwidth, int boardheigth, int boardsize, boolean client)
	{
		this.dice = new Dice(200, 200);
		this.myPlayer = new Player(1, "Nick", "Green");
		this.board = new Gameboard(boardwidth, boardheigth, boardsize, this.myPlayer, client);
		
	}
	
	public void setGameboard(Gameboard board)
	{
		this.board = board;
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
	   
	   
	private Gameboard board;
	private Dice dice;
	private Player myPlayer;
	
	public static void main(String[] args) {  
	      try {  
	    	  
	    	 Client client1 = new Client(800, 800, 100, true);
	    	  
	         // Getting the registry 
	         Registry registry = LocateRegistry.getRegistry(null); 
	    
	         // Looking up the registry for the remote object 
	         GameInterface stub = (GameInterface) registry.lookup("GameInterface"); 
	         
	         // Calling the remote method using the obtained object 
	         while(true)
	         {
	        	 if(!client1.dice.getRoll())
	 			 {
	        		 //System.out.println(stub.run_online(client1.dice.getVal())); 
	        		 if(client1.myPlayer != null)
	        			 client1.myPlayer = (Player) stub.moveOnlinePlayer(client1.myPlayer, client1.dice.getVal());
	        		 else
	        			 System.out.println("is null");
	        		 while(client1.myPlayer == null)
	        		 {
	        			 System.out.println("waiting");
	        			 client1.afterRoundDelay(200);
	        		 }
	        		 if(client1.myPlayer != null)
	        			 client1.board.update_fields(client1.myPlayer);
	 			 }
	        	 client1.afterRoundDelay(10);
	         }
	         
	         // System.out.println("Remote method invoked"); 
	      } catch (Exception e) {
	         System.err.println("Client exception: " + e.toString()); 
	         e.printStackTrace(); 
	      } 
	   } 
}
