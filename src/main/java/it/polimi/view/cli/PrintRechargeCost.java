package it.polimi.view.cli;

import it.polimi.model.WeaponCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintRechargeCost implements Serializable {

    /**
     * Print the colors correspondig to the right cost
     * @param weaponList      the list of weapon to view
     */
    public static void print(ArrayList<WeaponCard> weaponList){

        for(WeaponCard wc : weaponList){
            PrintEnumCardsAmmo.print(wc.getRechargeCost());
        }
    }
}
