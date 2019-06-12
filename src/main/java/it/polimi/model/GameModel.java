package it.polimi.model;

import it.polimi.model.Exception.MapException;
import it.polimi.view.RemoteView;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type GameModel model.
 */
public class GameModel implements Serializable {

    //capire quali classi vanno serializzate
    
    //observable pattern
    private ArrayList<RemoteView> remoteViews = new ArrayList<>();
    private Map map;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private Player actualPlayer;
    private State state;
    private WeaponState extraState;
    private WeaponsEffect weaponsEffect;
    private String errorMessage;
    
    public GameModel(){
        
        state=State.LOBBY;
    
        this.killShotTrack= new KillShotTrack();
        this.ammoDeck = new AmmoDeck();
        this.powerUpDeck=new PowerUpDeck();
        this.weaponDeck=new WeaponDeck();
    }
    

    
    
    /**
     * Gets actual player.
     *
     * @return the actual player
     */
    public Player getActualPlayer() {

        return actualPlayer;
    }

    public String getErrorMessage() {

        return errorMessage;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public Map getMap() {

        return map;
    }
    
    
    /**
     * Gets kill shot track .
     *
     * @return the kill shot track
     */
    public KillShotTrack getKillShotTrack() {

        return killShotTrack;
    }
    
    /**
     * Gets players.
     *
     * @return the players
     */

    public ArrayList<Player> getPlayers(boolean wantCurrent) {
        
        if (wantCurrent) {

            return players;        
        } else
        {
            ArrayList<Player> temp =new ArrayList<Player>(players);
            temp.remove(getActualPlayer());
            return temp;
        }

    }
    
    /**
     * Gets ammo deck.
     *
     * @return the ammo deck
     */
    public AmmoDeck getAmmoDeck() {

        return ammoDeck;
    }
    
    /**
     * Gets power up deck.
     *
     * @return the power up deck
     */
    public PowerUpDeck getPowerUpDeck() {

        return powerUpDeck;
    }
    
    /**
     * Gets weapon deck.
     *
     * @return the weapon deck
     */
    public WeaponDeck getWeaponDeck() {

        return weaponDeck;
    }
    
    public State getState () {
        
        return state;
    }
    

    public void setState (State state) throws RemoteException {
        
        this.state=state;
        notifyObserver1();
    }
    
    public WeaponState getExtraState () {
        
        return extraState;
    }
    
    public WeaponsEffect getWeaponsEffect () {
        
        return weaponsEffect;
    }
    
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    /**
     * Sets actual player.
     *
     * @param actualPlayer the actual player
     */
    public void setActualPlayer(Player actualPlayer) {
        
        this.actualPlayer = actualPlayer;
    }
    
    
    public ArrayList<Player> getDeadPlayers(){
        ArrayList<Player> tempPLayers = new ArrayList<>();
        for (Player a:players){
            if(!a.isAlive()){
                tempPLayers.add(a);
            }
        }
        return tempPLayers;
    }
    
    public void setExtraState (WeaponState extraState) {
        
        this.extraState = extraState;
    }
    
    public void setWeaponsEffect (WeaponsEffect weaponsEffect) {
        
        this.weaponsEffect = weaponsEffect;
    }
    
    public ArrayList<EnumColorPlayer> getPlayerColor(){
        
        ArrayList<EnumColorPlayer> playerColor = new ArrayList<>();
        
        for (Player a: getPlayers(true)){
            playerColor.add(a.getColor());
        }
        return playerColor;
    }
    
    public Player getPlayerByColor(EnumColorPlayer color){
        
        for (Player a:players){
            if (a.getColor()==color){
                return a;
            }
        }
        return null;
    }


    public List<RemoteView> getRemoteView(){

        return remoteViews;
    }
    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     */

    public void addObserver(RemoteView observer){
        
        remoteViews.add(observer);
    }
    
    /**
     * removes an RMI observer from the observers's remoteViews (setting him as 'null')
     * @param observer the observer to be removed
     */

    public void removeObserver(RemoteView observer){
        
        remoteViews.set(getRemoteView().indexOf(observer), null);
    }
    
    /**
     * adds again an RMI observer after he has lost connection
     * @param observer the observer to be added
     * @throws RemoteException if the reference could not be accessed
     */

    public void reAddObserver(RemoteView observer) throws RemoteException {
        int index = 0;
        for(Player a : players){
            if((a.getName()).equals(observer.getUser())) {
                index = players.indexOf(a);
                break;
            }
        }
        remoteViews.set(index, observer);
    }

    public void notifyObserver1(){


        for(RemoteView observer: getRemoteView()){

            try {
                observer.update(this);

            } catch (RemoteException e) {

            }
        }
    }
    
    public void notifyObserver (GameModel gameModel){
            try {
                int tmp = 0;
                for(RemoteView observer: getRemoteView()) {
                    if(observer!=null) {
                        if (true) {
                            if (true) {
                                observer.update(gameModel);
                            }
                        } else {
                            tmp = getRemoteView().indexOf(observer);
                        }
                    }
                }
            } catch (RemoteException e) {
                //do nothing
            }
    }

    public Player getPlayerById(int i) throws MapException {

        for (Player a : this.players){

            if(a.getId()==i){
                
                return a;
            }
        }
    throw  new MapException();
    }


}
