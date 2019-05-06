package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Game model.
 */
public class GameModel {

    private Map map;
    private KillShotTrackPoint killShotTrackPoint;
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
     * Gets kill shot track point.
     *
     * @return the kill shot track point
     */
    public KillShotTrackPoint getKillShotTrackPoint() {

        return killShotTrackPoint;
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
}
