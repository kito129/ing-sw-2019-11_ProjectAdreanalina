package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.InvalidActionForThisCard;

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

    private void baseEffect(Map map, Player currentPlayer, Player target) throws InvalidActionForThisCard {

        if(map.isVisible(currentPlayer,target)){

            ArrayList<EnumColorPlayer> lockRifleDamages= new ArrayList<EnumColorPlayer>();
            lockRifleDamages.add(currentPlayer.getColor());
            lockRifleDamages.add(currentPlayer.getColor());
            target.MultipleDamagesSingleMark(lockRifleDamages,currentPlayer.getColor());
        }else{

            throw new InvalidActionForThisCard();
        }
    }

    private void optionalEffect(Map map,Player currentPlayer, Player target)throws InvalidActionForThisCard {

        if (map.isVisible(currentPlayer, target)){

            target.SingleMark(currentPlayer.getColor());
        }else{

            throw new InvalidActionForThisCard();
        }
    }

    public void effect(Map map,Player currentPlayer,Player baseTarget,Player optionalTarget,boolean useOptionalEffect)throws InvalidActionForThisCard{

        if(useOptionalEffect && baseTarget!=optionalTarget) {

            baseEffect(map, currentPlayer, baseTarget);
            optionalEffect(map, currentPlayer, optionalTarget);
        }else if(useOptionalEffect==false){

            baseEffect(map,currentPlayer,baseTarget);
        }
        
    }





}
