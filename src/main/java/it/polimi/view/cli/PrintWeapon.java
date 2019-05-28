package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintWeapon implements Serializable {

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    /**
     * Print Client's weapons.
     * @param weaponList       list of weapon.
     */
    public static void print(ArrayList<WeaponCard> weaponList){

        for(WeaponCard wc : weaponList) {

            System.out.println();
            System.out.println("NAME: " + wc.getNameWeaponCard());

            if(wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.BLU)){

                    System.out.println("COLOR: " + ANSI_BLUE_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.RED)){

                System.out.println("COLOR: " + ANSI_RED_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            if(wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.YELLOW)){

                System.out.println("COLOR: " + ANSI_YELLOW_BACKGROUND + "  " + ANSI_BLACK_BACKGROUND + " ");
            }

            System.out.print("RECHARGE COST: ");
            PrintRechargeCost.print(weaponList);
            System.out.println("LOADED WEAPON: " + wc.isCharge());
            System.out.println("DESCRIPTION: ");
            System.out.println(wc.getDescription());
        }
    }
}