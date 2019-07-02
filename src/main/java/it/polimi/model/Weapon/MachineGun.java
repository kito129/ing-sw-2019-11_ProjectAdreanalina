package it.polimi.model.Weapon;

import it.polimi.model.Exception.NotVisibleTarget;
import it.polimi.model.*;

import java.util.ArrayList;

public class MachineGun extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> focusShotCost;
    private ArrayList<EnumColorCardAndAmmo> turretTripodCost;

    /**
     * Instantiates a new Machine Gun card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Creates the list of effects setting its value to BaseEffect,FocusShotEffect,TurretTripodEffect.
     * Creates the list of focus shot cost(cost of optional effect1) settings it to yellow.
     * Creates the list of turret tripod cost(cost of optional effect2) settings it to blu.
     */
    //todo controllare tutti gli effetti che devono essere inseriti
    public MachineGun() {

        super("MACHINE GUN", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo> rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects = new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.FocusShotEffect);
        weaponEffects.add(WeaponsEffect.TurretTripodEffect);
        setWeaponEffects(weaponEffects);
        focusShotCost = new ArrayList<EnumColorCardAndAmmo>();
        focusShotCost.add(EnumColorCardAndAmmo.YELLOW);
        turretTripodCost = new ArrayList<EnumColorCardAndAmmo>();
        turretTripodCost.add(EnumColorCardAndAmmo.BLU);
        setOptional(true);
        setDescription("Basic Effect: Choose 1 or 2 targets you can see and deal 1 damage to each.\n" +
                "with Focus Shot: Deal 1 additional damage to one of those targets.\n" +
                "with Turret Tripod: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see.\n" +
                "Notes: If you deal both additional points of damage, they must be dealt to 2 different targets. If you see only 2 targets, you deal 2 to each if you use both optional effects. If you use the basic effect on only 1 target, you can still use the the turret tripod to give it 1 additional damage.");
    }

    public ArrayList<EnumColorCardAndAmmo> getFocusShotCost() {

        return focusShotCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getTurretTripodCost() {

        return turretTripodCost;
    }


    public void baseEffect(Map map, Player currentPlayer, ArrayList<Player> targetPlayers) throws NotVisibleTarget {

        for (Player p : targetPlayers) {

            if (!(map.isVisible(p, currentPlayer))) {

                throw new NotVisibleTarget();
            }
        }
        for (Player p : targetPlayers) {

            p.singleDamage(currentPlayer.getColor());
        }
    }

    public void focusShotEffect(Player currentPlayer, Player target1or2) {

        target1or2.singleDamage(currentPlayer.getColor());
    }

    // todo danno aggiuntivo del tripod all'altro dei due bersagli ( quello su cui non hai usato focus).assicurarsi che siano quindi diversi

    public void turretTripodEffect(Player currentPlayer, Player target1or2) {

        target1or2.singleDamage(currentPlayer.getColor());
    }

    //todo target 3 deve essere diverso da 1 e 2
    public void turretTripodEffect(Map map, Player currentPlayer, Player target3) throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, target3)) {

            target3.singleDamage(currentPlayer.getColor());
        } else {

            throw new NotVisibleTarget();
        }
    }

}





/*  //todo metodi rifatti, da tenere in caso di errori di quello nuovo
    //todo assicurarsi che i due target in input siano diversi

    public void baseEffect(Map map,Player currentPlayer,Player target1,Player target2) throws NotVisibleTarget {

        if ((map.isVisible(currentPlayer, target1)) && (map.isVisible(currentPlayer, target2))) {

            target1.singleDamage(currentPlayer.getColor());
            target2.singleDamage(currentPlayer.getColor());
        }else {

            throw new NotVisibleTarget();
        }
    }


    public void baseEffect(Map map,Player currentPlayer, Player target1)throws NotVisibleTarget {

        if (map.isVisible(currentPlayer, target1)) {

            target1.singleDamage(currentPlayer.getColor());
        } else {

            throw new NotVisibleTarget();
        }
    }

 */



