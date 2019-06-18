package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NoTargetInSquare;
import it.polimi.model.Exception.NotValidInput;

import java.util.ArrayList;

public class Electroscythe extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> reaperModeCost;

    /**
     * Instantiates a new Lock Electoscythe card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to BLU.
     * Creates the list of effects setting its value to BaseMode,ReaperMode.
     * Creates the list of reaper mode cost (cost of alternative fire mode) settings it to BLU,RED.
     */
    public Electroscythe() {

        super("ELECTOSCYTHE", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects=new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.ReaperMode);
        setWeaponEffects(weaponEffects);
        reaperModeCost= new ArrayList<EnumColorCardAndAmmo>();
        reaperModeCost.add(EnumColorCardAndAmmo.BLU);
        reaperModeCost.add(EnumColorCardAndAmmo.RED);
        setOptional(false);
        setDescription("modalità base: Dai 1 danno a ogni altro giocatore presente nel quadrato in cui ti trovi.\n\n" +
                "modalità mietitore: Dai 2 danni a ogni altro giocatore presente nel quadrato in cui ti trovi.");

    }

    public ArrayList<EnumColorCardAndAmmo> getReaperModeCost() {

        return reaperModeCost;
    }

    public void baseMode(Map map, Player currentPlayer) throws NoTargetInSquare, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        ArrayList<Player> playersOnSquare = map.playersOnSquare(squareOfCurrentPlayer);
        playersOnSquare.remove(currentPlayer);
        if(playersOnSquare.size()>0){

            for(Player p:playersOnSquare) {

                p.singleDamage(currentPlayer.getColor());
            }
        }else {

            throw new NoTargetInSquare();
        }
    }

    public void reaperMode(Map map,Player currentPlayer) throws NoTargetInSquare, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        ArrayList<Player> playersOnSquare = map.playersOnSquare(squareOfCurrentPlayer);
        playersOnSquare.remove(currentPlayer);
        if(playersOnSquare.size()>0){

            ArrayList<EnumColorPlayer> electroscytheDamages=new ArrayList<>();
            electroscytheDamages.add(currentPlayer.getColor());
            electroscytheDamages.add(currentPlayer.getColor());
            for(Player p:playersOnSquare) {

                p.multipleDamages(electroscytheDamages);
            }
        }else {

            throw new NoTargetInSquare();
        }
    }
}






