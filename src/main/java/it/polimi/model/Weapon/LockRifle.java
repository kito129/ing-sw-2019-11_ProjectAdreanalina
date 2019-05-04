package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.InvalidActionForThisCard;

import java.util.ArrayList;

public class LockRifle extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo>secondLockCost;

    /**
     * Instantiates a new Lock Rifle card.
     * Creates the list of recharge cost settings its value to BLU,BLU.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of second lock cost(cost of optional effect 1) settings it to RED.
     */
    public LockRifle(){

        super("LOCK RIFLE", EnumColorCardAndAmmo.BLU);
        rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        secondLockCost=new ArrayList<EnumColorCardAndAmmo>();
        secondLockCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getSecondLockCost() {

        return secondLockCost;
    }

    private void baseEffect(Map map, Player currentPlayer, Player target) throws InvalidActionForThisCard {

        if(map.isVisible(currentPlayer,target)){

            ArrayList<EnumColorPlayer> lockRifleDamages= new ArrayList<EnumColorPlayer>();
            lockRifleDamages.add(currentPlayer.getColor());
            lockRifleDamages.add(currentPlayer.getColor());
            target.multipleDamagesSingleMark(lockRifleDamages,currentPlayer.getColor());
        }else{

            throw new InvalidActionForThisCard();
        }
    }

    private void secondLock(Map map, Player currentPlayer, Player target)throws InvalidActionForThisCard {

        if (map.isVisible(currentPlayer, target)){

            target.singleMark(currentPlayer.getColor());
        }else{

            throw new InvalidActionForThisCard();
        }
    }

    public void effect(Map map,Player currentPlayer,Player baseTarget,Player optionalTarget,boolean useSecondLock)throws InvalidActionForThisCard{

        if(useSecondLock){

            if(baseTarget!=optionalTarget){

                baseEffect(map, currentPlayer, baseTarget);
                secondLock(map, currentPlayer, optionalTarget);
            }else{

                throw new InvalidActionForThisCard();
            }
        }else{

            baseEffect(map,currentPlayer,baseTarget);
        }
    }





}
