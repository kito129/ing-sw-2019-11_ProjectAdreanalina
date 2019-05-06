package it.polimi.view.cli;

import it.polimi.model.WeaponCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintWeapon implements Serializable {

    /**
     * Print Client's weapons.
     * @param weaponList       list of player's weapon.
     */
    public static void print(ArrayList<WeaponCard> weaponList){

        for(WeaponCard wc : weaponList) {
            System.out.println("NAME: " +wc.getNameWeaponCard());
            System.out.println("COLOR: " +wc.getColorWeaponCard());
            System.out.println("RECHARGE COST: " +wc.getRechargeCost());
            System.out.println("LOADED WEAPON: " +wc.isCharge());
        }
    }
}