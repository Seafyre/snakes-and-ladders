package objects;
import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry; 

public class Client2 {
	private Client2(int boardwidth, int boardheigth, int boardsize, boolean client) 
	{
		this.init(boardwidth, boardheigth, boardsize, client);
	}  
	
	private void init(int boardwidth, int boardheigth, int boardsize, boolean client)
	{
		this.dice = new Dice(200, 200);
		this.myPlayer = new Player(2, "Sebastian", "Blue");
		this.otherPlayer = new Player(1, "Nick", "Green");
		this.board = new Gameboard(boardwidth, boardheigth, boardsize, this.otherPlayer, client);
		this.myturn = false;
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
	private Player otherPlayer;
	private boolean myturn;
	
	public static void main(String[] args) {  
	      try {  
	    	  
	    	 Client2 client2 = new Client2(800, 800, 100, true);
	    	  
	         // Getting the registry 
	         Registry registry = LocateRegistry.getRegistry(null); 
	    
	         // Looking up the registry for the remote object 
	         GameInterface stub = (GameInterface) registry.lookup("GameInterface"); 
	         
	         // Calling the remote method using the obtained object 
	         while(true)
	         {
	        	 if(client2.myturn == true && !client2.myPlayer.hasWon())
	 			 {
	        		 //System.out.println(stub.run_online(client2.dice.getVal())); 
	        		 if(!client2.dice.getRoll())
	        		 {
	        			 client2.dice.setDisabled(true);
		        		client2.myturn = false;
	        			client2.myPlayer = (Player) stub.moveOnlinePlayer(client2.myPlayer, client2.dice.getVal());
		        		 
		        		 
		        		client2.board.update_fields(client2.myPlayer);
	        		 }
	 			 }
	        	 else if(client2.myturn == false)
	        	 {
	        		 if(stub.roundFinished())
	        			 client2.board.update_fields(stub.getOtherPlayer());
	        		 if(stub.turn() == 2)
	        		 {
	        			 client2.myturn = true;
	        			 client2.dice.setDisabled(false);
	        		 }
	        		 //client2.afterRoundDelay(500);
	        		 //System.out.println("client2 waiting...");
	        	 }
	        	 if(client2.myPlayer.hasWon() || client2.otherPlayer.hasWon())
	        	 {
	        		 if(client2.myPlayer.hasWon())
	        			 System.out.println("you won");
	        		 else
	        			 System.out.println("enemy won");
	        		 
	        		 break;
	        	 }
	        	 client2.afterRoundDelay(10);
	         }
	         
	         // System.out.println("Remote method invoked"); 
	      } catch (Exception e) {
	         System.err.println("Client exception: " + e.toString()); 
	         e.printStackTrace(); 
	      } 
	   } 
}
