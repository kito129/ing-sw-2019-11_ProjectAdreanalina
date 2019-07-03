package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;


import java.util.ArrayList;

/**
 * The type Whisper.
 */
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
        setOptional(true);
        setDescription("Effect: Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you.\n" +
                "Notes: For example, in the 2-by-2 room, you cannot shoot a target on an adjacent square, but you can shoot a target on the diagonal. If you are beside a door, you can't shoot a target on the other side of the door, but you can shoot a target on a different square of that room.");
    }
    
    /**
     * Shoot and mark a player who current player can see. He must be at least two movements by current player's square.
     *
     * @param map           the map of the game.
     * @param currentPlayer the current player.
     * @param target1       the player you want to shoot.
     * @throws NotValidDistance
     * @throws NotVisibleTarget
     */
    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotValidDistance, NotVisibleTarget {

        if((map.distance(currentPlayer,target1)>1) && (map.isVisible(currentPlayer,target1))){

            ArrayList<EnumColorPlayer> whisperDamages= new ArrayList<>();
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            whisperDamages.add(currentPlayer.getColor());
            target1.multipleDamagesSingleMark(whisperDamages,currentPlayer.getColor());
        }else if(!(map.distance(currentPlayer,target1)>1)){

            throw new NotValidDistance();
        }else if(!(map.isVisible(currentPlayer,target1))){

            throw new NotVisibleTarget();
        }
    }


}
