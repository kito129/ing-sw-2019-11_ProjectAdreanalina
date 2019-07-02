package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintAmmo implements Serializable {

    public static void print(ArrayList<EnumColorCardAndAmmo> enumColorCardAndAmmos,int i){

        System.out.println();
        if (i==1) {
            System.out.println("YOUR AMMO:");
        } else if (i==2){
            System.out.println("COST EFFECT:");
        }
        PrintEnumCardsAmmo.print(enumColorCardAndAmmos);
    }
}
