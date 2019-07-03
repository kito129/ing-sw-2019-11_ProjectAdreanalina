package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.*;

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
        setOptional(false);
        setDescription("Basic Mode: Choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks.\n" +
                "in Rocket Fist Mode: Choose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there. If you want, you may move 1 more square in that same direction (but only if it is a legal move). You may deal 2 damage to 1 target there, as well.\n" +
                "Notes: In rocket fist mode, you're flying 2 squares in a straight line, punching 1 person per square.");
    }

    public ArrayList<EnumColorCardAndAmmo> getRocketFistModeCost() {

        return rocketFistModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,Player target1) throws NotValidDistance, MapException {

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

    public void rocketFistMode(Map map,Player currentPlayer,Player target1)throws NotValidDistance,MapException {

        if ((map.distance(currentPlayer, target1) == 1)) {

            Square squareOfTarget1Player=map.findPlayer(target1);
            map.movePlayer(currentPlayer,squareOfTarget1Player);
            target1.singleDamage(currentPlayer.getColor());
            target1.singleDamage(currentPlayer.getColor());
        } else if (!(map.distance(currentPlayer, target1) == 1)) {

            throw new NotValidDistance();
        }

    }

    public void rocketFistMode(Map map,Player currentPlayer,Player target1,Player target2)throws NotValidDistance,MapException,NotInSameDirection{

        if (((map.distance(currentPlayer, target1) == 1))&&(map.distance(target1,target2)==1)&&
                (map.sameDirection(currentPlayer,target1,target2))&& map.findPlayer(target2)!=map.findPlayer(currentPlayer)){

            ArrayList<EnumColorPlayer> powerGloveDamages=new ArrayList<>();
            powerGloveDamages.add(currentPlayer.getColor());
            powerGloveDamages.add(currentPlayer.getColor());
            Square squareOfTarget2Player=map.findPlayer(target2);
            map.movePlayer(currentPlayer,squareOfTarget2Player);
            target1.multipleDamages(powerGloveDamages);
            target2.multipleDamages(powerGloveDamages);
        } else if ((!(map.distance(currentPlayer, target1) == 1))||(!(map.distance(target1,target2)==1))||
                (map.findPlayer(target2)==map.findPlayer(currentPlayer))){

            throw new NotValidDistance();
        }else if(!(map.sameDirection(currentPlayer,target1,target2))){

            throw new NotInSameDirection();
        }
    }
}
