package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintEnumCardsAmmo implements Serializable {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Print the color corresponding to right card
     * @param enumColorCardAndAmmos      the color of cards and ammo
     */
    public static void print(ArrayList<EnumColorCardAndAmmo> enumColorCardAndAmmos){

        for(EnumColorCardAndAmmo a : enumColorCardAndAmmos){

            switch(a){

                case BLU:
                    System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                    break;
                case RED:
                    System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                    break;
                case YELLOW:
                    System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
                    break;
            }
        }
    }
}