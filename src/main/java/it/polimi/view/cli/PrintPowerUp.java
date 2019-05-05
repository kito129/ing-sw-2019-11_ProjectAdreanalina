package it.polimi.view.cli;

import it.polimi.model.Player;
import it.polimi.model.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPowerUp implements Serializable {

    /**
     * Print Client's powerup.
     */
    public static void print(ArrayList<PowerUpCard> powerUpCards){
        for(int i=0; i<powerUpCards.size(); i++){
            PowerUpCard puc = powerUpCards.get(i);
            System.out.println("NAME: " +puc.getNameCard());
            System.out.println("COLOR: " +puc.getColorPowerUpCard());
        }

    }
}