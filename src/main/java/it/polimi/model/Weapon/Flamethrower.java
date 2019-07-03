package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotInSameDirection;
import it.polimi.model.Exception.NotValidDistance;

import java.util.ArrayList;


public class Flamethrower extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> barbecueModeCost;

    /**
     * Instantiates a new Flame thrower card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED.
     * Creates the list of effects setting its value to BaseMode,BarbecueMode.
     * Creates the list of barbecue mode cost (cost of alternative fire mode) settings it to YELLOW,YELLOW.
     */

    public Flamethrower() {

        super("FLAME THROWER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.BarbecueMode);
        setWeaponEffects(weaponEffects);
        barbecueModeCost = new ArrayList<EnumColorCardAndAmmo>();
        barbecueModeCost.add(EnumColorCardAndAmmo.YELLOW);
        barbecueModeCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(false);
        setDescription("Basic Mode: Choose a square 1 move away and possibly a second square 1 more move away in the same direction. On each square, you may choose 1 target and give it 1 damage.\n" +
                "in Barbecue Mode: Choose 2 squares as above. Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.\n" +
                "Notes: This weapon cannot damage anyone in your square. However, it can sometimes damage a target you can't see – the flame won't go through walls, but it will go through doors. Think of it as a straight-line blast of flame that can travel 2 squares in a cardinal direction.");
    }

    /**
     * get barbecueModeCost
     */
    public ArrayList<EnumColorCardAndAmmo> getBarbecueModeCost() {

        return barbecueModeCost;
    }

    /**
     * Shoot one or two players on square distant a movement from current player's square and first target square.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param target1 the first player you want to shoot.
     * @param target2 the second player you want to shoot.
     * @throws NotValidDistance
     * @throws NotInSameDirection
     */
    public void baseMode(Map map, Player currentPlayer,Player target1,Player target2) throws NotValidDistance, NotInSameDirection {

        if((map.distance(currentPlayer,target1)==1)&&(map.distance(target1,target2)==1)
        &&(map.sameDirection(currentPlayer,target1,target2))){

            target1.singleDamage(currentPlayer.getColor());
            target2.singleDamage(currentPlayer.getColor());
        } else if ((!(map.distance(currentPlayer, target1) == 1)) || (!(map.distance(target1, target2) == 1))){

            throw new NotValidDistance();
        } else if (!(map.sameDirection(currentPlayer,target1,target2))){

            throw new NotInSameDirection();
        }
    }

    /**
     * Shoot one player on square distant a movement from current player's square.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param target1 the first player you want to shoot.
     * @throws NotValidDistance
     */
    public void baseMode(Map map,Player currentPlayer,Player target1)throws NotValidDistance{

        if ((map.distance(currentPlayer, target1) == 1)) {

            target1.singleDamage(currentPlayer.getColor());
        } else if (!(map.distance(currentPlayer, target1) == 1)) {

            throw new NotValidDistance();
        }
    }

    /**
     * Shoot all players on chosen squares.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param targetSquare1 the first square chosen.
     * @param targetSquare2 the second square chosen.
     * @throws MapException
     * @throws NoTargetInSquare
     * @throws NotValidDistance
     * @throws NotInSameDirection
     */
    public void barbecueMode(Map map,Player currentPlayer,Square targetSquare1,Square targetSquare2) throws MapException,NoTargetInSquare,NotValidDistance, NotInSameDirection{

        Square squareOfCurrentPlayer=map.findPlayer(currentPlayer);
        if((map.sameDirection(squareOfCurrentPlayer,targetSquare1,targetSquare2))&&(map.distance(squareOfCurrentPlayer,targetSquare1)==1)
        &&(map.distance(targetSquare1,targetSquare2)==1)) {

            ArrayList<Player> playersOnTarget1Square = new ArrayList<>(map.playersOnSquare(targetSquare1));
            ArrayList<Player> playersOnTarget2Square = new ArrayList<>(map.playersOnSquare(targetSquare2));
            if ((playersOnTarget1Square.size() != 0) && (playersOnTarget2Square.size() != 0)) {

                for (Player p : playersOnTarget1Square) {

                    p.singleDamage(currentPlayer.getColor());
                    p.singleDamage(currentPlayer.getColor());
                }
                for (Player p : playersOnTarget2Square) {

                    p.singleDamage(currentPlayer.getColor());
                }
            } else {

                throw new NoTargetInSquare();
            }
        } else if ((!(map.distance(squareOfCurrentPlayer,targetSquare1) == 1)) || (!(map.distance(targetSquare1,targetSquare2) == 1))){

            throw new NotValidDistance();
        } else if (!(map.sameDirection(squareOfCurrentPlayer,targetSquare1,targetSquare2))){

            throw new NotInSameDirection();
        }
    }
}



