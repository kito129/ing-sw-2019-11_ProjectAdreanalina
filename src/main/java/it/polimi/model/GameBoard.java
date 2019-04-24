package it.polimi.model;

public class GameBoard {

    private KillShotTrack killShotTrack;
    private AmmoDeck ammoDeck;
    private PowerUpDeck powerUpDeck;
    private WeaponDeck weaponDeck;
    private Map map;

    public AmmoDeck getAmmoDeck() {

        return ammoDeck;
    }

    public WeaponDeck getWeaponDeck() {

        return weaponDeck;
    }

    public KillShotTrack getKillShotTrack() {

        return killShotTrack;
    }

    public PowerUpDeck getPowerUpDeck() {

        return powerUpDeck;
    }

    public Map getMap() {

        return map;
    }
}
