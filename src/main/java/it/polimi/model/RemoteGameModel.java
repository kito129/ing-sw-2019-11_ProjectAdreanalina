package it.polimi.model;

import it.polimi.view.RemoteView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface RemoteGameModel{
    
    /**
    * Sets actual player.
    *
    * @param actualPlayer the actual player
    */
    void setActualPlayer(Player actualPlayer);
        
    /**
     * Gets actual player.
     *
     * @return the actual player
     */
    Player getActualPlayer();
    
    /**
     * Gets map.
     *
     * @return the map
     */
    Map getMap();
    
    /**
     * Gets kill shot track point.
     *
     * @return the kill shot track point
     */
    KillShotTrack getKillShotTrack();
    
    /**
     * Gets players.
     *
     * @return the players
     */
    ArrayList<Player> getPlayers();
    
    /**
     * Gets ammo deck.
     *
     * @return the ammo deck
     */
    AmmoDeck getAmmoDeck();
    
    /**
     * Gets power up deck.
     *
     * @return the power up deck
     */
     PowerUpDeck getPowerUpDeck();
    
    /**
     * Gets weapon deck.
     *
     * @return the weapon deck
     */
    WeaponDeck getWeaponDeck();
    
    /**
     * adds again an RMI observer after he has lost connection
     * @param observer the observer to be added
     * @throws RemoteException if the reference could not be accessed
     */
    void reAddObserver(RemoteView observer) throws RemoteException;
    
    /**
     * gets the list of RMI observers in game
     * @return the list of RMI observers in game
     * @throws RemoteException if the reference could not be accessed
     */
    List<RemoteView> getObservers() throws RemoteException;
    
    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     * @throws RemoteException if the reference could not be accessed
     */
    void addObserver(RemoteView observer) throws RemoteException;
    
    /**
     * removes an RMI observer from the observers's list (setting him as 'null')
     * @param observer the observer to be removed
     * @throws RemoteException if the reference could not be accessed
     */
    void removeObserver(RemoteView observer) throws RemoteException;
    
    /**
     * gets the actual state
     * @return the actual state
     * @throws RemoteException if the reference could not be accessed
     */
    State getState() throws RemoteException;
    

}
