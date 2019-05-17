package it.polimi.model;

import java.util.ArrayList;

public interface RemoteGameModel{
    /**
    * Sets actual player.
    *
    * @param actualPlayer the actual player
    */
    public void setActualPlayer(Player actualPlayer);
        
        /**
         * Gets actual player.
         *
         * @return the actual player
         */
        public Player getActualPlayer();
        
        /**
         * Gets map.
         *
         * @return the map
         */
        public Map getMap();
        
        /**
         * Gets kill shot track point.
         *
         * @return the kill shot track point
         */
        public KillShotTrackPoint getKillShotTrackPoint();
        /**
         * Gets players.
         *
         * @return the players
         */
        public ArrayList<Player> getPlayers();
        
        /**
         * Gets ammo deck.
         *
         * @return the ammo deck
         */
        public AmmoDeck getAmmoDeck();
        
        /**
         * Gets power up deck.
         *
         * @return the power up deck
         */
        public PowerUpDeck getPowerUpDeck();
        
        /**
         * Gets weapon deck.
         *
         * @return the weapon deck
         */
        public WeaponDeck getWeaponDeck();
        
        public State getState ();
        
        public void setState (State state);
        
        public ArrayList<Player> getDeadPlayers();
    
}
