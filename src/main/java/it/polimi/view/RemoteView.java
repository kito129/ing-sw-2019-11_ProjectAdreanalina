package it.polimi.view;

import it.polimi.model.EnumCardinalDirection;
import it.polimi.model.EnumColorSquare;
import it.polimi.model.GameModel;
import it.polimi.model.WeaponsEffect;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {
    
    
    //void setOnline (boolean online) throws RemoteException;
    
    int getIndex () throws RemoteException;
    
    int getIndex3 ()throws RemoteException;;
    
    int getIndex4 ()throws RemoteException;;
    
    int getIndex5 ()throws RemoteException;;
    
    int getChoicePlayer() throws RemoteException;

    int getChoicePlayer2() throws RemoteException;

    int getChoicePlayer3() throws RemoteException;

    int getRow() throws RemoteException;

    int getRow2() throws RemoteException;

    int getColumn() throws RemoteException;

    int getColumn2() throws RemoteException;

    int getTarget1() throws RemoteException;

    int getTarget2() throws RemoteException;

    int getTarget3() throws RemoteException;

    int getTarget4() throws RemoteException;
    
    boolean isUseSecondEffect () throws RemoteException;
    
    boolean isUseThirdEffect () throws RemoteException;
    
    WeaponsEffect getWeaponsEffect () throws RemoteException;
    
    boolean isOptionWeapon () throws RemoteException;
    
    EnumColorSquare getColorRoom() throws RemoteException;

    EnumCardinalDirection getCardinalDirection() throws RemoteException;

    String getUser() throws RemoteException;
    
    boolean isBooleanChose () throws RemoteException;
    
    int getIndex2 () throws RemoteException;
    
    void resetInput() throws RemoteException;
    
    void update(GameModel gameModel) throws RemoteException;

    void pingToClient()throws RemoteException;

    boolean getOnline() throws RemoteException;
    
}
