package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintAmmo implements Serializable {

    public static void print(ArrayList<EnumColorCardAndAmmo> enumColorCardAndAmmos){

        System.out.println();
        System.out.println("YOUR AMMO:");
        PrintEnumCardsAmmo.print(enumColorCardAndAmmos);
    }
}
