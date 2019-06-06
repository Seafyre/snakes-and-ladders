package objects;
import java.rmi.Remote; 
import java.rmi.RemoteException; 


public interface GameInterface extends Remote {
	Player moveOnlinePlayer(Player player, int val) throws RemoteException;
}
