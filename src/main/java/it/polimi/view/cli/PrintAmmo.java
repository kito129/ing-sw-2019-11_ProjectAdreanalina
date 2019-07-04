package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintAmmo implements Serializable {

    /**
     * print player's ammo or cost effect
     * @param enumColorCardAndAmmos     a square of the map
     * @param i                         to know if is for player's ammo or for cost effect of a weapon
     */
    public static void print(ArrayList<EnumColorCardAndAmmo> enumColorCardAndAmmos,int i){

        if (i==1) {
            System.out.print("YOUR AMMO: ");
        } else if (i==2){
            System.out.print("COST EFFECT: ");
        }
        PrintEnumCardsAmmo.print(enumColorCardAndAmmos);
    }
}
