package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class Thor extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> chainReactionCost;
    private ArrayList<EnumColorCardAndAmmo> highVoltageCost;

    /**
     * Instantiates a Thor card.
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of chain reaction cost(cost of optional effect1) settings it to yellow.
     * Creates the list of high voltage cost(cost of optional effect2) settings it to yellow.
     */
    public Thor(){

        super("MACHINE GUN", EnumColorCardAndAmmo.BLU);
        rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        chainReactionCost =new ArrayList<EnumColorCardAndAmmo>();
        chainReactionCost.add(EnumColorCardAndAmmo.BLU);
        highVoltageCost =new ArrayList<EnumColorCardAndAmmo>();
        highVoltageCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getChainReactionCost() {

        return chainReactionCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getHighVoltageCost() {

        return highVoltageCost;
    }

    public void baseEffect(){

    }

    public void chainReaction(){

    }

    public void highVoltage(){


    }
}
