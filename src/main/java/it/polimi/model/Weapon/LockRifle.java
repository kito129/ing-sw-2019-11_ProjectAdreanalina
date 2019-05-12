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
     * Creates the list of second lock cost(cost of optional effect 1) settings it to RED.
     */
    public LockRifle(){

        super("LOCK RIFLE", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        secondLockCost=new ArrayList<EnumColorCardAndAmmo>();
        secondLockCost.add(EnumColorCardAndAmmo.RED);
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


    //todo gestire il fatto che target 2 deve essere diverso da target 1

    public void secondLockEffect(Map map, Player currentPlayer, Player target2)throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, target2)){

            target2.singleMark(currentPlayer.getColor());
        }else{

            throw new NotVisibleTarget();
        }
    }

}
