package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;


import java.util.ArrayList;

public class Cyberblade extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> shadowstepCost;
    private ArrayList<EnumColorCardAndAmmo> sliceAndDiceCost;

    /**
     * Instantiates a new Cyberblade card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to YELLOW,RED.
     * Creates the list of effects setting its value to BaseEffect,ShadowstepEffect,SliceAndDiceEffect.
     * Creates the list of shadow step cost(cost of optional effect 1) settings it to null.
     * Creates the list of slice and dice cost(cost of optional effect 2) settings it to YELLOW.
     */
    public Cyberblade(){

        super("CYBERBLADE", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo> rechargeCost =new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.ShadowstepEffect);
        weaponEffects.add(WeaponsEffect.SliceAndDiceEffect);
        setWeaponEffects(weaponEffects);
        shadowstepCost =new ArrayList<EnumColorCardAndAmmo>();
        shadowstepCost.add(null);
        sliceAndDiceCost = new ArrayList<EnumColorCardAndAmmo>();
        sliceAndDiceCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getShadowstepCost() {

        return shadowstepCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getSliceAndDiceCost() {

        return sliceAndDiceCost;
    }

    public void baseEffect(Map map,Player currentPlayer,Player target1)throws NotValidDistance{

        if(map.findPlayer(currentPlayer)==map.findPlayer(target1)){

            ArrayList<EnumColorPlayer> cyberbladeDamages=new ArrayList<>();
            cyberbladeDamages.add(currentPlayer.getColor());
            cyberbladeDamages.add(currentPlayer.getColor());
            target1.multipleDamages(cyberbladeDamages);
        }else {

            throw new NotValidDistance();
        }
    }
    // todo prima o dopo leffetto base.
    public void shadowstepEffect(Map map, Player currentPlayer, Square destSquare)throws NotValidDistance{

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        if(map.distance(squareOfCurrentPlayer,destSquare)==1){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    //todo target2 deve essere diverso da target1.il codice è lo steso dell effetto base.

    public void sliceAndDiceEffect()throws NotValidDistance{

    }







}
