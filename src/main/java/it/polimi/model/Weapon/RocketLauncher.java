package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class RocketLauncher extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> rocketJumpCost;
    private ArrayList<EnumColorCardAndAmmo> fragmentingWarheadCost;


    /**
     * Instantiates a new RocketLauncher card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to RED,RED.
     * Creates the list of effects setting its value to BaseEffect,RocketJumpEffect,FragmentingWarheadEffect.
     * Creates the list of rocket jump cost(cost of optional effect 1) settings it to BLU.
     * Creates the list of fragment warhead cost(cost of optional effect 1) settings it to YELLOW.
     */
    public RocketLauncher(){

        super("ROCKET LAUNCHER", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost=new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffectPlusFragmentingWarheadEffect);
        weaponEffects.add(WeaponsEffect.RocketJumpEffect);
        setWeaponEffects(weaponEffects);
        rocketJumpCost =new ArrayList<EnumColorCardAndAmmo>();
        rocketJumpCost.add(EnumColorCardAndAmmo.BLU);
        fragmentingWarheadCost = new ArrayList<EnumColorCardAndAmmo>();
        fragmentingWarheadCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(true);
        setDescription("Basic Effect: Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.\n" +
                "with Rocket Jump: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n" +
                "with Fragmenting Warhead: During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.\n" +
                "Notes: If you use the rocket jump before the basic effect, you consider only your new square when determining if a target is legal. You can even move off a square so you can shoot someone on it. If you use the fragmenting warhead, you deal damage to everyone on the target's square before you move the target – your target will take 3 damage total.");
    }

    /**
     * get rocketJumpCost
     */
    public ArrayList<EnumColorCardAndAmmo> getRocketJumpCost() {

        return rocketJumpCost;
    }

    /**
     * get fragmentingWarheadCost
     */
    public ArrayList<EnumColorCardAndAmmo> getFragmentingWarheadCost() {

        return fragmentingWarheadCost;
    }

    /**
     * Shoot a player who current player can see and who is on a different square from current player's square.
     *
     * @param map the map of the game.
     * @param target1 the player you want to shoot.
     * @param currentPlayer the current player.
     * @param destSquare the square where shot player is moved.
     * @throws NotVisibleTarget
     * @throws NotValidDistance
     * @throws MapException
     */
    public void baseEffect(Map map, Player target1, Player currentPlayer,Square destSquare) throws NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        Square squareOfTarget1Player = map.findPlayer(target1);
        if((map.isVisible(target1,currentPlayer)) && (!(squareOfCurrentPlayer == squareOfTarget1Player))){

            if((destSquare!=null)&&(map.distance(squareOfTarget1Player,destSquare)==1)){

                map.movePlayer(target1,destSquare);
            }else if((destSquare!=null)&&(!(map.distance(squareOfTarget1Player,destSquare)==1))){

                throw new NotValidDistance();
            }
            ArrayList<EnumColorPlayer> rocketLauncherDamages=new ArrayList<>();
            rocketLauncherDamages.add(currentPlayer.getColor());
            rocketLauncherDamages.add(currentPlayer.getColor());
            target1.multipleDamages(rocketLauncherDamages);
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }else if(squareOfCurrentPlayer==squareOfTarget1Player){

            throw new NotValidDistance();
        }
    }

    /**
     * Moves the current player by one movement.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param destSquare the square where current player want to move on.
     * @throws NotValidDistance
     * @throws MapException
     */
    public void rocketJumpEffect(Map map, Player currentPlayer,Square destSquare) throws NotValidDistance, MapException {

        Square squareOfCurrentPlayer=map.findPlayer(currentPlayer);

        if((map.distance(squareOfCurrentPlayer,destSquare)==1)||(map.distance(squareOfCurrentPlayer,destSquare)==2)){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    /**
     * Shoot all players on the square where the previous shot player was.
     *
     * @param map the map of the game.
     * @param target1 the player you want to shoot.
     * @param currentPlayer the current player.
     * @param destSquare the square where current player want to move on.
     * @throws NoTargetInSquare
     * @throws NotVisibleTarget
     * @throws MapException
     */
    public void baseEffectWithFragmenting(Map map, Player target1, Player currentPlayer,Square destSquare) throws NoTargetInSquare,NotVisibleTarget, NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        Square squareOfTarget1Player = map.findPlayer(target1);
        ArrayList<Player> playersOnTarget1Square= new ArrayList<>(map.playersOnSquare(squareOfTarget1Player));
        if((map.isVisible(target1,currentPlayer)) && (!(squareOfCurrentPlayer == squareOfTarget1Player))
                &&(playersOnTarget1Square.size()!=0)){

            if((destSquare!=null)&&(map.distance(squareOfTarget1Player,destSquare)==1)){

                map.movePlayer(target1,destSquare);

            }else if((destSquare!=null)&&(!(map.distance(squareOfTarget1Player,destSquare)==1))){

                throw new NotValidDistance();
            }
            for(Player p:playersOnTarget1Square){

                p.singleDamage(currentPlayer.getColor());
            }
            ArrayList<EnumColorPlayer> rocketLauncherDamages=new ArrayList<>();
            rocketLauncherDamages.add(currentPlayer.getColor());
            rocketLauncherDamages.add(currentPlayer.getColor());
            target1.multipleDamages(rocketLauncherDamages);
        }else if(!(map.isVisible(target1,currentPlayer))){

            throw new NotVisibleTarget();
        }else if(squareOfCurrentPlayer==squareOfTarget1Player){

            throw new NotValidDistance();
        }else if(playersOnTarget1Square.size()==0){

            throw new NoTargetInSquare();
        }
    }


}
