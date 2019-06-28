package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.PowerUpCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintPowerUp implements Serializable {

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001b[0m";

    /**
     * Print Client's powerup.
     * @param powerUpCards         list of player's poweUp.
     */
    public static void print(ArrayList<PowerUpCard> powerUpCards,boolean wantDescription){
    
    
        for (int i = 0; i < powerUpCards.size(); i++) {
            
            PowerUpCard puc = powerUpCards.get(i);
        
            System.out.println("\n" + i +"): ");
            System.out.println("POWER UP:\t    " + puc.getNameCard());
            if(wantDescription) {
                System.out.println("DESCRIPTION:");
                System.out.println("\t" + puc.getDescription());
            }
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.BLU)) {
            
                System.out.println("COLOR:\t\t    " + ANSI_BLUE + "BLUE" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
            }
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.RED)) {
            
                System.out.println("COLOR:\t\t    " + ANSI_RED + "RED" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
            }
        
            if (puc.getColorPowerUpCard().equals(EnumColorCardAndAmmo.YELLOW)) {
            
                System.out.println("COLOR:\t\t    " + ANSI_YELLOW + "YELLOW" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
            }
        }
    }
}