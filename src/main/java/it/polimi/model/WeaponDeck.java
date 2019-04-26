package it.polimi.model;

import java.util.ArrayList;

/**
 * The type Weapon deck.
 */
public class WeaponDeck extends Deck {

    private ArrayList<AmmoCard> armyDeck;
    
    /**
     * Gets army deck.
     *
     * @return the army deck
     */
    public ArrayList<AmmoCard> getArmyDeck() {

        return armyDeck;
    }
}
