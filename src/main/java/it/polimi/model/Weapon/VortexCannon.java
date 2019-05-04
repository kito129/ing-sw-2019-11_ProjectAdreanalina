package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class VortexCannon extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> blackHoleCost;

    /**
     * Instantiates a Thor card.
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of chain reaction cost(cost of optional effect1) settings it to yellow.
     * Creates the list of high voltage cost(cost of optional effect2) settings it to yellow.
     */
    public VortexCannon(){

        super("MACHINE GUN", EnumColorCardAndAmmo.BLU);
        rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        blackHoleCost =new ArrayList<EnumColorCardAndAmmo>();
        blackHoleCost.add(EnumColorCardAndAmmo.BLU);

    }

    public ArrayList<EnumColorCardAndAmmo> getBlackHoleCost() {

        return blackHoleCost;
    }

    public void baseEffect(){

    }

    public void chainReaction(){

    }

    public void highVoltage(){


    }
}
