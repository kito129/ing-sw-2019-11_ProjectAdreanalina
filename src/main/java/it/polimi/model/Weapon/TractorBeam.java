package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCardAndAmmo;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class TractorBeam extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> punisherModeCost;

    /**
     * Instantiates a new Tractor Beam card.
     * Creates the list of recharge cost setting its value to BLU.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of punisher mode cost (cost of alternative fire mode) settings it to BLU,RED.
     */
    public TractorBeam() {

        super("TRACTOR BEAM", EnumColorCardAndAmmo.BLU);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        punisherModeCost= new ArrayList<EnumColorCardAndAmmo>();
        punisherModeCost.add(EnumColorCardAndAmmo.RED);
        punisherModeCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public ArrayList<EnumColorCardAndAmmo> getpunisherModecost() {

        return punisherModeCost;
    }

    public void basicMode(){

    }

    public void punisherMode(){


    }
}
