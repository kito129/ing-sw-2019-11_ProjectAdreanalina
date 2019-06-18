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
        setDescription("modalità base: Scegli 1 bersaglio in un quadrato distante esattamente 1 movimento.\n" +
                "Muovi in quel quadrato e dai al bersaglio 1 danno e 2 marchi.\n\n" +
                "modalità cento pugni: Scegli un quadrato distante esattamente 1 movimento.\n" +
                "Muovi in quel quadrato.\n" +
                "Puoi dare 2 danni a 1 bersaglio in quel quadrato.\n" +
                "Se vuoi puoi muovere ancora di 1 quadrato nella stessa direzione (ma solo se è un movimento valido).\n" +
                "Puoi dare 2 danni a un bersaglio anche in quel quadrato.\n\n" +
                "Nota: Nella modalità cento pugni stai volando per 2 quadrati in linea retta, prendendo a pugni 1 persona per quadrato.");
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
                (map.sameDirection(currentPlayer,target1,target2))){

            ArrayList<EnumColorPlayer> powerGloveDamages=new ArrayList<>();
            powerGloveDamages.add(currentPlayer.getColor());
            powerGloveDamages.add(currentPlayer.getColor());
            Square squareOfTarget2Player=map.findPlayer(target2);
            map.movePlayer(currentPlayer,squareOfTarget2Player);
            target1.multipleDamages(powerGloveDamages);
            target2.multipleDamages(powerGloveDamages);
        } else if ((!(map.distance(currentPlayer, target1) == 1))||(!(map.distance(target1,target2)==1))){

            throw new NotValidDistance();
        }else if(!(map.sameDirection(currentPlayer,target1,target2))){

            throw new NotInSameDirection();
        }





    }
}
