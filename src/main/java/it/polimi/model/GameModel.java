package it.polimi.model;

import java.rmi.Remote;
import java.util.ArrayList;

/**
 * The type Game model.
 */
public class GameModel implements RemoteGameModel {

    private Map map;
    private KillShotTrack killShotTrack;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private Player actualPlayer;
    private State state;
    
    /**
     * Sets actual player.
     *
     * @param actualPlayer the actual player
     */
    public void setActualPlayer(Player actualPlayer) {

        this.actualPlayer = actualPlayer;
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
}
