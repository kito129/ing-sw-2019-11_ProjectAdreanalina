package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class GrenadeLauncher extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> extraGrenadeCost;

    /**
     * Instantiates a new Grenade Launcher card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED.
     * Creates the list of effects setting its value to BaseEffect,ExtraGrenadeEffect.
     * Creates the list of extra grenade cost(cost of optional effect 1) settings it to RED.
     */
    public GrenadeLauncher(){

        super("GRENADE LAUNCHER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.ExtraGrenadeEffect);
        setWeaponEffects(weaponEffects);
        extraGrenadeCost =new ArrayList<EnumColorCardAndAmmo>();
        extraGrenadeCost.add(EnumColorCardAndAmmo.RED);
    }

    public ArrayList<EnumColorCardAndAmmo> getExtraGrenadeCost() {

        return extraGrenadeCost;
    }

    public void baseEffect(Map map,Player target1,Player currentPlayer)throws NotVisibleTarget{

        if(map.isVisible(currentPlayer,target1)){

            target1.singleDamage(currentPlayer.getColor());
        }else{

            throw new NotVisibleTarget();
        }
    }

    public void baseEffect(Map map,Player target1,Player currentPlayer,Square destSquare) throws NotVisibleTarget,NotValidDistance{

        Square squareOfTarget1Player = map.findPlayer(target1);

        if ((map.isVisible(currentPlayer, target1)) && (map.distance(squareOfTarget1Player,destSquare)==1)){

            target1.singleDamage(currentPlayer.getColor());
            map.movePlayer(target1,destSquare);
        }else if(!(map.isVisible(currentPlayer,target1))){

            throw new NotValidDistance();
        }else if(!(map.distance(squareOfTarget1Player,destSquare)==1)){

            throw new NotValidDistance();
        }
    }

    // todo si dovrebbe poter usare questo metodo anche prima del movimento dell'effetto base.
    public void extraGrenadeEffect(Map map, Player currentPlayer,Square targetSquare)throws NotVisibleTarget{

        Square squareOfCurrentPlayer =map.findPlayer(currentPlayer);
        ArrayList<Player> playersOnTargetSquare=map.playersOnSquare(targetSquare);
        playersOnTargetSquare.remove(currentPlayer);

        if(map.isVisible(targetSquare,squareOfCurrentPlayer)){

            for(Player p:playersOnTargetSquare){

                p.singleDamage(currentPlayer.getColor());
            }
        }else {

            throw  new NotVisibleTarget();
        }
    }

}
