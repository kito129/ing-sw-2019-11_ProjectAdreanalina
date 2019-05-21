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
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.PhaseGlideEffect);
        weaponEffects.add(WeaponsEffect.ChargedShotEffect);
        setWeaponEffects(weaponEffects);
        phaseGlideCost = new ArrayList<EnumColorCardAndAmmo>();
        phaseGlideCost.add(null);
        chargedShotCost = new ArrayList<EnumColorCardAndAmmo>();
        chargedShotCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getPhaseGlideCost() {

        return phaseGlideCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getChargedShotCost() {

        return chargedShotCost;
    }


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

    public void phaseGlideEffect(Map map, Square destSquare, Player currentPlayer) throws NotValidDistance, NotValidInput, MapException, NotValidSquareException {

        Square currentPlayerSquare=map.findPlayer(currentPlayer);
        if(map.distance(destSquare,currentPlayerSquare)<3 && map.distance(destSquare,currentPlayerSquare)>0){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }

    public void chargedShotEffect(Player currentPlayer, Player target1){

        target1.singleDamage(currentPlayer.getColor());
    }

}
