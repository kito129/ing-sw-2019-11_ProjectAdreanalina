package it.polimi.view.cli;

import it.polimi.model.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPowerUp implements Serializable {

    /**
     * Print Client's powerup.
     * @param powerUpCards         list of player's poweUp.
     */
    public static void print(ArrayList<PowerUpCard> powerUpCards){

        for(PowerUpCard puc : powerUpCards){
            System.out.println("NAME: " +puc.getNameCard());
            System.out.println("COLOR: " +puc.getColorPowerUpCard());
        }
    }
}