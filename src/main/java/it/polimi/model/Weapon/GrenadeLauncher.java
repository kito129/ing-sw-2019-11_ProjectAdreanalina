package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;

import java.util.ArrayList;

public class GrenadeLauncher extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> extraGrenadeCost;

    /**
     * Instantiates a new Grenade Launcher card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED.
     * Creates the list of effects setting its value to BaseEffect,MoveTarget,ExtraGrenadeEffect.
     * Creates the list of extra grenade cost(cost of optional effect 1) settings it to RED.
     */
    public GrenadeLauncher(){

        super("GRENADE LAUNCHER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.MoveTarget);
        weaponEffects.add(WeaponsEffect.ExtraGrenadeEffect);
        setWeaponEffects(weaponEffects);
        extraGrenadeCost =new ArrayList<EnumColorCardAndAmmo>();
        extraGrenadeCost.add(EnumColorCardAndAmmo.RED);
        setDescription("effetto base: Dai 1 danno a 1 bersaglio che puoi vedere. Poi puoi muovere il bersaglio di 1 quadrato.\n\n" +
                "granata extra: Dai 1 danno a ogni giocatore in quadrato che puoi vedere. Puoi usare questo effetto prima o dopo il movimento dell'effetto base.\n\n" +
                "Nota: Per esempio puoi sparare a un bersaglio, muoverlo in un quadrato con altri bersagli e danneggiare chiunque, incluso il primo bersaglio.\n" +
                "Oppure puoi dare 2 danni a un bersaglio principale e 1 a tutti gli altri in quel quadrato, e poi muovere il bersaglio principale.\n" +
                "Oppure puoi dare 1 danno a un bersaglio isolato e 1 a chiunque in quadrato diverso.\n" +
                "Se bersagli il quadrato in cui ti trovi non potrai essere mosso o danneggiato.");
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

    public void moveTarget(Map map,Player target1,Square destSquare)throws MapException,NotValidDistance{

        Square squareOfTarget=map.findPlayer(target1);
        if(map.distance(squareOfTarget,destSquare)==1){

            map.movePlayer(target1,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    public void extraGrenadeEffect(Map map, Player currentPlayer,Square targetSquare) throws NoTargetInSquare, NotVisibleTarget, MapException {

        Square squareOfCurrentPlayer =map.findPlayer(currentPlayer);
        if(map.isVisible(targetSquare,squareOfCurrentPlayer)){

            ArrayList<Player> playersOnTargetSquare=map.playersOnSquare(targetSquare);
            playersOnTargetSquare.remove(currentPlayer);
            if(playersOnTargetSquare.size()!=0){

                for(Player p:playersOnTargetSquare){

                    p.singleDamage(currentPlayer.getColor());
                }
            }else{

                throw new NoTargetInSquare();
            }
        }else {

            throw  new NotVisibleTarget();
        }
    }

}


/*// metodo non serve più
 public void baseEffect(Map map,Player target1,Player currentPlayer,Square destSquare) throws NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfTarget1Player = map.findPlayer(target1);

        if ((map.isVisible(currentPlayer, target1)) && (map.distance(squareOfTarget1Player,destSquare)==1)){

            target1.singleDamage(currentPlayer.getColor());
            map.movePlayer(target1,destSquare);
        }else if(!(map.isVisible(currentPlayer,target1))){

            throw new NotVisibleTarget();
        }else if(!(map.distance(squareOfTarget1Player,destSquare)==1)){

            throw new NotValidDistance();
        }
    }
 */