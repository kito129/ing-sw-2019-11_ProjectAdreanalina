package it.polimi.model.Weapon;

import it.polimi.model.*;

import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class TractorBeam extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> punisherModeCost;

    /**
     * Instantiates a new Tractor Beam card.
     * Sets the field color to BLU calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to BLU.
     * Creates the list of effects setting its value to BaseMode,PunisherMode.
     * Creates the list of punisher mode cost (cost of alternative fire mode) settings it to RED,YELLOW.
     */
    public TractorBeam() {

        super("TRACTOR BEAM", EnumColorCardAndAmmo.BLU);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.BLU);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.PunisherMode);
        setWeaponEffects(weaponEffects);
        punisherModeCost = new ArrayList<EnumColorCardAndAmmo>();
        punisherModeCost.add(EnumColorCardAndAmmo.RED);
        punisherModeCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(false);
        setDescription("modalità base: Muovi un bersaglio di 0, 1 o 2 quadrati fino a un quadrato che puoi vedere e dagli 1 danno.\n\n" +
                "modalità punitore: Scegli un bersaglio 0, 1, o 2 movimenti da te.\n" +
                "Muovi quel bersaglio nel quadrato in cui ti trovi edagli 3 danni.\n\n" +
                "Nota: Puoi muovere un bersaglio anche se non puoi vederlo.\n" +
                "Il bersaglio finisce in una posizione in cui puoi vederlo e danneggiarlo.\n" +
                "I movimenti possono non essere nella stessa direzione.");
    }

    public ArrayList<EnumColorCardAndAmmo> getPunisherModeCost() {

        return punisherModeCost;
    }

    public void baseMode(Map map,Square destSquare,Player currentPlayer,Player target1) throws NotVisibleTarget, NotValidDistance,MapException{

        Square currentPlayerSquare = map.findPlayer(currentPlayer);
        Square target1Square = map.findPlayer(target1);
        if((map.isVisible(destSquare,currentPlayerSquare)) && (map.distance(target1Square,destSquare)<3)
                &&(map.distance(target1Square,destSquare)>=0)){

            map.movePlayer(target1,destSquare);
            target1.singleDamage(currentPlayer.getColor());
        }else if(!map.isVisible(destSquare, currentPlayerSquare)){

            throw new NotVisibleTarget();
        }else if((map.distance(target1Square,destSquare)>2)&&(map.distance(target1Square,destSquare)==-1)){

            throw new NotValidDistance();
        }
    }

    public void punisherMode(Map map,Player currentPlayer,Player target1) throws NotValidDistance, MapException {

        if((map.distance(currentPlayer,target1)<3)&&(map.distance(currentPlayer,target1)!=-1)){

            Square currentPlayerSquare = map.findPlayer(currentPlayer);
            map.movePlayer(target1,currentPlayerSquare);
            ArrayList<EnumColorPlayer> tractorBeamDamages=new ArrayList<EnumColorPlayer>();
            tractorBeamDamages.add(currentPlayer.getColor());
            tractorBeamDamages.add(currentPlayer.getColor());
            tractorBeamDamages.add(currentPlayer.getColor());
            target1.multipleDamages(tractorBeamDamages);
        }else {

            throw new NotValidDistance();
        }





    }


}




