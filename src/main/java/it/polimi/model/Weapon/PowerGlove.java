package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotValidSquareException;

import java.util.ArrayList;

public class PowerGlove extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> rocketFistModeCost;

    /**
     * Instantiates a new Power glove card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to YELLOW,BLU.
     * Creates the list of effects setting its value to BaseMode,RocketFistMode.
     * Creates the list of rocket fist mode cost (cost of alternative fire mode) settings it to BLU.
     */
    public PowerGlove() {

        super("POWER GLOVE", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.RocketFistMode);
        setWeaponEffects(weaponEffects);
        rocketFistModeCost = new ArrayList<EnumColorCardAndAmmo>();
        rocketFistModeCost.add(EnumColorCardAndAmmo.BLU);
    }

    public ArrayList<EnumColorCardAndAmmo> getRocketFistModeCost() {

        return rocketFistModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,Player target1) throws NotValidDistance, NotValidInput, MapException, NotValidSquareException {

        if(map.distance(currentPlayer,target1)==1){

            Square squareOfTarget1=map.findPlayer(target1);
            map.movePlayer(currentPlayer,squareOfTarget1);
            ArrayList<EnumColorPlayer> powerGloveMarks=new ArrayList<>();
            powerGloveMarks.add(currentPlayer.getColor());
            powerGloveMarks.add(currentPlayer.getColor());
            target1.singleDamageMultipleMarks(currentPlayer.getColor(),powerGloveMarks);
        }else{

            throw new NotValidDistance();
        }
    }

    // todo chiedere a marco se la funzione in same line dice che sono sulla stessa linea ma se sono anche attaccate.
    public void rocketFistMode(Map map,Player currentPlayer) {

    }
}
