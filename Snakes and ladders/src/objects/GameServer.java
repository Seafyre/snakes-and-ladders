package objects;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class GameServer {

	public static void main(String[] args) {
		try {
			
			 Registry registry = LocateRegistry.createRegistry(1099);
	         // Instantiating the implementation class 
			 // Game game = new Game(800, 800, 100);
			 Game game = new Game(800, 800, 100, false);
	    
	         // Exporting the object of implementation class  
	         // (here we are exporting the remote object to the stub) 
	         GameInterface stub = (GameInterface) UnicastRemoteObject.exportObject(game, 0);  
	         
	         // Binding the remote object (stub) in the registry 
	         // Registry registry = LocateRegistry.getRegistry(); 
	         
	         registry.bind("GameInterface", stub);  
	         System.err.println("Server ready"); 
	      } catch (Exception e) { 
	         System.err.println("Server exception: " + e.toString()); 
	         e.printStackTrace(); 
	         }
		
	}
	
}