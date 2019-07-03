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
        setOptional(true);
        setDescription("Basic Effect: Deal 1 damage to 1 target you can see. Then you may move the target 1 square.\n" +
                "with Extra Grenade: Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect's move.\n" +
                "Notes: For example, you can shoot a target, move it onto a square with other targets, then damage everyone including the first target. Or you can deal 2 to a main target, 1 to everyone else on that square, then move the main target. Or you can deal 1 to an isolated target and 1 to everyone on a different square. If you target your own square, you will not be moved or damaged.");
    }

    /**
     * get extraGrenadeCost
     */
    public ArrayList<EnumColorCardAndAmmo> getExtraGrenadeCost() {

        return extraGrenadeCost;
    }

    /**
     * Shoot a player who current player can see.
     *
     * @param map the map of the game.
     * @param target1 the player you want to shoot.
     * @param currentPlayer the current player.
     * @throws NotVisibleTarget
     */
    public void baseEffect(Map map,Player target1,Player currentPlayer)throws NotVisibleTarget{

        if(map.isVisible(currentPlayer,target1)){

            target1.singleDamage(currentPlayer.getColor());
        }else{

            throw new NotVisibleTarget();
        }
    }

    /**
     * Moves the shot player by one movement.
     *
     * @param map the map of the game.
     * @param target1 the shot player.
     * @param destSquare the square where shot player is moved.
     * @throws MapException
     * @throws NotValidDistance
     */
    public void moveTarget(Map map,Player target1,Square destSquare)throws MapException,NotValidDistance{

        Square squareOfTarget=map.findPlayer(target1);
        if(map.distance(squareOfTarget,destSquare)==1){

            map.movePlayer(target1,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    /**
     * Shoot all players on a square that current player's can see.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param targetSquare the square chosen.
     * @throws NoTargetInSquare
     * @throws NotVisibleTarget
     * @throws MapException
     */
    public void extraGrenadeEffect(Map map, Player currentPlayer,Square targetSquare) throws NoTargetInSquare, NotVisibleTarget, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        if(map.isVisible(squareOfCurrentPlayer,targetSquare)){

            ArrayList<Player> playersOnTargetSquare= new ArrayList<>(map.playersOnSquare(targetSquare));
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