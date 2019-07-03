package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;

import java.util.ArrayList;

public class PlasmaGun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> phaseGlideCost;
    private ArrayList<EnumColorCardAndAmmo> chargedShotCost;

    /**
     * Instantiates a new Plasma gun card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,YELLOW.
     * Creates the list of effects setting its value to BaseEffect,PhaseGlideEffect,ChargedShotEffect.
     * Creates the list of phase glide cost(cost of optional effect1) settings it to null.
     * Creates the list of charged shot cost(cost of optional effect2) settings it to blu.
     */
    public PlasmaGun() {

        super("PLASMA GUN", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffectPlusChargedShotEffect);
        weaponEffects.add(WeaponsEffect.PhaseGlideEffect);
        setWeaponEffects(weaponEffects);
        phaseGlideCost = new ArrayList<EnumColorCardAndAmmo>();
        phaseGlideCost.add(null);
        chargedShotCost = new ArrayList<EnumColorCardAndAmmo>();
        chargedShotCost.add(EnumColorCardAndAmmo.BLU);
        setOptional(true);
        setDescription("Basic Effect: Deal 2 damage to 1 target you can see.\n" +
                "with Phase Glide: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n" +
                "with Charged Shot: Deal 1 additional damage to your target.\n" +
                "Notes: The two moves have no ammo cost. You don't have to be able to see your target when you play the card. For example, you can move 2 squares and shoot a target you now see. You cannot use 1 move before shooting and 1 move after");
    }

    /**
     * get phaseGlideCost
     */
    public ArrayList<EnumColorCardAndAmmo> getPhaseGlideCost() {

        return phaseGlideCost;
    }

    /**
     * get chargedShotCost
     */
    public ArrayList<EnumColorCardAndAmmo> getChargedShotCost() {

        return chargedShotCost;
    }

    /**
     * Shoot a player who current player can see.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param target1 the first player you want to shoot.
     * @throws NotVisibleTarget
     */
    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {

        if (map.isVisible(target1, currentPlayer)) {

            ArrayList<EnumColorPlayer> plasmaGunDamages=new ArrayList<>();
            plasmaGunDamages.add(currentPlayer.getColor());
            plasmaGunDamages.add(currentPlayer.getColor());
            target1.multipleDamages(plasmaGunDamages);
        }else{

            throw new NotVisibleTarget();
        }
    }

    /**
     * Move the current player by one or two movements.
     *
     * @param map the map of the game.
     * @param currentPlayer the current player.
     * @param destSquare the square where shot player is moved.
     * @throws MapException
     * @throws NotValidDistance
     */
    public void phaseGlideEffect(Map map, Square destSquare, Player currentPlayer) throws NotValidDistance, MapException {

        Square currentPlayerSquare=map.findPlayer(currentPlayer);
        if(map.distance(destSquare,currentPlayerSquare)==1 || map.distance(destSquare,currentPlayerSquare)==2){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    /**
     * Make a plus damage to the player just shot.
     *
     * @param currentPlayer the current player.
     * @param target1 the first player you want to shoot.
     */
    public void chargedShotEffect(Player currentPlayer, Player target1){

        target1.singleDamage(currentPlayer.getColor());
    }

}
