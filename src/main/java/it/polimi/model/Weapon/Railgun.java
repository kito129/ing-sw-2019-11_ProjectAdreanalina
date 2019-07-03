package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotInDirection;
import it.polimi.model.Exception.NotValidCardinalDirection;

import java.util.ArrayList;

public class Railgun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> piercingModeCost;

    /**
     * Instantiates a new Railgun card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,YELLOW,BLU.
     * Creates the list of effects setting its value to BaseMode,PiercingMode
     * Creates the list of piercing mode cost (cost of alternative fire mode) settings it to null.
     */
    public Railgun() {

        super("RAILGUN", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects = new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.PiercingMode);
        setWeaponEffects(weaponEffects);
        piercingModeCost = new ArrayList<EnumColorCardAndAmmo>();
        piercingModeCost.add(null);
        setOptional(false);
        setDescription("Basic Mode: Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it.\n" +
                "in Piercing Mode: Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.\n" +
                "Notes: Basically, you're shooting in a straight line and ignoring walls. You don't have to pick a target on the other side of a wall – it could even be someone on your own square – but shooting through walls sure is fun. There are only 4 cardinal directions. You imagine facing one wall or door, square-on, and firing in that direction. Anyone on a square in that direction (including yours) is a valid target. In piercing mode, the 2 targets can be on the same square or on different squares.");
    }

    /**
     * get piercingModeCost
     */
    public ArrayList<EnumColorCardAndAmmo> getPiercingModeCost() {

        return piercingModeCost;
    }

    /**
     * Shoot a player on a cardinal direction.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param target1 the player you want to shoot.
     * @param direction the direction chosen for shoot the target.
     * @throws NotInDirection
     * @throws NotValidCardinalDirection
     */
    public void baseMode(Map map, Player currentPlayer, Player target1, EnumCardinalDirection direction) throws NotInDirection, NotValidCardinalDirection {

        if((direction==EnumCardinalDirection.N)||(direction==EnumCardinalDirection.E)||(direction==EnumCardinalDirection.S)
                ||(direction==EnumCardinalDirection.W)){

            if (direction==EnumCardinalDirection.N) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                if (!(playersOnNorth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.E) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                if (!(playersOnEst.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.S) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                if (!(playersOnSouth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction==EnumCardinalDirection.W) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                if (!(playersOnWest.contains(target1))) {

                    throw new NotInDirection();
                }
            }

            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(railGunDamages);
        }else{

            throw new NotValidCardinalDirection();
        }
    }

    public void piercingMode(Map map,Player currentPlayer,ArrayList<Player> targets,EnumCardinalDirection direction)throws NotValidCardinalDirection,NotInDirection{

        if((direction==EnumCardinalDirection.N)||(direction==EnumCardinalDirection.E)||(direction==EnumCardinalDirection.S)
                ||(direction==EnumCardinalDirection.W)){

            if (direction==EnumCardinalDirection.N) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnNorth.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.E) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnEst.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.S) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnSouth.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            if (direction==EnumCardinalDirection.W) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                for(Player p:targets) {

                    if (!(playersOnWest.contains(p))) {

                        throw new NotInDirection();
                    }
                }
            }
            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            for(Player p:targets){

                p.multipleDamages(railGunDamages);
            }
        }else{

            throw new NotValidCardinalDirection();
        }
    }





}
/*non serve più
 public void piercingMode(Map map,Player currentPlayer,Player target1,Player target2,String direction)throws NotValidCardinalDirection,NotInDirection{

        if((direction.equals("NORTH"))||(direction.equals("EST"))||(direction.equals("SOUTH"))||(direction.equals("WEST"))){

            if (direction.equals("NORTH")) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                if ((!(playersOnNorth.contains(target1))) && (!(playersOnNorth.contains(target2)))){

                    throw new NotInDirection();
                }
            }
            if (direction.equals("EST")) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                if ((!(playersOnEst.contains(target1))) && (!(playersOnEst.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("SOUTH")) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                if ((!(playersOnSouth.contains(target1))) && (!(playersOnSouth.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("WEST")) {

                ArrayList<Player> playersOnWest = map.playerOnMyWest(currentPlayer);
                if ((!(playersOnWest.contains(target1))) && (!(playersOnWest.contains(target2)))) {

                    throw new NotInDirection();
                }
            }
            ArrayList<EnumColorPlayer> railGunDamages = new ArrayList<>();
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            railGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(railGunDamages);
            target2.multipleDamages(railGunDamages);
        }else{

            throw new NotValidCardinalDirection();
        }
    }

 */
