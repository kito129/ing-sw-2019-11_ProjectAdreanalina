package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotValidSquareException;


import java.util.ArrayList;

public class Cyberblade extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> shadowstepCost;
    private ArrayList<EnumColorCardAndAmmo> sliceAndDiceCost;

    /**
     * Instantiates a new Cyberblade card.
     * Sets the field color to YELLOW calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost settings its value to YELLOW,RED.
     * Creates the list of effects setting its value to BaseEffect,ShadowstepEffect,SliceAndDiceEffect.
     * Creates the list of shadow step cost(cost of optional effect 1) settings it to null.
     * Creates the list of slice and dice cost(cost of optional effect 2) settings it to YELLOW.
     */
    public Cyberblade(){

        super("CYBERBLADE", EnumColorCardAndAmmo.YELLOW);
        ArrayList<EnumColorCardAndAmmo> rechargeCost =new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseEffect);
        weaponEffects.add(WeaponsEffect.ShadowstepEffect);
        weaponEffects.add(WeaponsEffect.SliceAndDiceEffect);
        setWeaponEffects(weaponEffects);
        sliceAndDiceCost = new ArrayList<EnumColorCardAndAmmo>();
        sliceAndDiceCost.add(EnumColorCardAndAmmo.YELLOW);
        setOptional(true);
        setDescription("effetto base: Dai 2 danni a 1 bersaglio nel quadrato in cui ti trovi.\n\n" +
                "passo d'ombra: Muovi di 1 quadrato prima o dopo l'effetto base.\n\n" +
                "modalità sminuzzare: Dai 2 danni a un bersaglio differente nel quadrato in cui ti trovi. Il passo d'ombra può essere usato prima o dopo questo effetto.\n\n" +
                "Nota: Combinare tutti gli effetti permette di muoversi in un quadrato e colpire 2 persone; oppure di colpire qualcuno, muovere e colpire qualcun altro.\n" +
                "Oppure ancora colpire 2 persone e poi andare via.");
    }

    public ArrayList<EnumColorCardAndAmmo> getShadowstepCost() {

        return shadowstepCost;
    }

    public ArrayList<EnumColorCardAndAmmo> getSliceAndDiceCost() {

        return sliceAndDiceCost;
    }

    public void baseEffect(Map map,Player currentPlayer,Player target1) throws NotValidDistance, MapException {

        if(map.findPlayer(currentPlayer)==map.findPlayer(target1)){

            ArrayList<EnumColorPlayer> cyberbladeDamages=new ArrayList<>();
            cyberbladeDamages.add(currentPlayer.getColor());
            cyberbladeDamages.add(currentPlayer.getColor());
            target1.multipleDamages(cyberbladeDamages);
        }else {

            throw new NotValidDistance();
        }
    }

    public void shadowstepEffect(Map map, Player currentPlayer, Square destSquare) throws NotValidDistance, MapException {

        Square squareOfCurrentPlayer = map.findPlayer(currentPlayer);
        if(map.distance(squareOfCurrentPlayer,destSquare)==1){

            map.movePlayer(currentPlayer,destSquare);
        }else{

            throw new NotValidDistance();
        }
    }


    public void sliceAndDiceEffect(Map map,Player currentPlayer,Player target2)throws NotValidDistance,MapException{

        baseEffect(map,currentPlayer,target2);

    }







}
