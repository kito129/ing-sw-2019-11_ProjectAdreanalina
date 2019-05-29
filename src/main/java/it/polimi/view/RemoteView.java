package it.polimi.view;

import it.polimi.model.EnumColorSquare;
import it.polimi.model.RemoteGameModel;
import it.polimi.model.WeaponsEffect;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    
    
    int getIndex ();

    int getChoicePlayer();

    int getRow() throws RemoteException;
    
    int getColumn() throws RemoteException;

    int getTarget1();

    int getTarget2();

    int getTarget3();

    int getTarget4();
    
    boolean isUseSecondEffect ();
    
    boolean isUseThirdEffect ();
    
    WeaponsEffect getWeaponsEffect ();
    
    EnumColorSquare getColorRoom();

    String getUser() throws RemoteException;
    
    boolean getOnline() throws RemoteException;

    void reserInput () throws RemoteException;
    
    void update(RemoteGameModel gameModel) throws RemoteException;
    
}
