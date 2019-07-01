package it.polimi.model.Weapon;

import it.polimi.model.*;
import it.polimi.model.Exception.MapException;
import it.polimi.model.Exception.NotValidDistance;
import it.polimi.model.Exception.NotValidInput;
import it.polimi.model.Exception.NotVisibleTarget;

import java.util.ArrayList;

public class Hellion extends WeaponCard {

    private ArrayList<EnumColorCardAndAmmo> nanoTracerModeCost;

    /**
     * Instantiates a new Hellion card.
     * Sets the field color to RED calling the constructor of weapon card (the super class).
     * Creates the list of recharge cost setting its value to RED,YELLOW.
     * Creates the list of effects setting its value to BaseMode,NanoTracerMode
     * Creates the list of nano tracer mode cost (cost of alternative fire mode) settings it to RED.
     */
    public Hellion() {

        super("HELLION", EnumColorCardAndAmmo.RED);
        ArrayList<EnumColorCardAndAmmo>rechargeCost = new ArrayList<EnumColorCardAndAmmo>();
        rechargeCost.add(EnumColorCardAndAmmo.RED);
        rechargeCost.add(EnumColorCardAndAmmo.YELLOW);
        setRechargeCost(rechargeCost);
        ArrayList<WeaponsEffect> weaponEffects= new ArrayList<>();
        weaponEffects.add(WeaponsEffect.BaseMode);
        weaponEffects.add(WeaponsEffect.NanoTracerMode);
        setWeaponEffects(weaponEffects);
        nanoTracerModeCost = new ArrayList<EnumColorCardAndAmmo>();
        nanoTracerModeCost.add(EnumColorCardAndAmmo.RED);
        setOptional(false);
        setDescription("modalità base: Dai 1 danno a 1 bersaglio che puoi vedere e distante almeno 1 movimento.\n" +
                "Poi dai un marchio a quel bersaglio e a chiunque altro in quel quadrato.\n\n" +
                "modalità nano-traccianti: Dai 1 danno a 1 bersaglio che puoi vedere e distante almeno 1 movimento.\n" +
                "Poi dai 2 marchi a quel bersaglio e a chiunque altro in quel quadrato.");
    }

    public ArrayList<EnumColorCardAndAmmo> getNanoTracerModeCost() {

        return nanoTracerModeCost;
    }

    public void baseMode(Map map, Player currentPlayer,Player targetPlayer) throws NotVisibleTarget, NotValidDistance, MapException {

        if((map.isVisible(currentPlayer,targetPlayer)) && (map.distance(currentPlayer,targetPlayer)>0)){

            Square targetSquare = map.findPlayer(targetPlayer);
            ArrayList<Player> playersInTargetSquare;
            playersInTargetSquare= new ArrayList<>(map.playersOnSquare(targetSquare));
            targetPlayer.singleDamage(currentPlayer.getColor());
            for(Player p:playersInTargetSquare){

                p.singleMark(currentPlayer.getColor());
            }
        }else if(!(map.isVisible(currentPlayer,targetPlayer))){

            throw new NotVisibleTarget();
        }else if(!(map.distance(currentPlayer,targetPlayer)>0)){

            throw new NotValidDistance();
        }
    }

    public void nanoTracerMode(Map map, Player currentPlayer,Player targetPlayer) throws NotVisibleTarget, NotValidDistance, MapException {

        if((map.isVisible(currentPlayer,targetPlayer)) && (map.distance(currentPlayer,targetPlayer)>0)){

            Square targetSquare = map.findPlayer(targetPlayer);
            ArrayList<Player> playersInTargetSquare;
            playersInTargetSquare= new ArrayList<>(map.playersOnSquare(targetSquare));
            targetPlayer.singleDamage(currentPlayer.getColor());
            ArrayList<EnumColorPlayer> hellionMarks= new ArrayList<>();
            hellionMarks.add(currentPlayer.getColor());
            hellionMarks.add(currentPlayer.getColor());
            for(Player p:playersInTargetSquare){

                p.multipleMarks(hellionMarks);
            }
        }else if(!(map.isVisible(currentPlayer,targetPlayer))){

            throw new NotVisibleTarget();
        }else if(!(map.distance(currentPlayer,targetPlayer)>0)){

            throw new NotValidDistance();
        }
    }






}




