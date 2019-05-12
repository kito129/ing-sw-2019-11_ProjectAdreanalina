package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Thor extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> chainReactionCost;
    private ArrayList<EnumColorCardAndAmmo> highVoltageCost;

    /**
     * Instantiates a new Thor card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Creates the list of chain reaction cost(cost of optional effect1) settings it to blu.
     * Creates the list of high voltage cost(cost of optional effect2) settings it to blu.
     */
    public Thor() {

        super("T.H.O.R", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        chainReactionCost = new ArrayList<EnumColorCardAndAmmo>();
        chainReactionCost.add(EnumColorCardAndAmmo.BLU);
        highVoltageCost = new ArrayList<EnumColorCardAndAmmo>();
        highVoltageCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getChainReactionCost() {

        return chainReactionCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getHighVoltageCost() {

        return highVoltageCost;
    }


    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {

        if(map.isVisible(target1,currentPlayer)){

            ArrayList<EnumColorPlayer> thorDamages=new ArrayList<>();
            thorDamages.add(currentPlayer.getColor());
            thorDamages.add(currentPlayer.getColor());
            target1.multipleDamages(thorDamages);
        }else {

            throw new NotVisibleTarget();
        }
    }

    // todo garantire che il target di questa funzione vede il target dell effetto base
    public void chainReactionEffect(Player currentPlayer, Player target2) {

        target2.singleDamage(currentPlayer.getColor());
    }

    // todo garantire che il target 3 vede il target 2 della chian reaction.effetto usabile solo se si usa chain reaction
    public void highVoltageEffect(Player currentPlayer, Player target3) {

        ArrayList<EnumColorPlayer> highVoltageDamages=new ArrayList<>();
        highVoltageDamages.add(currentPlayer.getColor());
        highVoltageDamages.add(currentPlayer.getColor());
        target3.multipleDamages(highVoltageDamages);
    }

}


