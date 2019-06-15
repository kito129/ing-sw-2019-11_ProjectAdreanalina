package it.polimi.controller;


import it.polimi.model.GameModel;
import it.polimi.view.RemoteView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteGameController extends Remote {
    
    GameModel getGameModel() throws  RemoteException;
    
    void update(RemoteView view) throws RemoteException;
    
    void addObserver(RemoteView view) throws RemoteException;
    
}

