package it.polimi.view.cli;

import it.polimi.model.PlayerBoard;
import it.polimi.model.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPowerUp implements Serializable {

    public static ArrayList<PowerUpCard> powerUpCards;

    /**
     * Print Client's powerup.
     */
    public static void print(){

        for(int i=0; i<powerUpCards.size(); i++){
            PowerUpCard puc = powerUpCards.get(i);
            System.out.println("NAME: " +puc.getNameCard());
            System.out.println("COLOR: " +puc.getColorPowerUpCard());
        }

    }

    /**
     * Print Client's powerup.
     * @param playerBoard    the player's playerBords
     */
    public static void print(PlayerBoard playerBoard){

        powerUpCards = playerBoard.getPlayerPowerUps();

        for(int i=0; i<powerUpCards.size(); i++){
            PowerUpCard puc = powerUpCards.get(i);
            System.out.println("NAME: " +puc.getNameCard());
            System.out.println("COLOR: " +puc.getColorPowerUpCard());
        }

    }
}