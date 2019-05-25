package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;


import java.util.ArrayList;

public class Whisper extends WeaponCard {

    /**
     * Instantiates a new Whisper card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,BLU,YELLOW.
     * Creates the list of effects setting its value to BaseEffect.
     */
    public Whisper() {

        super("WHISPER", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        setWeaponEffects(weaponEffects);
        setDescription("effetto: Dai 3 danni e 1 marchio a 1 bersaglio che puoi vedere." +
                "Il bersaglio deve essere ad almeno 2 movimenti da te.\n\n" +
                "Nota: Per esempio, nella stanza 2x2, non puoi sparare a un bersaglio in un quadrato adiacente, ma puoi sparare a un bersaglio lungo la diagonale.\n" +
                "Se sei vicino a una porta non puoi sparare a un bersaglio dall'altra parte della porta, ma puoi sparare a un bersaglio su un quadrato diverso in quella stanza.");
    }

    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotValidDistance, NotVisibleTarget {

        if((map.distance(currentPlayer,target1)>1) && (map.isVisible(target1,currentPlayer))){

            ArrayList<EnumColorPlayer> whisperDamages= new ArrayList<>();
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            target1.multipleDamagesSingleMark(whisperDamages,currentPlayer.getColor());
        }else if(!(map.distance(currentPlayer,target1)>1)){

            throw new NotValidDistance();
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }
    }


}
