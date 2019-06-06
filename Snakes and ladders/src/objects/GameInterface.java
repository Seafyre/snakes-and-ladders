package objects;
import java.rmi.Remote; 
import java.rmi.RemoteException; 


public interface GameInterface extends Remote {
	int run_online(int val) throws RemoteException;
}
