package it.polimi.model;

import java.util.ArrayList;

public class GameModel {

    private Map map;
    private KillShotTrackPoint killShotTrackPoint;
    private ArrayList<Player> players;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;

    public Map getMap() {

        return map;
    }

    public KillShotTrackPoint getKillShotTrackPoint() {

        return killShotTrackPoint;
    }

    public ArrayList<Player> getPlayers() {

        return players;
    }

    public AmmoDeck getAmmoDeck() {

        return ammoDeck;
    }

    public PowerUpDeck getPowerUpDeck() {

        return powerUpDeck;
    }

    public WeaponDeck getWeaponDeck() {

        return weaponDeck;
    }
}
