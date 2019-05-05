package it.polimi.view.cli;

import it.polimi.model.WeaponCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintWeapons implements Serializable {

    /**
     * Print Client's weapons.
     */
    public static void print(ArrayList<WeaponCard> weaponList){
        for(int i=0; i<weaponList.size(); i++){
            WeaponCard wc = weaponList.get(i);
            System.out.println("NAME: " +wc.getNameWeaponCard());
            System.out.println("COLOR: " +wc.getColorWeaponCard());
            System.out.println("RECHARGE COST: " +wc.getRechargeCost());
            System.out.println("LOADED WEAPON: " +wc.isCharge());
        }

    }
}