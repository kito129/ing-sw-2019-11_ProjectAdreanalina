package it.polimi.view;

import it.polimi.model.RemoteGameModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    
    
    int getIndex ();
    
    int getRow() throws RemoteException;
    
    int getColumn() throws RemoteException;
    
    String getUser() throws RemoteException;
    
    boolean getOnline() throws RemoteException;
    
    void update(RemoteGameModel gameModel) throws RemoteException;
    
}
