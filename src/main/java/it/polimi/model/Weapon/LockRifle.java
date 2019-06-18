package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;


import java.util.ArrayList;

public class LockRifle extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo>secondLockCost;

    /**
     * Instantiates a new Lock Rifle card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,BLU.
     * Creates the list of effects setting its value to BaseEffect,SecondLockEffect.
     * Creates the list of second lock cost(cost of optional effect 1) settings it to RED.
     */
    public LockRifle(){

        super("LOCK RIFLE", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.SecondLockEffect);
        setWeaponEffects(weaponEffects);
        secondLockCost=new ArrayList<EnumColorCardAndAmmo>();
        secondLockCost.add(EnumColorCardAndAmmo.RED);
        setOptional(true);
        setDescription("effetto base: Dai 2 danni e 1 marchio a 1 bersaglio che puoi vedere.\n\n" +
                "secondo aggancio: Dai 1 marchio a un altro bersaglio che puoi vedere.");
    }

    public ArrayList<EnumColorCardAndAmmo> getSecondLockCost() {

        return secondLockCost;
    }

    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {

        if(map.isVisible(currentPlayer,target1)){

            ArrayList<EnumColorPlayer> lockRifleDamages= new ArrayList<EnumColorPlayer>();
            lockRifleDamages.add(currentPlayer.getColor());
            lockRifleDamages.add(currentPlayer.getColor());
            target1.multipleDamagesSingleMark(lockRifleDamages,currentPlayer.getColor());
        }else{

            throw new NotVisibleTarget();
        }
    }

    public void secondLockEffect(Map map, Player currentPlayer, Player target2) throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, target2)){

            target2.singleMark(currentPlayer.getColor());
        }else{

            throw new NotVisibleTarget();
        }
    }

}
