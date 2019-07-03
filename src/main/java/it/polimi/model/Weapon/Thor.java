package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

/**
 * The type Thor.
 */
public class Thor extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> chainReactionCost;
    private ArrayList<EnumColorCardAndAmmo> highVoltageCost;
    
    /**
     * Instantiates a new Thor card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Creates the list of effects setting its value to BaseEffect,ChainReactionEffect,HighVoltageEffect.
     * Creates the list of chain reaction cost(cost of optional effect1) settings it to blu.
     * Creates the list of high voltage cost(cost of optional effect2) settings it to blu.
     */
    public Thor() {

        super("T.H.O.R", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.ChainReactionEffect);
        weaponEffects.add(WeaponsEffect.HighVoltageEffect);
        setWeaponEffects(weaponEffects);
        chainReactionCost = new ArrayList<EnumColorCardAndAmmo>();
        chainReactionCost.add(EnumColorCardAndAmmo.BLU);
        highVoltageCost = new ArrayList<EnumColorCardAndAmmo>();
        highVoltageCost.add(EnumColorCardAndAmmo.BLU);
        setOptional(true);
        setDescription("Basic Effect: Deal 2 damage to 1 target you can see.\n" +
                "with Chain Reaction: Deal 1 damage to a second target that your first target can see.\n" +
                "with High Voltage: Deal 2 damage to a third target that your second target can see. You cannot use this effect unless you first use the chain reaction.\n" +
                "Notes: This card constrains the order in which you can use its effects. (Most cards don't.) Also note that each target must be a different player.");
    }
    
    /**
     * get chainReactionCost
     *
     * @return the chain reaction cost
     */
    public ArrayList<EnumColorCardAndAmmo> getChainReactionCost() {

        return chainReactionCost;
    }
    
    /**
     * get highVoltageCost
     *
     * @return the high voltage cost
     */
    public ArrayList<EnumColorCardAndAmmo> getHighVoltageCost() {

        return highVoltageCost;
    }
    
    
    /**
     * Base effect.
     *
     * @param map           the map
     * @param currentPlayer the current player
     * @param target1       the target 1
     * @throws NotVisibleTarget the not visible target
     */
    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {

        if(map.isVisible(currentPlayer,target1)){

            ArrayList<EnumColorPlayer> thorDamages=new ArrayList<>();
            thorDamages.add(currentPlayer.getColor());
            thorDamages.add(currentPlayer.getColor());
            target1.multipleDamages(thorDamages);
        }else {

            throw new NotVisibleTarget();
        }
    }
    
    /**
     * Chain reaction effect.
     *
     * @param currentPlayer the current player
     * @param target2       the target 2
     */
    public void chainReactionEffect(Player currentPlayer, Player target2){

        target2.singleDamage(currentPlayer.getColor());
    }
    
    /**
     * High voltage effect.
     *
     * @param currentPlayer the current player
     * @param target3       the target 3
     */
    public void highVoltageEffect(Player currentPlayer, Player target3) {

        ArrayList<EnumColorPlayer> highVoltageDamages=new ArrayList<>();
        highVoltageDamages.add(currentPlayer.getColor());
        highVoltageDamages.add(currentPlayer.getColor());
        target3.multipleDamages(highVoltageDamages);
    }

}


