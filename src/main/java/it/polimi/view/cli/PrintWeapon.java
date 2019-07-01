package it.polimi.view.cli;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;
import it.polimi.model.WeaponsEffect;

import java.io.Serializable;
import java.util.ArrayList;

public class PrintWeapon implements Serializable {

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001b[0m";

    /**
     * Print Client's weapons.
     * @param weaponList       list of weapon.
     */
    public static void printList (ArrayList<WeaponCard> weaponList, boolean wantDescription){
        
        if (weaponList.size()>0) {
    
            for (int i = 0; i < weaponList.size(); i++) {
                WeaponCard wc = weaponList.get(i);

                System.out.println();
                System.out.println(i + "): ");
                System.out.println("WEAPON:\t\t    " + wc.getNameWeaponCard());
        
                if (wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.BLU)) {
            
                    System.out.println("COLOR:\t\t    " + ANSI_BLUE + "BLUE" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
                }
        
                if (wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.RED)) {
            
                    System.out.println("COLOR:\t\t    " + ANSI_RED + "RED" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
                }
        
                if (wc.getColorWeaponCard().equals(EnumColorCardAndAmmo.YELLOW)) {
            
                    System.out.println("COLOR:\t\t    " + ANSI_YELLOW + "YELLOW" + ANSI_RESET + ANSI_BLACK_BACKGROUND + " ");
                }
        
                System.out.print("RECHARGE COST:\t    ");
                PrintEnumCardsAmmo.print(wc.getRechargeCost());
        
                System.out.println("\nLOADED:\t\t    " + wc.isCharge());
        
                if (wantDescription) {

                    System.out.println("DESCRIPTION: ");
                    System.out.println(wc.getDescription());
                }
            }
        } else {

            System.out.println();
            System.out.println("WEAPON LIST EMPTY");
        }
    }
    

    /**
     * Print Client's weapons.
     * @param weaponList       list of weapon.
     */
    public static void printName(ArrayList<WeaponCard> weaponList){

        System.out.println();
        for (int i = 0; i < weaponList.size(); i++) {

            WeaponCard wc = weaponList.get(i);
            System.out.println(i + ") " + wc.getNameWeaponCard());
        }
    }

    /**
     * Print Client's effect weapons.
     * @param weaponEffects       list of weapon effects.
     */
    public static void printEffectName(ArrayList<WeaponsEffect> weaponEffects){
        
        
        System.out.println();
        for (int i = 0; i < weaponEffects.size(); i++) {

            WeaponsEffect we = weaponEffects.get(i);
            System.out.println(i + ")" + we.toString());
        }
    }
}