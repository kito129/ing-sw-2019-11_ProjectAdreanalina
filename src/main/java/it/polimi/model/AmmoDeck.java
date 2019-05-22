package it.polimi.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Ammo deck.
 */
public class AmmoDeck {

    private ArrayList<AmmoCard> ammoCards;
    private ArrayList<AmmoCard> discardedCards;

    public AmmoDeck() {

        AmmoCard ammoCard1 = new AmmoCard(0, 1, 2, false);
        AmmoCard ammoCard2 = new AmmoCard(2, 1, 0, false);
        AmmoCard ammoCard3 = new AmmoCard(1, 0, 2, false);
        AmmoCard ammoCard4 = new AmmoCard(1, 2, 0, false);
        AmmoCard ammoCard5 = new AmmoCard(0, 2, 1, false);
        AmmoCard ammoCard6 = new AmmoCard(2, 0, 1, false);
        AmmoCard ammoCard7 = new AmmoCard(0, 1, 2, false);
        AmmoCard ammoCard8 = new AmmoCard(2, 1, 0, false);
        AmmoCard ammoCard9 = new AmmoCard(1, 0, 2, false);
        AmmoCard ammoCard10 = new AmmoCard(1, 2, 0, false);
        AmmoCard ammoCard11 = new AmmoCard(0, 2, 1, false);
        AmmoCard ammoCard12 = new AmmoCard(2, 0, 1, false);
        AmmoCard ammoCard13 = new AmmoCard(0, 1, 2, false);
        AmmoCard ammoCard14 = new AmmoCard(2, 1, 0, false);
        AmmoCard ammoCard15 = new AmmoCard(1, 0, 2, false);
        AmmoCard ammoCard16 = new AmmoCard(1, 2, 0, false);
        AmmoCard ammoCard17 = new AmmoCard(0, 2, 1, false);
        AmmoCard ammoCard18 = new AmmoCard(2, 0, 1, false);
        AmmoCard ammoCard19 = new AmmoCard(0, 2, 0, true);
        AmmoCard ammoCard20 = new AmmoCard(2, 0, 0, true);
        AmmoCard ammoCard21 = new AmmoCard(0, 0, 2, true);
        AmmoCard ammoCard22 = new AmmoCard(1, 1, 0, true);
        AmmoCard ammoCard23 = new AmmoCard(0, 1, 1, true);
        AmmoCard ammoCard24 = new AmmoCard(1, 0, 1, true);
        AmmoCard ammoCard25 = new AmmoCard(1, 1, 0, true);
        AmmoCard ammoCard26 = new AmmoCard(0, 1, 1, true);
        AmmoCard ammoCard27 = new AmmoCard(1, 0, 1, true);
        AmmoCard ammoCard28 = new AmmoCard(0, 2, 0, true);
        AmmoCard ammoCard29 = new AmmoCard(2, 0, 0, true);
        AmmoCard ammoCard30 = new AmmoCard(0, 0, 2, true);
        AmmoCard ammoCard31 = new AmmoCard(1, 1, 0, true);
        AmmoCard ammoCard32 = new AmmoCard(0, 1, 1, true);
        AmmoCard ammoCard33 = new AmmoCard(1, 0, 1, true);
        AmmoCard ammoCard34 = new AmmoCard(1, 1, 0, true);
        AmmoCard ammoCard35 = new AmmoCard(0, 1, 1, true);
        AmmoCard ammoCard36 = new AmmoCard(1, 0, 1, true);
        ammoCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        this.ammoCards.add(ammoCard1);
        this.ammoCards.add(ammoCard2);
        this.ammoCards.add(ammoCard3);
        this.ammoCards.add(ammoCard4);
        this.ammoCards.add(ammoCard5);
        this.ammoCards.add(ammoCard6);
        this.ammoCards.add(ammoCard7);
        this.ammoCards.add(ammoCard8);
        this.ammoCards.add(ammoCard9);
        this.ammoCards.add(ammoCard10);
        this.ammoCards.add(ammoCard11);
        this.ammoCards.add(ammoCard12);
        this.ammoCards.add(ammoCard13);
        this.ammoCards.add(ammoCard14);
        this.ammoCards.add(ammoCard15);
        this.ammoCards.add(ammoCard16);
        this.ammoCards.add(ammoCard17);
        this.ammoCards.add(ammoCard18);
        this.ammoCards.add(ammoCard19);
        this.ammoCards.add(ammoCard20);
        this.ammoCards.add(ammoCard21);
        this.ammoCards.add(ammoCard22);
        this.ammoCards.add(ammoCard23);
        this.ammoCards.add(ammoCard24);
        this.ammoCards.add(ammoCard25);
        this.ammoCards.add(ammoCard26);
        this.ammoCards.add(ammoCard27);
        this.ammoCards.add(ammoCard28);
        this.ammoCards.add(ammoCard29);
        this.ammoCards.add(ammoCard30);
        this.ammoCards.add(ammoCard31);
        this.ammoCards.add(ammoCard32);
        this.ammoCards.add(ammoCard33);
        this.ammoCards.add(ammoCard34);
        this.ammoCards.add(ammoCard35);
        this.ammoCards.add(ammoCard36);
        Collections.shuffle(this.ammoCards);
    }

    public AmmoCard drawAmmoCard(PowerUpDeck powerUpDeck) {

        if (ammoCards.size() != 0) {

            AmmoCard firstCard = ammoCards.get(0);
            discardedCards.add(firstCard);
            ammoCards.remove(0);
            //fatto qui perchè comodo
            if(firstCard.hasPowerUpCard()){
                firstCard.setPowerUpCard(powerUpDeck.drawnPowerUpCard());
            }
            return firstCard;
        }else{

            ammoCards.addAll(discardedCards);
            Collections.shuffle(ammoCards);
            discardedCards.clear();
            AmmoCard firstCard=ammoCards.get(0);
            discardedCards.add(firstCard);
            ammoCards.remove(0);
            //fatto qui perchè comodo
            if(firstCard.hasPowerUpCard()){
                firstCard.setPowerUpCard(powerUpDeck.drawnPowerUpCard());
            }
            return firstCard;
        }
    }
}
