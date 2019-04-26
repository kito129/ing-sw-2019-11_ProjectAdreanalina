package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Power up deck.
 */
public class PowerUpDeck extends Deck {

    private ArrayList<PowerUpCard> powerUpDeck;
    
    /**
     * Gets power up deck.
     *
     * @return the power up deck
     */
    public ArrayList<PowerUpCard> getPowerUpDeck() {

        return powerUpDeck;
    }
}
