package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class Sledgehammer extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> pulverizeModeCost;

    /**
     * Instantiates a new Sladgehammer card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW.
     * Creates the list of pulverize mode cost (cost of alternative fire mode) settings it to RED.
     */
    public Sledgehammer() {

        super("SLADGEHAMMER", EnumColorCardAndAmmo.YELLOW);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        pulverizeModeCost = new ArrayList<EnumColorCardAndAmmo>();
        pulverizeModeCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getPulverizeModeCost() {

        return pulverizeModeCost;
    }

    public void baseMode()  {


    }

    public void pulverizeMode() {

    }
}
