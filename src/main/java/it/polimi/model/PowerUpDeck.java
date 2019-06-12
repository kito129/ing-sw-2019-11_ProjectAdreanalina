package it.polimi.model;

import it.polimi.model.PowerUp.Newton;
import it.polimi.model.PowerUp.TagBackGrenade;
import it.polimi.model.PowerUp.TargetingScope;
import it.polimi.model.PowerUp.Teleporter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Power up deck.
 */
public class PowerUpDeck implements Serializable {

    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<PowerUpCard> discardedCards;

    public PowerUpDeck() {

        PowerUpCard newtonY = new Newton(EnumColorCardAndAmmo.YELLOW);
        PowerUpCard newtonB = new Newton(EnumColorCardAndAmmo.BLU);
        PowerUpCard newtonR = new Newton(EnumColorCardAndAmmo.RED);
        PowerUpCard grenadeY = new TagBackGrenade(EnumColorCardAndAmmo.YELLOW);
        PowerUpCard grenadeB = new TagBackGrenade(EnumColorCardAndAmmo.BLU);
        PowerUpCard grenadeR = new TagBackGrenade(EnumColorCardAndAmmo.RED);
        PowerUpCard targetingY = new TargetingScope(EnumColorCardAndAmmo.YELLOW);
        PowerUpCard targetingB = new TargetingScope(EnumColorCardAndAmmo.BLU);
        PowerUpCard targetingR = new TargetingScope(EnumColorCardAndAmmo.RED);
        PowerUpCard teleporterY = new Teleporter(EnumColorCardAndAmmo.YELLOW);
        PowerUpCard teleporterB = new Teleporter(EnumColorCardAndAmmo.BLU);
        PowerUpCard teleporterR = new Teleporter(EnumColorCardAndAmmo.RED);
        powerUpCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        this.powerUpCards.add(newtonY);
        this.powerUpCards.add(newtonY);
        this.powerUpCards.add(newtonB);
        this.powerUpCards.add(newtonB);
        this.powerUpCards.add(newtonR);
        this.powerUpCards.add(newtonR);
        this.powerUpCards.add(grenadeY);
        this.powerUpCards.add(grenadeY);
        this.powerUpCards.add(grenadeB);
        this.powerUpCards.add(grenadeB);
        this.powerUpCards.add(grenadeR);
        this.powerUpCards.add(grenadeR);
        this.powerUpCards.add(targetingY);
        this.powerUpCards.add(targetingY);
        this.powerUpCards.add(targetingB);
        this.powerUpCards.add(targetingB);
        this.powerUpCards.add(targetingR);
        this.powerUpCards.add(targetingR);
        this.powerUpCards.add(teleporterY);
        this.powerUpCards.add(teleporterY);
        this.powerUpCards.add(teleporterB);
        this.powerUpCards.add(teleporterB);
        this.powerUpCards.add(teleporterR);
        this.powerUpCards.add(teleporterR);
        Collections.shuffle(this.powerUpCards);
    }

    public PowerUpCard drawnPowerUpCard() {

        if (powerUpCards.size() != 0) {

            PowerUpCard firstCard = powerUpCards.get(0);
            discardedCards.add(firstCard);
            powerUpCards.remove(0);
            return firstCard;
        } else {

            powerUpCards.addAll(discardedCards);
            Collections.shuffle(powerUpCards);
            discardedCards.clear();
            PowerUpCard firstCard = powerUpCards.get(0);
            discardedCards.add(firstCard);
            powerUpCards.remove(0);
            return firstCard;
        }
    }
}
