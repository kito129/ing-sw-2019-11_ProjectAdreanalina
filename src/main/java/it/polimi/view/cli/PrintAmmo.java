package it.polimi.view.cli;

import it.polimi.model.AmmoCard;
import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintAmmo implements Serializable {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Print Client's ammo.
     * @param ammoCards   the player who has those ammo
     */
    public static void print(ArrayList<AmmoCard> ammoCards) {

        for(AmmoCard a : ammoCards){

            if(a.getAmmo().equals(EnumColorCardAndAmmo.BLU)){

                System.out.print(ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(a.getAmmo().equals(EnumColorCardAndAmmo.RED)){

                System.out.print(ANSI_RED_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(a.getAmmo().equals(EnumColorCardAndAmmo.YELLOW)){

                System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }
        }
    }
}

