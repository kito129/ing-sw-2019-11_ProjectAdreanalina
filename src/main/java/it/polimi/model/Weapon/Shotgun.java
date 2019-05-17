package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Shotgun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> longBarrelModeCost;

    /**
     * Instantiates a new Shotgun card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,YELLOW.
     * Creates the list of effects setting its value to BaseMode,LongBarrelMode.
     * Creates the list of long barrel mode cost (cost of alternative fire mode) settings it to null.
     */
    public Shotgun() {

        super("SHOTGUN", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.LongBarrelMode);
        setWeaponEffects(weaponEffects);
        longBarrelModeCost = new ArrayList<EnumColorCardAndAmmo>();
        longBarrelModeCost.add(null);
    }

    public ArrayList<EnumColorCardAndAmmo> getLongBarrelModeCost() {

        return longBarrelModeCost;
    }

    //todo mettere la funzione di spostamento

    public void baseMode(Map map, Player currentPlayer, Player target1)throws NotVisibleTarget {

        if (map.distance(currentPlayer,target1)==0){

            ArrayList<EnumColorPlayer> shotGunDamages=new ArrayList<>();
            shotGunDamages.add(currentPlayer.getColor());
            shotGunDamages.add(currentPlayer.getColor());
            shotGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(shotGunDamages);
        }else{

            throw new NotVisibleTarget();
        }
    }

    public void longBarrelMode(Map map, Player currentPlayer, Player target1) throws NotValidDistance {

        if(map.distance(currentPlayer,target1)==1){

            ArrayList<EnumColorPlayer> shotGunDamages= new ArrayList<>();
            shotGunDamages.add(currentPlayer.getColor());
            shotGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(shotGunDamages);
        }else {

            throw new NotValidDistance();
        }

    }
}
