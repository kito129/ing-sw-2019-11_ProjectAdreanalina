package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Thor extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> chainReactionCost;
    private ArrayList<EnumColorCardAndAmmo> highVoltageCost;

    /**
     * Instantiates a new Thor card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to BLU,RED.
     * Creates the list of effects setting its value to BaseEffect,ChainReactionEffect,HighVoltageEffect.
     * Creates the list of chain reaction cost(cost of optional effect1) settings it to blu.
     * Creates the list of high voltage cost(cost of optional effect2) settings it to blu.
     */
    public Thor() {

        super("T.H.O.R", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.ChainReactionEffect);
        weaponEffects.add(WeaponsEffect.HighVoltageEffect);
        setWeaponEffects(weaponEffects);
        chainReactionCost = new ArrayList<EnumColorCardAndAmmo>();
        chainReactionCost.add(EnumColorCardAndAmmo.BLU);
        highVoltageCost = new ArrayList<EnumColorCardAndAmmo>();
        highVoltageCost.add(EnumColorCardAndAmmo.BLU);
        setOptional(true);
        setDescription("effetto base: Dai 2 danni a 1 bersaglio che puoi vedere.\n\n" +
                "reazione a catena: Dai 1 danno a un secondo bersaglio che il tuo primo bersaglio può vedere.\n\n" +
                "alta tensione: Dai 2 danni a un terzo bersaglio che il tuo secondo bersaglio può vedere.\n" +
                "Non puoi usare questo effetto se prima non hai usato reazione a catena.\n\n" +
                "Nota: Questa carta limita l'ordine in cui puoi utilizzare i suoi effetti.\n" +
                "(Molte carte non lo fanno.)\n" +
                "Inoltre ogni bersaglio deve essere un giocatore differente.");
    }

    public ArrayList<EnumColorCardAndAmmo> getChainReactionCost() {

        return chainReactionCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getHighVoltageCost() {

        return highVoltageCost;
    }


    public void baseEffect(Map map, Player currentPlayer, Player target1) throws NotVisibleTarget {

        if(map.isVisible(currentPlayer,target1)){

            ArrayList<EnumColorPlayer> thorDamages=new ArrayList<>();
            thorDamages.add(currentPlayer.getColor());
            thorDamages.add(currentPlayer.getColor());
            target1.multipleDamages(thorDamages);
        }else {

            throw new NotVisibleTarget();
        }
    }

    public void chainReactionEffect(Player currentPlayer, Player target2){

        target2.singleDamage(currentPlayer.getColor());
    }

    public void highVoltageEffect(Player currentPlayer, Player target3) {

        ArrayList<EnumColorPlayer> highVoltageDamages=new ArrayList<>();
        highVoltageDamages.add(currentPlayer.getColor());
        highVoltageDamages.add(currentPlayer.getColor());
        target3.multipleDamages(highVoltageDamages);
    }

}


