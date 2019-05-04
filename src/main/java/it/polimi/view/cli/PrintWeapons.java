package it.polimi.view.cli;

import it.polimi.model.Player;
import it.polimi.model.WeaponCard;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintWeapons implements Serializable {

    /**
     * Print Client's weapons.
     */
    public static void print(ArrayList<WeaponCard> weaponList){
        for(int i=0; i<weaponList.size(); i++){
            WeaponCard wc = weaponList.get();
            System.out.println("NAME: " +wc.getNameCard());
            System.out.println("COLOR: " +wc.getColorCard());
            System.out.println("RECHARGE COST: " +wc.getRechargeCost());
            System.out.println("LOADED WEAPON: " +wc.isCharge());
        }

    }
}