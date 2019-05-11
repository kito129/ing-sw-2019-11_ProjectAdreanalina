package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class Shockwave extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> tsunamiModeCost;

    /**
     * Instantiates a new Shockwave card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW.
     * Creates the list of tsunami mode cost (cost of alternative fire mode) settings it to YELLOW.
     */
    public Shockwave() {

        super("SHOCKWAVE", EnumColorCardAndAmmo.YELLOW);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        tsunamiModeCost = new ArrayList<EnumColorCardAndAmmo>();
        tsunamiModeCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getTsunamiModeCost() {

        return tsunamiModeCost;
    }

    public void baseMode()  {


    }

    public void tsunamiMode() {

    }

}
