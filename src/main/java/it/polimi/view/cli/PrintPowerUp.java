package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPowerUp implements Serializable {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Print Client's powerup.
     * @param powerUpCards         list of player's poweUp.
     */
    public static void print(ArrayList<PowerUpCard> powerUpCards){
    
    
        for (int i = 0; i < powerUpCards.size(); i++) {
            
            PowerUpCard puc = powerUpCards.get(i);
        
            System.out.println("\n " + i +": ");
            System.out.println("POWER UP: " + puc.getNameCard());
            System.out.println("DESCRIPTION: ");
            System.out.println(puc.getNameCard());
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.BLU)) {
            
                System.out.println("COLOR: " + ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.RED)) {
            
                System.out.println("COLOR: " + ANSI_RED_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.YELLOW)) {
            
                System.out.println("COLOR: " + ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
            
        }
    }
}