package it.polimi.model;

import it.polimi.view.RemoteView;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Game model.
 */
public class GameModel implements RemoteGameModel {
    
    //observable pattern
    private List<RemoteView> list = new ArrayList<>();
    private Map map;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private Player actualPlayer;
    private State state;
    private State extraState;


    public GameModel(Map map,KillShotTrack killShotTrack,ArrayList<Player> players){

        this.map=map;
        this.killShotTrack=killShotTrack;
        this.players=players;
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
    public ArrayList<Player> getPlayers() {

        return players;
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
    
    public State getExtraState () {
        
        return extraState;
    }
    
    /**
     * Sets actual player.
     *
     * @param actualPlayer the actual player
     */
    public void setActualPlayer(Player actualPlayer) {
        
        this.actualPlayer = actualPlayer;
    }
    
    public void setState (State state) {
        
        this.state = state;
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
    
    public void setExtraState (State extraState) {
        
        this.extraState = extraState;
    }
    
    public ArrayList<EnumColorPlayer> getPlayerColor(){
        ArrayList<EnumColorPlayer> playerColor = new ArrayList<>();
        
        for (Player a: getPlayers()){
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
    
    @Override
    public List<RemoteView> getObservers (){
        return list;
    }
    /**
     * adds an RMI observer at the beginning
     * @param observer the observer to be added
     */
    @Override
    public void addObserver(RemoteView observer){
        list.add(observer);
    }
    
    /**
     * removes an RMI observer from the observers's list (setting him as 'null')
     * @param observer the observer to be removed
     */
    @Override
    public void removeObserver(RemoteView observer){
        
        list.set(getObservers().indexOf(observer), null);
    }
    
    /**
     * adds again an RMI observer after he has lost connection
     * @param observer the observer to be added
     * @throws RemoteException if the reference could not be accessed
     */
    @Override
    public void reAddObserver(RemoteView observer) throws RemoteException {
        int index = 0;
        for(Player a : players){
            if((a.getName()).equals(observer.getUser())) {
                index = players.indexOf(a);
                break;
            }
        }
        list.set(index, observer);
    }
}
