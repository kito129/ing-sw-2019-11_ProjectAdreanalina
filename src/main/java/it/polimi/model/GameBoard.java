package it.polimi.model;

import java.util.ArrayList;

public class GameBoard {

    private KillShotTrack killShotTrack;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private ArmyDeck armyDeck;
    private ArrayList<Player> players;
    private Map map;

    public AmmoDeck getAmmoDeck() {
        return ammoDeck;
    }

    public ArmyDeck getArmyDeck() {
        return armyDeck;
    }

    public KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public PowerUpDeck getPowerUpDeck() {
        return powerUpDeck;
    }

    public Map getMap() {

        return map;
    }
}
