package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class PowerGlove extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> rocketFistModeCost;

    /**
     * Instantiates a new Power glove card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,BLU.
     * Creates the list of rocket fist mode cost (cost of alternative fire mode) settings it to BLU.
     */
    public PowerGlove() {

        super("POWER GLOVE", EnumColorCardAndAmmo.YELLOW);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rocketFistModeCost = new ArrayList<EnumColorCardAndAmmo>();
        rocketFistModeCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getRocketFistModeCost() {

        return rocketFistModeCost;
    }

    public void baseMode()  {


    }

    public void rocketFistMode() {

    }
}
