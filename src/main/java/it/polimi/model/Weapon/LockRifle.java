package it.polimi.model.Weapon;

import it.polimi.model.EnumColorCard;
import it.polimi.model.WeaponCard;

import java.util.ArrayList;

public class LockRifle extends WeaponCard {

    /**
     * Instantiates a new Lock Rifle card.
     * Creates the list of recharge cost setting its value to BLU,BLU.
     * Sets the field color to BLU.
     */
    public LockRifle(){

        super("LOCK RIFLE",EnumColorCard.BLU);
        rechargeCost=new ArrayList<EnumColorCard>();
        rechargeCost.add(EnumColorCard.BLU);
        rechargeCost.add(EnumColorCard.BLU);
    }




}
