package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;


import java.util.ArrayList;

public class Whisper extends WeaponCard {

    /**
     * Instantiates a new Whisper card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,BLU,YELLOW.
     */
    public Whisper() {

        super("WHISPER", EnumColorCardAndAmmo.BLU);
        rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
    }

    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotValidDistance {

        Square currentPlayerSquare=map.findPlayer(currentPlayer);
        Square target1Square=map.findPlayer(target1);
        if(map.distance(currentPlayerSquare,target1Square)>1){

            ArrayList<EnumColorPlayer> whisperDamages= new ArrayList<>();
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            target1.multipleDamagesSingleMark(whisperDamages,currentPlayer.getColor());
        }else{

            throw new NotValidDistance();
        }
    }


}
