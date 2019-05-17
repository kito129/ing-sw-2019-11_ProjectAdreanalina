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
    }

    public ArrayList<EnumColorCardAndAmmo> getPiercingModeCost() {

        return piercingModeCost;
    }

    public void baseMode(Map map, Player currentPlayer, Player target1, String direction) throws NotInDirection, NotValidCardinalDirection {

        if((direction.equals("NORTH"))||(direction.equals("EST"))||(direction.equals("SOUTH"))||(direction.equals("WEST"))){

            if (direction.equals("NORTH")) {

                ArrayList<Player> playersOnNorth = map.playerOnMyNorth(currentPlayer);
                if (!(playersOnNorth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("EST")) {

                ArrayList<Player> playersOnEst = map.playerOnMyEst(currentPlayer);
                if (!(playersOnEst.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("SOUTH")) {

                ArrayList<Player> playersOnSouth = map.playerOnMySouth(currentPlayer);
                if (!(playersOnSouth.contains(target1))) {

                    throw new NotInDirection();
                }
            }
            if (direction.equals("WEST")) {

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


    public void piercingMode(Map map,Player currentPlayer,Player target1,String direction)throws NotValidCardinalDirection,NotInDirection{

        baseMode(map,currentPlayer,target1,direction);
    }

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

}
