package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Zx2 extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> scannerModeCost;

    /**
     * Instantiates a new Zx2 card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,RED.
     * Creates the list of effects setting its value to BaseMode,ScannerMode.
     * Creates the list of scanner mode cost (cost of alternative fire mode) settings it to null.
     */
    public Zx2() {

        super("ZX-2", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect>weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.ScannerMode);
        setWeaponEffects(weaponEffects);
        scannerModeCost = new ArrayList<EnumColorCardAndAmmo>();
        scannerModeCost.add(null);
        setOptional(false);
        setDescription("Basic Mode: Deal 1 damage and 2 marks to 1 target you can see.\n" +
                "in Scanner Mode: Choose up to 3 targets you can see and deal 1 mark to each.\n" +
                "Notes: Remember that the 3 targets can be in 3 different rooms.");
    }

    public ArrayList<EnumColorCardAndAmmo> getScannerModeCost() {

        return scannerModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,Player target1) throws NotVisibleTarget{

        if(map.isVisible(currentPlayer,target1)){

            ArrayList<EnumColorPlayer> zx2marks=new ArrayList<>();
            zx2marks.add(currentPlayer.getColor());
            zx2marks.add(currentPlayer.getColor());
            target1.singleDamageMultipleMarks(currentPlayer.getColor(),zx2marks);
        }else{

            throw new NotVisibleTarget();
        }
    }

    public void scannerMode(Map map,Player currentPLayer,ArrayList<Player> targets)throws NotVisibleTarget{

        for(Player player:targets){

            if(!(map.isVisible(currentPLayer,player))){

                throw new NotVisibleTarget();
            }
        }
        for(Player player:targets){

            player.singleMark(currentPLayer.getColor());
        }
    }
}
