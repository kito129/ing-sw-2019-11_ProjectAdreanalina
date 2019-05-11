package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class Shotgun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> longBarrelModeCost;

    /**
     * Instantiates a new Shotgun card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,YELLOW.
     * Creates the list of long barrel mode cost (cost of alternative fire mode) settings it to null.
     */
    public Shotgun() {

        super("SHOTGUN", EnumColorCardAndAmmo.YELLOW);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        longBarrelModeCost = new ArrayList<EnumColorCardAndAmmo>();
        longBarrelModeCost.add(null);
    }

    public ArrayList<EnumColorCardAndAmmo> getLongBarrelModeCost() {

        return longBarrelModeCost;
    }

    public void baseMode()  {


    }

    public void longBarrelMode() {

    }
}
